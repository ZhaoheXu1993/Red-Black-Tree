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
		if(start == null || end == null || ID1 > ID2) return;
		if(start == end){
			System.out.println(start.count);
			return;
		}
		
		//find nearest common parent
		TreeNode parent = RBTree.getCommonParent(start, end);
		sumCount(parent, start, end);
		
		System.out.println(sum);          //the total count for IDs between ID1 and ID2
	}
	
	public static void next(int ID){        //the lowest ID greater than this ID
		TreeNode cur = RBTree.search(ID);
		if(cur == null){
			System.out.println("0 0");
			return;
		}
		if(cur.right == null){
			System.out.println("0 0");
			return;
		}
		if(cur.right.left == null){
			System.out.println("0 0");
			return;
		}
		TreeNode nextNode = cur.right.left;
		while(nextNode.left != null){
			nextNode = nextNode.left;
		}
		System.out.println(Integer.toString(nextNode.ID) 
				+ " " + Integer.toString(nextNode.count));
	}
	
	public static void previous(int ID){    //the greatest ID less than this ID
		TreeNode cur = RBTree.search(ID);
		if(cur == null){
			System.out.println("0 0");
			return;
		}
		if(cur.left == null){
			System.out.println("0 0");
			return;
		}
		if(cur.left.right == null){
			System.out.println("0 0");
			return;
		}
		TreeNode preNode = cur.left.right;
		while(preNode.right != null){
			preNode = preNode.right;
		}
		System.out.println(Integer.toString(preNode.ID)
				+ " " + Integer.toString(preNode.count));
	}
	
	public static void sumCount(TreeNode root, TreeNode start, TreeNode end){  //middle order visit
		boolean isStart = false;
		boolean isEnd = false;
		if(root == start) isStart = true;
		if(root == end) isEnd = true;
		if(root.left != null && !isStart){
			sumCount(root.left, start, end);
		}
		sum += root.count;
		if(root.right != null && !isEnd){
			sumCount(root.right, start, end);
		}
	}
}
