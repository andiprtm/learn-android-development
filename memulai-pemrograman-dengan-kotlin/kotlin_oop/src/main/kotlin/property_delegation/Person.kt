package property_delegation

class Person {
    var name: String by DelegateName()
}