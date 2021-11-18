package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import com.valleyprogramming.agt.system.{exec, getClipboardText}
import java.awt.Color
import scala.collection.mutable.ArrayBuffer
import util.control.Breaks.*
import java.awt.event.KeyEvent.*

def bringAppToForeground(appName: String) : Unit =
    sleep(1_000)
    startApp(appName)
    sleep(2_000)

def getUrlFromBrowser: String = 
    sleep(100)
    apple('l')  // url field
    apple('a')  // select all
    apple('c')  // copy the url
    sleep(100)
    getClipboardText

def goToNextTab: Unit =
    pressAndReleaseKeys(Seq(VK_ALT, VK_META, VK_RIGHT))
    // pressAndReleaseKeys(Seq(VK_SHIFT, VK_CONTROL, VK_TAB), 100)
    sleep(100)
    // this also works, but wasn’t needed:
    // val cmd = """tell application "System Events" to key code 123 using {option down, command down}"""
    // runAppleScript(cmd)
    // sleep(200)


/**
 * Get a list of all the URLs from my open Firefox tabs.
 */
@main def getFirefoxTabsUrls =

    val listOfUrls = ArrayBuffer[String]()
    bringAppToForeground("Firefox")

    breakable {
        while true do
            val url = getUrlFromBrowser
            if listOfUrls.contains(url) then
                break
            else
                listOfUrls += url
                goToNextTab
            end if
    }

    println(s"Here are the contents of your ${listOfUrls.size} open tabs:")
    listOfUrls.foreach(println)






