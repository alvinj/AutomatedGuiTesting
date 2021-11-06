package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import com.valleyprogramming.agt.system.*
import java.awt.event.KeyEvent.*
import java.awt.Color

@main def macMenubarExample =

    speak("Using the menu bar")
    sleep(500)
    activateMenuBar
    for i <- 1 to 6 do
        ty(VK_DOWN)
        sleep(50)
    esc

