package inverted.holdings.code.p005;

import java.util.Random;
import java.util.Scanner;

public class hangman
{   
    //  Method Stubs:
    public static void printASCIIman(int answerLength, char[] answer, char[] badGuesses) { }
        // printASCIIman prints out the pieces of an ASCII man
        //  it uses the input parameter to determine how much of the hanging man to print
    public static int generateWord(char[] myWord) { return 0; }
        // generateWord populates the input character array with a word taken from a list of
        //  random words and returns the length of the word chosen
    public static void initializeWUnderscores(int len, char[] answer, char[] badGuesses) { }
        // initializeAnswerWUnderscores just initializes the input char[] with underscores from
        //  element zero to element len - 1
    public static boolean evaluateLetter(char[] answer, char[] badGuesses, char[] puzzle, char letter) { return false; }
        // evaluateLetter should call the letterInAnswer function!
        //  if letterInAnswer returns false, then it should find the next empty char in badGuesses
        //  and populate that character with the guess
    public static boolean letterInAnswer(char letter, char[] answer, char[] puzzle) { return false; }
        // letterInAnswer should take the input letter and see if it is in puzzle;
        //  if it is, then it should change every corresponding letter in answer
    public static void jout(String thingieToPrint) { }
        // jout will print a single string (and NOT a newline at the end)
    public static boolean solvedPuzzle(char[] answer) { return false; }
    // solvedPuzzle evaluates whether or not the answer is completely solved
    //	returns true if it is solved, and false if it is not 
    
    public static void main(String[] args)
    {
        char[] ans, bguesses, puzzle;
        int badcount = 0, wordlen = 0;
        boolean inPuzzle = false;
        ans = new char[25];
        bguesses = new char[25];
        puzzle = new char[25];
        boolean solved = false, dead = false;
        
        wordlen = generateWord(puzzle);
        initializeWUnderscores(wordlen, ans, bguesses);
        
        while(!solved && !dead)
        {
            printASCIIman(wordlen, ans, bguesses);
            char letter = getNextChar();
            inPuzzle = evaluateLetter(ans, bguesses, puzzle, letter);
            
            if (!inPuzzle) badcount++;
            
            solved = solvedPuzzle(ans);
            dead = (6 <= badcount);
        }
        
        printASCIIman(wordlen, ans, bguesses);
        if (solved) jout("Congrats!  You win!"); else jout("DIE MOTHERFU[<<[{{(<<bleep>>>}]>>]]");   
    }    
    
    public static char getNextChar() {
        final Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input.charAt(0);
    };   
}