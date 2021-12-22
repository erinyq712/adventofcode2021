package se.nyquist

class Decoder {

    private val validCombinations = Digit.values().map { it.representation }
    private val value = "abcdefg"
    private val valueArray : List<String> = IntRange(0, value.length-1).map{value.substring(it,it+1)}.toList()
    private val permutations : List<List<String>> = allPermutations(valueArray)

    fun hasDecoded(): Boolean {
        return true
    }

    fun decode(input: List<String>, output: List<String>): Int {
        for(permutation in permutations) {
            if (validate(permutation, input)) {
                println(permutation.joinToString(""))
                return encode(output, permutation).map { it.toInt() }.joinToString("").toInt()
            }
        }
        return Int.MIN_VALUE
    }

    fun encode(input: List<String>, permutation: List<String>): List<Digit> {
        val translation = getTranslationMap(permutation)
        return input.map { getDigits(it, translation) }.toList()
    }

    private fun getDigits(
        input: String,
        translation: Map<String, String>
    ) : Digit {
        val digits = getDigit(IntRange(0, input.length - 1).map { translation[input.substring(it, it + 1)] }.sortedBy { it }.joinToString(""))
        println(digits)
        return digits
    }

    private fun getTranslationMap(permutation: List<String>) : Map<String, String> {
        val translation = mutableMapOf<String, String>()
        IntRange(0, value.length - 1).map { translation.put(permutation[it], valueArray[it]) }
        return translation
    }

    private fun validate(permutation: List<String>, samples: List<String>) : Boolean {
        val translation = getTranslationMap(permutation)
        val valid = samples.all { input ->
            val translated : String = IntRange(0, input.length-1).map{ translation[ input.substring(it,it+1) ] }.sortedBy { it }.joinToString("")
            validCombinations.contains(translated)
        }
        return valid
    }
}