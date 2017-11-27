/**
 * This class is a abstract class of the game player. 
 * It contains seven attributes of the player. 
 * It can set and get the type, userName, familyName, givenName, 
 * playeNum, wonNum and winningRate of the player. 
 * It can also get and return the number of stones the player wants to remove.
 * 
 * @author Zhenxiang Wang
 */
public abstract class NimPlayer {
	private String type;
	private String userName;
	private String familyName;
	private String givenName;
	private int playNum;
	private int wonNum;

	/**
	 * This method reads the number of stones the player wants to remove from
	 * the keyboard, and returns this number.
	 * 
	 * @return the number of stones the player wants to remove, which is an
	 *         integer.
	 */
	abstract public int removeStone(int stoneLeft, int maxRemoveNum);
	
	/**
	 * This method gets and returns the type of the player.
	 * 
	 * @return type, AI or Human.
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method sets the type of the player.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method gets and returns the user name.
	 * 
	 * @return userName, which is a string.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method sets the user name.
	 * 
	 * @param userName,
	 *            which is a string.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method gets and returns the family name of the user.
	 * 
	 * @return user's family name, which is a string.
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * This method sets the family name of the user.
	 * 
	 * @param familyName, which is a string.
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * This method gets and returns the given name of the user.
	 * 
	 * @return given name of the user
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * This method sets the given name of the user.
	 * 
	 * @param givenName
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * This method gets and returns the play times of the user.
	 * 
	 * @return playNum, which is an integer.
	 */
	public int getPlayNum() {
		return playNum;
	}

	/**
	 * This method sets the play times of the user.
	 * 
	 * @param the number of play times, which is an integer.
	 */
	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}

	/**
	 * This method gets and returns the winning times of the user.
	 * 
	 * @return wonNum, which is an integer.
	 */
	public int getWonNum() {
		return wonNum;
	}

	/**
	 * This method sets the winning times of the user.
	 * 
	 * @param wonNum, which is an integer.
	 */
	public void setWonNum(int wonNum) {
		this.wonNum = wonNum;
	}

	/**
	 * This method calculates the winning rate of the user, 
	 * and return this rate.
	 * 
	 * @return winningRate, which is a double.
	 */
	public double getWinningRate() {
		if (this.playNum == 0) {
			return 0.0;
		} else if (this.wonNum == 0) {
			return 0.0;
		} else {
			return (double) this.wonNum / (double) this.playNum;
		}
	}


}
