package se.nyquist

class VersionCounter {
    var counter = 0L

    fun add(version: Int) {
        counter += version
    }
}