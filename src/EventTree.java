public class EventTree {
	private EventTree(){}
	
	private static int sum = 0;
	
	public static void increase(int ID, int m){
		TreeNode cur = RBTree.search(ID);
		if(cur == null) RBTree.insert(cur = new TreeNode(ID, m));
		else
			cur.count += m;
		
		System.out.println(cur.count);
		
	}
	
	public static void reduce(int ID, int m){
		TreeNode cur = RBTree.search(ID);
		if(cur == null) {
			System.out.println(0);
			return;
		}
		cur.count -= m;
		if(cur.count <= 0){
			RBTree.delete(cur);
			System.out.println(0);
		}
		else{
			System.out.println(cur.count);
		}
	}
	
	public static void count(int ID){
		TreeNode cur = RBTree.search(ID);
		if(cur == null){
			System.out.println(0);
			return;
		}
		else
			System.out.println(cur.count);
	}
	
	public static void inrange(int ID1, int ID2){ //middle order visit
		TreeNode start = RBTree.search(ID1);
		TreeNode end = RBTree.search(ID2);
		sum = 0;
		if(ID1 > ID2){
			System.out.println(0);
			return;
		}
		if(start == null){             //the smallest one larger than start
			if(RBTree.getRoot() == null){
				System.out.println(0);
				return;
			} 
			start = RBTree.getSuccessorById(RBTree.getRoot(), ID1);
		}
		if(end == null){               //the largest one smaller than end
			if(RBTree.getRoot() == null) {
				System.out.println(0);
				return;
			}
			end = RBTree.getPrecursorById(RBTree.getRoot(), ID2);
		}
		if(start == null || end == null){      //no node larger than start 'OR' no node small than end
			System.out.println(0);
			return;
		}
		if(start == end){
			System.out.println(start.count);
			return;
		}
		
		//find nearest common parent
		TreeNode parent = RBTree.getCommonParent(start, end);
		if(parent == null){
			System.out.println(0);
			return;
		}
		sumCount(parent, start, end);
		
		System.out.println(sum);          //the total count for IDs between ID1 and ID2
	}
	
	public static void next(int ID){        //the lowest ID greater than this ID
		if(RBTree.getRoot() == null)
			System.out.println("0 0");
		else
			next(RBTree.getRoot(), ID);
	}
	
	//if the node is not in the tree
	public static void next(TreeNode root, int ID){
		if(ID < root.ID){
			TreeNode precursor = RBTree.getPrecursor(root);
			if(precursor == null){
				System.out.println(Integer.toString(root.ID)
						+ " " + Integer.toString(root.count));
				return;
			}
			if(ID >= precursor.ID){
				System.out.println(Integer.toString(root.ID)
						+ " " + Integer.toString(root.count));
				return;
			}
			else{
				next(root.left, ID);
			}
		}
		//equal checked
		if(ID >= root.ID){
			TreeNode successor = RBTree.getSuccessor(root);
			if(successor == null){             //no one's ID is larger than ID
				System.out.println("0 0");
				return;
			}
			if(ID < successor.ID){
				System.out.println(Integer.toString(successor.ID)
						+ " " + Integer.toString(successor.count));
				return;
			}
			else{
				next(root.right, ID);
			}
		}
	}
	
	public static void previous(int ID){    //the greatest ID less than this ID
		if(RBTree.getRoot() == null)
			System.out.println("0 0");
		else
			previous(RBTree.getRoot(), ID);
	}
	
	//if node is not in the tree
	public static void previous(TreeNode root, int ID){
		if(ID > root.ID){
			TreeNode successor = RBTree.getSuccessor(root);
			if(successor == null){
				System.out.println(Integer.toString(root.ID)
						+ " " + Integer.toString(root.count));
				return;
			}
			if(ID <= successor.ID){
				System.out.println(Integer.toString(root.ID)
						+ " " + Integer.toString(root.count));
				return;
			}
			else{
				//System.out.println(root.ID);
				previous(root.right, ID);
			}
		}
		//equal checked
		if(ID <= root.ID){
			TreeNode precursor = RBTree.getPrecursor(root);
			if(precursor == null){             //no one's ID is smaller than ID
				System.out.println("0 0");
				return;
			}
			if(ID > precursor.ID){
				System.out.println(Integer.toString(precursor.ID)
						+ " " + Integer.toString(precursor.count));
				return;
			}
			else{
				previous(root.left, ID);
			}
		}
	}
	
	public static void sumCount(TreeNode root, TreeNode start, TreeNode end){  //middle order visit
		boolean isStart = false;
		boolean isEnd = false;
		boolean isSml = false;
		boolean isLrg = false;
		if(root.ID < start.ID) isSml = true;
		if(root.ID > end.ID) isLrg = true;
		if(root.ID == start.ID) isStart = true;
		if(root.ID == end.ID) isEnd = true;
		if(root.left != null && !isStart && !isSml){
			sumCount(root.left, start, end);
		}
		if(!isSml && !isLrg){
			//System.out.println("Visiting " + root.ID);
			sum += root.count;
		}
		if(root.right != null && !isEnd && !isLrg){
			sumCount(root.right, start, end);
		}
	}
}
