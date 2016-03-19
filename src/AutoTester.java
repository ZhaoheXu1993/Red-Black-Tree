public class AutoTester {
	private AutoTester(){}
	
	//insert & delete
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
	
	//successor & precursor
	public static void treetc_02(){
		System.out.println("CHECKING SUCCESSOR");
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 0).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 1).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 2).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 3).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 4).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 5).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 6).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 7).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 8).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 9).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 10).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 11).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 12).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 13).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 14).ID);
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 15));
		System.out.println(RBTree.getSuccessorById(RBTree.getRoot(), 16));
		System.out.println("CHECKING PRECURSOR");
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 0));
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 1));
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 2).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 3).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 4).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 5).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 6).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 7).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 8).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 9).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 10).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 11).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 12).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 13).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 14).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 15).ID);
		System.out.println(RBTree.getPrecursorById(RBTree.getRoot(), 16).ID);
	}
	
	//increase & reduce
	public static void eventtc_01(){
		RBTree.printTree();
		EventTree.reduce(4,2);
		RBTree.printTree();
		EventTree.previous(15);
		EventTree.previous(6);
	}
	
	//inrange
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
		//CHECKING
		System.out.println("inrange 0 1");
		EventTree.inrange(0, 1);
		System.out.println("inrange -2 0");
		EventTree.inrange(-2, 0);
		System.out.println("inrange 9 12");
		EventTree.inrange(9, 12);
		System.out.println("inrange 9 16");
		EventTree.inrange(9, 16);
		System.out.println("inrange 15 16");
		EventTree.inrange(15, 16);
		System.out.println("inrange 16 20");
		EventTree.inrange(16, 20);
	}
	
	//next & previous
	public static void eventtc_03(){
		RBTree.printTree();
		System.out.println("CHECKING NEXT");
		EventTree.next(0);
		EventTree.next(1);
		EventTree.next(2);
		EventTree.next(3);
		EventTree.next(4);
		EventTree.next(5);
		EventTree.next(6);
		EventTree.next(7);
		EventTree.next(8);
		EventTree.next(9);
		EventTree.next(10);
		EventTree.next(11);
		EventTree.next(12);
		EventTree.next(13);
		EventTree.next(14);
		EventTree.next(15);
		EventTree.next(16);
		System.out.println("CHECKING PREVIOUS");
		EventTree.previous(0);
		EventTree.previous(1);
		EventTree.previous(2);
		EventTree.previous(3);
		EventTree.previous(4);
		EventTree.previous(5);
		EventTree.previous(6);
		EventTree.previous(7);
		EventTree.previous(8);
		EventTree.previous(9);
		EventTree.previous(10);
		EventTree.previous(11);
		EventTree.previous(12);
		EventTree.previous(13);
		EventTree.previous(14);
		EventTree.previous(15);
		EventTree.previous(16);
		
	}
}
