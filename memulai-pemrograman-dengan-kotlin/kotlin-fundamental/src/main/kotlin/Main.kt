fun doSomething(vararg input: Int): Int {
    return (input.sum() / input.size)
}
fun main() {
    for (i in 1..3) {
        for (j in 1..i) {
            print(j)
        }
    }
}