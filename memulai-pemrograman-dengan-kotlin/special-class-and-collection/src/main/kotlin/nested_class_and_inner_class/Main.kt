package nested_class_and_inner_class

fun main() {
    /**
     * nested class and inner class
     * this used if your member class is just one, you can encapsulate it
     */
    val house = Nested()
    val room = house.Room()
    room.measureRoomArea()
    /*
        output: 25
     */
}