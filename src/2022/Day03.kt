fun main() {

  val interpreter = Day03()
  fun testGetCharacterPriority(){
    val testArray = mapOf("a" to 1, "b" to 2, "f" to 6, "A" to 27, "B" to 28, "F" to 32, "Z" to 52)
    for (entry in testArray.entries.iterator()) {
      val test = interpreter.getCharacterPriority(entry.key)
      check(test == entry.value)
    }
  }

  fun testSplitIntoCompartments(){
    val rucksackItems = arrayOf("abfdcABDCf", "poiuytrewqQWERTYUIOP")
    for (entry in rucksackItems.iterator()){
      interpreter.splitIntoCompartments(entry)
    }
  }

  fun testDuplicates() {
    val rucksackItems = mapOf("abfdcABDCf" to 1, "poiuytrewqQWERTYuiOP" to 2, "ASDFasdf" to 0)
    for ((entry, counter) in rucksackItems.iterator()){
      val compartments = interpreter.splitIntoCompartments(entry)
      val res = interpreter.getDuplicates(compartments[0], compartments[1])
      check(res.length == counter)
    }
  }

  fun calculatePriorityOfDuplicates(input: List<String>): Int {
    var score = 0
    input.map {
      val compartments = interpreter.splitIntoCompartments(it)
      val duplicates = interpreter.getDuplicates(compartments[0], compartments[1])
      var linescore = 0
      for (el in duplicates.iterator()){
        linescore += interpreter.getCharacterPriority(el.toString())
      }
      score += linescore
    }
    return score
  }

  fun checkGroupBadges(input: List<String>): Int {
    var score = 0

    // Divide all the rucksacks in groups of three
    val groupRucksacks = input.chunked(3)
    groupRucksacks.map {
      // Let's make every rucksack distinct first
      val rucksack1 = it[0].toCharArray().distinct()
      val rucksack2 = it[1].toCharArray().distinct()
      val rucksack3 = it[2].toCharArray().distinct()
      for (el_1 in rucksack1){
        var shared = false
        for (el_2 in rucksack2) {
          if (el_1 == el_2 && rucksack3.contains(el_1)){
            // Element is in all three!
            shared = true
          }
        }
        if (shared) {
          // Score it
          val itemscore = interpreter.getCharacterPriority(el_1.toString())
          score += itemscore
        }
      }
    }
    return score
  }

  // test if implementation meets criteria from the description, like:
  testGetCharacterPriority()
  testSplitIntoCompartments()
  testDuplicates()

  val testInput = readInput("resources/day03_example")
  check(calculatePriorityOfDuplicates(testInput) == 157)
  check(checkGroupBadges(testInput) == 70)

  val realInput = readInput("resources/day03_dataset")
  val realResult = calculatePriorityOfDuplicates(realInput)
  val realBadgesResult = checkGroupBadges(realInput)

  println("Total priorities: $realResult")
  println("Priorities of badges per group of three elves: $realBadgesResult")
}

class Day03 {
  fun getCharacterPriority(str: String): Int {
    val toCheck = str.first()

    if (toCheck.isUpperCase()){
      return toCheck.code - 38
    }
    return toCheck.code - 96
  }

  fun splitIntoCompartments(str: String): Array<String> {
    val firstCompartment = str.subSequence(0, str.length/2).toString()
    val secondCompartment = str.subSequence(str.length/2, str.length).toString()

    return arrayOf(firstCompartment, secondCompartment)
  }

  fun getDuplicates(firstCompartment: String, secondCompartment: String): String {
    val duplicateList: MutableList<Char> = mutableListOf()
    for (element in firstCompartment.iterator()){
      if (secondCompartment.contains(element)) {
        duplicateList.add(element)
      }
    }
    return duplicateList.distinct().joinToString("")
  }
}