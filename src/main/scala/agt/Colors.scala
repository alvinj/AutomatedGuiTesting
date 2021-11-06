package com.valleyprogramming.agt

import java.awt.{Color, Robot}
import java.awt.event.InputEvent.*
import java.awt.event.KeyEvent.*
import com.valleyprogramming.agt.main.sleep

object colors:

    private val robot = Robot()
    robot.setAutoDelay(40)
    robot.setAutoWaitForIdle(true)

    private def time(): Long = System.currentTimeMillis()

    // TODO: Java doesn’t see the same color as the
    // Digital Color Meter app, so atm I need this.
    private def almostEqual(a: Int, b: Int, tolerance: Int = 10): Boolean =
        val diff = Math.abs(a - b)
        if diff <= tolerance then
            true
        else
            false
    end almostEqual

    private def colorIsSeen(pixelColor: Color, desiredColor: Color): Boolean =
        if almostEqual(desiredColor.getRed, pixelColor.getRed) && 
           almostEqual(desiredColor.getGreen, pixelColor.getGreen) && 
           almostEqual(desiredColor.getBlue, pixelColor.getBlue)
        then
            true
        else
            false
    end colorIsSeen

    /**
     * Wait for a color to appear at an x/y location.
     * Throws an exception if the wait time is exceeded.
     * @param waitFor Time to wait in ms.
     */
    @throws(classOf[Exception])
    def waitForColor(
        p: Point,
        c: Color,
        maxWaitTime: Long = 10_000
    ): Unit =
        val pauseTime = 50
        var timeWaited: Long = 0
        val tStart = time()
        while timeWaited < maxWaitTime do
            // leave this method when the color is found
            val c2 = robot.getPixelColor(p.x, p.y)
            if colorIsSeen(c2, c) then return ()

            // slight pause before looking for color again
            sleep(pauseTime)
            timeWaited = time() - tStart

        // coming here means the color was never found
        throw Exception(s"Color(${c.getRed}, ${c.getGreen}, ${c.getBlue}) was never seen at (x=${p.x}, y=${p.y}).")
    end waitForColor

    /**
     * TODO: refactor; this is almost identical to the previous method.
     * TODO: throw an exception if the initial color is wrong.
     * Wait for a color an x/y location *to go away*.
     * Throws an exception if the wait time is exceeded.
     * @param waitFor Time to wait in ms.
     */
    @throws(classOf[Exception])
    def waitForColorToGoAway(
        p: Point,
        c: Color,
        maxWaitTime: Long = 10_000
    ): Unit =
        val pauseTime = 50
        var timeWaited: Long = 0
        val tStart = time()
        while timeWaited < maxWaitTime do
            // leave this method when the color goes away
            val c2 = robot.getPixelColor(p.x, p.y)
            if !colorIsSeen(c2, c) then return ()

            // slight pause before looking for color again
            sleep(pauseTime)
            timeWaited = time() - tStart

        // coming here means the color never went away
        throw Exception(s"Color(${c.getRed}, ${c.getGreen}, ${c.getBlue}) never went away at (x=${p.x}, y=${p.y}).")

    end waitForColorToGoAway

end colors

