package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.myflexiblefragment.databinding.FragmentOptionalDialogBinding

class OptionalDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentOptionalDialogBinding
    private var optionDialogListener: OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOptionalDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnChoose.setOnClickListener {
            val checkRadioButtonId = binding.rgOptions.checkedRadioButtonId
            if (checkRadioButtonId != -1){
                var coach: String? = when (checkRadioButtonId){
                    R.id.rb_saf -> binding.rbSaf.text.toString().trim()
                    R.id.rb_mou -> binding.rbMou.text.toString().trim()
                    R.id.rb_lvg -> binding.rbLvg.text.toString().trim()
                    R.id.rb_moyes -> binding.rbLvg.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }

        binding.btnClose.setOnClickListener{
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

}