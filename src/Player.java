
public class Player {

	private String name;
	private int score, id;

	public Player(String name) {
		this.name = name;
		score = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setScore(int points) {
		score+=points;
	}

}
