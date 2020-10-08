public class Edge implements EdgeInterface {

    Point p1;
    Point p2;
    float distance=0;
    int index=0,rev_index=0;
    PointInterface[] edge = new PointInterface[2];
    DynamicArray<Edge> edge_edge_list = new DynamicArray<>();
    DynamicArray<Triangle> edge_triangle_list = new DynamicArray<>();

    public Edge(Point p1,Point p2,int index,int rev_index){
        this.p1=p1;
        this.p2=p2;
        edge[0]=p1;
        edge[1]=p2;
        this.index=index;
        this.rev_index=rev_index;
        this.distance = (float) Math.sqrt( Math.pow(p1.getX()-p2.getX(),2) + Math.pow(p1.getY()-p2.getY(),2) + Math.pow(p1.getZ()-p2.getZ(),2));
    }

    public PointInterface [] edgeEndPoints(){
        return this.edge;
    }

    public float getDistance(){
        return this.distance;
    }

    public DynamicArray<Edge> getEdge_edge_list(){
        return this.edge_edge_list;
    }

    public DynamicArray<Triangle> getEdge_triangle_list(){
        return this.edge_triangle_list;
    }

    public int get_size_edge_triangle_list(){
        return this.getEdge_triangle_list().size;
    }

    public int compare(Edge edge){
        if(this.getDistance() < edge.getDistance())
            return 1;
        else return -1;
    }
    public String toString(){
        return ("["+this.p1.toString()+","+this.p2.toString()+"]");
    }
}
