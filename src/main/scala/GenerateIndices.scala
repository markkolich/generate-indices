import java.io._
import scala.xml._

object GenerateIndices {

  def main(args: Array[String]) {
    if(args.length<1) {
      println("Usage: generate-indices.jar [root dir name]")
    } else {
      val root = new File(args(0))
      recurse(root, root)
    }
  }

  def recurse(root: File, file: File): Unit = {
    if (file.isDirectory) {
      write(file, generateIndex(root, file))
    }
    file.listFiles().filter(_.isDirectory()).foreach { f =>
      recurse(root, f)
    }
  }

  def generateIndex(root: File, dir: File) = {
    val dirs = dir.listFiles.filter(_.isDirectory).map(_.getName + "/").toList.sorted
    val files = dir.listFiles.filter(f => f.isFile && f.getName != "index.html").map(_.getName).toList.sorted
    // When using an embedded stylesheet in an XML literal, a double curly brace
    // "{{" or "}}" is the escape pattern mojo to actually get a curly brace.
    <html>
    <!-- Generated using https://github.com/markkolich/generate-indices -->
    <head><style type="text/css">body{{font-family:sans-serif;font-size:20px;color:#B78E68;}}h1{{font-size:30px;}}ul{{list-style:none;padding-left:20px;}}a:visited{{color:blue;}}</style></head>
    <body>
      <h1>{ path(root, dir) }</h1>
      <ul>
        <li><a href="../">..</a></li>
        { for (f <- dirs ::: files) yield <li><a href={ f }>{ f }</a></li> }
      </ul>
    </body>
    </html>
  }

  def path(root: File, dir: File): String = if (dir == root) root.getName else path(root, dir.getParentFile) + "/" + dir.getName

  def write(dir: File, n: Node) {
    val out = new FileWriter(new File(dir, "index.html"))
    out.write("""<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">""")
    out.write(new PrettyPrinter(120,2).format(n))
    out.close
  }

}

