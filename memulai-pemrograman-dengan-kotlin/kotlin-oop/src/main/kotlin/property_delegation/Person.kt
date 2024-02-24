package property_delegation

class Person {
    /**
     * match the type with the value type in the delegate class
     */
    var name: Any by DelegateName()
}