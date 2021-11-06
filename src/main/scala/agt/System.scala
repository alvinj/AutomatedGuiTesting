package com.valleyprogramming.agt

import sys.process.*

// all for clipboard stuff
import java.awt.HeadlessException
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.UnsupportedFlavorException
import java.io.IOException

object system:

    type ExitCode = Int
    type Stdout = String
    type Stderr = String
    
    /**
     * Run an external system command, and return all the information
     * from it. Stdout and Stderr can be multiline strings.
     */
    def exec(cmd: String): (ExitCode, Stdout, Stderr) =
        val stdout = StringBuilder()
        val stderr = StringBuilder()
        val status = Seq("/bin/sh", "-c", cmd) ! ProcessLogger(
            stdout append concat(_ , "\n"),
            stderr append concat(_ , "\n")
        )
        (status, stdout.toString.trim, stderr.toString.trim)

    private def concat(s1: String, s2: String) = s1 + s2

    // /**
    //  * Run a command and get its result.
    //  * {{{
    //  * exec(Seq("ls", "-a", "-l", "/tmp"))   // works
    //  * exec(Seq("ls", "-a", "-l", "/FOO"))   // throws a RuntimeException
    //  * }}}
    //  */
    // def exec(cmds: Seq[String]): String =
    //     cmds.!!.trim

    /**
     * Run a command and get its exit status (i.e., 0, 1, etc.)
     * TODO: this also prints output to STDOUT.
     * {{{
     * val exitCode = execForExitStatus(Seq("ls", "-a", "-l", "/tmp"))
     * }}}
     */
    def execForExitStatus(cmds: Seq[String]): Int =
        cmds.!

    /**
     * Run a command and get its result.
     * The command can include wildcard characters, pipelines, and semi-colons.
     * {{{
     * execInShell("ls -al | grep aa")
     * execInShell("cd /Users/al; ls -al | grep .Trash")
     * val s = execInShell("ps aux | grep -i firefox")  // multiline string
     * val n = s.split("\\n").size
     * }}}
     */
    def execInShell(cmd: String): String =
        Seq("/bin/sh", "-c", cmd).!!.trim

    def getClipboardText: String =
        Toolkit.getDefaultToolkit()
               .getSystemClipboard()
               .getData(DataFlavor.stringFlavor)
               .asInstanceOf[String]

end system







