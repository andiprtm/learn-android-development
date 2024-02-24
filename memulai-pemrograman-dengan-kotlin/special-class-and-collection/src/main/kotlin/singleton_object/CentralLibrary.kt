package singleton_object

object CentralLibrary {
    fun borrowBookById(id: Int) {
        print("Book with $id has been borrowed")
    }
}