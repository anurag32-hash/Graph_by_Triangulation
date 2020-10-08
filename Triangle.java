public class Triangle implements TriangleInterface {

    Point p1,p2,p3;
    Edge e1,e2,e3;
    PointInterface [] triangle_coord = new PointInterface[3];
    EdgeInterface [] triangle_edges = new EdgeInterface[3];
    int timestap=0;
    int area=0;
    int number_triangles_of_component=0;
    int max_shortest_distance=0;

    public Triangle(Point p1,Point p2,Point p3,Edge e1,Edge e2,Edge e3,int timestap,int area){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        triangle_coord[0]=p1;
        triangle_coord[1]=p2;
        triangle_coord[2]=p3;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        triangle_edges[0]=this.e1;
        triangle_edges[1]=this.e2;
        triangle_edges[2]=this.e3;
        this.timestap=timestap;
        this.area=area;
    }

    public PointInterface [] triangle_coord(){
        return this.triangle_coord;
    }
    public int getArea(){
        return this.area;
    }

    public Point getP1(){
        return this.p1;
    }
    public Point getP2(){
        return this.p2;
    } 
    public Point getP3(){
        return this.p3;
    }
    public Edge getE1(){
        return this.e1;
    }
    public Edge getE2(){
        return this.e2;
    }
    public Edge getE3(){
        return this.e3;
    }

    public boolean hasvertex(float x,float y,float z){
        if(this.getP1().getX()==x && this.getP1().getY()==y && this.getP1().getZ()==z)
            return true;
        else if(this.getP2().getX()==x && this.getP2().getY()==y && this.getP2().getZ()==z)
            return true;
        else if(this.getP3().getX()==x && this.getP3().getY()==y && this.getP3().getZ()==z)
            return true;
        else return false;
    }

    public boolean haspoint(Point p){
        if(this.getP1().compare1(p)==1 || this.getP2().compare1(p)==1 || this.getP3().compare1(p)==1)
            return true;
        else return false;
    }

    public int compare (Triangle triangle){
        if(this.timestap < triangle.timestap)
            return 1;
        else
            return -1;
    }

    public int compare1 (Triangle triangle){
        if(this.number_triangles_of_component > triangle.number_triangles_of_component)
            return 1;
        else if(this.number_triangles_of_component < triangle.number_triangles_of_component)
            return -1;
        else{
            if(this.max_shortest_distance > triangle.max_shortest_distance)
                return 1;
            else if(this.max_shortest_distance < triangle.max_shortest_distance)
                return -1;
            else
                return 1;
        }
    }

    public String toString(){
        return "["+this.getP1().toString()+","+this.getP2().toString()+","+this.getP3().toString()+"]";
    }

    public int equality_of_triangles(Triangle triangle){
        if(triangle.haspoint(this.getP1())==true && triangle.haspoint(this.getP2())==true && triangle.haspoint(this.getP3())==true)
            return 0;
        else return -1;
    }
}
