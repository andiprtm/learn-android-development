package property_delegation

import kotlin.reflect.KProperty

class DelegateName {
    /**
     * this delegation can process any value
     */
    private var value: Any = "Default" // we have declared that the value to set is any type

    // and this getter return any value to
    operator fun getValue(classRef: Any?, property: KProperty<*>) : Any {
        println("Fungsi ini sama seperti getter untuk properti ${property.name} pada class $classRef")
        return value
    }

    // the setter also receives any type of value
    operator fun setValue(classRef: Any?, property: KProperty<*>, newValue: Any){
        println("Fungsi ini sama seperti setter untuk properti ${property.name} pada class $classRef")
        println("Nilai ${property.name} dari: $value akan berubah menjadi $newValue")
        value = newValue
    }
}