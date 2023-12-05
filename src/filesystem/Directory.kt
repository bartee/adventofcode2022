package filesystem

class Directory (var name: String){

  var files = mutableListOf<File>()
  var subdirs = mutableListOf<Directory>()

  fun addFile(file: File){
    files.add(file)
  }

  fun addDir(directory: Directory){
    subdirs.add(directory)
  }

  fun getDir(name: String): Directory? {
    val child = subdirs.filter { it.name.contentEquals(name) }.first()
    return child
  }

  fun getSize() : Int{
    var res = 0
    for (file in files.iterator()){
      res += file.size
    }
    for (dir in subdirs.iterator()){
      res += dir.getSize()
    }
    return res
  }

  fun getMinimalRecursiveSize(threshold: Int): Int{
    var totalSize = 0
    val currentSize = this.getSize()
    if (currentSize >= threshold) {
      totalSize += currentSize
      for (child in this.subdirs){
        totalSize += child.getMinimalRecursiveSize(threshold)
      }
    }
    return totalSize
  }

  fun getMaximalRecursiveSize(threshold: Int): Int{
    var totalSize = 0

    val currentSize = this.getSize()
    if (currentSize < threshold) {
      totalSize += currentSize
    }

    for (child in this.subdirs){
      totalSize += child.getMaximalRecursiveSize(threshold)
    }

    return totalSize
  }
}