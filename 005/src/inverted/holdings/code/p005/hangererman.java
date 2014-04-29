package inverted.holdings.code.p005;

import java.util.Random;
import java.util.Scanner;

public class hangererman
{   
    //  Method Stubs:
    public static void printASCIIman(int answerLength, char[] answer, char[] badGuesses) 
        // printASCIIman prints out the pieces of an ASCII man
        //  it uses the input parameter to determine how much of the hanging man to print
        {
            int guessedWrong;
            
            System.out.print("\n\nWrong guesses: ");
            for (guessedWrong = 0; badGuesses[guessedWrong] != '_'; guessedWrong++)
                System.out.print(badGuesses[guessedWrong] + " ");
            System.out.println("\n");
            
            if(0 < guessedWrong)
                System.out.println("    \n  ( )  ");
            else
                System.out.println();
            
            if(2 == guessedWrong)
                System.out.println("   +   ");
            else if (3 == guessedWrong)
                System.out.println(" --+   ");
            else if (3 < guessedWrong)
                System.out.println(" --+-- ");
            else
                System.out.println();
            
            if(guessedWrong == 5)
                System.out.println("  /  ");
            else if (guessedWrong > 5)
            {
                System.out.println("  / \\  "); 
                System.out.println("\n\nSorry.  I'm afraid you've been hung.");
            }
            else
                System.out.println();
            
            for (int i = 0; i < answerLength; i++)
                jout(" " + answer[i]);
            jout("\n");
        }
    public static int generateWord(char[] myWord) {
        Random rand = new Random();
        int length = 0;
        int puzzle = rand.nextInt(3);
        if(puzzle == 0){
            myWord[0] = 't';
            myWord[1] = 'o';
            myWord[2] = 'o';
            length = 3;
        } else if(puzzle == 1){
            myWord[0] = 'b';
            myWord[1] = 'a';
            myWord[2] = 'd';
            length = 3;
        } else if(puzzle == 2){
            myWord[0] = 'a';
            myWord[1] = 'n';
            myWord[2] = 't';
            myWord[3] = 'i';
            myWord[4] = 'd';
            myWord[5] = 'i';
            myWord[6] = 's';
            myWord[7] = 'e';
            myWord[8] = 's';
            myWord[9] = 't';
            myWord[10] = 'a';
            myWord[11] = 'b';
            myWord[12] = 'l';
            myWord[13] = 'i';
            myWord[14] = 's';
            myWord[15] = 'h';
            myWord[16] = 'm';
            myWord[17] = 'e';
            myWord[18] = 'n';
            myWord[19] = 't';
            length = 20;
        }
        return length;
    }
        // generateWord populates the input character array with a word taken from a list of
        //  random words and returns the length of the word chosen
    public static void initializeWUnderscores(int len, char[] answer, char[] badGuesses) {
        for(int i=0; i<badGuesses.length; ++i)
            badGuesses[i] = '_';
        for(int i=0; i<len; ++i)
            answer[i] = '_';
    }
        // initializeAnswerWUnderscores just initializes the input char[] with underscores from
        //  element zero to element len - 1
    public static boolean evaluateLetter(char[] answer, char[] badGuesses, char[] puzzle, char letter) {
        boolean correct = letterInAnswer(letter, answer, puzzle);
        if(!correct) {
            int i=0;
            while(badGuesses[i] != '_') i++;
            badGuesses[i] = letter;
        }
        return correct;
    } // break this up
        // evaluateLetter should call the letterInAnswer function!
        //  if letterInAnswer returns false, then it should find the next empty char in badGuesses
        //  and populate that character with the guess
    public static boolean letterInAnswer(char letter, char[] answer, char[] puzzle) {
        boolean correct = false;
        for(int i=0; i<puzzle.length; ++i)
            if(puzzle[i] == letter)
                correct = true;
        
        if(correct) {
            for(int i=0; i<puzzle.length; ++i) {
                if(puzzle[i] == letter)
                    answer[i] = letter;
            }
        }
        
        return correct;
    }
        // letterInAnswer should take the input letter and see if it is in puzzle;
        //  if it is, then it should change every corresponding letter in answer
    public static void jout(String thingieToPrint) {
        System.out.print(thingieToPrint);
    }
    
    public static boolean solvedPuzzle(char[] answer) {
        int i = 0;
        boolean solved = true;
        while(answer[i] != 0){
            if(answer[i] == '_') {
                solved = false;
            }
            i++;
        }
        return solved;
    }
    
