# generate-indices

Trivial Scala project to generate HTML indices (index.html pages) for a set of nested directories.

Meh.

## Using

Download the latest runnable JAR from the <a href="https://github.com/markkolich/generate-indices/downloads">download page</a>.

The JAR is self-contained courtesy of the <a href="https://github.com/sbt/sbt-onejar">sbt-onejar</a> plugin.  Although the project is written in Scala, you don't need Scala to run it -- just a working JVM.

Let's say you want to generate index.html pages for all files and directories under directory **foobar**, then run ...

    #~/> java -jar generate-indices-0.2-one-jar.jar foobar

## Building 

This project is built using <a href="https://github.com/harrah/xsbt">SBT (the Simple Build Tool)</a> **0.12.1**.

To clone+build this project, you must have <a href="http://www.scala-sbt.org/release/docs/Getting-Started/Setup">SBT 0.12.1 installed and configured on your computer</a>.

First, clone the repository.

    #~> git clone git://github.com/markkolich/generate-indices.git

Second, run SBT from within generate-indices.

    #~> cd generate-indices
    #~/generate-indices> sbt
    ...
    >

You will see an SBT `>` prompt once all dependencies are resolved and the project is loaded.

Lastly, in SBT, run `one-jar` to compile and package the self-contained runnable JAR.

    > one-jar
    ...
    [info] Compiling 1 Scala source to ~/generate-indices/target/classes...
    [info] Packaging ~/generate-indices/target/generate-indices-0.2.jar ...
    [info] Done packaging.
    [info] Packaging ~/generate-indices/target/generate-indices-0.2-one-jar.jar ...
    [info] Done packaging.
    [success] Total time: 9 s

Note the resulting one-jar enabled JAR is placed into the **generate-indices/target** directory.

In a separate shell, attempt to run your newly created JAR.

    #~> java -jar ~/generate-indices/target/generate-indices-0.2-one-jar.jar
    Usage: generate-indices.jar [root dir name]

Yay!

## Licensing

Copyright (c) 2012 <a href="http://mark.koli.ch">Mark S. Kolich</a>

All code in this project is freely available for use and redistribution under the <a href="http://opensource.org/comment/991">MIT License</a>.

See <a href="https://github.com/markkolich/generate-indices/blob/master/LICENSE">LICENSE</a> for details.
