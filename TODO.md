# TODO List

- Add ScalaTest and unit tests for the `exec` method
- `ctrl` key method
- `alt/opt` key method
- `shift` key method
- methods for function keys
- arrow key methods
- create 1+ methods to access a browser’s DOM code
- `waitForImage` method
- `waitForImageToGoAway` method
- `killall` method


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
