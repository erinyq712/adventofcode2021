package se.nyquist

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class AutoCompleterTest {
    val autoCompleter = AutoCompleter()
    val parser = Parser()

    @ParameterizedTest
    @MethodSource
    fun complete(input: String, output: String, score: Int) {
        val status = parser.parse(input)
        val completed = autoCompleter.complete(input, status)
        assertEquals(output, completed.completed)
        assertEquals(score, completed.score)
    }

    companion object {
        @JvmStatic
        fun complete(): Stream<Arguments> =
            Stream.of(
                Arguments.of("[({(<(())[]>[[{[]{<()<>>", "[({(<(())[]>[[{[]{<()<>>}}]])})]", 288957),
                Arguments.of("[(()[<>])]({[<{<<[]>>(", "[(()[<>])]({[<{<<[]>>()}>]})", 5566),
                Arguments.of("{([(<{}[<>[]}>{[]{[(<()>", "", 0),
                Arguments.of("(((({<>}<{<{<>}{[]{[]{}", "(((({<>}<{<{<>}{[]{[]{}}}>}>))))", 1480781),
                Arguments.of("[[<[([]))<([[{}[[()]]]", "", 0),
                Arguments.of("[{[{({}]{}}([{[{{{}}([]", "", 0),
                Arguments.of("{<[[]]>}<{[{[{[]{()[[[]", "{<[[]]>}<{[{[{[]{()[[[]]]}}]}]}>", 995444),
                Arguments.of("[<(<(<(<{}))><([]([]()", "", 0),
                Arguments.of("<{([([[(<>()){}]>(<<{{", "", 0),
                Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]", "<{([{{}}[<[[[<>{}]]]>[]]])}>", 294)
            )
    }
}