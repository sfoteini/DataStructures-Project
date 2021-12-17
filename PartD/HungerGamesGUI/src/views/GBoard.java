package views;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

import game.Board;
import game.Player;


/**
 * Creates the graphics of the board.
 */

public class GBoard extends JPanel {

	private JLabel[][] tiles;
	private int TILE_SIZE;
	
	/**
	 * Initialize the board panel.
	 */
	public GBoard(Board board, Player[] players) {
		TILE_SIZE = 20;
		
		setBackground(Color.WHITE);
		setBounds(0, 0, TILE_SIZE * board.getN(), TILE_SIZE * board.getM());
		setLayout(null);
		
		tiles = createTiles(board);
		for(int i = 0; i < board.getN(); i++) {
			for(int j = 0; j < board.getN(); j++) {
				this.add(tiles[i][j]);
			}
		}
		drawBoard(board, players);
	}
	
	/**
	 * The createTiles() method creates the board of the game.
	 * @return an array that represents the board of the game.
	 */
	private JLabel[][] createTiles(Board board){
		JLabel[][] b = new JLabel[board.getN()][board.getN()];
		for(int i = 0; i < board.getN(); i++) {
			for(int j = 0; j < board.getN(); j++) {
				b[i][j] = new JLabel("");
				b[i][j].setBounds(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				b[i][j].setOpaque(true);
				b[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				b[i][j].setHorizontalAlignment(SwingConstants.LEADING);
				b[i][j].setVerticalAlignment(SwingConstants.CENTER);
			}
		}
		
		return b;
	}
	
	/**
	 * The tileAsIcon() method sets to the given tile the appropriate image.
	 * @param tile the tile of the board.
	 * @param x the x coordinate of the tile.
	 * @param y the y coordinate of the tile.
	 * @param board the board of the game.
	 */
	public void tileAsIcon(JLabel tile, int x, int y, Board board) {
		// Look for weapon in the tile.
		for(int i = 0; i < board.getW(); i++) {
			if(board.getWeapons()[i].getX() == x && board.getWeapons()[i].getY() == y) {
					tile.setIcon(new ImageIcon(GBoard.class.getResource(board.getWeapons()[i].getImgSrc())));
					return;
				}
			}
		
		// Look for food in the tile.
		for(int i = 0; i < board.getF(); i++) {
			if(board.getFood()[i].getX() == x && board.getFood()[i].getY() == y) {
				tile.setIcon(new ImageIcon(GBoard.class.getResource(board.getFood()[i].getImgSrc())));
				return;
			}
		}
		
		// Look for trap in the tile.
		for(int i = 0; i < board.getT(); i++) {
			if(board.getTraps()[i].getX() == x && board.getTraps()[i].getY() == y) {
				tile.setIcon(new ImageIcon(GBoard.class.getResource(board.getTraps()[i].getImgSrc())));
				return;
			}
		}
		tile.setIcon(null);
	}
	
	/**
	 * The clearTiles() method sets the color of the tile to white, deletes the icon 
	 * and makes the tile invisible.
	 */
	public void clearTiles() {
		for(JLabel[] RTile : tiles) {
			for(JLabel tile : RTile) {
				// Default color : red RGB(248, 195, 191)
				tile.setBackground(new Color(248, 195, 191));
				tile.setIcon(null);
				tile.setVisible(false);
			}
		}
	}
	
	/**
	 * The isInside() method checks if the given (x,y) coordinates lie inside the limits 
	 * indicated by the array "limits".
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param limits the limits array.
	 * @return true if tile lies inside the limits.
	 */
	public boolean isInside(int x, int y, int[][] limits) {
		// minx = limits[0][0];
		// maxx = limits[1][0];
		// miny = limits[0][1];
		// maxy = limits[2][1];
		return x >= limits[0][0] && x <= limits[1][0] && 
			   y >= limits[0][1] && y <= limits[2][1];
	}
	
	/**
	 * The isOnMargin() method checks if the given (x, y) coordinates is on the border
	 * of the area indicated by the array "limits".
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param limits the limits array.
	 * @return true if the is on the border of the area.
	 */
	public boolean isOnMargin(int x, int y, int[][] limits) {
		// minx = limits[0][0];
		// maxx = limits[1][0];
		// miny = limits[0][1];
		// maxy = limits[2][1];
		return x == limits[0][0] || x == limits[1][0] || 
			   y == limits[0][1] || y == limits[2][1];
	}
	
	/**
	 * The tileAsColor() method sets the right background color to the given tile.
	 * @param tile the tile of the board.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param board the board of the game.
	 */
	public void tileAsColor(JLabel tile, int x, int y, Board board) {
		// Check for weapon area : white color RGB(255, 255, 255)
		if(isInside(x, y, board.getWeaponAreaLimits())) {
			tile.setBackground(new Color(255, 255, 255));
			return;
		}
		
		// Check for food area : grey color RGB(243, 243, 243)
		if(isInside(x, y, board.getFoodAreaLimits()) && isOnMargin(x, y, board.getFoodAreaLimits())) {
			tile.setBackground(new Color(243, 243, 243));
			return;
		}
		
		// Check for trap area : blue color RGB(204, 237, 252)
		if(isInside(x, y, board.getTrapAreaLimits()) && isOnMargin(x, y, board.getTrapAreaLimits())) {
			tile.setBackground(new Color(204, 237, 252));
			return;
		}
		
		// Default color : red RGB(248, 195, 191)
		tile.setBackground(new Color(248, 195, 191));
		return;
	}
	
	/**
	 * The drawBoard() method updates the board state.
	 */
	public void drawBoard(Board board, Player[] players) {
		clearTiles();
		
		for(int x = - board.getN() / 2; x <= board.getN() / 2; x++) {
			for(int y = - board.getM() / 2; y <= board.getM() / 2; y++) {
				tileAsColor(tiles[board.y2i(y)][board.x2j(x)], x, y, board);
				tileAsIcon(tiles[board.y2i(y)][board.x2j(x)], x, y, board);
				tiles[board.y2i(y)][board.x2j(x)].setVisible(true);
			}
		}
		
		// Add players' positions on the board
		tiles[board.y2i(players[0].getY())][board.x2j(players[0].getX())].
						setIcon(new ImageIcon(GBoard.class.getResource(players[0].getImgSrc())));
		
		tiles[board.y2i(players[1].getY())][board.x2j(players[1].getX())].
						setIcon(new ImageIcon(GBoard.class.getResource(players[1].getImgSrc())));
		
		if(players[0].getX() == players[1].getX() && players[0].getY() == players[1].getY())
			tiles[board.y2i(players[0].getY())][board.x2j(players[0].getX())].
						setIcon(new ImageIcon(GBoard.class.getResource("/resources/TwoPlayersInATile.png")));
		
	}
	
}
