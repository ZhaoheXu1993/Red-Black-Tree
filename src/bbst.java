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
		initialize(inputPath);
		System.out.println("Finished Initialization");
		
		//following is input from CMD, so using scanner
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			String tmpString = sc.nextLine();
			String[] operation = new String[3];
			operation = tmpString.split(" ");
			//System.out.println(operation.length);
			int arg0 = 0;
			int arg1 = 0;
			if(operation.length == 1) return;
			if(operation.length == 2)
				arg0 = Integer.parseInt(operation[1]);
			else{
				arg0 = Integer.parseInt(operation[1]);
				arg1 = Integer.parseInt(operation[2]);
			}
			switch(operation[0]){
				case "increase":
					EventTree.increase(arg0, arg1);
					break;
				case "reduce":
					EventTree.reduce(arg0, arg1);
					break;
				case "count":
					EventTree.count(arg0);
					break;
				case "inrange":
					EventTree.inrange(arg0, arg1);
					break;
				case "next":
					EventTree.next(arg0);
					break;
				case "previous":
					EventTree.previous(arg0);
					break;
				case "getParent":
					TreeNode start = null;
					TreeNode end = null;
					if(RBTree.search(arg0) == null) {
						start = RBTree.getSuccessorById(RBTree.getRoot(),arg0);
					}
					else{
						start = RBTree.search(arg0);
					}
					if(RBTree.search(arg1) == null){
						end = RBTree.getPrecursorById(RBTree.getRoot(),arg1);
					}
					else{
						end = RBTree.search(arg1);
					}
					if(start == null || end == null) break;
					System.out.println(RBTree.getCommonParent(start, end).ID);
					break;
				default: 
					break;
			}
		}
		
		//AutoTester.treetc_01(); //insert & delete
		//AutoTester.treetc_02(); //successor & precursor
		//AutoTester.eventtc_01(); //increase & reduce
		//AutoTester.eventtc_02(); //common parent & inrange
		//AutoTester.eventtc_03(); //next & previous
	}
	
	public static void initialize(String inputPath){
		File inputFile = new File(inputPath);
		BufferedReader reader = null;            
		int treeSize = 0;
		//here can use BufferReader， InputStreamReader ...
		try{
			reader = new BufferedReader(new FileReader(inputFile));
			treeSize = Integer.parseInt(reader.readLine());
			//System.out.println(treeSize); //CHECKING READ
		}catch(Exception e){
			e.printStackTrace();
		}

		//int count = 0;
		String tmpString = null;
		for(int i = 0; i < treeSize; i++){
			try{
				String[] node = new String[2];
				tmpString = reader.readLine();
				node = tmpString.split(" ");
				//System.out.println(Integer.parseInt(node[0]) + "[" + Integer.parseInt(node[1]) + "]");  //CHECKING READ
				TreeNode newnode = new TreeNode(Integer.parseInt(node[0]), Integer.parseInt(node[1]));
				RBTree.insert(newnode);
				//count++;
				//System.out.println(count);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
