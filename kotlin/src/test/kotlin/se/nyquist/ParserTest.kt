package se.nyquist

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class ParserTest {
    private val parser = Parser()

    @ParameterizedTest
    @MethodSource
    fun test(input: String, position: Int, score: Int) {
        val status = parser.parse(input)
        assertEquals(position, status.errorPosition)
        assertEquals(score, status.score)
    }

    companion object {
        @JvmStatic
        fun test(): Stream<Arguments> =
            Stream.of(
                Arguments.of("[({(<(())[]>[[{[]{<()<>>",-1,0),
                Arguments.of("[(()[<>])]({[<{<<[]>>(",-1,0),
                Arguments.of("{([(<{}[<>[]}>{[]{[(<()>",12,1197),
                Arguments.of("(((({<>}<{<{<>}{[]{[]{}",-1,0),
                Arguments.of("[[<[([]))<([[{}[[()]]]",8,3),
                Arguments.of("[{[{({}]{}}([{[{{{}}([]",7,57),
                Arguments.of("{<[[]]>}<{[{[{[]{()[[[]",-1,0),
                Arguments.of("[<(<(<(<{}))><([]([]()",10,3),
                Arguments.of("<{([([[(<>()){}]>(<<{{",16,25137),
                Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]",-1,0)
            )
    }
}
