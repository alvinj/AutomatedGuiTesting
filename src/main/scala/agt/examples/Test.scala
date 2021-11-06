package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import com.valleyprogramming.agt.system.*
import java.awt.event.KeyEvent.*
import java.awt.Color

@main def test1 =

    // WORKS
    // moveMouse(500, 500)
    // moveMouse(Point(900, 500), 50, 1_500)

    // SIMULATOR TESTS START
    def waitForAppleHealthIcon = 
        "waiting until i see the white in the Apple Health icon ...".c
        waitForColor(Point(909, 520), Color(255, 255, 255))

    def waitClickAppleNewsIcon = 
        click(1099, 422)
        "click the Apple News icon".c

    def waitForAppleLogoInNews = 
        waitForColor(Point(796, 193), Color(255, 255, 255))
        "wait for black Apple logo on News page"

    def spinNewsFeedUp = 
        "quickly spin the news feed upward".c
        clickDragRelease(Point(963, 717), Point(963, 443), 2, 200)

    def goBackToHomeScreen =
        "back to Home screen".c
        pressAndReleaseKeys(Seq(VK_SHIFT, VK_META, VK_H))

    "starting the iPhone simulator ...".c
    startIphoneSimulator
    waitForAppleHealthIcon
    waitClickAppleNewsIcon
    waitForAppleLogoInNews

    spinNewsFeedUp
    sleep(1_500)  // wait so i can see it

    goBackToHomeScreen
    waitForAppleHealthIcon
    sleep(500)    // this is for me as i watch it

    "quit the simulator".c
    pressAndReleaseKeys(Seq(VK_META, VK_Q))
    sleep(500)    // again for me

    writeComments
    // SIMULATOR TESTS END


    // c("create a series of system commands")
    // val cmds = Seq(
    //     "ls -al",                      // status=0, stdout
    //     "ls -al cookie",               // status=1, stderr='ls: cookie: No such file or directory'
    //     "cat /etc/passwd",             // status=0, stdout
    //     "wc -l /etc/passwd | tr -s ' ' | cut -d' ' -f2",   // status: 0, stdout: 120, stderr: ''
    //     "cd ~; ls -1 | grep SOURCE"    // uses ; and |
    // )
    //
    // c("run each of the system commands")
    // for cmd <- cmds do
    //     println(s"\nCMD: $cmd")
    //     val (status, stdout, stderr) = exec(cmd)
    //     println(s"status: $status")
    //     println(s"stdout: $stdout")
    //     println(s"stderr: $stderr")
    //
    // writeComments

    // works
    // moveMouse(700, 500)
    // rightClick
    // done

    // // WORKS
    // p("Waiting for color to go away ...")
    // sleep(2_000)
    // waitForColorToGoAway(Point(449, 276), Color(35, 30, 34), 10_000)
    

    // // WORKS
    // var n = ""
    // val nProcsCmd = "ps aux | grep -i chrome | grep -v grep | wc -l"
    //
    // // start google chrome and see how many processes are running
    // speak("Starting Chrome...")
    // sleep(500)
    // startApp("Google Chrome")
    // waitForColor(Point(588, 649), Color(45, 45, 51), 10_000)
    // n = execInShell(nProcsCmd); println(s"Number of Chrome processes running = $n")
    //
    // // go to google
    // speak("Going to Google")
    // apple('l')
    // ty("google.com\n")
    // n = execInShell(nProcsCmd); println(s"Number of Chrome processes running = $n")
    //
    // // create a new tab, go to apple
    // speak("Going to Apple")
    // apple('t')
    // ty("apple.com\n")
    // n = execInShell(nProcsCmd); println(s"Number of Chrome processes running = $n")
    //
    // // get the url from the url field
    // waitForColorToGoAway(Point(484, 325), Color(45, 45, 51), 10_000)
    // apple('l')  // focus in url field
    // apple('a')  // select all
    // apple('c')  // copy the url
    // val url = getClipboardText
    // println(s"URL: $url")
    //
    // sleep(1_000)
    // speak("Closing the tabs")
    // apple('w')
    // apple('w')
    //
    // // kill chrome (use one or the other)
    // apple('q')
    // // killall("Google Chrome")


    // // WORKS
    // speak("Moving the mouse around")
    // moveMouse(100, 100)
    // moveMouse(1_400, 600)
    // moveMouse(100, 300)
    // moveMouse(800, 500)


    // // WORKS
    // speak("Using the menu bar")
    // sleep(500)
    // activateMenuBar
    // for i <- 1 to 6 do
    //     ty(VK_DOWN)
    //     sleep(50)
    // esc

    // // works
    // click(700, 600)
    // ty("Foo\\nBar")
    // done



