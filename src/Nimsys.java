import java.io.*;
import java.util.Scanner;

/**
 * This class is the system of the Nim game.
 * 
 * @author Zhenxiang Wang
 */
public class Nimsys {
	private NimPlayer[] playerList = new NimPlayer[100];
	public static Scanner keyboard = new Scanner(System.in);
	private String command;
	private int playerCount = 0;
	static File file = new File("players.dat");

	/**
	 * This method manages the whole Nim game system.
	 * If the player data file exists, it will be loaded.
	 * It also reacts to commands from the keyboard respectively.
	 * 
	 * @param args, unused.
	 */
	public static void main(String args[]) throws IOException {
		Nimsys nimSys = new Nimsys();
		System.out.println("Welcome to Nim");
		System.out.println();
		if (file.exists()) {
			nimSys.read("players.dat");
		}
		while (true) {
			System.out.print("$");
			nimSys.reactToInput();
			System.out.println();
		}
	}

	/**
	 * This method read the inputs from keyboard and react to it respectively.
	 * This method also handle the invalid input.
	 * 
	 */
	public void reactToInput() {
		String str = keyboard.nextLine();
		// split the string into command and other input messages.
		String[] line1 = str.split(" ");
		command = line1[0];
		String[] line2;
		// react to the input according to the first command entered by users.
		try {
			if (command.equals("addplayer")) {
				if (line1.length == 1) {
					throw new Exception("Incorrect number of arguments supplied to command.");
				} else {
					line2 = line1[1].split(",");
					if (line2.length < 3) {
						throw new Exception("Incorrect number of arguments supplied to command.");
					} else {
						addPlayer(line2[0], line2[1], line2[2]);
					}
				}
			} else if (command.equals("addaiplayer")) {
				if (line1.length == 1) {
					throw new Exception("Incorrect number of arguments supplied to command.");
				} else {
					line2 = line1[1].split(",");
					if (line2.length < 3) {
						throw new Exception("Incorrect number of arguments supplied to command.");
					} else {
						addAIPlayer(line2[0], line2[1], line2[2]);
					}
				}
			} else if (command.equals("removeplayer")) {
				if (line1.length == 1) {
					System.out.println("Are you sure you want to remove all players? (y/n)");
					String confirm = keyboard.nextLine();
					if (confirm.equalsIgnoreCase("y")) {
						removeAllPlayers();
					}
				} else {
					removePlayer(line1[1]);
				}
			} else if ((command.equals("editplayer"))) {
				if (line1.length == 1) {
					throw new Exception("Incorrect number of arguments supplied to command.");
				} else {
					line2 = line1[1].split(",");
					if (line2.length < 3) {
						throw new Exception("Incorrect number of arguments supplied to command.");
					} else {
						editPlayer(line2[0], line2[1], line2[2]);
					}
				}
			} else if ((command.equals("resetstats"))) {
				if (line1.length == 1) {
					System.out.println("Are you sure you want to "
							+ "reset all player statistics? (y/n)");
					String confirm = keyboard.nextLine();
					if (confirm.equalsIgnoreCase("y")) {
						resetAllStats();
					}
				} else {
					resetStats(line1[1]);
				}
			} else if ((command.equals("displayplayer"))) {
				if (line1.length == 1) {
					displayAllPlayers();
				} else {
					displayPlayer(line1[1]);
				}
			} else if ((command.equals("rankings"))) {
				if (line1.length == 1) {
					rankings();
				} else {
					rankings(line1[1]);
				}
			} else if ((command.equals("startgame"))) {
				if (line1.length == 1) {
					throw new Exception("Incorrect number of arguments supplied to command.");
				} else {
					line2 = line1[1].split(",");
					if (line2.length < 4) {
						throw new Exception("Incorrect number of arguments supplied to command.");
					} else {
						startGame(new Integer(line2[0]), 
								new Integer(line2[1]), line2[2], line2[3]);
					}
				}
			} else if ((command.equals("exit"))) {
				System.out.println();
				exit();
			} else {
				throw new Exception("'" + command + "' is not a valid command.");
			}
		} catch (Exception e) { //catch the exception.
			String message = e.getMessage();
			System.out.println(message);
		}
	}

