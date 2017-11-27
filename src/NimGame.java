/**
 * This class is the game program.
 * 
 * @author Zhenxiang Wang
 *
 */
public class NimGame {
	private int upperBound;
	private int stoneLeft;
	private int maxRemoveNum;
	private NimPlayer player1;
	private NimPlayer player2;

	/**
	 * This method is the game process:It gets the number of stones to be
	 * removed and shows an updated display of stones. Then it repeats the last
	 * step until there are no stones remaining. When this occurs, the program
	 * displays 'Game Over', and the name of the winner.
	 */
	public void playGame() {
		maxRemoveNum= Math.min(upperBound,stoneLeft);
		printStones(stoneLeft);
		boolean turnToPlayer1 = true;
		int removeNum;
		do {
			try {
				if (turnToPlayer1) {
					removeNum = player1.removeStone(stoneLeft, maxRemoveNum);
					if (removeNum > maxRemoveNum || removeNum < 1) {
						System.out.println();
						throw new Exception("Invalid move. You must remove between 1 and "
								+ Math.min(upperBound, stoneLeft) + " stones.");
					} else {
						stoneLeft -= removeNum;
						if (stoneLeft > 0) {
							printStones(stoneLeft);
						}
						turnToPlayer1 = !turnToPlayer1;
					}

				} else {
					removeNum = player2.removeStone(stoneLeft, maxRemoveNum);
					if (removeNum > maxRemoveNum || removeNum < 1) {
						System.out.println();
						throw new Exception("Invalid move. You must remove between 1 and "
								+ Math.min(upperBound, stoneLeft) + " stones.");
					} else {
						stoneLeft -= removeNum;
						if (stoneLeft > 0) {
							printStones(stoneLeft);
						}
						turnToPlayer1 = !turnToPlayer1;
					}
				}
			} catch (Exception e) {
				String message = e.getMessage();
				System.out.println(message);
				printStones(stoneLeft);
			}
		} while (stoneLeft > 0);
		System.out.println();
		System.out.println("Game Over");
		if (turnToPlayer1) {
			System.out.println(player1.getGivenName() + " " + player1.getFamilyName() + " wins!");
			player1.setWonNum(player1.getWonNum() + 1);
		} else {
			System.out.println(player2.getGivenName() + " " + player2.getFamilyName() + " wins!");
			player2.setWonNum(player2.getWonNum() + 1);
		}
		player1.setPlayNum(player1.getPlayNum() + 1);
		player2.setPlayNum(player2.getPlayNum() + 1);
	}

	/**
	 * This method prints the number of stones, and also display the stones,
	 * which will be represented by asterisks '*'.
	 * 
	 * @param StoneNum, which is an integer.
	 */
	public void printStones(int StoneNum) {
		int i;
		System.out.println();
		System.out.print(StoneNum + " stones left:");
		for (i = 1; i <= StoneNum; i++) {
			System.out.print(" *");
		}
		System.out.println();
	}

	/**
	 * This method gets the upperBound of the stoneNum.
	 * 
	 * @return upperBound
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * This method sets the upperBound of the stoneNum.
	 * 
	 * @param upperBound
	 */
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * This method gets the number of stone left.
	 * 
	 * @return stoneLeft
	 */
	public int getStoneLeft() {
		return stoneLeft;
	}

	/**
	 * This method sets the number of stone left.
	 * 
	 * @param stoneLeft
	 */
	public void setStoneLeft(int stoneLeft) {
		this.stoneLeft = stoneLeft;
	}

	/**
	 * This method gets the max remove number.
	 * 
	 * @return
	 */
	public int getMaxRemoveNum() {
		return maxRemoveNum;
	}

	/**
	 * This method sets the max remove number.
	 * 
	 * @param maxRemoveNum
	 */
	public void setMaxRemoveNum(int maxRemoveNum) {
		this.maxRemoveNum = maxRemoveNum;
	}

	/**
	 * This method gets the first player.
	 * 
	 * @return player1, which is a NimPlayer.
	 */
	public NimPlayer getPlayer1() {
		return player1;
	}

	/**
	 * This method sets the first player.
	 * 
	 * @param player1,
	 *            which is a NimPlayer.
	 */
	public void setPlayer1(NimPlayer player1) {
		this.player1 = player1;
	}

	/**
	 * This method gets the second player.
	 * 
	 * @return player2, which is a NimPlayer.
	 */
	public NimPlayer getPlayer2() {
		return player2;
	}

	/**
	 * This method sets the second player.
	 * 
	 * @return player2, which is a NimPlayer.
	 */
	public void setPlayer2(NimPlayer player2) {
		this.player2 = player2;
	}

}
