/**
 * Created by viola_lv on 5/4/16.
 */
//public class TicTacToe {
/*
 * This program Plays TicTacToe game - using 2D arrays
 * @Written by Sergey Skumatov,
 * CSC116500, chapter 8, exercise 10.
 *
 */



//import java.util.*;
//import static java.awt.Font.BOLD; // for bold text.
    import java.util.Scanner;

    public class TicTacToe
//public class TicTacToe
    {
        // Create String Array
        final static int ROWS = 3;
        final static int COLS = 3;
        final static String [][] board = new String [ROWS][COLS];

        //Constants for X player and O player
        final static String X = "X";
        final static String O = "O";
        //final String X = "X";
        //final String O = "O";


        static int row;
        static int col;
        static int maxMoves = 9;
        static int startChecking = 4;



        public static void main(String[] args)
        {

            // Create a Scanner.
            Scanner keyboard = new Scanner(System.in);

            //reset movet to 0
            int moves = 0;

            //display the welcome text
            System.out.println("Welcome to TicTacToe game");
            System.out.println("  X player goes first");



            //Function that print the board
            printBoard();

            //The main part. While. Using functions. Run until While is done.
            while (moves < maxMoves) //maxMoves 9. the max turns in the game
            {
                //the X palyer start first
                makeMove(keyboard, X); //Function makeMove. Send keyboard input and the player turn.    //get coordinat and put in in a board for X player. If return is TRUE then next step (line)
                moves++; //next turn
                if (moves > startChecking) //startCheking 4. After 4, fifth turn begin check for a winner.       //more then 4 moves sends to check the winner
                {
                    if (checkWinner(X)) //Function to check the winner                                      //check the winner for X player. if checkWinner returns TRUE, then print the winner.
                    {
                        System.out.println("Congratulation! The " + X + " player is a Winner!!!");
                        break;
                    }
                }
                printBoard(); //returns with changes after turns

                //the O player turns
                makeMove(keyboard, O); //Function makeMove. Send keyboard input and the player turn.    //get coordinat and put in in a board for O player
                moves++;
                if (moves > startChecking) //startCheking 4. After 4, fifth turn begin check for a winner.       //more then 4 moves sends to check the winner
                {
                    if (checkWinner(O)) //Function to check the winner                                      //check the winner for O player
                    {
                        System.out.println("Congratulation! The " + O + " player is a Winner!!!");
                        break;
                    }
                }
                printBoard();

            }
        }



        //Print out the tictactoe board
        public static void printBoard()
        {
            System.out.println();
            for (row = 0; row < board.length; row++) //rows
            {
                for (col = 0; col < board[row].length; col++) //columns
                {
                    if (board[row][col] == null) //if board is empty then prints *
                    {
                        System.out.print("*");
                    } else
                    {
                        System.out.print(board[row][col]);
                    }
                    if (col < 2)
                    {
                        System.out.print(" "); //prints the space between *
                    }
                    else
                    {
                        System.out.println();
                    }
                }
            }
            System.out.println();
        }




        //Check if player wins.  Check right after X makes a play
        public static Boolean checkWinner(String play)
        {
            //Declare local variables
            int playInRow = 0;
            int playD1 = 0;
            int playD2 = 0;
            //nested one dimensional array
            int[] playInColumn = new int[board[0].length];   //nested array
            for (row = 0; row < board.length; row++)
            {
                playInRow = 0;//set board to 0
                for (col = 0; col < board[row].length; col++)
                {
                    if (null == board[row][col]) //empty board then continue
                    {
                        continue;
                    }
                    if (board[row][col].equals(play)) //play = X (turn). if player X, then
                    {
                        playInRow++; //next turn
                        playInColumn[col]++; //next turn
                        if (row == col) //0=0 or 1=1 or 2=2, Diagonal. Up left to down right
                        {
                            playD1++; //next turn Diagonal
                        }
                        else if (2 == row + col) //0=2 or 1=1 or 2=0. down left to up right
                        {
                            playD2++; // next turn
                        }
                    }

                }
                if (playInRow == 3) //if (play = X) got to 3 in a row (any row)
                {
                    return true; //then return Boolean TRUE
                }
            }
            if (3 == playD1 || 3 == playD2) //if (play = X) got 3 in any diagonal
            {
                return true; //return true
            }
            for (row = 0; row < playInColumn.length; row++)
            {
                if (playInColumn[row] == 3) //find 3 same letters for the column.
                {
                    return true; //return true
                }
            }
            return false; //return false in all othr cases.
        }




        //makeMove receives the coordinate where palyer makes a move and marks it with the play string X or O
        public static void makeMove(Scanner keyboard, String play)
        {
            //Declare local variables
            int r; //row
            int c; //column
            Boolean rightInput = false;

            //start while
            while(!rightInput) //while all is not false - no return
            {
                r = -1; //set row to -1
                c = -1; //set column to -1
                //System.out.println ("Enter coordinates to play your rows (0-2) and collumns (0-2) "  + (BOLD + play)  + " Player"); //With bold text Play. ?
                System.out.println ("Enter coordinates to play your rows (0-2) and collumns (0-2) "  +  play  + " Player");
                if (keyboard.hasNextInt())   // must be integers. hasNextInt-has to be int
                {
                    r = keyboard.nextInt(); //input for the row
                }
                if (keyboard.hasNextInt()) //must be integers. hasNextInt-has to be int
                {
                    c = keyboard.nextInt(); //input for the column
                }
                else
                {
                    keyboard.nextLine();      // in case if was entered not numbers
                    System.out.println("Both inputs must be between 0 and 2.");
                    //continue;
                }
                // in case if numbers are not in the range from 0 to 2
                if ((r < 0) || (r > 2) || (c < 0) || (c > 2)) {
                    System.out.println("Both inputs must be between 0 and 2.");
                    //continue;
                }
                // make sure the space is not occupied
                else if (board[r][c] != null ) //if space != * (some thing already entered), then print...
                {
                    System.out.println("That location is occupied, enter the different location");
                    //continue;
                }
                else
                {
                    board[r][c] = play;
                    return;
                }
            }
            return;
        }


    }