	/**
	 * This method adds new human player to the game. The information be added to a
	 * player includes userName, familyName and givenName. If the player with
	 * the given username already exists, an indication will be given.
	 * 
	 * @param userName
	 * @param familyName
	 * @param givenName
	 */
	public void addPlayer(String userName, String familyName, String givenName) {
		boolean playerNotExist = true;
		if (playerCount > 0) {
			for (int i = 0; i < playerCount; i++) {
				if (userName.equals(playerList[i].getUserName())) {
					System.out.println("The player already exists.");
					playerNotExist = false;
					break;
				}
			}
		}
		if (playerNotExist) {
			playerList[playerCount] = new NimHumanPlayer();
			playerList[playerCount].setUserName(userName);
			playerList[playerCount].setFamilyName(familyName);
			playerList[playerCount].setGivenName(givenName);
			playerCount = playerCount + 1;

		}
	}

	/**
	 * This method adds new AI player to the game. The information be added to a
	 * player includes userName, familyName and givenName. If the player with
	 * the given username already exists, an indication will be given.
	 * @param userName
	 * @param familyName
	 * @param givenName
	 */
	public void addAIPlayer(String userName, String familyName, String givenName) {
		boolean playerNotExist = true;
		if (playerCount > 0) {
			for (int i = 0; i < playerCount; i++) {
				if (userName.equals(playerList[i].getUserName())) {
					System.out.println("The player already exists.");
					playerNotExist = false;
					break;
				}
			}
		}
		if (playerNotExist) {
			playerList[playerCount] = new NimAIPlayer();
			playerList[playerCount].setUserName(userName);
			playerList[playerCount].setFamilyName(familyName);
			playerList[playerCount].setGivenName(givenName);
			playerCount = playerCount + 1;

		}
	}

	/**
	 * This method remove a player according to the userName. If the user does
	 * not exist, an indication will be given.
	 * 
	 * @param userName
	 */
	public void removePlayer(String userName) {
		boolean playerNotExist = true;
		for (int i = 0; i < playerCount; i++) {
			if (userName.equals(playerList[i].getUserName())) {
				for (int j = i; j < playerCount; j++) {
					playerList[j] = playerList[j + 1];
				}
				playerCount = playerCount - 1;
				playerNotExist = false;
				break;
			}
		}
		if (playerNotExist) {
			System.out.println("The player does not exist.");
		}
	}

	/**
	 * This method remove all players.
	 */
	public void removeAllPlayers() {
		for (int i = 0; i < playerCount; i++) {
			playerList[i] = null;
		}
		playerCount = 0;
	}

	/**
	 * This method edits the familyName and the givenName of a user. If the user
	 * does not exist, an indication will be given.
	 * 
	 * @param userName
	 * @param familyName
	 * @param givenName
	 */
	public void editPlayer(String userName, String familyName, String givenName) {
		boolean playerNotExist = true;
		for (int i = 0; i < playerCount; i++) {
			if (userName.equals(playerList[i].getUserName())) {
				playerList[i].setFamilyName(familyName);
				playerList[i].setGivenName(givenName);
				playerNotExist = false;
				break;
			}
		}
		if (playerNotExist) {
			System.out.println("The player does not exist.");
		}
	}

	/**
	 * This method resets the playNum and wonNum to 0 of the player according to
	 * the userName given. If the user does not exist, an indication will be given.
	 * 
	 * @param userName
	 */
	public void resetStats(String userName) {
		boolean playerNotExist = true;
		for (int i = 0; i < playerCount; i++) {
			if (userName.equals(playerList[i].getUserName())) {
				playerList[i].setPlayNum(0);
				playerList[i].setWonNum(0);
				playerNotExist = false;
				break;
			}
		}
		if (playerNotExist) {
			System.out.println("The player does not exist.");
		}
	}

	/**
	 * This method resets the playNum and wonNum to 0 of all players.
	 */
	public void resetAllStats() {
		for (int i = 0; i < playerCount; i++) {
			playerList[i].setPlayNum(0);
			playerList[i].setWonNum(0);
		}
	}

	/**
	 * This method display player information. If the user does not exist,
	 * an indication will be given.
	 * 
	 * @param userName
	 */
	public void displayPlayer(String userName) {
		alphabeticalSort(playerList, playerCount);
		boolean playerNotExist = true;
		for (int i = 0; i < playerCount; i++) {
			if (userName.equals(playerList[i].getUserName())) {
				System.out.println(playerList[i].getUserName() + "," +
						playerList[i].getGivenName()+ "," + playerList[i].getFamilyName()
						+ "," + playerList[i].getPlayNum()
						+ " games," + playerList[i].getWonNum() + " wins");
				playerNotExist = false;
				break;
			}
		}
		if (playerNotExist) {
			System.out.println("The player does not exist.");
		}
	}

