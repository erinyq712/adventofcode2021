package se.nyquist

class Probe(private val targetArea: TargetArea, position: Pair<Int, Int>, private val velocity: Pair<Int, Int>) {

    var maxPos = position.second

    private var state = ProbeState(position, velocity)

    private fun onTarget(): Boolean {
        return targetArea.xRange.contains(state.position.first) && targetArea.yRange.contains(state.position.second)
    }

    private fun outsideTarget(): Boolean {
        return targetArea.xRange.last < state.position.first || targetArea.yRange.first > state.position.second
    }

    private fun update() {
        state = state.nextPosition()
        if (state.position.second > maxPos) {
            maxPos = state.position.second
        }
    }

     override fun toString(): String {
         return "Probe(maxPos=$maxPos, state=$state)"
     }

     fun search(hits: MutableList<Pair<Int,Int>>) : Boolean {
         while (!onTarget() && !outsideTarget()) {
             update()
             if (onTarget()) {
                 hits.add(velocity)
                 return true
             }
             if (outsideTarget()) {
                 return false
             }
         }
         return false
     }
 }
