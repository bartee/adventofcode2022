package filesystem

class Navigator {

  private var activeDirectory: Directory
  var currentPath: String = ""
  var rootdir = Directory("ROOT")

  init {
    this.activeDirectory = rootdir
  }
  fun navigateToParent(){
    println("Navigating to parent")
    var pathList = currentPath.split("/").toMutableList()
    pathList.removeFirst()
    navigateToTop()
    for (dir in pathList) {
      navigateToChild(dir)
    }
  }

  fun navigateToChild(childName: String){
    println("Navigating to $childName (currently in ${this.activeDirectory.name})")
    val child = activeDirectory.getDir(childName)
    if (child is Directory) {
      currentPath += "/"+childName
      activeDirectory = child
    }
  }

  fun navigateToTop(){
    activeDirectory = rootdir
    currentPath = "/"
  }

  fun hasSize(size: Int):Boolean {
    return activeDirectory.getSize() >= size
  }

  fun getMinimalRecursiveSize(threshold: Int) : Int {
    // get the sum of all dirs that have at least a given threshold
    this.navigateToTop()
    return this.activeDirectory.getMinimalRecursiveSize(threshold)
  }

  fun getMaximalRecursiveSize(threshold: Int): Int {
    this.navigateToTop()
    return this.activeDirectory.getMaximalRecursiveSize(threshold)
  }

  fun addDir(dir: Directory) {
    this.activeDirectory.addDir(dir)
  }

  fun addFile(file: File) {
    this.activeDirectory.addFile(file)
  }

}