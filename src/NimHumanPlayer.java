/**
 * This class is human player, a subclass of NimPlayer.
 * It has the common behavior and attributes of human,
 * but rewrites the removeStone method. 
 * 
 * @author Zhenxiang Wang
 *
 */
public class NimHumanPlayer extends NimPlayer {
	private String type="Human";
	public NimHumanPlayer(){
		setType(type);
	}
	
	//Human player removes stones by inputing from the keyboard.
	@Override
	public int removeStone(int stoneLeft, int maxRemoveNum) {
		System.out.println(getGivenName() + "'s turn - remove how many?");
		int removeNum = Integer.parseInt(Nimsys.keyboard.nextLine());
		return removeNum;
	}

}
