package sealed_class

fun main() {
    /**
     * sealed class
     * this class is used to contain data with a different type
     * the sealed class is abstract, so you can't initialize in the class
     * you must implement all the propertied instead
     */
    val result: Result = Result.Error("ups!!")
    when (result) {
        //jika salah satu kondisi dihapus, kode akan error
        is Result.Success -> {
            println("Success: ${result.data}")
        }
        is Result.Error -> {
            println("Error: ${result.message}")
        }
        is Result.Loading -> {
            println("Loading...")
        }
        /*
            output: Error: ups!!
         */
    }
}