    public static void main(String[] args)
    {
        char[] ans, bguesses, puzzle;
        int badcount = 0, goodcount = 0, wordlen = 0;
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
            else goodcount++;
            
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


/*
package inverted.holdings.code.p005;

import java.util.Random;
import java.util.Scanner;

public class hangererman
{	
	public static void main(String[] args)
	{
		String[] answer = new String[15];
		String[] guesses = new String[26];
		boolean[] guessedPosition = new boolean[15];
		
		Random rng = new Random();
		Scanner in = new Scanner(System.in);
		int x = rng.nextInt(3), lCount = 0, guessedWrong = 0;
		boolean solved = false, dead = false, guessedRight = false;
		
		if ( 1 > x )
		{
			answer[0] = "s";
			answer[1]  = answer[3] = "e";
			answer[2] = "r";
			answer[4] = "i";
			answer[5] = "n";
			answer[6] = "";
		}
		else if ( 2 > x )
		{
			answer[0] = "f";
			answer[1] = "r";
			answer[2] = "a";
			answer[3] = "u";
			answer[4] = "d";
			answer[5] = "";
		}
		else if ( 3 > x )
		{
			answer[0] = "p";
			answer[1] = answer[6] = "s";
			answer[2] = "u";
			answer[3] = answer[9] = answer[12] = "e";
			answer[4] = "d";
			answer[5] = "o";
			answer[7] = answer[11] = "c";
			answer[8] = "i";
			answer[10] = "n";
			answer[13] = "";
		}
		
		for (int i = 0; !answer[i].equals(""); i++) 
		{ guessedPosition[i] = false; lCount++; }
		
		System.out.println("Welcome to Hangererman.");
		
		while ( !solved && !dead )
		{		
			guessedRight = false;
			
			System.out.print("\n\nWrong guesses: ");
			for (int i = 0; i < guessedWrong; i++)
				System.out.print(guesses[i] + " ");
			System.out.println("\n");
			
			if(0 < guessedWrong)
				System.out.println("    \n  ( )  ");
			else
				System.out.println();
			
			if(2 == guessedWrong)
				System.out.println("   +   ");
			else if (3 == guessedWrong)
				System.out.println(" --+   ");
			else if (3 < guessedWrong)
				System.out.println(" --+-- ");
			else
				System.out.println();
			
			if(guessedWrong == 5)
				System.out.println("  /  ");
			else if (guessedWrong > 5)
			{
				System.out.println("  / \\  "); 
				dead = true; 
				System.out.println("\n\nSorry.  I'm afraid you've been hung.");
				break; 
			}
			else
				System.out.println();
			
			System.out.println();
			
			for (int i = 0; !answer[i].equals(""); i++) 
			{ 
				if(guessedPosition[i])
					System.out.print(answer[i]);
				else
					System.out.print("_"); 
			}
			
			System.out.println();
			
			System.out.print("Letter to guess (no capitals!): ");
			String letter = in.nextLine();
			
			for (int i = 0; i < lCount; i++)
				if (letter.equals(answer[i]))
				{
					guessedPosition[i] = true;
					guessedRight = true;
				}
			
			if(guessedRight)
			{
				solved = true;
				
				for (int i = 0; i < lCount; i++)
					if (false == guessedPosition[i])
						solved = false;
				
				if(solved)
					System.out.println("Hooray!  You win!");
			}
			else
			{
				guesses[guessedWrong] = letter;
				guessedWrong++;
			}
		}
	}	
}

/*
 * int x, y;
 * int[] zArray = new int[5];
 * boolean[] myB = new boolean[5];
 * 
 * x 		[????]
 * y 		[????]
 * zArray	[????][????][????][????][????]
 * 
 * x = 5;
 * y = 5;
 * zArray[0] = 5;
 * zArray[1] = 7;
 * zArray[2...
 * 
 * if (zArray[0] > 1)
 * 		myB[0] = true;
 * else
 * 		myB[0] = false;
 * if (zArray[1] > 1)
 * 		myB[1] = true;
 * else
 * 		myB[1] = false;
 *  	.....
 *  ....
 * 
 * for (int counter = 0; counter < 5; counter++)
 * 	{
 * 		if (zArray[counter] == userGuess)
 * 			myB[counter] = true;
 * 		else
 * 			myB[counter] = false;
 * }
 * 
 * x 		[0005]
 * y 		[0005]
 * zArray	[0005][  7 ][-13 ][ 0  ][200 ]
 * myB		[true][true][false][false][true]
 * 
 * 
 * zArray[3] > 1;
 * myB[3];
 * 
 */