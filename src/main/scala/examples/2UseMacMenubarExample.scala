package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import com.valleyprogramming.agt.system.*
import java.awt.event.KeyEvent.*
import java.awt.Color

@main def macMenubarExample =

    // note: all of the 'sleep' calls are so that i can watch this run

    activateMenuBar
    sleep(500)

    // the first arrow-down causes the Apple menu to be displayed
    arrowDown

    // now go down 3
    arrowDown
    arrowDown
    arrowDown

    // come back up 3
    arrowUp
    arrowUp
    arrowUp

    // go right at the top of the menu items
    arrowRight
    arrowRight

    // then go back left
    arrowLeft
    arrowLeft

    // esc closes the menu
    esc



