package filesystem

class CLIReader {

  val cmdRegex = """^\$.*""".toRegex()
  val dirRegex = """dir\ \w+""".toRegex()
  val fileRegex = """\d+\ .*""".toRegex()
  val tree = Navigator()

  var isListingDir: Boolean = false
  fun parseInput(str: String){

    if (cmdRegex.matches(str)) {
      executeCommand(str.replace("$ ", ""))
    } else if (isListingDir) {
      if (dirRegex.matches(str)) {
        // we've found a dir!
        val dir = Directory(str.replace("dir ", ""))
        tree.addDir(dir)
      } else if (fileRegex.matches(str)){
        val desc = str.split(" ")
        val file = File(desc[1].toString(), desc[0].toInt())
        tree.addFile(file)
      } else {
        println("No clue what to do with $str")
      }
    } else {
      println("No clue - not listing a dir - what to do with $str")
    }
  }

  fun executeCommand(cmd: String) {
    if (cmd == "ls") {
      // listing a directory
      isListingDir = true
    } else if (cmd.startsWith("cd ")){
      isListingDir = false
      val target = cmd.replace("cd ","")
      if (target == "/"){
        tree.navigateToTop()
      } else if (target == "..") {
        tree.navigateToParent()
      } else {
        println("Navigating to $target ($cmd)")
        tree.navigateToChild(target)
      }
    }
  }
}