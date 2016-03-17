public class AutoTester {
	private AutoTester(){}
	
	public static void treetc_01(){
		RBTree.delete(RBTree.search(7));
		RBTree.delete(RBTree.search(8));
		RBTree.delete(RBTree.search(4));
		RBTree.delete(RBTree.search(10));
		RBTree.insert(new TreeNode(10, 1));
		RBTree.delete(RBTree.search(8));
		RBTree.insert(new TreeNode(8, 1));     
		RBTree.delete(RBTree.search(10));
		RBTree.delete(RBTree.search(15));
		RBTree.delete(RBTree.search(5));
		RBTree.delete(RBTree.search(2));
		RBTree.delete(RBTree.search(6));
		RBTree.insert(new TreeNode(17, 1));   
		RBTree.insert(new TreeNode(5, 1)); 
		RBTree.delete(RBTree.search(8));
		RBTree.printTree();
	}
	
	public static void eventtc_01(){
		RBTree.printTree();
		EventTree.reduce(4,2);
		RBTree.printTree();
		EventTree.previous(15);
		EventTree.previous(6);
	}
	
	public static void eventtc_02(){
		RBTree.printTree();
		System.out.println(RBTree.getCommonParent(RBTree.search(2), RBTree.search(15)).ID);
		System.out.println(RBTree.getCommonParent(RBTree.search(7), RBTree.search(15)).ID);
		System.out.println(RBTree.getCommonParent(RBTree.search(10), RBTree.search(15)).ID);
		System.out.println("inrange 2 5");
		EventTree.inrange(2, 5);
		System.out.println("inrange 2 15");
		EventTree.inrange(2, 15);
		System.out.println("inrange 4 15");
		EventTree.inrange(4, 15);
		System.out.println("inrange 6 10");
		EventTree.inrange(6, 10);
		System.out.println("inrange 1 1");
		EventTree.inrange(1, 1);
		System.out.println("inrange 6 6");
		EventTree.inrange(6, 6);
	}
}
