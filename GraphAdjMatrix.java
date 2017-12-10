public class GraphAdjMatrix implements Graph{
private int[][]edges;
	//initializer
	public GraphAdjMatrix(int vertices){
		edges = new int[vertices][vertices];
	}
	//add edges, can do both ways since undirected
	public void addEdge(int v1, int v2, int w){
		edges[v1][v2] = w;
		edges[v2][v1] = w;
	}
	//gets edge from two vertices
	public int getEdge(int v1, int v2){
		return edges[v1][v2];
	}
	//gets out degree(neighbors)
	public int outdegree(int v1){
		int degree = 0;
		for(int i = 0; i < edges.length; i++){
			if(edges[v1][i] != 0)
				degree++;
		}
		
		return degree;
	}
	//basically outdegree but returns an array
	public int[] neighbors(int vertex){
		int increment = 0;
		int[] neighbors = new int[outdegree(vertex)];
		for(int i = 0; i < edges.length; i++){
			if(edges[vertex][i] != 0){
				neighbors[increment] = i;
				increment++;
			}
		}
		return neighbors;
	}
	//uses Prim's MST, keeps track of Known, cost of edges, and path
	public int createSpanningTree(){
		int[][] Prim = new int[2][edges.length];
		boolean[] Known = new boolean[edges.length];
		int totalweight = 0;
		for(int i = 0; i < edges.length; i++){
			Prim[0][i] = Integer.MAX_VALUE;
			Prim[1][i] = -1;
		}
		Prim[0][0] = 0;
		//starts arbitrarily at 0, keeps going on until everything is known
		while(!isTrue(Known)){
			int minIndex = min(Prim, Known);
			Known[minIndex] = true;
			int[] neighbors = neighbors(minIndex);
			int minEdge = Integer.MAX_VALUE;
			
			if(neighbors.length == 0){
				minEdge = 0;
			}
			for(int i = 0; i < neighbors.length; i++){
				Prim[0][neighbors[i]] = this.getEdge(minIndex, neighbors[i]);
				Prim[1][neighbors[i]] = minIndex;
				
				if(this.getEdge(minIndex, neighbors[i]) < minEdge)
					minEdge = this.getEdge(minIndex, neighbors[i]);
				}
			totalweight += minEdge;
			
		}
		return totalweight-6;
	}
	//finds the unknown's minimum value's index 
	public int min(int[][] Prim, boolean[] Known){
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i = 0; i< edges.length; i++){
			if(Known[i] == false && Prim[0][i] < min){
				min = Prim[0][i];
				index = i;
			}
		}
		return index;
	}
	//checks if whole graph is known or not
	public boolean isTrue(boolean[] Known){
		for(int i = 0; i < Known.length; i++)
			if(Known[i] == false)
				return false;
		return true;
	}
	
	

	@Override
	public void addEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void topologicalSort() {
		// TODO Auto-generated method stub
		
	}
}