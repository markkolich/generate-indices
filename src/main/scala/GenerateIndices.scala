import java.io._
import scala.xml._

import scala.xml.Unparsed

object GenerateIndices {

  def main(args: Array[String]) {
    if(args.length<1) {
      println("Usage: generate-indices.jar [root dir name]")
    } else {
      val root = new File(args(0))
      recurse(root, root)
    }
  }

  private def recurse(root: File, file: File): Unit = {
    if (file.isDirectory) {
      write(file, generateIndex(root, file))
    }
    file.listFiles().filter(_.isDirectory()).foreach { f =>
      recurse(root, f)
    }
  }

  private def generateIndex(root: File, dir: File) = {
    val dirs = dir.listFiles.filter(f => {
      val name = f.getName
      f.isDirectory() &&
      !name.startsWith(".git")
    }).map(_.getName + "/").toList.sorted
    val files = dir.listFiles.filter(f => {
      val name = f.getName
      f.isFile &&
      name != "index.html" &&
      name != "README.md" &&
      !name.startsWith(".git")
    }).map(_.getName).toList.sorted
    // When using an embedded stylesheet in an XML literal, a double curly brace
    // "{{" or "}}" is the escape pattern mojo to actually get a curly brace.
    <html>
    <head><style type="text/css">body{{font-family:sans-serif;font-size:20px;color:#B78E68;}}hr{{color:#ccc;background-color:#ccc;border:0;height:1px;}}h1{{font-size:30px;}}.footer{{color:#B78E68;font-size:12px;}}.footer a{{color:#B78E68 !important;}}ul{{list-style:none;padding-left:20px;}}a:visited{{color:blue;}}</style></head>
    <body>
      <h1>{ path(root, dir) }</h1>
      <ul>
        { if(dir != root) { <li><a href="../">..</a></li> } }
        { for (f <- dirs ::: files) yield <li><a href={ f }>{ f }</a></li> }
      </ul>
      {Unparsed("<hr/>")}
      <p class="footer">Generated using <a href="http://github.com/markkolich/generate-indices">generate-indices</a></p>
    </body>
    </html>
  }

  private def path(root: File, dir: File): String = if (dir == root) root.getName else path(root, dir.getParentFile) + "/" + dir.getName

  private def write(dir: File, n: Node) {
    val out = new FileWriter(new File(dir, "index.html"))
    out.write("""<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">""")
    out.write(new PrettyPrinter(120,2).format(n))
    out.close
  }

}

