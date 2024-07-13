# Automated GUI Testing (AGT) Software

This is an update to the Automated GUI Testing (AGT) software that I originally
released in 2010. That code was written with JRuby, and this new code is written
with Scala 3.

**WARNING 1:** Please be aware that the code is currently in flux, and everything is 
subject to change. That being said, some stuff works. :)

**WARNING 2:** This project isn’t like most others. Because it automates keystrokes,
mouse movements, and mouse clicks, it can do real damage to your computer. Therefore,
don’t just arbitrarily run a script without first understanding what it does.

**WARNING 1:** I have only tested this code on macOS systems.

**NOTE**: Although I call this software “Automated GUI Testing,” it can also be
used for *GUI scripting* tasks. That being said, my current primary reason for it
is GUI testing, hence the name.


## Documentation

The most current documentation can be found in the examples under the
[src/main/scala/examples](src/main/scala/examples) folder.

### Other docs

Eventually there will be more documentation under a _docs_ folder.
Until then, this is a quick, unorganized list of the methods that are built into the AGT software.

General methods:
- `p(a: Any)` — a shortcut for the println method
- `done` — call when your script is ended (if desired)
- `sleep(timeInMs: Int)` — sleep for X ms
- `c` — add to the *end* of a string to create a comment; all comments are stored as a list
- `writeComments` — print out the list of comments

Mouse-related methods:
- `moveMouse(x: Int, y: Int)` — move the mouse to the X/Y coordinates
- `moveMouse(p: Point, numSteps: Int = 50, totalMoveTime: Int = 1_000)` — move the mouse, with animation control
- `click(x: Int, y: Int, delayInMs: Int = 250)` — left-click the given point
- `click` — left-click wherever the mouse cursor is right now
- `clickDragRelease(p1: Point, p2: Point, numSteps: Int = 50, totalMoveTime: Int = 1_000)` — click and drag, good for scrolling a list
- `doubleclick(x: Int, y: Int)` — double-click the X/Y coordinates
- `rightClick` — right-click at the current X/Y coordinates

Keyboard-related methods:
- `arrowUp` — convenience method for the up arrow
- `arrowDown` — convenience method for the down arrow
- `arrowLeft` — convenience method for the left arrow
- `arrowRight` — convenience method for the right arrow
- `enter` — convenience method for the ENTER key
- `esc` — convenience method for the ESC key
- `tab` — convenience method for the TAB key
- `ty(c: Int)` — type a character
- `ty(s: String, inEscapeMode: Boolean = false)` — type a string (just like typing at the keyboard)
- `pressAndReleaseKeys(keycodes: Seq[Int], keyPressTime: Int = 200)` — press and then release a sequence of keys

Color-related methods:
- `waitForColor(p: Point, c: Color, maxWaitTime: Long = 10_000)` — wait for a color to appear at a point
- `waitForColorToGoAway(p: Point, c: Color, maxWaitTime: Long = 10_000)` — wait for a color to disappear at a point
- `getPixelColor(x: Int, y: Int)` — get the color at the X/Y coordinates

macOS methods:
- `startApp(app_name: String)` — start an app (also brings an app to the foreground)
- `startIphoneSimulator` — a convenience method to start the iPhone/iOS simulator
- `speak(text: String)` — speak the given string using the Mac text-to-speech ability
- `runAppleScript(cmd: String)` — run the given AppleScript
- `apple(key: Int | Char)` — pass a key to the Apple/Command key
- `activateMenuBar` — activate the Mac menu bar

System-related methods:
- `exec(cmd: String): (ExitCode, Stdout, Stderr)` — run an external command
- `getClipboardText` — get the current text off the system clipboard




