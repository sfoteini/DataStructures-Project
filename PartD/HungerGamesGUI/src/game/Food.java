package game;


/**
 * Class denoting a food supply.
 */
public class Food {
	private int id;
	private int x;
	private int y;
	private int points;
	private String imgSrc;
	
	/**
	 * Initializes the Food by setting its id, x and y coordinates
	 * and points to zero.
	 */
	Food(){
		id = 0;
		x = 0;
		y = 0;
		points = 0;
		imgSrc = "";
	}
	
	/**
	 * Initializes the Food by setting its id, x and y coordinates, points.
	 * @param id the id of the Food.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @param points the points of the supply.
	 */
	Food(int id, int x, int y, int points){
		this.id = id;
		this.x = x;
		this.y = y;
		this.points = points;
		imgSrc = loadImgSrc();
	}
	
	/**
	 * Initializes the food supply by an other food object.
	 * @param f the food object.
	 */
	Food(Food f){
		id = f.id;
		x = f.x;
		y = f.y;
		points = f.points;
		imgSrc = f.imgSrc;
	}
	
	/**
	 * The loadImgSrc() method calculates the source of the image.
	 * @return the source.
	 */
	public String loadImgSrc() {
		int i = (int)(Math.random() * 3 + 1);
		String src = "/resources/Food" + i + ".png";
		return src;
	}
	
	/**
	 * The setId() method sets the id of the food supply.
	 * @param id the id to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * The setX() method sets the x coordinate of the food supply.
	 * @param x the x value to be set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * The setY() method sets the y coordinate of the food supply.
	 * @param y the y value to be set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * The setPoints() method sets the points that the player earns when they eat the food.
	 * @param points the points to be set.
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * The setImgSrc() method set the source of the icon of the food supply.
	 * @param imgSrc the source to be set.
	 */
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	/**
	 * The getId() method returns the id of the food supply.
	 * @return the id of the food supply.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getX() method returns the x coordinate of the food supply.
	 * @return the x coordinate of the food supply.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * The getY() method returns the y coordinate of the food supply.
	 * @return the y coordinate of the food supply.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * The getPoints() method returns the points that the player earns when they eat the food.
	 * @return the points of the food supply.
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * The getImgSrc() method returns the source of the icon of the food supply.
	 * @return the source of the image.
	 */
	public String getImgSrc() {
		return imgSrc;
	}
}
