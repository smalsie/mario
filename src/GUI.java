import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


public class GUI {
	private JFrame screen,leaderScreen,addPlayerScreen;
	private JTextArea log;
	private Game game;
	private int raceNum = 1;
	private int numRaces;
	private boolean running, canGoBack,leaderBoardOpen, end;
	private JPanel gameScreen;
	private int left;
	
	public GUI() {
		game = new Game();
		screen = new JFrame("Mario!");
		screen.setMinimumSize(new Dimension(510,510));
		screen.setSize(new Dimension(510, 510));
		screen.setResizable(false);
		running = false;
		firstScreen();
		canGoBack = true;
		leaderBoardOpen = false;
		end = false;
		
	}
	
	/**
	 * Helper method to ensure consistency in leaving application.
	 */
	private void exitApp() {
		// Display confirmation dialog before exiting application
		int response = JOptionPane.showConfirmDialog(screen, 
				"Do you really want to quit?",
				"Quit?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
		// Don't quit
	}
	
public void firstScreen() {

		final JPanel firstScreen = new JPanel();
		// Step 1: create the components
		log = new JTextArea();
		JButton addPlayer = new JButton();
		JButton startGame = new JButton();
		final JTextField playerName = new JTextField();
		JButton quitButton = new JButton();
		JLabel playerNameLabel = new JLabel("Name: ");
		final JLabel noOfPlayers = new JLabel();
		JScrollPane listScroller = new JScrollPane(log);
		
		// Step 2: Set the properties of the components
		addPlayer.setText("Add Player");
		addPlayer.setToolTipText("Add a new player to the tournament.");
		startGame.setText("Start");
		startGame.setToolTipText("Start the tournament.");
		quitButton.setText("Quit");
		quitButton.setToolTipText("Quit the application.");
		noOfPlayers.setText("Current Number of Players: " + game.getNumPlayers());
		log.setEditable(false);
		
		log.setText("Players: \n" +
				game.printPlayers());
		
		// Step 3: Create containers to hold the components
				
		
		JPanel nameRowOne = new JPanel();
		JPanel nameRowTwo= new JPanel();
		JPanel bottomRow = new JPanel();
		JPanel buttons = new JPanel();
		
		playerName.setColumns(30);
				
		screen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				
		// Step 4: Specify LayoutManagers
		
		firstScreen.setLayout(new BorderLayout());
		nameRowOne.setLayout(new FlowLayout());
		nameRowTwo.setLayout(new FlowLayout());
		bottomRow.setLayout(new BorderLayout());
		buttons.setLayout(new FlowLayout());
			
		// Step 5: Add components to containers 
		
		nameRowOne.add(playerNameLabel);
		nameRowOne.add(playerName);
		nameRowTwo.add(addPlayer);
		if(game.getNumPlayers() > 0)
			bottomRow.add(listScroller, BorderLayout.NORTH);
		
		if(game.getNumPlayers() > 1)
			buttons.add(startGame);
		
		buttons.add(quitButton);
		
		bottomRow.add(buttons, BorderLayout.EAST);
		bottomRow.add(noOfPlayers, BorderLayout.WEST);
		
		firstScreen.add(nameRowOne, BorderLayout.NORTH);
		firstScreen.add(nameRowTwo, BorderLayout.CENTER);
		firstScreen.add(bottomRow, BorderLayout.SOUTH);
		
		screen.getContentPane().add(firstScreen);
		// Step 6: Arrange to handle events in the user interface
				
		addPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(playerName.getText().equals("")) {
					JOptionPane.showMessageDialog(screen, "Please enter in a player name!");
				} else {
					
					if(game.addPlayer(playerName.getText()))
					{
						//JOptionPane.showMessageDialog(screen, "Player " + playerName.getText() + " added");
						firstScreen.setVisible(false);
						firstScreen();
					} else {
						JOptionPane.showMessageDialog(screen, "I'm sorry, something went wrong, please try again.");
					}
					
				}
			}

		});

		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				firstScreen.setVisible(false);
				secondScreen();
			}

		});
		
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				exitApp();
				
			}
			
		});
		
		
		// Step 7: Display the GUI
		screen.pack();
		screen.setVisible(true);
	}
	
