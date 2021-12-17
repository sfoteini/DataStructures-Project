package views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;

import game.Player;
import java.awt.Cursor;


/**
 * Creates the panel for the player information.
 */

public class PlayerInfo extends JPanel {
	private JLabel lblIcon;
	private JTextArea textArea;
	private JLabel lblPlayerIcon;

	/**
	 * Initialize the PlayerInfo panel.
	 */
	public PlayerInfo() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 210, 150);
		setLayout(null);
		
		lblPlayerIcon = new JLabel("");
		lblPlayerIcon.setBounds(175, 20, 20, 20);
		add(lblPlayerIcon);
		
		textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textArea.setBounds(15, 20, 180, 110);
		add(textArea);
		
		lblIcon = new JLabel("");
		lblIcon.setBounds(0, 0, 210, 150);
		lblIcon.setIcon(new ImageIcon(PlayerInfo.class.getResource("/resources/PlayerInfo.png")));
		add(lblIcon);
	}
	
	/**
	 * The setTextArea() method updates the info text of a player in the text area.
	 * @param p the player.
	 */
	public void setTextArea(Player p) {
		String text = "Player " + p.getId() + ": \nPosition: (" + p.getX() + ", " + p.getY() + ") \n"
				+ "Score: " + p.getScore() + "\n" +
				"Weapons: \n\tPistol: " + ((p.getPistol() == null) ? 0 : 1) + "\n\tBow: " +
				((p.getBow() == null) ? 0 : 1) + "\n\tSword: " + ((p.getSword() == null) ? 0 : 1);
		textArea.setText(text);
	}
	
	/**
	 * The getLblPlayerIcon() method returns the JLabel with the player icon.
	 * @return the label with the player icon.
	 */
	public JLabel getLblPlayerIcon() {
		return lblPlayerIcon;
	}
	
}