	/**
	 * This method display all players' information.
	 */
	public void displayAllPlayers() {
		// sort playerList in userName's alphabetical order
		alphabeticalSort(playerList, playerCount);
		for (int i = 0; i < playerCount; i++) {
			System.out.println(playerList[i].getUserName() + "," + playerList[i].getGivenName()
					+ "," + playerList[i].getFamilyName() + "," + playerList[i].getPlayNum()
					+ " games," + playerList[i].getWonNum() + " wins");
		}
	}

	/**
	 * This method outputs a list of player rankings in winningRate's desc or asc order.
	 * It displays the winning ratio, playNum and full name of the player.
	 * 
	 * @param order
	 */
	public void rankings(String order) {
		//sort playerList in userName's anti-alphabetical order
		// then sort the sorted playerList in winningRate's ascending order.
		if (order.equals("desc")) {
			ascSort(antiAlphabeticalSort(playerList, playerCount), playerCount);
			if (playerCount > 10) {
				playerCount = 10;
			}
			// print the playerList in reverse order.
			for (int i = playerCount - 1; i >= 0; i--) {
				int winningRatio = (int) Math.round(playerList[i].getWinningRate() * 100);
				System.out.printf("%-5s|", winningRatio + "%");
				System.out.printf(" %02d games |", playerList[i].getPlayNum());
				System.out.printf(" %s %s\n", playerList[i].getGivenName(),
						playerList[i].getFamilyName());
			}
		} else if (order.equals("asc")) {
			// sort playerList in userName's alphabetical order
			// then sort the sorted playerList in winningRate's ascending order.
			ascSort(alphabeticalSort(playerList, playerCount), playerCount);
			if (playerCount > 10) {
				playerCount = 10;
			}
			// print the playerList in order.
			for (int i = 0; i < playerCount; i++) {
				int winningRatio = (int) Math.round(playerList[i].getWinningRate() * 100);
				System.out.printf("%-5s|", winningRatio + "%");
				System.out.printf(" %02d games |", playerList[i].getPlayNum());
				System.out.printf(" %s %s\n", playerList[i].getGivenName(),
						playerList[i].getFamilyName());
			}
		}

	}

	/**
	 * This method outputs a list of player rankings in winningRate's descending order. 
	 * It displays the winning ratio, playNum and full name of the player.
	 */
	public void rankings() {
		// sort playerList in userName's anti-alphabetical order
		// then sort the sorted playerList in winningRate's ascending order.
		ascSort(antiAlphabeticalSort(playerList, playerCount), playerCount);
		if (playerCount > 10) {
			playerCount = 10;
		}
		for (int i = playerCount - 1; i >= 0; i--) {// print the playerList in
													// reverse order.
			int winningRatio = (int) Math.round(playerList[i].getWinningRate() * 100);
			System.out.printf("%-5s|", winningRatio + "%");
			System.out.printf(" %02d games |", playerList[i].getPlayNum());
			System.out.printf(" %s %s\n", playerList[i].getGivenName(),
					playerList[i].getFamilyName());

		}
	}

	/**
	 * This method sorts the playerList in winningRate's ascending order, 
	 * using the insertion sort.
	 * 
	 * @param playerList
	 * @param playerCount
	 * @return playerList
	 */
	public NimPlayer[] ascSort(NimPlayer[] playerList, int playerCount) {

		for (int i = 1; i < playerCount; i++) {
			NimPlayer tempPlayer = playerList[i];
			int j = i - 1;
			while (j >= 0 && playerList[j].getWinningRate() > tempPlayer.getWinningRate()) {
				playerList[j + 1] = playerList[j];
				j = j - 1;
			}
			playerList[j + 1] = tempPlayer;
		}
		return playerList;
	}

