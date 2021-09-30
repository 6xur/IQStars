## Code Review

Reviewed by: Hoa Nguyen,  u4147512 

Reviewing code written by: Shitong Xiao,  u7080308 

Component:  https://gitlab.cecs.anu.edu.au/u4147512/comp1110-ass2-tue09c/-/blob/master/src/comp1110/ass2/gui/Viewer.java#L1-262 

### Comments 

There are at least two good features of this viewer class. First, it delivers a highly functional board, mainly thanks to the "makeControl" method (with the use of hanle() ) (lines 229 - 247), and the appropriate use of images in the assets (lines 57, 81, 204).  Second, the code to fit pieces' and wizards' color and orientation to the board is very clear. 

The code is well-documented throughout. Every code sub-block has comments. Each code line is in a length that is reader-friendly, and all of them are correctly indented. 

The program decomposition is appropriate. I particularly like the use of 'switch' to fit color and orientation (lines 98 - 193) – a highly complicated task. This use of flow-control has helped fulfill the task efficiently.  

The code follows all code conventions and it is written in a consistent manner. That is, names of constants are all in caplocks (e.g. VIEWER_HEIGHT), method names start with verbs (e.g. makeControls). In line 54, “VIEWER_HEIGHT*2/3”, I may use a constant instead of 2/3, but this might not be necessary as “2/3” appears only one time in the whole class. 

The code has no errors. It runs well. The board, pieces and wizards are displayed as expected. 

Overall, I enjoy reading this code, from which I have learnt a lot. Good job, Shirley! 






