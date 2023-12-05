fun main() {
    fun groupCaloryListPerElf(input: List<String>): HashMap<Int, Int> {
        // Every line is an amount of calories carried by an elf
        // Every blank line indicates a new elf.
        var res = 0
        var iterator = 0
        var resultset = HashMap<Int, Int>()
        var counter = 0
        input.map {
            if (it.isNullOrEmpty()) {
                res = 0
                iterator++
            } else {
                res = res + it.toInt()
            }
            resultset.set(iterator, res)
            counter++
        }
        return resultset
    }

    fun part1(input: List<String>): Int {
        var resultset = groupCaloryListPerElf(input).values
        return resultset.max()
    }

    fun part2(input: List<String>): Int {
        var resultset = groupCaloryListPerElf(input).values
        return resultset.sortedDescending().subList(0,3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val test_input = readInput("resources/day01_example")
    check(part1(test_input) == 24000)
    check(part2(test_input) == 45000)

    // Calculate the results from the input:
    val input = readInput("resources/day01_dataset")

    println("Max calories: " + part1(input))
    println("Max calories top 3 elves: " +part2(input))
}
