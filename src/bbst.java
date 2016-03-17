import java.util.Scanner;
import java.io.*;

public class bbst {
	private final static int n = 5;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//if makefile is not for *.java, but just for bbst.java
		//can do following to make other files except for bbst
		
		//Runtime.getRuntime().exec("javac TreeNode.java");
		//Runtime.getRuntime().exec("javac RBTree.java");
		//Runtime.getRuntime().exec("javac EventTree.java");
		//Runtime.getRuntime().exec("javac AutoTester.java");
		
		String inputPath = args[0];
		File inputFile = new File(inputPath);
		
		/*int[] ID = {2,4,6,8,5,7,1,10,15};
		int[] count = {1,1,1,1,1,1,1,1,1};
		TreeNode root = new TreeNode(ID[0], count[0]);
		RBTree.updateRoot(root);
		for(int i = 1; i < ID.length; i++){
			TreeNode node = new TreeNode(ID[i], count[i]);
			RBTree.insert(node);
		}*/
		
		//AutoTester.treetc_01();
		//AutoTester.eventtc_01();
		//AutoTester.eventtc_02();
	}
}
