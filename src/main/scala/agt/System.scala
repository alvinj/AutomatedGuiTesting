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

    def getClipboardText: String =
        Toolkit.getDefaultToolkit()
               .getSystemClipboard()
               .getData(DataFlavor.stringFlavor)
               .asInstanceOf[String]

    /**
     * TODO: test
     */
    def putStringOnClipboard(text: String): Unit =
        val stringSelection = new StringSelection(text)
        val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
        clipboard.setContents(stringSelection, null)

    /**
     * TODO: test; get rid of older function when you can.
     */
    def getStringFromClipboard: Option[String] =
        try
            val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
            val contents = clipboard.getContents(null)
            if contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor) then
                Some(contents.getTransferData(DataFlavor.stringFlavor).asInstanceOf[String])
            else
                None
        catch
            case e: Exception =>
                System.err.println("Error getting string from clipboard: " + e.getMessage)
                None

end system





