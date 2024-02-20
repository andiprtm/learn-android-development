package property_delegation

class Hero {
    var name: String by DelegateName()
}