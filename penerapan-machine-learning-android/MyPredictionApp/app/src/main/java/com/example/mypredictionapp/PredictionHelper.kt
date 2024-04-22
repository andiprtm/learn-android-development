package com.example.mypredictionapp

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.google.android.gms.tflite.client.TfLiteInitializationOptions
import com.google.android.gms.tflite.gpu.support.TfLiteGpu
import com.google.android.gms.tflite.java.TfLite
import org.tensorflow.lite.InterpreterApi
import org.tensorflow.lite.gpu.GpuDelegateFactory
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class PredictionHelper(
    private val modelName: String = "rice_stock.tflite",
    val context: Context,
    private val onResult: (String) -> Unit,
    private val onError: (String) -> Unit,
) {

    private var interpreter: InterpreterApi? = null
    private var isGPUSupported: Boolean = false

    init {
        TfLiteGpu.isGpuDelegateAvailable(context).onSuccessTask { gpuAvalaible ->
            val optionBuilder = TfLiteInitializationOptions.builder()
            if (gpuAvalaible) {
                optionBuilder.setEnableGpuDelegateSupport(true)
                isGPUSupported = true
            }
            TfLite.initialize(context, optionBuilder.build())
        }.addOnSuccessListener {
            loadLocalModel()
        }.addOnFailureListener {
            onError(context.getString(R.string.tflite_is_not_initialized_yet))
        }
    }

    private fun loadLocalModel() {
        try {
            val buffer: ByteBuffer = loadModelFile(context.assets, modelName)
            initializeInterpreter(buffer)
        } catch (ioException: IOException ){
            ioException.printStackTrace()
        }
    }

    private fun initializeInterpreter(model: Any) {
        interpreter?.close()
        try {
            val options = InterpreterApi.Options()
                .setRuntime(InterpreterApi.Options.TfLiteRuntime.FROM_SYSTEM_ONLY)
            if (isGPUSupported){
                options.addDelegateFactory(GpuDelegateFactory())
            }
            if (model is ByteBuffer) {
                interpreter = InterpreterApi.create(model, options)
            }
        } catch (e: Exception) {
            onError(e.message.toString())
            Log.e(TAG, e.message.toString())
        }
    }

    fun predict(inputString: String) {
        if (interpreter == null) {
            return
        }

        val inputArray = FloatArray(1)
        inputArray[0] = inputString.toFloat()
        val outputArray = Array(1) { FloatArray(1) }
        try {
            interpreter?.run(inputArray, outputArray)
            onResult(outputArray[0][0].toString())
        } catch (e: Exception) {
            onError(context.getString(R.string.no_tflite_interpreter_loaded))
            Log.e(TAG, e.message.toString())
        }
    }

    private fun loadModelFile(assets: AssetManager, modelName: String): MappedByteBuffer {
        assets.openFd(modelName).use { fileDescriptor ->
            FileInputStream(fileDescriptor.fileDescriptor).use { inputStream ->
                val fileChanel = inputStream.channel
                val startOfSet = fileDescriptor.startOffset
                val declaredLength = fileDescriptor.declaredLength
                return fileChanel.map(FileChannel.MapMode.READ_ONLY, startOfSet, declaredLength)
            }
        }
    }

    fun close() {
        interpreter?.close()
    }

    companion object {
        private const val TAG = "PredictionHelper"
    }
}