public void secondScreen() {

		final JPanel secondScreen = new JPanel();
		
		// Step 1: create the components
		JButton confirmButton = new JButton();
		JButton backButton = new JButton();
		JButton quitButton = new JButton();
		
		final JLabel playersNumLabel = new JLabel("Please enter in the number of players for this race: ");
		final JTextField playersPerRace = new JTextField();
		
		// Step 2: Set the properties of the components
		confirmButton.setText("Next");
		confirmButton.setToolTipText("Go forward.");
		backButton.setText("Back");
		backButton.setToolTipText("Go back.");
		quitButton.setText("Quit");
		quitButton.setToolTipText("Quit the application.");
	

		// Step 3: Create containers to hold the components
		
		
		JPanel nameRowOne = new JPanel();
		JPanel nameRowTwo= new JPanel();
		JPanel bottomRow = new JPanel();
		JPanel buttons = new JPanel();
		
		playersPerRace.setColumns(10);
		
		// Step 4: Specify LayoutManagers
		
		secondScreen.setLayout(new BorderLayout());
		nameRowOne.setLayout(new FlowLayout());
		nameRowTwo.setLayout(new FlowLayout());
		bottomRow.setLayout(new BorderLayout());
		buttons.setLayout(new FlowLayout());
			
		// Step 5: Add components to containers 
		
		nameRowOne.add(playersNumLabel);
		nameRowTwo.add(playersPerRace);
				
		buttons.add(confirmButton);
		
		if(canGoBack)
			buttons.add(backButton);
		
		buttons.add(quitButton);
		
		bottomRow.add(buttons, BorderLayout.EAST);
		
		secondScreen.add(nameRowOne, BorderLayout.NORTH);
		secondScreen.add(nameRowTwo, BorderLayout.CENTER);
		secondScreen.add(bottomRow, BorderLayout.SOUTH);
		
		screen.getContentPane().add(secondScreen);
		// Step 6: Arrange to handle events in the user interface
		
		
		
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				exitApp();
				
			}
			
		});
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				secondScreen.setVisible(false);
				firstScreen();
				
				
			}
			
		});
		
		final int totalPlayers = game.getNumPlayers();
		
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				left = game.getNumPlayers() - Integer.parseInt(playersPerRace.getText());
				int response = JOptionPane.showConfirmDialog(secondScreen, 
						"Are you sure you want to start with " + playersPerRace.getText() + " players in this race? There will be " + left + " players left.",
						"Continue?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					
					secondScreen.setVisible(false);
					if(!running)
						game.setUpRound();
					
					game.setPlayersPerRace(Integer.parseInt(playersPerRace.getText()));
					gameScreen();
				}
				
			}
			
		});
		
		// Step 7: Display the GUI
		screen.pack();
		secondScreen.setVisible(true);
	}

public void gameScreen() {
	gameScreen = new JPanel();
	gameScreen.setPreferredSize(new Dimension(400,800));
	canGoBack = false;
	// Step 1: create the components
	JButton nextRaceButton = new JButton();
	JButton backButton = new JButton();
	JButton quitButton = new JButton();
	JButton addPlayer = new JButton();
	JButton leaderBoardButton = new JButton();
	
	
	numRaces = game.getRacesPerRound();
	
	final JLabel races = new JLabel("Race " + raceNum + ", "+ left + " players left.");
	final JTextField playersPerRace = new JTextField();
	
	// Step 2: Set the properties of the components
	nextRaceButton.setText("Next Race");
	nextRaceButton.setToolTipText("Start the next race.");
	backButton.setText("Back");
	backButton.setToolTipText("Go back.");
	quitButton.setText("Quit");
	quitButton.setToolTipText("Quit the application.");
	leaderBoardButton.setText("Leader Board");
	leaderBoardButton.setToolTipText("Show the Leader Board.");
	addPlayer.setText("Add Player");
	addPlayer.setToolTipText("Add a new player.");

	
	// Step 3: Create containers to hold the components
		
	
	JPanel nameRowOne = new JPanel();
	JPanel nameRowTwo= new JPanel();
	JPanel bottomRow = new JPanel();
	JPanel buttons = new JPanel();
	
	playersPerRace.setColumns(10);
	
	// Step 4: Specify LayoutManagers
	
	gameScreen.setLayout(new BorderLayout());
	nameRowOne.setLayout(new FlowLayout());
	nameRowTwo.setLayout(new FlowLayout());
	bottomRow.setLayout(new BorderLayout());
	buttons.setLayout(new FlowLayout());
		
	// Step 5: Add components to containers 
	
	
	nameRowOne.add(races);
	
	final ArrayList<Player> racers = game.getRacers();
	
	ArrayList<JPanel> panels = new ArrayList<JPanel>();
	
	String[] columnNames = {"Player",
            "Points",
            "Position"};
	final Object[][] data = new Object[racers.size()][4];
	
	String[] places = { "1", "2", "3", "4", "5", "6","7","8","9","10","11","12" };

	//Create the combo box, select item at index 4.
	//Indices start at 0, so 4 specifies the pig.
	JComboBox place = new JComboBox(places);
	place.setSelectedIndex(0);
	
	for(int i = 0; i < racers.size(); i++) {
		
		data[i][0] = racers.get(i).getName();
		data[i][1] = racers.get(i).getScore();
		data[i][3] = racers.get(i).getId();
		
		
	}
	
	
	
	JTable table = new JTable(data, columnNames);
	
	
	TableColumn gradeColumn = table.getColumnModel().getColumn(2);
	gradeColumn.setCellEditor(new DefaultCellEditor(place));
	
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	
	JScrollPane playesTable = new JScrollPane(table);
	
			
	buttons.add(nextRaceButton);
	buttons.add(backButton);
	buttons.add(leaderBoardButton);
	buttons.add(addPlayer);
	buttons.add(quitButton);
	
	bottomRow.add(buttons, BorderLayout.EAST);
	
	gameScreen.add(nameRowOne, BorderLayout.NORTH);
	gameScreen.add(playesTable, BorderLayout.CENTER);
	
	
	
	gameScreen.add(bottomRow, BorderLayout.SOUTH);
	screen.getContentPane().add(gameScreen);
	// Step 6: Arrange to handle events in the user interface
	
	
	
	quitButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			exitApp();
			
		}
		
	});

backButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			game.minusPlayersPlayed(1);
			gameScreen.setVisible(false);
			gameScreen();
			
		}
		
	});

	leaderBoardButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(leaderBoardOpen)
				 leaderScreen.setVisible(false);
			
			leaderBoard();
			
		}
		
	});
	
	addPlayer.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			addPlayerScreen();
			
		}
		
	});
	
	nextRaceButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] placsSel = new int[13];
			boolean error = false;
			boolean nullError = false;
			for(int a = 1; a <= 12; a++) 
				placsSel[a] = 0;
			
				
			for(int i = 0; i < racers.size(); i++) {
				if(data[i][2] !=null) 
					placsSel[Integer.parseInt((String) data[i][2])]++;	
				else 
					nullError = true;
				
				
			}
				
			
			for(int a = 0; a < 12; a++) {
				if(placsSel[a] > 1) {
					error = true;
					System.out.println(a + " : " + placsSel[a]);
				}
			}
			
			if(nullError){
				JOptionPane.showMessageDialog(gameScreen, "You have one or more players without an entered position!");
			} else if(error){
				JOptionPane.showMessageDialog(gameScreen, "You have one or more players with the same position!");
			} else {
			
				for(int i = 0; i < racers.size(); i++) {
					
					game.updateScore((int) data[i][3], Integer.parseInt((String) data[i][2]));
				}	
					if(game.finished()) {
						raceNum = 0;
						running = false;
						
						int response = JOptionPane.showConfirmDialog(screen, 
								"Do you want to run another round?",
								"Go again?",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							game.setUpRound();
						} else {
							screen.setVisible(false);
							end = true;
							if(leaderScreen != null)
								leaderScreen.setVisible(false);
							leaderBoard();
							return;
						}
						
						
					}
					
					running = true;
					raceNum++;
					
					gameScreen.setVisible(false);
					secondScreen();
				
				
			}
		}
		
	});
	
	backButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		
			
		}
		
	});
	
	
}

