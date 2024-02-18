package data_types_and_variable

fun main() {
    /**
     * variable structure
     * keyword(var or val) identifier: Type = initialization
     *
     * rules:
     * you cannot make a more than one of variable that have same name
     * because kotlin support of type inference, it can be like this
     *    keyword identifier = initialization
     * cannot use number in identifier
     * cannot use space in identifier
     */

    var company: String = "Dicoding"

    // val or var?
    /**
     * val keyword
     * val is a keyword that makes the value of variables with this keyword unassigned
     */

    val name: String = "Andi"
    // name = joni
    /**
     * if you uncomment above statement, you will have an error like this:
     * Val cannot be reassigned
     * because variables that use val keyword is cannot be reassigned
     */

    company = "Dicoding Indonesia"
    /**
     * if using var, the variable can be assigned
     */

    //concat the variable
    // if you use this, you will get a warning like this
    // 'String' concatenation can be converted to a template
    println(name + " studying in " + company)

    // or this
    // *it is recommended to use this template string
    println("$name studying in $company")
}