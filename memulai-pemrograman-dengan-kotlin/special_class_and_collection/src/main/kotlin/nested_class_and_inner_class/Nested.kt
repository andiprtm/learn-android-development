package nested_class_and_inner_class

class Nested {
    // Properti outer class
    val buildingArea = 100
    val totalRooms = 6
    inner class Room {
        val totalRooms = 4
        fun measureRoomArea() {
            // Inner class can access properties of outer class
            print(buildingArea/this@Nested.totalRooms)
        }
    }
}