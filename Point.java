public class Point implements PointInterface {

    float x,y,z;
    float [] coordinates = new float[3];
    int index=0;
    DynamicArray<Point> point_point_list = new DynamicArray<>();
    DynamicArray<Edge> point_edge_list = new DynamicArray<>();
    DynamicArray<Triangle> point_triangle_list = new DynamicArray<>();

    public Point(float x,float y,float z,int index){
        this.x=x;
        this.y=y;
        this.z=z;
        coordinates[0]=x;
        coordinates[1]=y;
        coordinates[2]=z;
        this.index=index;
    }

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public float getZ(){
        return this.z;
    }
    public float[] getXYZcoordinate(){
        return coordinates;
    }
    public DynamicArray<Point> getPoint_point_list(){
        return this.point_point_list;
    }
    public DynamicArray<Edge> getPoint_edge_list(){
        return this.point_edge_list;
    }
    public DynamicArray<Triangle> getPoint_triangle_list(){
        return this.point_triangle_list;
    }

    public int compare1 (Point p){
        if(this.getX()==p.getX() && this.getY()==p.getY() && this.getZ()==p.getZ())
            return 1;
        else return -1;
    }

    public String getString (){
        String str;
        str = String.valueOf(this.getX())+this.getY()+this.getZ();
        return str;
    }
    public String toString(){
        return ("("+this.getX()+","+this.getY()+","+this.getZ()+")");
    }

    public int compare(Point p){
        if(this.getX() < p.getX())
            return 1;
        else if(this.getX() > p.getX())
            return -1;
        else {
            if(this.getY() < p.getY())
                return 1;
            else if(this.getY() > p.getY())
                return -1;
            else {
                if(this.getZ() < p.getZ())
                    return 1;
                else if(this.getZ() > p.getZ())
                    return -1;
                else
                    return 1;
            }
        }
    }
    public float distance(Point p){
        return (float) Math.sqrt(Math.pow(p.getX()-this.getX(),2)+Math.pow(p.getY()-this.getY(),2)+Math.pow(p.getZ()-this.getZ(),2));
    }




}
