import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Game {

	private ArrayList<Player> players;
	private int playersPerRace, racesPerRound,playersLeft;
	public int[] round;
	private Random r;
	private final int maxInRace = 4;
	private int playersPlayed;
	private HashMap<Integer,Integer> points;

	public Game() {
		players = new ArrayList<Player>();
		r = new Random();
		points = new HashMap<Integer,Integer>();
		setUpPoints();
	}

	public int getPlayersLeft() {
		return playersLeft;
	}

	public void setPlayersPerRace(int num) {
		playersPerRace = num;
		System.out.println("Players in race: " + playersPerRace);
		playersLeft = playersLeft - num;
		System.out.println("Players Left: " + playersLeft);

		if(playersLeft < 0)
			playersLeft = 0;


		System.out.println("Players Left: " + playersLeft);
	}


	public boolean finished() {
		System.out.println();
		System.out.println("Players Played" + playersPlayed);
		System.out.println("Total Players" + players.size());
		System.out.println();
		return playersPlayed >= players.size();
	}

	public boolean addPlayer(String name) {
		if(players.add(new Player(name))){
			players.get(players.size()-1).setId(players.size()-1);
			return true;
		}
		return false;
	}

	public void setUpRound() {
		playersPlayed = 0;
		int noOfPlayers = players.size();
		playersLeft = noOfPlayers;
		racesPerRound = playersPerRace;
		System.out.println("Races: " + racesPerRound);	
		round = new int[noOfPlayers];

		int placeNum = 0;
		while(placeNum < noOfPlayers) {

			int num = r.nextInt(noOfPlayers)+1;
			int count = 0;

			for(int i =0; i < noOfPlayers; i++) {

				if(round[i] == num)
					count++;

			}

			if(count ==0){


				round[placeNum] = num;

				placeNum++;

			}
		}

	}

	public int getNumPlayers() {
		return players.size();
	}

	public String printPlayers() {
		String s = "";
		for(int i = 0; i< players.size(); i++) {
			s += (i+1) + ") " + players.get(i).getName() + "\n";
		}
		return s;
	}

	private void setUpPoints() {
		points.put(1, 15);
		points.put(2, 12);
		points.put(3, 10);
		points.put(4, 8);
		points.put(5, 7);
		points.put(6, 6);
		points.put(7, 5);
		points.put(8, 4);
		points.put(9, 3);
		points.put(10, 2);
		points.put(11, 1);
		points.put(12, 0);

	}

	public ArrayList<Player> getRacers() {
		ArrayList<Player> racers = new ArrayList<Player>();
		System.out.println("Playerss in race" + playersPerRace);
		System.out.println("A:" + playersPlayed + "B:" + playersPerRace);
		int c = (playersPlayed+playersPerRace);

		if(c > players.size())
			c = players.size();

		System.out.println("A:" + playersPlayed + "B:" + playersPerRace);

		System.out.println("c: " + c);
		for(int i =  playersPlayed; i < c; i++) {
			System.out.println("i: " + i);
			if(round[i] != 0) {
				racers.add(players.get((round[i]-1)));
			}

			System.out.println(racers.size());
		}
		playersPlayed +=playersPerRace;
		return racers;
	}

	public void addHalfWayThrough(String name) {
		Player player = new Player(name);
		System.out.println();
		System.out.println(player.getName());
		System.out.println();
		if(players.add(player)){
			player.setId(players.size()-1);
			System.out.println();
			System.out.println(player.getId());
			System.out.println();
			int[] temp = new int[players.size()];

			int i = 0;
			while(i < round.length) {
				temp[i] = round[i];
				i++;
			}
			temp[i] = player.getId()+1;
			
			System.out.println();
			System.out.println(temp[i]);
			System.out.println();

			round = temp;

			playersLeft++;

		}
	}

	public void addNewToRace() {
		int[] temp = new int[players.size()];
		int i = 0;
		System.out.println();
		System.out.println("PLayers Played: " +playersPlayed);
		System.out.println();
		while( i < playersPlayed) {
			temp[i] = round[i];
			i++;
		}
		System.out.println();
		System.out.println("EFWEFW: " +getPName(round[round.length-1]));
		System.out.println();
		temp[i] = round[round.length-1];
		i++;
		while( i < round.length) {
			temp[i] = round[i-1];
			i++;
		}
		playersPlayed -=playersPerRace;
		round = temp;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getPlayer(int id) {
		return players.get(id);
	}

	public void updateScore(int playerId, int scoreId) {
		Player p = players.get(playerId);
		p.setScore(points.get(scoreId));
	}

	public int getRacesPerRound() {
		return racesPerRound;
	}

	public ArrayList<Player> getInOrder() {
		ArrayList<Player> ordered = new ArrayList<Player>();
		boolean added = false;

		for(int i = 0; i < players.size(); i++) {
			int score = players.get(i).getScore();

			for(int a = 0; a< ordered.size(); a++) {
				if(score > ordered.get(a).getScore()) {
					ArrayList<Player> temp = new ArrayList<Player>();
					for(int b = 0; b < a; b++) {
						temp.add(ordered.get(b));
					}
					temp.add(players.get(i));

					for(int c = a; c < ordered.size(); c++) {
						temp.add(ordered.get(c));
					}

					ordered = temp;
					added = true;
					break;
				}
			}

			if(!added)
				ordered.add(players.get(i));

		}

		return ordered;
	}

	public void minusPlayersPlayed(int num) {
		playersPlayed -=num;
	}
	
	public int getPlayersPlayed() {
		return playersPlayed;
	}
	
	public String getPName(int num) {
		return " NUM: " + num + " ID: " + players.get(num-1).getId() + " Name: " + players.get(num-1).getName();
	}
	
	public int getPlayersPerRace() {
		return playersPerRace;
	}

	public static void main(String[] args) {
		Game g =new Game();
		g.addPlayer("Josh");
		g.addPlayer("Hugh");
		g.addPlayer("Josh2");
		g.addPlayer("Hugh2");
		g.addPlayer("Josh3");
		g.addPlayer("Hugh3");
		g.setPlayersPerRace(3);
		g.setUpRound();
		g.getRacers();
		

		for(int i = 0; i < g.round.length; i++ )
			System.out.print("A"+ g.getPName(g.round[i]) + ", ");

		System.out.println();

		
		
		g.addHalfWayThrough("Lucas");
		for(int i = 0; i < g.round.length; i++ )
			System.out.print("B"+ g.getPName(g.round[i]) + ", ");

		System.out.println();
		
		g.addNewToRace();
		for(int i = 0; i < g.round.length; i++ )
			System.out.print("C"+ g.getPName(g.round[i]) + ", ");

	}

}


