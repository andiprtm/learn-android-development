package primary_cunstructor

class Animal(pName: String, pWeight: Double, pAge: Int, pIsMammal: Boolean) {
    /**
     * the ini block is used to validate the value of argument before initialize.
     * so don't let our user app is freedom to input anything
     * check it first
     */
    val name: String
    val weight: Double
    val age: Int
    val isMammal: Boolean

    init {
        weight = if(pWeight < 0) 0.1 else pWeight
        age = if(pAge < 0) 0 else pAge
        name = pName
        isMammal = pIsMammal
    }
}