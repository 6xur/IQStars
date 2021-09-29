## Code Review

Reviewed by: Shitong Xiao, u7080308

Reviewing code written by: Robert Xu u7279920

Component: [getViablePieceStrings()](https://gitlab.cecs.anu.edu.au/u4147512/comp1110-ass2-tue09c/-/blob/master/src/comp1110/ass2/IQStars.java)

### Comments 

The overall code quality of the method is quite high:
    1) it successfully addressed the problem (i.e. finding all possible viable piece given a game state string and the next 
   location to be covered)
    2) good comments make the code clear and readable, which is also one of the best features of the code
    3) to check the validity of each piece string, the code decomposed the problem into several sub-parts: is the string 
    well-formed? is the piece already placed on board? does the piece overlap any piece that is already placed?
    does it cover the given location? does it cover all wizards of the same colour? does it cover any wizards of a different colour?
    Then the code implements other methods in both IQStars class and Piece class. The decomposition simplified the problem
    and is another great feature of the code.
    4) all methods and variables used in the code are properly named, following Java code conventions
    5) there is no error found in the code

Further, there are two suggestions made by myself which might be helpful to provide some other ideas for solving the problem:
    1) IQStars.isGameStateValid() method might be useful to find viable pieces. For each possible candidate, 
    we could first add them to the current state, and check whether the new state is valid or not. If the new state is 
    valid, we then add the candidate to the set of viable pieces
    2) we could first find all the colors that has already had a piece on board, so that we would not consider any candidates
    of the same color
    


