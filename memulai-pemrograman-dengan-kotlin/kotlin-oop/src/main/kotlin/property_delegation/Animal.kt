package property_delegation

class Animal {
    /**
     * match the type with the value type in the delegate class
     */
    var name: Any by DelegateName()
}