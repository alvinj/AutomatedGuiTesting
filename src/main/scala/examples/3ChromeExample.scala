package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import com.valleyprogramming.agt.system.*
import java.awt.event.KeyEvent.*
import java.awt.Color

def printNumberOfChromeProcesses = 
    val (exitCode, stdout, stdin) = exec("ps aux | grep -i chrome | grep -v grep | wc -l")
    println(s"Number of Chrome processes running = $stdout")

@main def chromeExample =

    // All of the `sleep` commands in here are for me, so I can watch the test.
    // They aren’t necessary for a completely-automated test.
    
    // The main expectation of this script is that Chrome is not currently running.
    // Also, when it does start, this script types into the URL field of the 
    // first browser tab.
    // It also closes two tabs and quits Chrome when it’s finished.

    // start google chrome and see how many processes are running
    speak("Starting Chrome...")
    sleep(1_000)
    startApp("Google Chrome")
    waitForColor(Point(588, 649), Color(45, 45, 51), 10_000)
    printNumberOfChromeProcesses
    sleep(500)

    // go to google, check the processes
    speak("Going to Google.com")
    apple('l')
    ty("google.com\n")
    printNumberOfChromeProcesses
    sleep(500)

    // create a new tab, go to apple, check the processes
    speak("Going to Apple.com")
    apple('t')
    ty("apple.com\n")
    printNumberOfChromeProcesses
    sleep(500)

    // get the url from the url field
    waitForColorToGoAway(Point(484, 325), Color(45, 45, 51), 10_000)
    apple('l')  // focus in url field
    apple('a')  // select all
    apple('c')  // copy the url
    val url = getClipboardText
    "URL: $url".c
    sleep(1_000)

    speak("Closing the tabs")
    sleep(500)
    apple('w')
    apple('w')

    speak("Closing Chrome")
    sleep(500)
    apple('q')
    // killall("Google Chrome")





