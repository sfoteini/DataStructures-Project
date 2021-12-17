package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


/**
 * Creates the home page of the game.
 */
public class Menu extends JFrame {

	private JPanel contentPane;
	private JPanel homePanel;
	private final ButtonGroup player1TypeButtonGroup;
	private final ButtonGroup player2TypeButtonGroup;
	private Button playButton;
	
	private String player1Type;
	private String player2Type;

	/**
	 * Initialize the frame.
	 */
	public Menu() {
		setTitle("Home - Hunger Games");
		player1TypeButtonGroup = new ButtonGroup();
		player2TypeButtonGroup = new ButtonGroup();
		
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 30, 900, 680);
		
		// Set look and feel
		try {
			UIManager.setLookAndFeel(Menu.getLookAndFeelClassname("Windows"));
		}
		catch(Exception ex) {
	        ex.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		homePanel = new JPanel();
		homePanel.setOpaque(false);
		homePanel.setBounds(192, 70, 500, 500);
		contentPane.add(homePanel);
		homePanel.setLayout(null);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setOpaque(false);
		selectionPanel.setBounds(30, 150, 440, 300);
		selectionPanel.setLayout(null);
		homePanel.add(selectionPanel);
		
		JLabel lblSelectPlayers = new JLabel("SELECT PLAYERS");
		lblSelectPlayers.setBounds(10, 11, 150, 25);
		selectionPanel.add(lblSelectPlayers);
		
		JLabel lblPlayer = new JLabel("PLAYER 1");
		lblPlayer.setBounds(10, 47, 100, 25);
		selectionPanel.add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("PLAYER 2");
		lblPlayer_1.setBounds(10, 134, 100, 25);
		selectionPanel.add(lblPlayer_1);
		
		// Add 6 JRadioButtons for the player type
		JRadioButton rdbtnRandomPlayer = new JRadioButton("Random Player");
		player1TypeButtonGroup.add(rdbtnRandomPlayer);
		rdbtnRandomPlayer.setOpaque(false);
		rdbtnRandomPlayer.setBounds(20, 79, 120, 25);
		rdbtnRandomPlayer.setActionCommand("Random Player");
		selectionPanel.add(rdbtnRandomPlayer);
		
		JRadioButton rdbtnHeuristicPlayer = new JRadioButton("Heuristic Player");
		player1TypeButtonGroup.add(rdbtnHeuristicPlayer);
		rdbtnHeuristicPlayer.setOpaque(false);
		rdbtnHeuristicPlayer.setBounds(160, 79, 120, 25);
		rdbtnHeuristicPlayer.setActionCommand("Heuristic Player");
		selectionPanel.add(rdbtnHeuristicPlayer);
		
		JRadioButton rdbtnMinMaxPlayer = new JRadioButton("Min Max Player");
		player1TypeButtonGroup.add(rdbtnMinMaxPlayer);
		rdbtnMinMaxPlayer.setOpaque(false);
		rdbtnMinMaxPlayer.setBounds(300, 79, 120, 25);
		rdbtnMinMaxPlayer.setActionCommand("Min Max Player");
		selectionPanel.add(rdbtnMinMaxPlayer);
		
		JRadioButton rdbtnRandomPlayer_1 = new JRadioButton("Random Player");
		player2TypeButtonGroup.add(rdbtnRandomPlayer_1);
		rdbtnRandomPlayer_1.setOpaque(false);
		rdbtnRandomPlayer_1.setBounds(20, 166, 120, 25);
		rdbtnRandomPlayer_1.setActionCommand("Random Player");
		selectionPanel.add(rdbtnRandomPlayer_1);
		
		JRadioButton rdbtnHeuristicPlayer_1 = new JRadioButton("Heuristic Player");
		player2TypeButtonGroup.add(rdbtnHeuristicPlayer_1);
		rdbtnHeuristicPlayer_1.setOpaque(false);
		rdbtnHeuristicPlayer_1.setBounds(160, 166, 120, 25);
		rdbtnHeuristicPlayer_1.setActionCommand("Heuristic Player");
		selectionPanel.add(rdbtnHeuristicPlayer_1);
		
		JRadioButton rdbtnMinMaxPlayer_1 = new JRadioButton("Min Max Player");
		player2TypeButtonGroup.add(rdbtnMinMaxPlayer_1);
		rdbtnMinMaxPlayer_1.setOpaque(false);
		rdbtnMinMaxPlayer_1.setBounds(300, 166, 120, 25);
		rdbtnMinMaxPlayer_1.setActionCommand("Min Max Player");
		selectionPanel.add(rdbtnMinMaxPlayer_1);
		
		// Add the play button
		playButton = new Button("PLAY");
		playButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		playButton.setForeground(Color.WHITE);
		playButton.setBackground(new Color(204, 0, 51));
		playButton.setActionCommand("PLAY");
		playButton.setBounds(170, 265, 100, 25);
		selectionPanel.add(playButton);
		
		/*
		 * Create an event for the play button.
		 */
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(buttonSelected(player1TypeButtonGroup) && buttonSelected(player2TypeButtonGroup)) {
					player1Type = calculatePlayerType(player1TypeButtonGroup);
					player2Type = calculatePlayerType(player2TypeButtonGroup);
		
					
					GameFrame gameFrame = new GameFrame(player1Type, player2Type);
					gameFrame.setVisible(true);
						
					setVisible(false);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select players!", "Error Message", 0);
				}
				
			}
		});
		
		// Set the background image
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Menu.class.getResource("/resources/HomeFrame.png")));
		label.setBounds(192, 70, 500, 500);
		contentPane.add(label);
	}
	
	/**
	 * The buttonSelected() checks if there is a selected JRadioButton in a button group.
	 * @param buttons the button group.
	 * @return true if a button is selected.
	 */
	private boolean buttonSelected(ButtonGroup buttons) {
		ArrayList<AbstractButton> listRadioButton = Collections.list(buttons.getElements());
		for(AbstractButton jrb : listRadioButton) {
			if(((JRadioButton) jrb).isSelected())
				return true;
		}
		return false;
	}
	
	/**
	 * The calculatePlayerType() method calculates the type of the selected player.
	 * @param buttons the button group.
	 * @return the type of the player.
	 */
	private String calculatePlayerType(ButtonGroup buttons) {
		ArrayList<AbstractButton> listRadioButton = Collections.list(buttons.getElements());
		for(AbstractButton jrb : listRadioButton) {
			if(((JRadioButton) jrb).isSelected())
				return ((JRadioButton) jrb).getActionCommand();
		}
		return "";
	}
	
	/**
	 * The getPlayer1Type() method returns the type of player 1.
	 * @return the type of player 1.
	 */
	public String getPlayer1Type() {
		return player1Type;
	}
	
	/**
	 * The getPlayer2Type() method returns the type of player 2.
	 * @return the type of player 2.
	 */
	public String getPlayer2Type() {
		return player2Type;
	}
	
	/**
	 * The getLookAndFellClassName() method (helper method) returns the class name of 
	 * the installed LookAndFeel based on a given name.
	 * @param name the given name.
	 * @return the class name if installed or null.
	 */
	public static String getLookAndFeelClassname(String name) {
		LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
	    for (LookAndFeelInfo info : laf) {
	        if (info.getName().contains(name)) {
	            return info.getClassName();
	        }
	    }
	    return null;
	}
}
