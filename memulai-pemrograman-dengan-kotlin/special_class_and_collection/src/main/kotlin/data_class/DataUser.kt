package data_class

data class DataUser(var name : String, var age : Int) {
    fun intro(){
        println("My name is $name, I am $age years old")
    }
}