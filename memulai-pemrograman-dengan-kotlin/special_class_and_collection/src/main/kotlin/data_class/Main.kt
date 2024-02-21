package data_class

fun main() {
    /**
     * data class
     * This class makes it easier for data stored in class
     * because the default function that have provided like equals(), toString(), hashCode(), etc.
     */
    val dataUser = DataUser("nrohmen", 17)
    val dataUser4 = dataUser.copy()
    println(dataUser4 == dataUser)
    /*
        output: true
     */

    // copy object
    println(dataUser4)
    /*
        output: DataUser(name=nrohmen, age=17)
     */

    // copy and replace
    val dataUser5 = dataUser.copy(name = "joni", age = 4)
    println(dataUser5)
    /*
        output: DataUser(name=joni, age=4)
     */

    // replace
    dataUser5.name = "boni"
    println(dataUser5)
    /*
        output: DataUser(name=boni, age=4)
     */

    // destructuring
    val (name, age)= dataUser
    println("name = $name, age = $age")
    /*
        output: name = nrohmen, age = 17
     */

    // destructuring alternative
    val name5 = dataUser5.component1()
    val age5 = dataUser5.component2()
    println("My name is $name5, I am $age5 years old")
    /*
        output: My name is boni, I am 4 years old
     */

    // behavior of data class
    dataUser4.intro()
    /*
        output: My name is nrohmen, I am 17 years old
     */
}