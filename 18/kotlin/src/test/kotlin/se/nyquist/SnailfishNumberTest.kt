package se.nyquist

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SnailfishNumberTest {

    @Test
    fun explode() {
        val sample1 = parse("[[[[[9,8],1],2],3],4]")
        assertEquals("[[[[0,9],2],3],4]", sample1.explode().toString())
        val sample2 = parse("[7,[6,[5,[4,[3,2]]]]]")
        assertEquals("[7,[6,[5,[7,0]]]]", sample2.explode().toString())
        val sample3 = parse("[[6,[5,[4,[3,2]]]],1]")
        assertEquals("[[6,[5,[7,0]]],3]", sample3.explode().toString())
        val sample4 = parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")
        assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", sample4.explode().toString())
        val sample5 = parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", sample5.explode().toString())
    }

    @Test
    fun split() {
        val sample1 = parse("10")
        assertEquals("[5,5]", sample1.split().toString())
        val sample2 = parse("11")
        assertEquals("[5,6]", sample2.split().toString())
    }

    @Test
    fun reduce() {
        val sample1 = parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", sample1.reduce().toString())

        val sample2 = parse("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]") + parse("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")
        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", sample2.toString())
    }

    @Test
    fun plus() {
        val samples1 = listOf("[1,1]","[2,2]","[3,3]","[4,4]").map { parse(it) }
        assertEquals("[[[[1,1],[2,2]],[3,3]],[4,4]]", samples1.reduce(SnailfishNumber::plus).toString())

        val samples2 = listOf("[1,1]","[2,2]","[3,3]","[4,4]","[5,5]").map { parse(it) }
        assertEquals("[[[[3,0],[5,3]],[4,4]],[5,5]]", samples2.reduce(SnailfishNumber::plus).toString())

        val samples3 = listOf("[1,1]","[2,2]","[3,3]","[4,4]","[5,5]","[6,6]").map { parse(it) }
        assertEquals("[[[[5,0],[7,4]],[5,5]],[6,6]]", samples3.reduce(SnailfishNumber::plus).toString())

        val samples4 = listOf("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]","[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]","[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]","[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]","[7,[5,[[3,8],[1,4]]]]","[[2,[2,2]],[8,[8,1]]]","[2,9]","[1,[[[9,3],9],[[9,0],[0,7]]]]","[[[5,[7,4]],7],1]","[[[[4,2],2],6],[8,7]]").map { parse(it) }
        assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", samples4.reduce(SnailfishNumber::plus).toString())
    }
}