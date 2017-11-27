/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.3, 2.4 and 2.5 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
	
	This class is AI player, a subclass of NimPlayer.
    It has the common behavior and attributes of human,
    but rewrites the removeStone method.
    
	Coded by: Jin Huang
	Modified by: Jianzhong Qi, 29/04/2015
	Modified by: Zhenxiang Wang, 23/05/2017
*/
public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the task in Section 2.3
	
	private String type = "AI";

	public NimAIPlayer() {
		setType(type);
	}

	// AI player removes stones automatically.
	@Override
	public int removeStone(int stoneLeft, int maxRemoveNum) {
		int removeNum = 0;
		int remainder = stoneLeft % (maxRemoveNum + 1);
		System.out.println(getGivenName() + "'s turn - remove how many?");
		if (remainder == 1) {
			removeNum = 1;
		} else if (remainder == 0) {
			removeNum = maxRemoveNum;
		} else {
			removeNum = remainder - 1;
		}
		return removeNum;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";

		return move;
	}
}
