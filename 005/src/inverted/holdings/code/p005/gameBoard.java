package inverted.holdings.code.p005;

import inverted.holdings.code.p006.jout;

public class gameBoard
{
	public static jout j = new jout();
	private String board[][];
	private int rows, columns;
	
	public void setBoard(int xCount, int yCount) 
	{ 
		rows = xCount;
		columns = yCount;
		board = new String[rows][columns]; 
		
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				board[i][j] = " ";
	}
	
	public void draw()
	{
		for (int i = 0; i < rows*2 + 1; i++)
		{
			if (0 == i%2) 
			{ 
				j.out("+"); 
				for (int j = 0; j < columns; j++)	{ jout.jout("---+"); } 
				j.out("\n");
			}
			else
			{
				j.out("|"); 
				for (int j = 0; j < columns; j++)	{ jout.jout(" " + board[i/2][j] + " |"); }
				j.out(" " + (rows - i/2) + "\n");
			}
		}
		
		for (int i = 0; i < columns; i++)	{ j.out("  " + (char) (i + 65) + " "); }
		j.outln("\n");
	}
}
