package inverted.holdings.code.p005;
import inverted.holdings.code.p006.jout;
import java.util.Scanner;

public class ticTacToe
{
	private static jout j = new jout(); 
	
	public ticTacToe()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)
	{
		boolean notWon = true;
		boolean invalid;
		int chosenSquare = -1;
		Scanner in = new Scanner(System.in);
		String playerInput = "";
		ticTacToePlayer playerX, playerO;
		playerX = new ticTacToePlayer('X');
		playerO = new ticTacToePlayer('O');

		gameBoard myNewBoard = new gameBoard();
		myNewBoard.setBoard(3,3);
		myNewBoard.draw();
		
		ticTacToeBoard.draw(3, 3);
		
		while(notWon)
		{
			j.outln("Choose a square to place an X! (1-9) ");
			playerInput = in.nextLine();
			chosenSquare = Integer.parseInt(playerInput);
			
			do {
				try { playerX.claim(chosenSquare, playerO); invalid = false; }
				catch (Exception e) { invalid = true; } 
			} while (invalid);
			
			ticTacToeBoard.redraw(playerX, playerO);
			
			if (notWon = !playerX.wins()) { break; }

			j.outln("Choose a square to place an O! (1-9) ");
			playerInput = in.nextLine();
			chosenSquare = Integer.parseInt(playerInput);
			
			do {
				try { playerO.claim(chosenSquare, playerX); }
				catch (Exception e) { invalid = true; } 
			} while (invalid);
			
			ticTacToeBoard.redraw(playerX, playerO);
			
			notWon = (playerO.wins());
		}
	}
	
}
