package tests

import java.awt.Color
import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import java.awt.event.KeyEvent.*

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

@main def iPhoneSimulatorExample =

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



