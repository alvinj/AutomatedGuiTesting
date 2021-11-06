package tests

import com.valleyprogramming.agt.Point
import com.valleyprogramming.agt.main.*
import com.valleyprogramming.agt.macos.*
import com.valleyprogramming.agt.colors.*
import com.valleyprogramming.agt.system.*
import java.awt.event.KeyEvent.*
import java.awt.Color

@main def moveMouseExample =

    moveMouse(500, 500)
    moveMouse(Point(900, 500), 50, 1_500)
    rightClick
