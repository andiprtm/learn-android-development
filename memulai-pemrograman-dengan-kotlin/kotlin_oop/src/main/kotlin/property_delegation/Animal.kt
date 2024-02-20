package property_delegation

class Animal {
    var name: String by DelegateName()
}