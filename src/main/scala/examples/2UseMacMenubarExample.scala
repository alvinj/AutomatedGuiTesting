package tests

import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*

@main def macMenubarExample =

    // TODO: This has an sbt problem where the first time it’s run, sbt
    // shows a "Boot" process in the menu bar. Look into that.

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



