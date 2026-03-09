# Unit 8 Programming Project

In this project, you will create a system for reserving seats on a flight, and Connect 4. 

## Part A: `Reservation` Class

An airline flight reservation system stores information about reservations for each seat on a flight. The `Reservation` class stores the name of the passenger and whether the passenger is a frequent flyer. Create a `Reservation` class that includes:

- A constructor that takes the `name` of the passenger (a string) and the `frequentFlyer` status (a boolean) as parameters
- An accessor method named `getPassengerName()` that returns the passenger's name on the `Reservation`
- An accessor method named `isFrequentFlyer()` that returns `true` if this passenger is a frequent flyer and `false` otherwise

## Part B: `Flight` Class

The `Flight` class stores all `Reservation`s for a `Flight`. Each `Flight` represents an airplane with a certain amount of seats. Your `Flight` class should include the following:

- A two-dimensional array of `Reservation`s with empty seats represented by `null`
- A constructor method that takes two `int`s in the following order:
  - the number of rows on the flight
  - the number of seats per row
    - If the number of seats per row is even, you should add a blank seat in the middle to represent an aisle
    - If the number of seats is odd, you should add a blank seat in the middle, towards the "right side" of the plane to represent the aisle
    - Aisles should be represented by a bunch of `Reservation`s that have a passenger name of `"AISLE"`, that are not frequent fliers      
- An accessor method `getFrequentFlyers()` that returns an `ArrayList<String>` with the names of all the frequent flyers on the `Flight`. If there are no frequent flyers, should return an empty `ArrayList`
- A mutator method called `reserveNextAvailableSeat(String name, boolean freqFlyer)` that reserves the next open seat for that passenger
  - Returns `true` if a seat was reserved and `false` otherwise
  - This should not change any current `Reservation`s in the `Flight`
  - The aisle is not a valid seat to place people in
  - Next is defined Left to right, then top to bottom (like how english is read)
- A mutator method called `reserveAdjacentSeats(String passengerName, boolean firstFrequentFlyer, String passengerNameTwo, boolean secondFrequentFlyer)` that reserves the next pair of adjacent seats
  - If at least 1 pair of empty adjacent seats exist, the method reserves the next pair of empty adjacent seats and returns `true`
  - If no pair of empty adjacent seats exists, the method does not make any reservations and returns `false`
    - Two seats are adjacent if they are in the same row and have consecutive column numbers.
    - This should not change any current `Reservation`s in the `Flight`
    - The aisle is not a valid seat to place people in
    - Next is defined Left to right, then top to bottom (like how english is read)
- The method `reserveAisleSeat(String name, boolean freqFlyer)` attempts to reserve an aisle seat for the passenger whose name is taken as a parameter
  - If at least 1 empty aisle seat exists, the method reserves the next aisle seat and returns `true`
    - Method `reserveAisleSeat` does not modify existing `Reservation`s
    - A seat is an aisle seat if it is the index before or after the aisle's index in a row
      - Empty seats are represented by `null`
      - Next is defined left to right, then top to bottom (like how english is read)
- The method `getIsolatedPassengers()` returns an `ArrayList<String>` of the names of all passengers with no adjacent `Reservation`s
  - A  passenger has no adjacent reservations if each seat adjacent to the  passenger’s seat is empty, or is an aisle
  - The object references in the returned list  may appear in any order
  - If there are no passengers with no adjacent reservations, the method returns an empty list
  - Two seats are adjacent if they are in the same row and have consecutive column numbers
  - Empty seats are represented by `null`
- The method `toString()` that returns a `String` to represent the `Reservation`s on the `Flight`
  - The aisle should be represented as `AISLE`
  - Empty seats (or `null` entries) should be represented as `EMPTY`
  - `Reservation`s that have a passenger should be represented by the passenger's name
  - There should be a single space between each seat. There should be no spaces along the edges of the plane
  - Rows should be separated by a new line
- The method `getSeats()` that returns the two-dimensional array of seats. This is used for testing purposes, and normally would not be necessary in your program.'

### Recommended Implementation Order
1. the constructor
2. `toString()`
3. `getSeats()`
4. The rest can be completed in any order

## Connect 4 Rules

TLDR: If you don't know the rules, google it, or watch a youtube play through.

