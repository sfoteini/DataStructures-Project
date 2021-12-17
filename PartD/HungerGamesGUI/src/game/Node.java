package game;
import java.util.ArrayList;

/**
 * Class denoting a node in the MinMax tree.
 */
public class Node {
	private Node parent;
	private ArrayList<Node> children;
	private int nodeDepth;
	private int[] nodeMove;					// x, y coordinates of the current position,
											// number of the dice.
	private Board nodeBoard;
	private double nodeEvaluation;
	
	/**
	 * Initializes the node by settings its variables to null.
	 */
	Node(){
		parent = null;
		children = new ArrayList<Node>();
		nodeDepth = 0;
		nodeMove = new int[0];
		nodeBoard = null;
		nodeEvaluation = Double.NEGATIVE_INFINITY;
	}
	
	/**
	 * Initializes the node by setting its parent node, children nodes, depth, 
	 * number of move, board and evaluation number.
	 * @param parent the parent of the node.
	 * @param children the children of the node.
	 * @param nodeDepth the depth of the node.
	 * @param nodeMove the number of move.
	 * @param nodeBoard the board of the node.
	 * @param nodeEvaluation the evaluation of the node's move.
	 */
	Node(Node parent, ArrayList<Node> children, int nodeDepth, int[] nodeMove, 
			Board nodeBoard, double nodeEvaluation){
		
		this.parent = parent;
		this.nodeDepth = nodeDepth;
		this.nodeBoard = nodeBoard;
		this.nodeEvaluation = nodeEvaluation;
		
		// Copy the arrayList.
		this.children = new ArrayList<Node>(children.size());
		for(int i = 0; i < children.size(); i++){
			this.children.add(i, children.get(i));
		}
		
		// Copy the array.
		this.nodeMove = new int[nodeMove.length];
		for(int i = 0; i < nodeMove.length; i++) {
			this.nodeMove[i] = nodeMove[i];
		}
	}
	
	/**
	 * Initializes the node by an other node object.
	 * @param n the node object.
	 */
	Node(Node n){
		parent = n.parent;
		nodeDepth = n.nodeDepth;
		this.nodeBoard = new Board(n.nodeBoard);
		nodeEvaluation = n.nodeEvaluation;
		
		// Copy the arrayList.
		children = new ArrayList<Node>(n.children.size());
		for(int i = 0; i < n.children.size(); i++){
			children.add(i, n.children.get(i));
		}
		
		// Copy the array.
		nodeMove = new int[n.nodeMove.length];
		for(int i = 0; i < n.nodeMove.length; i++) {
			nodeMove[i] = n.nodeMove[i];
		}
	}
	
	/**
	 * The setParent() method sets the parent of the node.
	 * @param parent the parent to be set.
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * The setChildren() method sets the children of the node.
	 * @param children the children to be set.
	 */
	public void setChildren(ArrayList<Node> children) {
		this.children =  children;
	}
	
	/**
	 * The setNodeDepth() method sets the depth of the node.
	 * @param nodeDepth the depth to be set.
	 */
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	
	/**
	 * The setNodeMove() method sets the move of the node.
	 * @param nodeMove the move to be set.
	 */
	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
	
	/**
	 * The setNodeBoard() method sets the board of the node.
	 * @param nodeBoard the board to be set.
	 */
	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	
	/**
	 * The setNodeEvaluation() method sets the evaluation of the node's move.
	 * @param nodeEvaluation the evaluation to be set.
	 */
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
	/**
	 * The detParent() method returns the parent of the node.
	 * @return the parent of the node.
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * The getChildren() method returns the children of the node.
	 * @return the children of the node.
	 */
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	/**
	 * The getNodeDepth() method returns the depth of the node.
	 * @return the depth of the node.
	 */
	public int getNodeDepth() {
		return nodeDepth;
	}
	
	/**
	 * The getNodeMove() method returns the move of the node.
	 * @return the move of the node.
	 */
	public int[] getNodeMove() {
		return nodeMove;
	}
	
	/**
	 * The getNodeBoard() method returns the board of the node.
	 * @return the board of the node.
	 */
	public Board getNodeBoard() {
		return nodeBoard;
	}
	
	/**
	 * The getNodeEvaluation() method returns the evaluation of the node.
	 * @return the evaluation of the node.
	 */
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	
	/**
	 * The addChild() method adds a node in the children arrayList.
	 * @param child the node to be added.
	 */
	public void addChild(Node child) {
		children.add(child);
	}
	
}
