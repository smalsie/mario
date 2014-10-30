import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class TESTGUI {
private JFrame screen;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TESTGUI();
	}
	
	public TESTGUI() {
		screen = new JFrame("");
		screen.setSize(1100,800);
		firstScreen();
	}
	
public void firstScreen() {

		
		// Step 1: create the components
		
		JButton addPlayer = new JButton();
		JButton startGame = new JButton();
		final JTextField playerName = new JTextField();
		JButton quitButton = new JButton();
		JLabel playerNameLabel = new JLabel("Name: ");
		final JLabel noOfPlayers = new JLabel();
		// Step 2: Set the properties of the components
		addPlayer.setText("Add Player");
		addPlayer.setToolTipText("Add a new player to the tournament.");
		startGame.setText("Start");
		startGame.setToolTipText("Start the tournament.");
		quitButton.setText("Quit");
		quitButton.setToolTipText("Quit the application.");
	
		
		// Step 3: Create containers to hold the components
		
		final JPanel firstScreen = new JPanel();
		
		JPanel nameRowOne = new JPanel();
		JPanel nameRowTwo= new JPanel();
		JPanel bottomRow = new JPanel();
		JPanel buttons = new JPanel();
		
		playerName.setColumns(30);
				
		
				
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
		
		buttons.add(quitButton);
		
		bottomRow.add(buttons, BorderLayout.EAST);
		bottomRow.add(noOfPlayers, BorderLayout.WEST);
		
		firstScreen.add(nameRowOne, BorderLayout.NORTH);
		firstScreen.add(nameRowTwo, BorderLayout.CENTER);
		firstScreen.add(bottomRow, BorderLayout.SOUTH);
		
		screen.add(firstScreen);
		// Step 6: Arrange to handle events in the user interface
		
		
		addPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(playerName.getText().equals("")) {
					JOptionPane.showMessageDialog(screen, "Please enter in a player name!");
				} else {
					
				}
			}

		});

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				firstScreen.setVisible(false);
				secondScreen();
			
			}

		});
		
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
			
		});
		
		
		// Step 7: Display the GUI
		screen.pack();
		screen.setVisible(true);
	}

public void secondScreen() {

	
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
	
	JPanel secondScreen = new JPanel();		
	
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
	
	buttons.add(quitButton);
	
	bottomRow.add(buttons, BorderLayout.EAST);
	
	secondScreen.add(nameRowOne, BorderLayout.NORTH);
	secondScreen.add(nameRowTwo, BorderLayout.CENTER);
	secondScreen.add(bottomRow, BorderLayout.SOUTH);
	
	
	screen.add(secondScreen);
	// Step 6: Arrange to handle events in the user interface
	
	
	
	quitButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//exitApp();
			
		}
		
	});
	
	backButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			
		}
		
	});
	//screen.setVisible(true);
	//screen.pack();

	
}


}
