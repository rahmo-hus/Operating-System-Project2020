# OOS Project - Faculty of Electrical Engineering, 2020
## Application specification 
A simple shell application that allows the user to interact with the file system. This application works exclusively on Linux operating systems and supports the following commands:

* <b>login</b> -  logs the user into the application and assigns the appropriate directory. Only the logged-in user can enter all subsequent commands.

* <b>where</b> - displays the user's current location on the file system.

* <b>go</b> <i>putanja</i> -positions the user on the path specified as an argument.

* <b>create</b> [-d] <i>path</i> - creates a file at the specified path, and if the -d parameter is specified, creates a folder.

* <b>list</b> [<i>path</i>] - formats and displays the directory tree specified as an argument (current directory if no argument is specified).

* <b>print</b> <i>file</i> - prints the contents of a text file to the console. If a non-text file is specified, an error is displayed.

* <b>find</b> <i>"text" file</i> -  returns the line number where the specified text is found in the file (first occurrence only). The text is always single-line.

* <b>findDat</b> <i>file path</i> - searches for a file in the specified path and returns its location as output.

* <b>logout</b> - logs the user out of the application and deletes the assigned folder.
