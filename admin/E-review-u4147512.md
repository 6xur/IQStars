## Code Review

Reviewed by: <Hoa Nguyen>, <u4147512>

Reviewing code written by: <Shitong Xiao> <u7080308>

Component: <viewer.java>

### Comments 

<
What are the best features of this code?
There are at least three good features of this code. 
First, it delivers a highly functional board viewer, mainly thanks to the "makeControl" method (with the use of hanle() ) and the appropriate use of images in the assets.  
Second, I highly appreciate the coding task to fitting pieces' and wizards' color and orientation to the board. The task is pretty complicated, but the author wrote a neat and crystal clear block of code lines to achieve it. 
Overall, the code is written in a neat and easy-to-read manner.

Is the code well-documented?
The code is well-documented. Every sub-block of code for a sub-task has comments. Each code line is in a length that is reader-friendly, and all of them are correctly indented. 

Is the program decomposition (class and method structure) appropriate?
Yes, it is. I particularly like the use of 'switch' to fit color and orientation – a highly complicated task. This wise use of flow-control has helped fulfill the task efficiently and neatly.  

Does it follow Java code conventions (for example, are methods and variables properly named), and is the style consistent throughout?
The code follows all code conventions. That is, names of constants are all in caplocks (e.g. VIEWER_HEIGHT), method names start with verbs (e.g. makeControls). In line 54, “VIEWER_HEIGHT*2/3”, I may use a constant instead of 2/3, but this might not be necessary as “2/3” appears only one time in this part of the code. 

If you suspect an error in the code, suggest a particular situation in which the program will not function correctly.
No, there should be no errors because the code runs well. The board, pieces and wizards are displayed as expected. 



>


