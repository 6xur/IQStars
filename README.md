# COMP1110 Assignment 2

## Academic Honesty and Integrity

Honesty and integrity are of utmost importance. These goals are *not* at odds with being resourceful and working collaboratively. 
You *should* be resourceful, you should collaborate within your team, and you should discuss the assignment and other aspects of the course with others taking the class. 
However, *you must never misrepresent the work of others as your own*. If you have taken ideas from elsewhere or used code sourced from elsewhere, you must say so with *utmost clarity*.
At each stage of the assignment you will be asked to submit a statement of originality, either as a group or as individuals.
This statement is the place for you to declare which ideas or code contained in your submission were sourced from elsewhere.

Please read the ANU's [official position](http://academichonesty.anu.edu.au/) on academic honesty. If you have any questions, please ask me.

Carefully review the statements of originality in the [admin](admin) folder which you must complete at each stage.  Edit the relevant statement and update it as you complete each stage of the assignment, ensuring that when you complete each stage, a truthful statement is committed and pushed to your repo.

## Purpose

In this assignment you will *work as a group* to master a number of major themes of this course, 
including software design and implementation, group work, using development tools such as Git and IntelliJ, 
and using JavaFX to build a user interface.  **Above all, this assignment will emphasize group work**; 
while you will receive an individual mark for your work based on your contributions to the assignment,
**you can only succeed if all members contribute to your group's success**.

## Assignment Deliverables

The assignment is worth 30% of your total assessment, and it will be marked out of 30. 
So each mark in the assignment corresponds to a mark in your final assessment for the course. 
Note that for some stages of the assignment you will get a _group_ mark, and for others you will be _individually_ marked. 
The mark breakdown and the due dates are described on the [deliverables](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/) page.

Your work will be marked via your tutor accessing git, so it is essential that you carefully follow instructions for setting up and maintaining your group repository. 
At each deadline you will be marked according to whatever is committed to your repository at the time of the deadline. 
You will be assessed on how effectively you use git as a development tool.

## Problem Description

