package com.valleyprogramming.agt

import java.awt.{Color, MouseInfo, Robot}
import java.awt.event.InputEvent.*
import java.awt.event.KeyEvent.*
import scala.sys.process.*
import com.valleyprogramming.agt.system.{exec, getClipboardText}
import com.valleyprogramming.agt.main.pressAndReleaseKeys

/**
 * A good macOS/AppleScript resource: https://eastmanreference.com/complete-list-of-applescript-key-codes
 */
object macos:

    private val robot = Robot()
    robot.setAutoDelay(40)
    robot.setAutoWaitForIdle(true)

    // SEE: https://alvinalexander.com/mac-os-x/how-to-fire-mac-notifications-applescript-scala-java/
    // SEE: https://alvinalexander.com/blog/post/java/how-run-multi-multiple-line-applescript-java-program
    // SEE: https://alvinalexander.com/mac-os-x/how-to-fire-mac-notifications-applescript-scala-java
    // SEE: https://alvinalexander.com/scala/how-to-execute-applescript-from-java-scala/
    /**
     * Start an application and bring it to the foreground.
     */
    def startApp(app_name: String): Unit =
        val cmd = s"""
            |tell application "${app_name}"
            |    activate
            |end tell""".stripMargin
        runAppleScript(cmd)

    /**
     * Starting the iPhone/iOS Simulator is a little weird, so this is a special
     * method for that.
     */
    def startIphoneSimulator: Unit =
        import com.valleyprogramming.agt.system.exec
        exec("open -a Simulator.app")

    /**
     * Use the macOS “text to voice” capability, using the default system voice.
     */
    def speak(text: String): Unit =
        val cmd = s"""  say "${text}"  """.trim
        runAppleScript(cmd)

    /**
     * Run an AppleScript command, including multiline-string commands.
     * {{{
       // works
       val cmd = """
         |repeat 3 times
         |  say "hello"
         |end repeat
         """.stripMargin
       runAppleScript(cmd)
        
       // works
       val cmd = """
         |tell application "Music"
         |    play the playlist named "Easy Listening"
         |end tell
         """.stripMargin
       runAppleScript(cmd)
     * }}}
     */
    def runAppleScript(cmd: String): Unit =
        val runtime = Runtime.getRuntime
        val code = Array("osascript", "-e", cmd)
        val process = runtime.exec(code)

    // this method seems a little on the fragile side
    // /**
    //  * I verified this, but i deleted the example i was using.
    //  */
    // def runAppleScript(cmd: Seq[String]): Unit =
    //     val completeCommand = List("osascript", "-e") ++ cmd
    //     val runtime = Runtime.getRuntime
    //     val process = runtime.exec(completeCommand.toArray)

    /**
     * Should be able to use `apple('l')` or `apple(VK_L)`.
     */
    def apple(key: Int | Char): Unit = key match
        case i: Int => apple_fixnum(i)
        case c: Char => appleChar(c)

    /**
     * This uses a `Robot` instance.
     */
    def apple_fixnum(key: Int): Unit =

        val old_auto_delay = robot.getAutoDelay
        robot.setAutoDelay(0)

        wait(10)
        robot.keyPress(VK_META)
        wait(10)
        robot.keyPress(key)
        wait(200)
        robot.keyRelease(key)
        wait(10)
        robot.keyRelease(VK_META)
        wait(50)

        robot.setAutoDelay(old_auto_delay)

    end apple_fixnum


    // TODO test this. it’s not clear why i take a String here,
    // and then use only the first Char.
    // this needs the `robot` instance.
    def appleChar(c: Char): Unit =
        var i = c.intValue

        // notes: i really needs to be [65-90] (decimal)
        // [A-Z] == [65-90]
        // [a-z] == [97-122]
        // need to subtract 32 b/c java takes only the 26 cap
        // char's. need to add 'shift' to really get caps tho.
        if i > 96 && i < 123 then i = i - 32
        apple_fixnum(i)
    end appleChar

    /**
      * This could more generally be, “press multiple keys at one time.”
      */
    def activateMenuBar: Unit =
        // press these two keys simultaneously, then let them go
        robot.keyPress(VK_CONTROL)
        wait(10)
        robot.keyPress(VK_F2)
        wait(100)

        robot.keyRelease(VK_CONTROL)
        wait(10)
        robot.keyRelease(VK_F2)
        wait(100)

    /**
      * TODO: this is duplicate code from the 'main' package.
      * Specify the `robot.delay` time. This is needed in places
      * such as keystroke and mouseclick functions.
      */
    private def wait(timeInMs: Int): Unit =
        robot.delay(timeInMs)

    /**
     * TODO: needs testing; handle errors.
     * `recipient` can be either an email address or phone number.
     */
    def sendTextMessage(recipient: String, message: String): Int =
        val appleScript = s"""
            |tell application "Messages"
            |    set targetService to 1st service whose service type = iMessage
            |    set targetBuddy to buddy "$recipient" of targetService
            |    send "$message" to targetBuddy
            |end tell
        """.stripMargin
        Seq("osascript", "-e", appleScript).!

    // this is the keystroke to make a macOS app full screen.
    def makeForegroundWindowFullScreen(): Unit =
        // CMD - CTRL - F
        val keySeq = Seq(
            VK_META,
            VK_CONTROL,
            VK_F
        )
        pressAndReleaseKeys(keySeq: Seq[Int], 300)

    /**
     * TODO: test, and return/handle the results
     */
    def createSnapshotAndWriteToFile(outputFile: String, symbol: String): Unit =
        val cmd = s"screencapture -o -m -t jpg $outputFile"
        val (status, stdout, stderr) = exec(cmd)

end macos

