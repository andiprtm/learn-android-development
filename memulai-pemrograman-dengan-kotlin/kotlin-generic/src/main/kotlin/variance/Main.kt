package variance

// covariant
interface List<out E> : Collection<E> {
    operator fun get(index: Int): E
}

// contravariant
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun main() {
    /**
     * variance
     */
}