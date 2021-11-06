# TODO List

- `ctrl` key method
- `alt/opt` key method
- `shift` key method
- methods for function keys
- arrow keys
- create 1+ methods to access a browser’s DOM code
- `waitForImage` method
- `killall` method


## Notes

This is one way to handle 'option' and 'command' keys on macOS.

```applescript
tell application "System Events"
    keystroke "g" using {option down, command down}
    keystroke pageNumber
    delay 1
    keystroke return
end tell
```
