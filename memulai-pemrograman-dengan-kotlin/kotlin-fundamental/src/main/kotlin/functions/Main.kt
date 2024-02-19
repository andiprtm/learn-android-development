package functions

fun main() {
    /**
     * function
     * like other language function use to group of a logic or specific algorithm
     * the structure is like this :
     *
     * fun functionName(param1: Type1, param2: Type2, ...): ReturnType {
     *     return result
     * }
     *
     * when you what the function not return any value. use return type to Unit
     * but right now kotlin detect to redundant so you cant ignore it
     */

    setUser("Andi", 21)
    printUser("Andi")

}

fun setUser(name: String, age: Int): String {
    return "Your name is $name, and you $age years old"
}

fun printUser(name: String) {
    print("Your name is $name")
}