Boards are 6 tall, by 7 wide. Pieces are added from the top, and pieces fall to the lowest available spot in their column, due to gravity. Pieces may not be added to columns that are full. First person to have 4-in-a-row (vertically, horizontally, or diagonally) wins.

The specific value of each space is determined by `final` variables that are included in the class (`RED`/`YELLOW`/`EMPTY`).

## Part C: `Connect4.java`
- `public static void printBoard(int[][] board)` - provided for you. Prints the current board. 
- `public static boolean isFull(int[][] board)` - returns true if all the board spaces have a piece in them. Red pieces are indicated by the static final variable `Connect4.RED` and yellow pieces are indicated by `Connect4.YELLOW`. Empty spaces are indicated by `Connect4.EMPTY`.
- `public static boolean isBoardValid(int[][] board)` - returns true if the board is a valid configuration. A board is valid if there are no floating pieces, and there are either an equal number of yellow/red pieces, or there is 1 more red piece than yellow pieces.
- `public static int getWinner(int[][] board)` - returns `RED_WIN` if only red wins, and `YELLOW_WIN` if only yellow wins. Returns `NO_WINNER` if neither have won. Returns `BOTH_WIN` if both red and yellow have won. Each of these return variables are found at the top of the class.
- `public Connect4()` - constructor that starts the game with an empty board. Initializes anything you might need. 
- `public Connect4(int[][] board)` - constructor that starts the game with a specific board. Used for testing. If the board is not valid, start with an empty board. You need to figure out whose turn is next based on the board state. 
- `public int[][] getBoard()` - returns the current board state.
- `public int getNextPlayer()` - returns `Connect4.RED` or `Connect4.YELLOW` depending on who the next player is for the current board. 
- `public bool dropPiece(int col)` - drops a piece in the appropriate column (0 for leftmost, 6 for rightmost). Returns true if a piece was dropped. Returns false if the piece cannot be dropped there (out of bounds, or in a full column). Returns false if the game is already over (there is a winner). The color of the piece dropped alternates, based on how many pieces there are. Red always goes first, then yellow. 
- `public void play(Scanner sc)` - plays the whole game. Please output the whole board every time someone takes a turn. For this method, it should only expect numbers (0-7) to indicate the row. Numbers that do not work for `dropPiece` should not alter the board state. The overall user experience should be easy to follow. This means, show the state of the board before each time you ask for input, and give confirmation when things work, and if an input is bad, tell the user why its bad. 

**Note**: boards are stored as row-major 2D arrays, as is convention. This means that `board[0][4]` will access the top row, 5th column.

## Implementation Hints

**Implementation Order**: Start from `isFull`, then `isBoardValid`, then `getWinner`. For `getWinner`, do detection for horizontal wins first, then vertical, then the diagonals. Then, work on the constructors, which will have you thinking about attributes of the class. The default constructor, along with the getters should be easy. Then do the parameterized constructor. Then do `dropPiece`, and `play`.

`isFull`: as soon as you find one empty spot, the board is NOT full.

`isBoardValid`: a board is valid if there are no empty spaces under any pieces. A column major traversal is recommended.

`getWinner`: try to do method decomposition. One possible way is to split up the logic for determining what to return, from the logic that finds 4-in-a-rows. You could also split up the logic for each type of 4 in a row. Do whatever makes the problem easier for you to wrap your mind around. Good code doesn't mean least number of lines. Good code means easy to understand.

default constructor & `getBoard` & `getNextPlayer`: easy peasy.

parameterized constructor: you can tell who will go next based on the number of red/yellow pieces that are on the board. 

`dropPiece`: Alternating yellow/red can be done with an instance variable. You might consider making a helper method that checks if a column is full.

`play`: just use the methods you have already made. If you want, you import the `InputHelper` class from previous projects. 

## Extra credit

- AI to play against (the smarter the better, but a basic one works too)
  - if you do this, make a separate play method (ex: `playAI()`)

## Grading Breakdown

- Code compiles & runs without errors: 1 pts
- Formatting/indentation: 2 points
- All code commented: 2 points
- No public methods/attributes besides the ones specified (additional private methods/attributes are encouraged): 1 point
- All code is DRY (Don't repeat yourself): 2 points
- Test cases: 22 points (55 tests, so 0.4 pts each)

Total: 30 points
