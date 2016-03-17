import java.util.LinkedList;
import java.util.Queue;

public class RBTree {
	private static TreeNode root;
	private final static boolean RED = true;
	private final static boolean BLACK = false;
	
	private RBTree(){
	}
	
	public static TreeNode getRoot(){
		return root;
	}
	
	public static void updateRoot(TreeNode node){
		root = node;
		root.isRed = BLACK;
	}
	
	public static void insert(TreeNode node){
		if(root == null) {
			updateRoot(node); 
			return;
		}
		TreeNode parent = new TreeNode(0, 0);
		TreeNode cur = root;
		while(cur != null){
			parent = cur;
			if(node.ID <= parent.ID){
				cur = cur.left;
			}
			else{
				cur = cur.right;
			}
		}
		node.parent = parent;
		if(parent == null) updateRoot(node);  //inserted node is root
		else if(node.ID <= parent.ID){
			parent.left = node;
		}
		else{
			parent.right = node;
		}
		//Red Black Tree insert FIXUP
		if(node.isRed && parent.isRed) 
			insertFix(node);
	}
	
	private static void insertFix(TreeNode node){
		if(node.parent == null){ 					//root but not black
			updateRoot(node);
			return;
		}
		if(!node.isRed) return;         			//node is black
		if(!node.parent.isRed) return;  			//parent is black, nothing happen
		TreeNode parent = node.parent;
		while(parent.isRed){
			TreeNode uncle = new TreeNode(0, 0);
			if(parent == parent.parent.left)
				uncle = parent.parent.right;
			else
				uncle = parent.parent.left;
			boolean uncle_color = (uncle==null)?BLACK:uncle.isRed;
			if(uncle_color){          			//case 1
				parent.isRed = BLACK;
				if(uncle != null) uncle.isRed = BLACK;
				parent.parent.isRed = RED;
				insertFix(parent.parent);
			}
			else if(parent == parent.parent.left){ 
				//parent is grandparent's leftchild
				if(node == parent.right){ 			//case 2
					leftRotate(parent);
					//check
					insertFix(parent);							//check
				}
				else{                          		//case 3
					//check
					parent.isRed = BLACK;
					parent.parent.isRed = RED;
					rightRotate(parent.parent);					//check
					//if(uncle != null) insertFix(uncle);
				}
			}
			else{
				//parent is grandparent's rightchild
				if(node == parent.left){  			//case 2
					rightRotate(parent);
					insertFix(parent);
				}
				else{								//case 3			
					parent.isRed = BLACK;
					parent.parent.isRed = RED;
					leftRotate(parent.parent);
				}
			}
		}
	}
	
	public static TreeNode search(int ID){
		TreeNode cur = root;
		while(cur != null){
			if(cur.ID > ID){
				cur = cur.left;
				//parent = cur;
			}
			else if(cur.ID < ID){
				cur = cur.right;
				//parent = cur;
			}
			else{
				return cur;
			}
		}
		return null;          // means the node does not exist 
	}
	
	public static TreeNode findSuccessor(TreeNode node){
		if(node.left.right == null && node.right.left == null) {
			int tmpID = node.ID;
			int tmpCount = node.count;
			node.ID = node.left.ID;
			node.count = node.left.count;
			node.left.ID = tmpID;
			node.left.count = tmpCount;
			return node.left;
		}
		if(node.left.right != null){
			TreeNode cur = node.left.right;
			while(cur.right != null)
				cur = cur.right;
			int tmpID = node.ID;
			int tmpCount = node.count;
			node.ID = cur.ID;
			node.count = cur.count;
			cur.ID = tmpID;
			cur.count = tmpCount;
			return cur;
		}
		else{
			TreeNode cur = node.right.left;
			while(cur.left != null)
				cur = cur.left;
			int tmpID = node.ID;
			int tmpCount = node.count;
			node.ID = cur.ID;
			node.count = cur.count;
			cur.ID = tmpID;
			cur.count = tmpCount;
			return cur;
		}
	}
	
	public static void delete(TreeNode node){
		if(node == null) return; //if the node is null, no delete, need insert
		TreeNode cur = node;  //node's copy
		
		if(node.left == null || node.right ==null)
			cur = node;
		else
			cur = findSuccessor(node);     //exchange data(ID, count) from node to successor. get successor
		
		//leaf node
		if(cur.left == null && cur.right == null){  
			if(cur.parent == null){     //deleted node is root and no more nodes in RBTree
				updateRoot(null);
				return;
			}
			//Firstly, do deleteFix, then delete
			deleteFix(cur);
			if(cur.parent == null){     //deleted node is root and no more nodes in RBTree
				updateRoot(null);
				return;
			}
			
			cur.parent.isRed = BLACK;   //set parent BLACK
			if(cur == cur.parent.left)
				cur.parent.left = null;
			else
				cur.parent.right = null;

			//RBTree.printTree();
			return;
		}
		
		//has one child. child is the node which will instead the deleted node
		TreeNode child = cur;
		if(cur.left != null)
			child = cur.left;
		else
			child = cur.right;
		child.parent = cur.parent;
		if(child.parent == null){
			updateRoot(child);
			return;
		}
		if(cur == cur.parent.left)
			cur.parent.left = child;
		else
			cur.parent.right = child;
		//DELETE NODE:
		//1. If the deleted node is black,
		//then there will be some changes on its child
		//2. If the deleted node is red,
		//nothing will happen
		if(!cur.isRed){
			if(child.isRed) child.isRed = BLACK; //case "RED+BLACK"
			else 
				deleteFix(child);                //case "BLACK+BLACK"
		}
			
	}
	
