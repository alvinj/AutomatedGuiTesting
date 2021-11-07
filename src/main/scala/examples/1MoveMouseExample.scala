package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*

@main def moveMouseExample =

    moveMouse(500, 500)
    moveMouse(Point(900, 500), 50, 1_500)
    
    // this does a right-click at whatever X/Y point the mouse is currently at
    rightClick
