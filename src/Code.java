import java.util.ArrayList;
import java.util.List;

class Node {
	int start;
	int end;
	int weight;

	public Node(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getWeight() {
		return weight;
	}
}

public class Code {

	static List<Node> graph;
	static int distrance[];
	static int preNode[];
	static boolean visit[];
	static int graphSize = 8;

	public static void dijkstra(int r) {
		// r : 시작으로 삼을 정점 : 

		distrance = new int[graphSize];
		preNode = new int[graphSize];
		visit = new boolean[graphSize];

		for (int i = 0; i < graphSize; i++) {
			distrance[i] = -1; // -1 : 무한대로 가정.
			visit[i] = false;
		}

		distrance[r] = 0;
		preNode[r] = 0;
		visit[r] = true;

		int curNode;

		while (!allVisitCheck()) {

			curNode = extractMin();
			visit[curNode] = true;
			System.out.println("현재 노드 : " + curNode);

			// 연결된 노드 찾아서 거리 갱신
			for (int i = 0; i < graph.size(); i++) {

				if (graph.get(i).getStart() == curNode) {
					int loc = graph.get(i).getEnd();
					if (!visit[loc] && distrance[loc] != 0) {

						if (distrance[loc] == -1){
							distrance[loc] = distrance[curNode] + graph.get(i).getWeight();
							preNode[loc] = curNode;
						}
						else if (distrance[loc] > distrance[curNode] + graph.get(i).getWeight()){
							distrance[loc] = distrance[curNode] + graph.get(i).getWeight();
							preNode[loc] = curNode;
						}

					}
				}

			}

			for (int j = 0; j < graphSize; j++)
				System.out.print(distrance[j] + "  ");
			System.out.println();
			System.out.println();

		}
		
		
		System.out.println("------");
		for (int j = 0; j < graphSize; j++)
			System.out.print(preNode[j] + "  ");
		System.out.println();

	}

	public static int extractMin() {
		// 그래프에서 d값이 가장 작은 정점을 리턴한다.
		int min = 99999;
		int result = 0;

		for (int i = 0; i < graphSize; i++) {

			if (visit[i])
				continue;

			if (distrance[i] != -1 && distrance[i] != 0) {
				if (min > distrance[i]) {
					min = distrance[i];
					result = i;
				}
			}
		}

		return result;
	}

	public static boolean allVisitCheck() {

		for (int i = 0; i < visit.length; i++)
			if (visit[i] == false)
				return false;

		return true;
	}

	public static void main(String[] args) {

		graph = new ArrayList<Node>();
		
		// 예제 1 
		/*
		graph.add(new Node(0, 1, 8));
		graph.add(new Node(0, 4, 9));
		graph.add(new Node(0, 5, 11));
		graph.add(new Node(1, 2, 10));
		graph.add(new Node(2, 3, 2));
		graph.add(new Node(3, 7, 4));
		graph.add(new Node(4, 1, 6));
		graph.add(new Node(4, 2, 1));
		graph.add(new Node(4, 5, 3));
		graph.add(new Node(5, 6, 8));
		graph.add(new Node(5, 7, 8));
		graph.add(new Node(6, 3, 5));
		graph.add(new Node(6, 4, 12));
		graph.add(new Node(7, 6, 7));
		*/
		
		
		// 예제 2
		graph.add(new Node(0, 1, 7));
		graph.add(new Node(0, 2, 12));
		graph.add(new Node(0, 3, 8));
		graph.add(new Node(1, 3, 5));
		graph.add(new Node(1, 4, 9));
		graph.add(new Node(2, 5, 9));
		graph.add(new Node(2, 6, 6));
		graph.add(new Node(3, 2, 5));
		graph.add(new Node(3, 4, 2));
		graph.add(new Node(3, 6, 14));
		graph.add(new Node(4, 7, 3));
		graph.add(new Node(5, 6, 12));
		graph.add(new Node(6, 7, 7));
		graph.add(new Node(7, 5, 5));
		

		dijkstra(0);
	}
}