	public static void deleteFix(TreeNode node){
		while(node != root && !node.isRed){
			TreeNode wnode = node;       //brother node
			if(node == node.parent.left){
				wnode = node.parent.right;
				//brother node is red
				//case 1 is for turning into case 2 or 3 or 4
				if(wnode.isRed){                 						//case 1
					wnode.isRed = BLACK;
					node.parent.isRed = RED;
					leftRotate(node.parent);
					wnode = node.parent.right;   //reset the wnode!!!
				}
				
				//brother node is black,
				//and its two children are black
				boolean wnode_left_isRed = (wnode.left == null)? BLACK : wnode.left.isRed;
				boolean wnode_right_isRed = (wnode.right == null)? BLACK : wnode.right.isRed;
				if(!wnode_left_isRed             						//case 2
						&& !wnode_right_isRed){
					wnode.isRed = RED;
					node = node.parent;          //set node's parent as node
	              
					//push black to parent. 
					if(node.isRed){				 //If parent is RED+BLACK now.
						node.isRed = BLACK;
						return;
					}
					else{                        //If parent is BLACK+BLACK now.
						continue;
					}
				}
				//brother node is black,
				//and its left is red, right is black
				//case 3 is for turning into case 4
				else if(!wnode_right_isRed){     						//case 3
					if(wnode.left != null) wnode.left.isRed = BLACK;
					wnode.isRed = RED;
					rightRotate(wnode);
					wnode = node.parent.right;    //reset the wnode!!!
				}
				//brother node is black,
				//and its left is red/black, right is red
				//case 4 is from case 3
				wnode.isRed = node.parent.isRed;
				if(wnode.right != null) wnode.right.isRed = BLACK;
				leftRotate(node.parent);
				node = root;
			}
			else{
				wnode = node.parent.left;
				//brother node is red
				//case 1 is for turning into case 2 or 3 or 4
				if(wnode.isRed){                 //case 1
					wnode.isRed = BLACK;
					node.parent.isRed = RED;
					rightRotate(node.parent);
					wnode = node.parent.left;   //reset the wnode!!!
				}
				
				//brother node is black,
				//and its two children are black
				boolean wnode_left_isRed = (wnode.left == null)? BLACK : wnode.left.isRed;
				boolean wnode_right_isRed = (wnode.right == null)? BLACK : wnode.right.isRed;
				if(!wnode_left_isRed             //case 2
						&& !wnode_right_isRed){
					wnode.isRed = RED;
					node = node.parent;          //set node's parent as node
					
					//push black to parent. 
					if(node.isRed){				 //If parent is RED+BLACK now.
						node.isRed = BLACK;
						return;
					}
					else{                        //If parent is BLACK+BLACK now.
						continue;
					}
				}
				//brother node is black,
				//and its right is red, left is black
				//case 3 is for turning into case 4
				else if(!wnode_left_isRed){     //case 3
					if(wnode.right != null) wnode.right.isRed = BLACK;
					wnode.isRed = RED;
					leftRotate(wnode);
					wnode = node.parent.left;    //reset the wnode!!!
				}
				//brother node is black,
				//and its left is red/black, right is red
				//case 4 is from case 3
				wnode.isRed = node.parent.isRed;
				if(wnode.left != null) wnode.left.isRed = BLACK;
				rightRotate(node.parent);
				node = root;
			}
			node.isRed = BLACK;
		}
	}
	
	public static void rightRotate(TreeNode node){
		TreeNode curRight = node.left.right;
		TreeNode cur = node.left;
		TreeNode gp = node.parent;
		node.left = curRight;
		if(curRight != null) curRight.parent = node;
		cur.right = node;
		node.parent = cur;
		cur.parent = gp;
		if(gp == null) { //means cur is root now;
			updateRoot(cur);
			return; 
		}
		if(gp.left == node)
			gp.left = cur;
		else
			gp.right = cur;
	}
	
	public static void leftRotate(TreeNode node){
		TreeNode curLeft = node.right.left;
		TreeNode cur = node.right;
		TreeNode gp = node.parent;
		node.right = curLeft;
		if(curLeft != null) curLeft.parent = node;
		cur.left = node;
		node.parent = cur;
		cur.parent = gp;
		if(gp == null) { //means cur is root now;
			updateRoot(cur);
			return; 
		}
		if(gp.left == node)
			gp.left = cur;
		else
			gp.right = cur;
	}
	
	public static void rightleftRotate(TreeNode node){}
	
	public static void leftrightRotate(TreeNode node){}
	
	public static TreeNode getCommonParent(TreeNode xnode, TreeNode ynode){
		//xnode.ID <= ynode.ID
		TreeNode parent = RBTree.getRoot();
		
		while(parent != null){
			if(parent.ID >= xnode.ID && parent.ID <= ynode.ID)
				return parent;
			if(parent.ID > ynode.ID){
				parent = parent.left;
			}
			if(parent.ID < xnode.ID){
				parent = parent.right;
			}
		}
		return parent;
	}
	
	public static void printTree(){
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i = 0; i < size; i++){
				TreeNode cur = q.poll();
				if(cur == null) {
					System.out.print("NULL ");
					TreeNode tmp = new TreeNode(-1,-1);
					q.add(tmp);
					continue;
				}
				if(cur.ID == -1){
					System.out.print("   ");
					continue;
				}
				//cur.root = root;
				String c = (cur.isRed)?"R":"B";
				System.out.print(cur.ID + c + "[" + cur.count + "]" + " ");
				if(cur.left!=null){
					q.add(cur.left);
				}
				else{
					q.add(null);
				}
				if(cur.right!=null){
					q.add(cur.right);
				}
				else{
					q.add(null);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
