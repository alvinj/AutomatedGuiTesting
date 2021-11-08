# TODO List

- `ctrl` key method
    - can be done currently with `pressAndReleaseKeys`
- `alt/opt` key method
    - can be done currently with `pressAndReleaseKeys`
- `shift` key method
    - can be done currently with `pressAndReleaseKeys`
- methods for function keys
- create 1+ methods to access a browser’s DOM code
- changes/updates to the `findSmallImageInLargeImage` code:
    - `waitForImage` method
    - `waitForImageToGoAway` method
- eventually use a much better image recognition tool
- `killall` method


## Done (finished recently)

- arrow key methods
- basic image-recognition (`findSmallImageInLargeImage` method)
- add ScalaTest and unit tests for the `exec` method


## Notes

Note that this is one way to handle 'option' and 'command' keys on macOS.

```applescript
tell application "System Events"
    keystroke "g" using {option down, command down}
    keystroke pageNumber
    delay 1
    keystroke return
end tell
```



