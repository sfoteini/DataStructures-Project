package views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Button;
import game.Game;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Creates the main frame of the game.
 */
public class GameFrame extends JFrame {

	private JPanel contentPane;
	private PlayerInfo player1Info;
	private PlayerInfo player2Info;
	private GBoard gBoard;
	private JLabel lblRound;
	private JTextArea txtrInfo;
	private Button nextRoundButton;
	private Game game;


	/**
	 * Initialize the frame.
	 */
	public GameFrame(String player1Type, String player2Type) {
		// Set the frame
		setTitle("Hunger Games");
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 30, 900, 680);
		
		// set look and feel
		try {
			UIManager.setLookAndFeel(Menu.getLookAndFeelClassname("Windows"));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Set players info areas
		player1Info = new PlayerInfo();
		player1Info.setBounds(10, 490, 210, 150);
		getContentPane().add(player1Info);
		
		player2Info = new PlayerInfo();
		player2Info.setBounds(674, 490, 210, 150);
		getContentPane().add(player2Info);
		
		// Start a new game
		game = new Game(0);
		game.start(player1Type, player2Type);
		
		// Make the GBoard
		gBoard = new GBoard(game.getBoard(), game.getPlayers());
		gBoard.setBounds(247, 66, 400, 400);
		getContentPane().add(gBoard);
		
		// Add the round label
		lblRound = new JLabel("ROUND " + game.getRound());
		lblRound.setHorizontalAlignment(SwingConstants.CENTER);
		lblRound.setBounds(372, 30, 150, 25);
		getContentPane().add(lblRound);
		
		// Add the info area
		txtrInfo = new JTextArea();
		txtrInfo.setOpaque(false);
		txtrInfo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtrInfo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtrInfo.setEditable(false);
		txtrInfo.setText("");
		txtrInfo.setBounds(247, 490, 400, 95);
		contentPane.add(txtrInfo);
		
		// Add the next round button
		nextRoundButton = new Button("NEXT ROUND");
		nextRoundButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		nextRoundButton.setForeground(Color.WHITE);
		nextRoundButton.setBackground(new Color(204, 0, 51));
		nextRoundButton.setActionCommand("NEXT ROUND");
		nextRoundButton.setBounds(397, 618, 100, 25);
		contentPane.add(nextRoundButton);
		
		
		// Update the player information and icon
		player1Info.setTextArea(game.getPlayers()[0]);
		player2Info.setTextArea(game.getPlayers()[1]);
		player1Info.getLblPlayerIcon().setIcon(new ImageIcon(GameFrame.class.getResource(game.getPlayers()[0].getImgSrc())));
		player2Info.getLblPlayerIcon().setIcon(new ImageIcon(GameFrame.class.getResource(game.getPlayers()[1].getImgSrc())));
		
		nextRoundEvent();
	}
	
	/**
	 * The nextRoundEvent() method adds an action to the nextRoundButton. When the nextRoundButton
	 * is pressed, the next round starts.
	 */
	public void nextRoundEvent() {
		nextRoundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Run next round
				game.run();
				
				// Update graphics in the board
				gBoard.drawBoard(game.getBoard(), game.getPlayers());
				
				// Update round label
				lblRound.setText("Round " + game.getRound());
				
				// Update info areas
				txtrInfo.setText(game.getGameInfo());
				player1Info.setTextArea(game.getPlayers()[0]);
				player2Info.setTextArea(game.getPlayers()[1]);
				
				// Check for game over
				if(game.getGameOver()) {
					nextRoundButton.setEnabled(false);
					nextRoundButton.setVisible(false);
					JOptionPane.showMessageDialog(null, "Game over! " + game.check(), "Game Over", -1);
				}
			}
		});
	}

	/**
	 * The getGBoard() method returns the gBoard of the game.
	 * @return the gBoard.
	 */
	public GBoard getGBoard() {
		return gBoard;
	}
	
	/**
	 * The getPlayer1Info() method returns the panel of player's 1 info.
	 * @return the info panel.
	 */
	public PlayerInfo getPlayer1Info() {
		return player1Info;
	}
	
	/**
	 * The getPlayer2Info() method returns the panel of player's 2 info.
	 * @return the info panel.
	 */
	public PlayerInfo getPlayer2Info() {
		return player2Info;
	}
	
	/**
	 * The getLblRound() returns the label of round.
	 * @return the label of round.
	 */
	public JLabel getLblRound() {
		return lblRound;
	}
	
	/**
	 * The getTxtrInfo() method returns the info text area.
	 * @return the info text area.
	 */
	public JTextArea getTxtrInfo() {
		return txtrInfo;
	}
	
}
