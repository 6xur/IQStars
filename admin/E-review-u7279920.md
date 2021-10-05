## Code Review

Reviewed by: Robert Xu, u7279920

Reviewing code written by: Hoa Nguyen u4147512

Component: [isGameStateStringWellFormed](https://gitlab.cecs.anu.edu.au/u4147512/comp1110-ass2-tue09c/-/blob/master/src/comp1110/ass2/IQStars.java#L53-117)

### Comments

The best feature of this method is the use of filter() inside a for-loop at line 72 to line 75, where filter() is used to obtain only the colours that equal c. Then, it counts the number of elements in this stream. Since each piece colour can appear at most once in a valid state string; if the returned count is larger than one, we know that there're duplicates and therefore the program returns false. This section of the code is very concise and clearly manifests the logic behind.

Another great feature is how Hoa checked for the order of the pieces. For instance, starting on line 78 to line 85, each colour is "assigned" an unique value ranging from 1 to 7 in the same order that the colours should appear. A char array named sPArr is then used to store the result from  converting the colours to their assigned numerical values. Then, the order of piece colours is checked by cheking if elements of sPArr are in order. Again, the logic behind this section of the code can be easily interpreted.

There're two comments for this method and they're sufficient for clearly informing anyone reading the code; this is because the code logic is easy to see and follow along. I would suggest adding separate comments for checking the order of the pieces and checking the colour order/location order of the wizards instead of just having one comment for both.

The method structure is arranged appropriately, with the checks for piece validity and wizard validity happening at different places, and one after the other. Furthermore, each check/sub part occurs in the same order as they're written in the original Java doc.

The code style is consistent throughout however I find the variable naming slightly unintuitive. For example, on line 55 and 56, the piece string is named "sP" while the wizard string is named "sW". As someone who is familiar with the context of this code, I can immediately recognize that these abbreviations stand for stringPieces and stringWizards. However, I'd suggest to avoid abbreviations for better clarity.

There isn't any error in the code. In summary, the code is concise and very easy-to-understand.
