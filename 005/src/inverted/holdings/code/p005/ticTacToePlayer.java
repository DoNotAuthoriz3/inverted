package inverted.holdings.code.p005;

import java.util.ArrayList;
import java.lang.Exception;
import java.util.List;

public class ticTacToePlayer
{
	private String myName;
	private List<Integer> mySquares = new ArrayList<Integer>(5);
	
	public ticTacToePlayer(char designator) { myName = String.valueOf(designator); }
	public ticTacToePlayer(String designator) { myName = designator; }

	public boolean claim (int chosenSquare, ticTacToePlayer opponent) throws Exception 
	{	
		if ((1 > chosenSquare) || (9 < chosenSquare)) throw new Exception();
		
		if (!opponent.owns(chosenSquare)) mySquares.add(chosenSquare); 
		
		return !opponent.owns(chosenSquare);
	}
	
	public boolean owns (int chosenSquare) { return mySquares.contains(chosenSquare); }
	
	public boolean wins () { return false; }
}