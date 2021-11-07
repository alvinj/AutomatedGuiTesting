package com.valleyprogramming.agt

import java.awt.{Color, MouseInfo, Robot}
// These contain all the VK, ALT, CTRL, META, SHIFT, BUTTON keys you can type.
// In these classes the keys are all represented as Ints.
import java.awt.event.InputEvent.*
import java.awt.event.KeyEvent.*

object main:

    private val robot = Robot()
    robot.setAutoDelay(50)
    robot.setAutoWaitForIdle(true)
    private val ARROW_KEY_WAIT_TIME = 100
    
    private val SHIFT_KEY_HASH = Map(
        ':' -> ';', 
        '"' -> '\'',
        '<' -> ',', 
        '>' -> '.',
        '?' -> '/', 
        '{' -> '[',
        '}' -> ']', 
        '|' -> '\\',
        '_' -> '-', 
        '+' -> '=',
        '~' -> '`', 
        '!' -> '1',
        '@' -> '2', 
        '#' -> '3',
        '$' -> '4', 
        '%' -> '5',
        '^' -> '6', 
        '&' -> '7',
        '*' -> '8', 
        '(' -> '9',
        ')' -> '0'
    )

    /**
     * A shortcut for using `println`.
     */
    def p(a: Any): Unit = println(a)

    /**
     * When using output printed to STDOUT, it helps to call
     * this method when you’re done, especially when you run
     * many tests one after another. This helps serve as a
     * marker.
     */
    def done =
        p("Reached the end of the script")
        System.exit(0)

    /**
     * TODO: There is a bug here where the loop is not getting to the final point,
     * and then the cursor jumps to the final, correct point.
     *
     * Move the mouse to the given X/Y coordinates, animating the
     * movement.
     */
    def moveMouse(x: Int, y: Int) =
        val old_auto_delay = robot.getAutoDelay

        val pi = MouseInfo.getPointerInfo().getLocation()
        val x0 = pi.x
        val y0 = pi.y

        val x_dist = x - x0
        val y_dist = y - y0

        var x_new = x0
        var y_new = y0

        robot.setAutoDelay(15)
        val steps = 50
        for i <- 1 to steps do
            x_new = (x_new + x_dist.toFloat/steps).toInt
            y_new = (y_new + y_dist.toFloat/steps).toInt
            robot.mouseMove(x_new, y_new)
        end for

        // just to be sure we get to the destination
        robot.mouseMove(x, y)

        robot.setAutoDelay(old_auto_delay)
    end moveMouse

    /**
     * TODO: There is a bug here where the loop is not getting to the final point,
     * and then the cursor jumps to the final, correct point.
     *
     * Move the mouse to the given X/Y coordinates, animating the
     * movement.
     */
    def moveMouse(p: Point, numSteps: Int = 50, totalMoveTime: Int = 1_000) =
        val old_auto_delay = robot.getAutoDelay

        val pi = MouseInfo.getPointerInfo().getLocation()
        val x0 = pi.x
        val y0 = pi.y

        val x_dist = p.x - x0
        val y_dist = p.y - y0

        var x_new = x0
        var y_new = y0

        val stepTime = (totalMoveTime / numSteps).toInt
        robot.setAutoDelay(stepTime)
        for i <- 1 to numSteps do
            x_new = (x_new + x_dist.toFloat/numSteps).toInt
            y_new = (y_new + y_dist.toFloat/numSteps).toInt
            robot.mouseMove(x_new, y_new)
        end for

        // just to be sure we get to the destination
        robot.mouseMove(p.x, p.y)

        robot.setAutoDelay(old_auto_delay)
    end moveMouse

    /**
     * Does a single-click at the given x/y coordinates.
     * The "delay" is the time between the mousePress and
     * mouseRelease method calls. The "do_animation" field
     * lets you specify whether you want to animate the mouse
     * movement from the current location to the new x/y
     * coordinates, prior to the click.
     */
    def click(x: Int, y: Int, delayInMs: Int = 250) =
        moveMouse(x,y)
        robot.mousePress(BUTTON1_MASK)
        robot.delay(delayInMs)
        robot.mouseRelease(BUTTON1_MASK)

    /**
     * Do a left-click at the current X/Y coordinates.
     */
    def click =
        robot.mousePress(BUTTON1_DOWN_MASK)
        robot.mouseRelease(BUTTON1_DOWN_MASK)

    /**
     * Simulate clicking the mouse at the first point, then dragging the
     * mouse pointer to the second point, and releasing it. This gives you a way
     * to spin a list up or down. It should also work as a way to drag-and-drop.
     */
    def clickDragRelease(clickPoint: Point, releasePoint: Point, numSteps: Int = 50, totalMoveTime: Int = 1_000): Unit =
        moveMouse(clickPoint.x, clickPoint.y)
        sleep(100)
        // move the mouse to the new point, simulating a drag and release
        val oldAutoDelay = robot.getAutoDelay
        robot.setAutoDelay(10)
        robot.mousePress(BUTTON1_DOWN_MASK)
        moveMouse(Point(releasePoint.x, releasePoint.y), numSteps, totalMoveTime)
        robot.mouseRelease(BUTTON1_DOWN_MASK)
        robot.setAutoDelay(oldAutoDelay)

    /**
      * Perform a mouse double-click operations at the given
      * x/y coordinates.
      */
    def doubleClick(x: Int, y: Int): Unit =
        click(x, y, 50)
        click(x, y, 50)

    /**
      * Perform a mouse double-click operations at the current
      * x/y coordinates.
      */
    def doubleClick: Unit =
        click
        click

    /**
     * Do a right-click at the current X/Y coordinates.
     */
    def rightClick =
        robot.mousePress(BUTTON3_DOWN_MASK)
        robot.mouseRelease(BUTTON3_DOWN_MASK)

    /**
      * Specify the `robot.delay` time. This is needed in places
      * such as keystroke and mouseclick functions.
      */
    private def wait(timeInMs: Int): Unit =
        robot.delay(timeInMs)

    /**
      * A convenience method for when you want a pause in the action.
      */
    def sleep(timeInMs: Int): Unit =
        Thread.sleep(timeInMs)

    /**
     * Gets the RGB colors at the given pixel location..
     * Returns a Color object so you can use c.getRed, c.getGreen, c.getBlue
     * to get the values.
     */
    def getPixelColor(x: Int, y: Int): Color =
        robot.getPixelColor(x, y)

    // A convenience method for typing a [Tab] character.
    def tab = ty(VK_TAB)

    // A convenience method for typing an [Enter] character.
    def enter = ty(VK_ENTER)

    // A convenience method for typing an [Escape] character.
    def esc = ty(VK_ESCAPE)
    
    def arrowUp = 
        ty(VK_UP)
        wait(ARROW_KEY_WAIT_TIME)

    def arrowDown = 
        ty(VK_DOWN)
        wait(ARROW_KEY_WAIT_TIME)

    def arrowLeft = 
        ty(VK_LEFT)
        wait(ARROW_KEY_WAIT_TIME)

    def arrowRight = 
        ty(VK_RIGHT)
        wait(ARROW_KEY_WAIT_TIME)

    /**
      * TODO: this method may be unsafe. See the approach in `ty`
      *       and `typeCorrectedKeycode` below.
      * Type data represented by integers or bytes, such as VK_TAB,
      * or any other VK_* character. Usage is like this:
      * type_keys VK_TAB
      */
    def ty(c: Int): Unit =
        wait(15)
        robot.keyPress(c)
        wait(50)
        robot.keyRelease(c)
        wait(50)

    // the method formerly named 'type'
    def ty(s: String, inEscapeMode: Boolean = false): Unit = s.toList match
        // work with this as a List[Char]
        case c :: cs =>
            if inEscapeMode then
                typePostEscapeKey(c)
                ty(cs.mkString, false)
            else if c.intValue == 92 then  // the \ char
                ty(cs.mkString, true)
            else
                typeCorrectedKeycode(c.intValue)
                ty(cs.mkString, false)
            end if
        case Nil =>
            // do nothing

    private def typeKeycode(keycode: Int): Unit =
        // println(s"keycode: $keycode")
        wait(10)
        robot.keyPress(keycode)
        wait(20)
        robot.keyRelease(keycode)

    private def shiftTypeKeycode(keycode: Int): Unit =
        robot.keyPress(VK_SHIFT)
        robot.keyPress(keycode)
        wait(250)
        robot.keyRelease(keycode)
        robot.keyRelease(VK_SHIFT)

    /**
     * Currently these all need to be `VK_*` key definitions, like
     * `VK_SHIFT`, `VK_META`, `VK_H`, etc.
     */
    def pressAndReleaseKeys(keycodes: Seq[Int], keyPressTime: Int = 200): Unit =
        for k <- keycodes do robot.keyPress(k)
        wait(keyPressTime)
        for k <- keycodes do robot.keyRelease(k)

    private def typePostEscapeKey(c: Char): Unit = c.intValue match
        case 102 => typeKeycode(12) 
        case 110 => typeKeycode(10)
        case 114 => typeKeycode(13)
        case 116 => typeKeycode(9)
        case 98  => typeKeycode(VK_BACK_SPACE)   // TODO: temporary kludge (make \b == backspace)
        case 100 => typeKeycode(VK_DELETE)
        case _   => System.err.println("this shouldn’t happen")

    // TODO this still needs some work (see the old code)
    // TODO change it to a match expression
    private def typeCorrectedKeycode(i: Int): Unit =
        // handle "shift" key characters first
        if SHIFT_KEY_HASH.contains(i.toChar) then
            //puts "\nDEBUG: Got an //{a} character (//{i})"
            val key_to_press: Char = SHIFT_KEY_HASH(i.toChar)
            val keycode: Int = key_to_press.intValue
            // call our special method to type these characters
            shiftTypeKeycode(keycode)
        else if i > 31 && i < 65 then
            // keyboard chars (32-64); by handling the shift key
            // characters first, i should just be able to type whatever
            // comes here
            typeKeycode(i)
        else if i > 64 && i < 91 then
            // uppercase characters (65-90)
            shiftTypeKeycode(i)
        else if i > 90 && i < 97 then
            // more keyboard chars (91-96)
            typeKeycode(i)
        else if i > 122 && i < 127 then
            // more keyboard chars (123-126)
            typeKeycode(i)
        else
            // lowercase characters
            // java really only handles uppercase characters, so convert lowercase
            // values to uppercase here
            if i > 96 && i < 123 then
                typeKeycode(i - 32)
            else
                typeKeycode(i)
            end if
        end if


    /**
     * CODE BELOW HERE IS RELATED TO COMMENTS/LOGGING
     * ----------------------------------------------
     */

    import java.time.LocalDateTime
    import java.time.format.DateTimeFormatter
    private val comments = scala.collection.mutable.ArrayBuffer[String]()

    /**
     * Instead of writing comments in your code, call this method.
     * Your comments are kept in a stack, and if your test fails,
     * the stack of comments is printed, which is a great help in
     * debugging. The comments are also saved with a timestamp.
     */
    def comment(comment: String): Unit =
        val t = LocalDateTime.now
        val f = DateTimeFormatter.ofPattern("yyyy-LL-dd kk:mm:ss")   // 2021-11-04 18:12:43 (Nov. 4, 6:12pm)
        val tf = f.format(t)
        comments += s"${tf}: $comment"

    def writeComments: Unit = comments.foreach(println)

    extension (s: String)
        def c: Unit = comment(s)

end main