The assignment involves implementing a board game called [IQ Stars](https://www.smartgames.eu/uk/one-player-games/iq-stars)
made by the games developer [SmartGames](https://www.smartgames.eu/uk) in Java.

<img src="assets/iqstars.jpg" width="400">

#### Objective 

The game is a puzzle. The object of the game is to place seven plastic pieces on a board so that the pieces
fill the board perfectly, with no overlaps and no gaps. The player starts by selecting a
[challenge](#challenges) which will have some number of pieces already placed. Their task is to place all 
remaining pieces.

A completed game:

<img src="assets/completedGame.png" width="400">

To help you visualize the game, we have provided a
[paper](assets/paperGame.pdf) version, which you can cut out.

#### Challenges

A game starts by choosing a challenge which specifies in what positions certain
puzzle pieces must be placed. The puzzle pieces that are pre-placed by the challenge
at the start of the game cannot be moved by the player until the game has ended.

Here is the starting challenge for the game above:

<img src="assets/challenge.png" width="400">

In the challenge above, the _red_, _yellow_, _blue_, and _pink_ pieces 
are placed as part of the challenge. They can't be moved. You can see that much of the 
board is unfilled. The player's task is to place the remaining three pieces in such a way that 
all of the board is covered perfectly, with no gaps and no overlaps.

Some challenges are much easier to solve than others.  When you think about the game,
you may wish to reflect on what makes some challenges so much easier than others.
One of the assignment tasks is to create interesting challenges. 
Note that as a general rule for puzzles, the more constrained the player is the fewer options they have, and
consequently the solution to the challenge is simpler.

The game comes with five difficulty levels: _starter_, _junior_, _expert_, _master_, and _wizard_.
There are 24 challenges at each level, for a total of 120 prescribed challenges.
These challenges are provided for you in the
[Games class](https://gitlab.cecs.anu.edu.au/comp1110/comp1110-ass2/blob/master/src/comp1110/ass2/Games.java).

#### Solutions

Each challenge has just one solution.

The following sequence shows one possible progression of a solution to the game
above (note that the order in which the pieces are placed is not important; this
is just one possible sequence of moves).

<img src="assets/challenge.png" width="200">
<img src="assets/step1.png" width="200">
<img src="assets/step2.png" width="200">
<img src="assets/completedGame.png" width="200">

#### Wizard Challenges

The wizard level works slightly differently to the other levels.
Challenges in this level show only the position and colour of single stars (not full pieces).
These single stars are referred to as _wizards_.
Players must figure out the orientations of the pieces that the wizards are a part of.

Here is an example of a wizard challenge:

<img src="assets/wizardChallenge.png" width="400">

This challenge has three wizards; one red, one orange and one indigo.
Here is one possible progression of a solution for this challenge:

<img src="assets/wizardChallenge.png" width="200">
<img src="assets/wStep1.png" width="200">
<img src="assets/wStep2.png" width="200">
<img src="assets/wStep3.png" width="200">
<img src="assets/wStep4.png" width="200">
<img src="assets/wStep5.png" width="200">
<img src="assets/wStep6.png" width="200">
<img src="assets/wCompletedGame.png" width="200">

#### Board

The game is played on a board comprised of 26 **locations** arranged in a hexagonal grid. 
The grid has four rows. 
The first and third rows have seven columns, and the second and fourth rows have six columns.

<img src="assets/blankBoardNumbered.png" width="400">

In the real-world game, each location consists of a star-shaped indent into which a piece may fit. 
We refer to the placement of pieces in terms of the location of the leftmost star in the top row.
In our game, locations are encoded as two digits, the first one identifying the column from `0` to `6`, followed by another identifying the row from `0` to `3`.
  
The example illustrated below shows how the location of pieces on the board is encoded.

<img src="assets/challengeNumbered.png" width="400">

For example, in the game above, the _red_ piece is in position `0,1` (column `0`, row `1`),
the _blue_ piece is in position `2,0` (column `2`, row `0`),
the _yellow_ piece is in position `4,0` (column `4`, row `0`), 
and the _pink_ piece is in position `5,1` (column `5`, row `1`).

#### Pieces

The game comprises **7 playing shapes**, each of a distinct color 
(_red_, _orange_, _yellow_, _green_, _blue_, _indigo_ and _pink_).

Each piece is made up of either _three_ (_orange_, _indigo_) or _four_ (_red_, _yellow_, _green_, _blue_, _pink_) stars connected in some pattern.

The diagram below illustrates all seven pieces and their six possible orientations, labelled from `0` to `5`.
When we encode pieces in this assignment, we use lower case letters (e.g. `b`) to describe the colour of the piece.

<img src="assets/allPieces.png" width="600" class="center">

Because the red piece and the indigo piece are symmetric, some of their rotations are identical.
To make sure that placement descriptions are consistent, we will always choose the first of these identical rotations.
For example, rather than having the rotation of the indigo piece as `3`, it will be `0`.

#### Legal Piece Placements

For a piece placement to be valid, the following must be true:

* All of the stars in the piece must be placed on board locations
 (**no part of a piece may be off the board**).
* All of the stars in the piece must be placed on vacant board
  locations (**pieces may not overlap**).
* If there is a wizard of the same colour as the piece on the board, the piece must cover that wizard.
* The piece may not cover a wizard of a different colour.

#### Encoding Game States

Game states are encoded as strings. 
Your game will need to be able to initialize itself using these strings and some of
your tasks relate directly to these strings.

##### Game State Strings

A game state string consists of a _piece placement_ comprising between zero and seven (inclusive) 
piece strings, a 'W', and a _wizard placement_ comprising zero or more wizard strings.  

For the sample game below, the game state string is `"r252y200g540Wr43o42i33"`.
It consists of piece strings for the _red_ (`r252`), _yellow_ (`y200`) and _green_ (`g540`) pieces,
and wizard strings for the _red_ (`r43`), _orange_ (`o42`) and _indigo_ (`i33`) pieces.
These encodings are explained further below.

<img src="assets/gameStateExample.png" width="400">

##### Piece Strings

A piece string consists of four characters describing the location and orientation of one particular piece on the board:

* The first character identifies **which of the seven pieces** is being placed. 
  The table above shows the letters used, with each letter corresponding to a colour.
* The second character identifies **which orientation** the piece is in 
  (an integer from `0` to `5`, or `0` to `2` in the case of the _red_ and _indigo_ pieces).
* The third character identifies **which column** the top left of the piece is in 
  (columns are labelled `0` to `6` in the `0th` and `2nd` rows, and `0` to `5` in the `1st` and `3rd` rows).
* The fourth character identifies **which row** the top left star of the piece is
  in (rows are labelled `0` to `3`).

The table above shows the first and second characters for each of the
pieces in each of their orientations.

In the game state string above, `r252` describes the position of the _red_ piece, which is placed in orientation `2` at location (`5`, `2`),
`y200` describes the position of the _yellow_ piece, which is placed in orientation `2` at location (`0`, `0`) and 
`g540` describes the position of the _green_ piece, which is placed in orientation `5` at location (`4`, `0`).

##### Piece Placements

A piece placement consists of between zero and seven (inclusive) [piece strings](#piece-strings).
The piece placement may not include any piece twice. 
A completed game must include seven piece strings. 
Pieces are always listed in rainbow order (the ordering of the rows in the figure above 
(_red_, _orange_, _yellow_, _green_, _blue_, _indigo_, _pink_)).
Each piece is encoded using four characters as above.
For example, the piece placement of the completed game below is `"r252o541y200g540b312i121p420"`.
Note that the piece placement is **ordered** (the _red_ piece (`r`) first, and _pink_ piece (`p`) last, following the ordering in the diagram above).
Correct ordering is a requirement for valid piece placements.

<img src="assets/wCompletedGame2.png" width="400">

##### Wizard Strings

A wizard string consists of three characters describing the location of one particular wizard on the board:

* The first character identifies **which of the seven colours** the wizard is.
  The table above shows the letters used, with each letter corresponding to a colour.
* The second character identifies **which column** the wizard is in
  (columns are labelled `0` to `6` in the `0th` and `2nd` rows, and `0` to `5` in the `1st` and `3rd` rows).
* The third character identifies **which row** the wizard is in 
  (rows are labelled `0` to `3`).

In the game state string above, `r43` describes the position of the _red_ wizard, which is placed at location (`4`, `3`).
Note that this wizard is covered by a piece in the above game, so it is not visible in the picture.
`o42` describes the position of the _orange_ wizard, which is placed at location (`4`, `2`) and
`i33` describes the position of the _indigo_ wizard, which is placed at location (`3`, `3`).

##### Wizard Placements

A wizard placement consists of some number of [wizard strings](#wizard-strings).
Wizards are always listed in rainbow order (the ordering of the rows in the figure above
(_red_, _orange_, _yellow_, _green_, _blue_, _indigo_, _pink_)).
The wizard placement may include the same colour multiple times, as long as the wizards aren't placed on top of each other.
In this case, the wizard strings are ordered first by row and then by column in ascending order.
Each wizard is encoded using three characters as above.
For example, the wizard placement of the game below is `"g11g32i00p43"`.
Note that the wizard placement is **ordered** (the _green_ piece (`g`) first, and _pink_ piece (`p`) last, following the ordering in the diagram above).
There are two green wizards, so the one in the higher row is listed first.
Correct ordering is a requirement for valid wizard placements.

<img src="assets/wExample.png" width="400">

## Legal and Ethical Issues

First, as with any work you do, you must abide by the principles of
[honesty and integrity](http://academichonesty.anu.edu.au). You are
expected to demonstrate honesty and integrity in everything you do.

In addition to those ground rules, you are to follow the rules one
would normally be subject to in a commercial setting. In particular,
you may make use of the works of others under two fundamental
conditions: a) your use of their work must be clearly acknowledged,
and b) your use of their work must be legal (for example, consistent
with any copyright and licensing that applies to the given
material). *Please understand that violation of these rules is a very
serious offence.* However, as long as you abide by these rules, you
are explicitly invited to conduct research and make use of a variety
of sources. You are also given an explicit means with which to declare
your use of other sources (via originality statements you must
complete). It is important to realize that you will be assessed on the
basis of your original contributions to the project. While you won't
be penalized for correctly attributed use of others' ideas, the work
of others will not be considered as part of your
contribution. Therefore, these rules allow you to copy another
student's work entirely if: a) they gave you permission to do so, and
b) you acknowledged that you had done so. Notice, however, that if you
were to do this you would have no original contribution and so would
receive no marks for the assignment (but you would not have broken any
rules either).

## Evaluation Criteria

It is essential that you refer to the
[deliverables page](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/)
to check that you understand each of the deadlines and what is
required.  Your assignment will be marked via tests run through git's
continuous integration (CI) framework, so all submittable materials
will need to be in git and in the *correct* locations, as prescribed
by the
[deliverables page](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/).


**The mark breakdown is described on the
[deliverables](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/)
page.**

### Part One

In the first part of the assignment you will:
* Set up your assignment (Task #1).
* Create a design skeleton (Task #2).
* Implement parts of the text interface to the game (Tasks #3 and #4).
* Implement a simple viewer that allows you to visualize game states (Task #5).
* Implement a check to determine whether a game state is valid (Task #6).

The criteria for the [completion of part one](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2C) is as follows:

<a name="p"></a>
**Pass**
* Tasks #1, #2, #3 and #4

<a name="cr"></a>
**Credit**
* Task #5 *(in addition to all tasks required for Pass)*

<a name="d"></a>
**Distinction**
* Task #6 *(in addition to all tasks required for Credit)*

### Part Two

Create a fully working game, using JavaFX to implement a playable
graphical version of the game in a 933x700 window.

Notice that aside from the window size, the details of exactly how the
game looks etc, are **intentionally** left up to you.  The diagrams
above are for illustration purposes only, although you are welcome to
use all of the resources provided in this repo, including the images
in the assets folder of each of the seven shapes.

The only **firm** requirements are that:

* you use Java 15 and JavaFX,
* the game respects the specification of the game given here,
* the game be easy to play,
* it runs in a 933x700 window, and
* that it is executable on a standard lab machine from a jar file called `game.jar`,

Your game must successfully run from `game.jar` from within another
user's (i.e.  your tutor's) account on a standard lab machine (in
other words, your game must not depend on features not self-contained
within that jar file and the Java 15 runtime).

An indicative grade level for each task for the [completion of part two](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2F) is as follows:

<a name="2p"></a>
**Pass**
* Correctly implements all of the <b>Part One</b> criteria.
* Appropriate use of git (as demonstrated by the history of your repo).
* Completion of Task #7.
* Executable on a standard lab computer from a runnable jar file,
  game.jar, which resides in the root level of your group repo.

<a name="2c"></a>
**Credit**
* _All of the Pass-level criteria, plus the following..._
* Task #8.

<a name="2d"></a>
**Distinction**
* _All of the Credit-level criteria, plus the following..._
* Tasks #9 and #10.

<a name="2hd"></a>
**High Distinction**
* _All of the Distinction-level criteria, plus the following..._
* Tasks #11 and #12.
