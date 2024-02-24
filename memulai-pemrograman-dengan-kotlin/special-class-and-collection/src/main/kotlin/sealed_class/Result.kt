package sealed_class

sealed class Result {
    sealed class Success(val data: Any) : Result()
    data class Error(val message: String) : Result()
    data object Loading : Result()
}