public void addPlayerScreen() {
	
	// Step 1: create the components
	JButton confirmButton = new JButton();
	JButton backButton = new JButton();
	
	final JLabel playersNumLabel = new JLabel("Please enter in the new player name: ");
	final JTextField playersPerRace = new JTextField();
	
	// Step 2: Set the properties of the components
	confirmButton.setText("Enter");
	confirmButton.setToolTipText("Insert the player.");
	backButton.setText("Back");
	backButton.setToolTipText("Go back.");


	// Step 3: Create containers to hold the components
	
	addPlayerScreen = new JFrame("Mario");		
	
	JPanel nameRowOne = new JPanel();
	JPanel nameRowTwo= new JPanel();
	JPanel bottomRow = new JPanel();
	JPanel buttons = new JPanel();
	
	playersPerRace.setColumns(10);
			
	addPlayerScreen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			
	// Step 4: Specify LayoutManagers
	
	addPlayerScreen.setLayout(new BorderLayout());
	nameRowOne.setLayout(new FlowLayout());
	nameRowTwo.setLayout(new FlowLayout());
	bottomRow.setLayout(new BorderLayout());
	buttons.setLayout(new FlowLayout());
		
	// Step 5: Add components to containers 
	
	nameRowOne.add(playersNumLabel);
	nameRowTwo.add(playersPerRace);
			
	buttons.add(confirmButton);
	buttons.add(backButton);
	
		
	bottomRow.add(buttons, BorderLayout.EAST);
	
	addPlayerScreen.add(nameRowOne, BorderLayout.NORTH);
	addPlayerScreen.add(nameRowTwo, BorderLayout.CENTER);
	addPlayerScreen.add(bottomRow, BorderLayout.SOUTH);
	
	// Step 6: Arrange to handle events in the user interface
	
	
	backButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			addPlayerScreen.setVisible(false);
			
		}
		
	});
	
	confirmButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(screen, 
					"Are you sure you want to add the player " + playersPerRace.getText() + "?",
					"Continue?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				
				
				game.addHalfWayThrough(playersPerRace.getText());
				JOptionPane.showMessageDialog(addPlayerScreen, "Player " +playersPerRace.getText()+ " added!");
				int responsee = JOptionPane.showConfirmDialog(screen, 
						"Do you want to add " + playersPerRace.getText() + " to the current race?",
						"Continue?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (responsee == JOptionPane.YES_OPTION) {
					game.addNewToRace();
					game.setPlayersPerRace(game.getPlayersPerRace()+1);
					//game.minusPlayersPlayed(game.getPlayersPerRace());
					System.out.println();
					System.out.println(game.getPlayersPlayed());
					System.out.println();
					gameScreen.setVisible(false);
					gameScreen();
				}
				}
				addPlayerScreen.setVisible(false);
			}
		
	});
	
	// Step 7: Display the GUI
	addPlayerScreen.pack();
	addPlayerScreen.setVisible(true);
}

public void leaderBoard() {

	
	// Step 1: create the components
	leaderBoardOpen = true;
	JButton closeButton = new JButton();
		
	final JLabel races = new JLabel("Leader Board");
	final JTextField playersPerRace = new JTextField();
	
	// Step 2: Set the properties of the components
	
	closeButton.setText("Close");
	closeButton.setToolTipText("Close the window.");
	
	// Step 3: Create containers to hold the components
	
	leaderScreen = new JFrame("Leader Board");		
	
	JPanel nameRowOne = new JPanel();
	JPanel nameRowTwo= new JPanel();
	JPanel bottomRow = new JPanel();
	JPanel buttons = new JPanel();
	
	playersPerRace.setColumns(10);
			
	//leaderScreen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	
			
	// Step 4: Specify LayoutManagers
	
	leaderScreen.setLayout(new BorderLayout());
	nameRowOne.setLayout(new FlowLayout());
	nameRowTwo.setLayout(new FlowLayout());
	bottomRow.setLayout(new BorderLayout());
	buttons.setLayout(new FlowLayout());
		
	// Step 5: Add components to containers 
	
	
	nameRowOne.add(races);
	
	//final ArrayList<Player> racers = game.getInOrder();
	final ArrayList<Player> racers = game.getPlayers();
	
	String[] columnNames = {"Position","Player",
            "Points"};
	final Object[][] data = new Object[racers.size()][3];
	
	
	for(int i = 0; i < racers.size(); i++) {
		
		data[i][0] = (i+1);
		data[i][1] = racers.get(i).getName();
		data[i][2] = racers.get(i).getScore();
		
		
	}
	
	
	
	JTable table = new JTable(data, columnNames);
	
	
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
	
	JScrollPane playesTable = new JScrollPane(table);
	
			
	buttons.add(closeButton);
	
	bottomRow.add(buttons, BorderLayout.EAST);
	
	leaderScreen.add(nameRowOne, BorderLayout.NORTH);
	leaderScreen.add(playesTable, BorderLayout.CENTER);
	
	
	
	leaderScreen.add(bottomRow, BorderLayout.SOUTH);
	
	// Step 6: Arrange to handle events in the user interface
	
	
	
	closeButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			leaderBoardOpen = false;
			leaderScreen.setVisible(false);
			
			if(end)
				System.exit(0);
			
		}
		
	});
	
	
	// Step 7: Display the GUI
	leaderScreen.pack();
	leaderScreen.setVisible(true);
}
	
	public static void main(String[] args) {
		new GUI();

	}
	
	
}