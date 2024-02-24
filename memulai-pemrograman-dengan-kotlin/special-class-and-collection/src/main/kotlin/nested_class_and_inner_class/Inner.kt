package nested_class_and_inner_class

class Inner {
    // Properti outer class
    val buildingArea = 100
    class Room {

        val totalRooms = 4
        fun measureRoomArea() {
            // Class members cannot access properties of outer classes
            // print(buildingArea/totalRooms)
        }
    }
}