	/**
	 * This method sorts the playerList in userName's alphabetical order.
	 * 
	 * @param playerList
	 * @param playerCount
	 * @return playerList
	 */
	public NimPlayer[] alphabeticalSort(NimPlayer[] playerList, int playerCount) {

		for (int i = 1; i < playerCount; i++) {
			NimPlayer tempPlayer = playerList[i];
			int j = i - 1;
			while (j >= 0 && playerList[j].getUserName().compareTo
						(tempPlayer.getUserName()) > 0) {
				playerList[j + 1] = playerList[j];
				j = j - 1;
			}
			playerList[j + 1] = tempPlayer;
		}
		return playerList;
	}

	/**
	 * This method sorts the playerList in userName's anti-alphabetical order.
	 * 
	 * @param playerList
	 * @param playerCount
	 * @return playerList
	 */
	public NimPlayer[] antiAlphabeticalSort(NimPlayer[] playerList, int playerCount) {

		for (int i = 1; i < playerCount; i++) {
			NimPlayer tempPlayer = playerList[i];
			int j = i - 1;
			while (j >= 0 && playerList[j].getUserName().compareTo
						(tempPlayer.getUserName()) < 0) {
				playerList[j + 1] = playerList[j];
				j = j - 1;
			}
			playerList[j + 1] = tempPlayer;
		}
		return playerList;
	}

	/**
	 * This method creates and commences a NimGame. 
	 * It inputs stoneLeft, upperBound, the first userName and the second userName.
	 * Then two players play the game and get the winner.
	 * 
	 * @param stoneLeft
	 * @param upperBound
	 * @param userName1
	 * @param userName2
	 */
	public void startGame(int stoneLeft, int upperBound, String userName1, String userName2) {
		//give an indication.
		if (search(userName1) == -1 || search(userName2) == -1) {
			System.out.println("One of the players does not exist.");
		} else {
			NimGame game = new NimGame();// start a new game.
			// initialize the game information.
			game.setStoneLeft(stoneLeft);
			game.setUpperBound(upperBound);
			game.setPlayer1(playerList[search(userName1)]);
			game.setPlayer2(playerList[search(userName2)]);
			System.out.println();
			System.out.println("Initial stone count: " + stoneLeft);
			System.out.println("Maximum stone removal: " + upperBound);
			System.out.println("Player 1: " + playerList[search(userName1)].getGivenName()
					+ " " + playerList[search(userName1)].getFamilyName());
			System.out.println("Player 2: " + playerList[search(userName2)].getGivenName()
					+ " " + playerList[search(userName2)].getFamilyName());
			game.playGame();// play the game.
		}

	}

	/**
	 * This method search the player by userName.
	 * @param userName
	 * @return the index of the player, whose userName is same as the input.
	 */
	public int search(String userName) {
		for (int i = 0; i < playerCount; i++) {
			if (userName.equals(playerList[i].getUserName())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method exits the Nim system.
	 * Before exiting, the player data will be stored in a file.
	 * @throws IOException
	 */
	public void exit() throws IOException {
		FileWriter fileWriter = null;
		fileWriter = new FileWriter(file, false);
		for (int i = 0; i < playerCount; i++) {
			fileWriter.write(playerList[i].getType() + "," + playerList[i].getUserName() + ","
					+ playerList[i].getGivenName() + "," + playerList[i].getFamilyName() + ","
					+ playerList[i].getPlayNum() + "," + playerList[i].getWonNum() + "\n");
		}
		fileWriter.flush();
		fileWriter.close();
		System.exit(0);
	}

	/**
	 * This method restores the player data to the program.
	 * @param filename
	 * @throws IOException
	 */
	public void read(String filename) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
		String line = bufferedReader.readLine();
		String[] arrs = null;
		playerCount = 0;
		while (line != null) {
			arrs = line.split(",");
			if (arrs[0].equals("AI")) {
				playerList[playerCount] = new NimAIPlayer();
			}
			if (arrs[0].equals("Human")) {
				playerList[playerCount] = new NimHumanPlayer();
			}
			playerList[playerCount].setType(arrs[0]);
			playerList[playerCount].setUserName(arrs[1]);
			playerList[playerCount].setGivenName(arrs[2]);
			playerList[playerCount].setFamilyName(arrs[3]);
			playerList[playerCount].setPlayNum(Integer.parseInt(arrs[4]));
			playerList[playerCount].setWonNum(Integer.parseInt(arrs[5]));
			line = bufferedReader.readLine();
			playerCount++;
		}
		bufferedReader.close();
	}

}
