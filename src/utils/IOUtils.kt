package utils

object IOUtils {
    fun getInputInteger(): Int? {
        return try {
            readln().toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}