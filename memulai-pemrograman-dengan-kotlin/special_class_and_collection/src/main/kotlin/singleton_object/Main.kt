package singleton_object

fun main() {
    /**
     * singleton object
     * This class does not need to initialize its instance.
     * and its behavior can be used directly and called anywhere.
     */
    CentralLibrary.borrowBookById(21)
    /*
        output: Book with 21 has been borrowed
     */
}