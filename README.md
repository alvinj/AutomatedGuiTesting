# Automated GUI Testing Software

This is the Automated GUI Testing (AGT) software that I originally
released in 2010. The code is released under the license included
in the _LICENSE.txt_ file.

I just made a few updates to the code in early 2019 to account for
changes to the Ruby/JRuby API and have it working again, at least 
to the point that a couple of my old scripts are working again.

Please be aware that this software was released rather quickly in 2010,
so it’s not the most well-documented thing I’ve ever created and released.



## Documentation

There is some documentation under the _scripts/docs_ folder.

This is a quick list of the methods that are built into the AGT software:

- click (x, y, delay=500, do_animation=true)
- c (s)
- comment (s)
- doubleclick (x, y)
- done
- echo (data)
- start_timer
- stop_timer
- get_elapsed_time
- move_mouse (x, y)
- move_mouse_animated (x,y)
- type_keys (data)
- type (data)
- wait (time_ms)
- wait_for_xycolor(*data)
- wait_for_xycolor_to_go_away(*data)
- foreground (app_name)
- apple (the_key)
- ctrl (key_code)
- tab
- enter
- esc
- type_keycode (char_as_int)

You can find most or all of those methods in the
_AgileGuiTesting.rgb_ file.

For more details, please see the _scripts/doc_ folder for Ruby rdoc 
documentation. In particular, you'll want to look at the documentation 
for the _AgileGuiTesting.rgb_ file, as that’s the main source code file.

Also, please see scripts like these for examples of how to use AGT:

- 1OpenChrome.rb (i just wrote this script in 2019 to get things working again)
- 3ChromeOpenDDAndOma.rb
- 4TypeIntoTextEdit.rb



## Running a script

To run a script, move into the _scripts_ directory and then
run a command like this:

````
jruby -I. 1OpenChrome.rb
````



## Needs some cleanup

Also please note that this project needs some cleanup.
There were several “scripts*” directories in the project,
and rather than delete them all I thought I’d keep them 
and clean them up when I have some free time.
















