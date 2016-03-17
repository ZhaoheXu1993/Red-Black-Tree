public class TreeNode {
	public int ID;
	public int count;
	public boolean isRed; 
	public TreeNode left,right,parent;
	
	public TreeNode(int ID, int count){
		this.ID = ID;
		this.count = count;
		this.isRed = true;
		left = right = parent = null;
	}
}
