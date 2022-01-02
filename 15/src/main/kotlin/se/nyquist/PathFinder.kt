package se.nyquist

import java.io.File

fun main() {
    val lines = File("input.txt").readLines()
    val map = lines.map { it.toCharArray().map { c -> c.digitToInt() }.toTypedArray()}.toTypedArray()
    val finder = PathFinder(map)
    val route = finder.find()
    val points = route.route.joinToString(",") { "(${it.first},${it.second})" }
    println("Min cost: ${route.cost} for route: $points")
}

data class Route(val route: List<Pair<Int,Int>>, val cost: Long) {
    override fun toString(): String {
        return "Route(cost=$cost)"
    }
}

class PathFinder(private val map : Array<Array<Int>>, private val bestPath : MutableMap<Pair<Int,Int>, Route> = mutableMapOf()) {

     fun find(): Route {
        val startpos = Pair(0,0)
        val endpos = Pair(map[map.lastIndex].lastIndex, map.lastIndex)
        val routes = routes(endpos, Route(listOf(startpos),0))
        return routes.sortedBy { it.cost }.first()
    }

    private fun routes(endpos: Pair<Int, Int>, initalRoute: Route) : List<Route> {
        val routes = mutableListOf(initalRoute)
        val result = mutableListOf<Route>()
        while (routes.isNotEmpty()) {
            val route = routes.removeAt(0)
            val current = route.route.last()
            if (current == endpos) {
                result.add(route)
            } else {
                routes.addAll(getNewRoutes(current, endpos, route))
            }
        }
        return result
    }

    private fun getNewRoutes(
        current: Pair<Int, Int>,
        endpos: Pair<Int, Int>,
        route: Route
    ): Collection<Route> {
        val candidates = mutableListOf<Pair<Int, Int>>()
        if (current.first < endpos.first) {
            candidates.add(Pair(current.first + 1, current.second))
        }
        if (current.second < endpos.second) {
            candidates.add(Pair(current.first, current.second + 1))
        }
        if (current.first > 0) {
            candidates.add(Pair(current.first - 1, current.second))
        }
        if (current.second > 0) {
            candidates.add(Pair(current.first, current.second - 1))
        }
        return candidates
                .filter {
                    !bestPath.containsKey(it) ||
                            (bestPath.containsKey(it) && bestPath.getValue(it).cost > route.cost + getCost(it))
                }
                .map { createRoute(route, it) }.toList()
    }

    private fun createRoute(
        route: Route,
        current: Pair<Int, Int>,
    ) : Route {
        val coordinates = route.route.toMutableList()
        coordinates.add(current)
        val newCost = route.cost + getCost(current)
        val newRoute = Route(coordinates, newCost)
        bestPath[current] = newRoute
        return newRoute
    }

    private fun getCost(it: Pair<Int, Int>): Int {
        return map[it.first][it.second]
    }
}


