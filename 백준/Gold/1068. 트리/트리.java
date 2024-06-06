import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    List<Node> child;

    public Node() {
        child = new ArrayList<>();
    }

    public boolean isLeafNode() {
        return child.isEmpty();
    }
    
    public void toDelete(Node node) {
    	child.remove(node);
    }

}

class NodeList {
    List<Node> list;
    Node root;

    public NodeList(int size) {
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Node());
        }
    }
    
    public void setRoot(int rootIdx) {
    	root = list.get(rootIdx);
    }

    public void delete(int idx, int parentIdx) {
    	if(parentIdx == -1) {
    		root = null;
    		return;
    	}
        list.get(parentIdx).toDelete(list.get(idx));
    }

    public int check() {
        if(root == null) return 0;
        return checkNodeChild(root);
    }

	private int checkNodeChild(Node node) {
		if(node.isLeafNode()) return 1;
		int result = 0;
		for(Node childNode: node.child) {
			result += checkNodeChild(childNode);
		}
		return result;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, deleteNode;
	static NodeList nodeList;
	static int[] nodeRootList;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		nodeList = new NodeList(N); nodeRootList = new int[N];
		for(int i = 0; i<N; i++) {
			int root = Integer.parseInt(st.nextToken());
			nodeRootList[i] = root;
			if(root == -1) {
				nodeList.setRoot(i);
				continue;
			}
			nodeList.list.get(root).child.add(nodeList.list.get(i));
		}
		deleteNode = Integer.parseInt(br.readLine().trim());
	}
	
	private static void testProcess() {
		nodeList.delete(deleteNode, nodeRootList[deleteNode]);
		result = nodeList.check();
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}