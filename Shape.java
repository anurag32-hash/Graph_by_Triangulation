public class Shape implements ShapeInterface {
    int triangle_count = 0;
    int point_count = 0;
    int edge_count = 0;
    DynamicArray<Triangle> alltriangles = new DynamicArray<>();
    DynamicArray<Edge> alledges = new DynamicArray<>();
    DynamicArray<Point> allpoints = new DynamicArray<>();
    RBTree<Integer, Integer> trianglerbtree = new RBTree<>();
    RBTree<String, Integer> edgerbtree = new RBTree<>();
    RBTree<String, Integer> pointrbtree = new RBTree<>();                                              

    public boolean ADD_TRIANGLE(float[] triangle_coord) {

        if (checktriangle(triangle_coord) == true) {
            //System.out.println("triangle is okay");
            RedBlackNode<String, Integer> point_node1 = pointrbtree.search(String.valueOf(triangle_coord[0]) + (triangle_coord[1]) + (triangle_coord[2]));
            RedBlackNode<String, Integer> point_node2 = pointrbtree.search(String.valueOf(triangle_coord[3]) + (triangle_coord[4]) + (triangle_coord[5]));
            RedBlackNode<String, Integer> point_node3 = pointrbtree.search(String.valueOf(triangle_coord[6]) + (triangle_coord[7]) + (triangle_coord[8]));

            if (point_node1 == null) {
                //  System.out.println("point1 in null");
                Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2], this.point_count);
                allpoints.add(p1);
                pointrbtree.insert(String.valueOf(triangle_coord[0]) + (triangle_coord[1]) + (triangle_coord[2]), this.point_count);
                this.point_count += 1;
            }
            if (point_node2 == null) {
                Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5], this.point_count);
                allpoints.add(p2);
                pointrbtree.insert(String.valueOf(triangle_coord[3]) + (triangle_coord[4]) + (triangle_coord[5]), this.point_count);
                this.point_count += 1;
            }
            if (point_node3 == null) {
                Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8], this.point_count);
                allpoints.add(p3);
                pointrbtree.insert(String.valueOf(triangle_coord[6]) + (triangle_coord[7]) + (triangle_coord[8]), this.point_count);
                this.point_count += 1;
            }

            Point p1 = allpoints.get(pointrbtree.search(String.valueOf(triangle_coord[0]) + triangle_coord[1] + triangle_coord[2]).getValue());
            Point p2 = allpoints.get(pointrbtree.search(String.valueOf(triangle_coord[3]) + triangle_coord[4] + triangle_coord[5]).getValue());
            Point p3 = allpoints.get(pointrbtree.search(String.valueOf(triangle_coord[6]) + triangle_coord[7] + triangle_coord[8]).getValue());

            RedBlackNode<String, Integer> edge_node1 = edgerbtree.search(String.valueOf(triangle_coord[0]) + triangle_coord[1] + triangle_coord[2] + triangle_coord[3] + triangle_coord[4] + triangle_coord[5]);
            RedBlackNode<String, Integer> edge_node2 = edgerbtree.search(String.valueOf(triangle_coord[3]) + triangle_coord[4] + triangle_coord[5] + triangle_coord[6] + triangle_coord[7] + triangle_coord[8]);
            RedBlackNode<String, Integer> edge_node3 = edgerbtree.search(String.valueOf(triangle_coord[6]) + triangle_coord[7] + triangle_coord[8] + triangle_coord[0] + triangle_coord[1] + triangle_coord[2]);

            if (edge_node1 == null) {

                Edge e1 = new Edge(p1, p2, this.edge_count,this.edge_count+1);
                alledges.add(e1);
                edgerbtree.insert(p1.getString() + p2.getString(), this.edge_count);
                this.edge_count += 1;

                Edge e2 = new Edge(p2, p1, this.edge_count,this.edge_count-1);
                alledges.add(e2);
                edgerbtree.insert(p2.getString() + p1.getString(), this.edge_count);
                this.edge_count += 1;
            }

            if (edge_node2 == null) {

                Edge e3 = new Edge(p2, p3, this.edge_count,this.edge_count+1);
                alledges.add(e3);
                edgerbtree.insert(p2.getString() + p3.getString(), this.edge_count);
                this.edge_count += 1;

                Edge e4 = new Edge(p3, p2, this.edge_count,this.edge_count-1);
                alledges.add(e4);
                edgerbtree.insert(p3.getString() + p2.getString(), this.edge_count);
                this.edge_count += 1;
            }

            if (edge_node3 == null) {

                Edge e5 = new Edge(p3, p1, this.edge_count,this.edge_count+1);
                alledges.add(e5);
                edgerbtree.insert(p3.getString() + p1.getString(), this.edge_count);
                this.edge_count += 1;

                Edge e6 = new Edge(p1, p3, this.edge_count,this.edge_count-1);
                alledges.add(e6);
                edgerbtree.insert(p1.getString() + p3.getString(), this.edge_count);
                this.edge_count += 1;
            }

            Edge e1 = alledges.get(edgerbtree.search(String.valueOf(triangle_coord[0]) + triangle_coord[1] + triangle_coord[2] + triangle_coord[3] + triangle_coord[4] + triangle_coord[5]).getValue());
            Edge rev_e1 = alledges.get(edgerbtree.search(String.valueOf(triangle_coord[3]) + triangle_coord[4] + triangle_coord[5] + triangle_coord[0] + triangle_coord[1] + triangle_coord[2]).getValue());
            Edge e2 = alledges.get(edgerbtree.search(String.valueOf(triangle_coord[3]) + triangle_coord[4] + triangle_coord[5] + triangle_coord[6] + triangle_coord[7] + triangle_coord[8]).getValue());
            Edge rev_e2 = alledges.get(edgerbtree.search(String.valueOf(triangle_coord[6]) + triangle_coord[7] + triangle_coord[8] + triangle_coord[3] + triangle_coord[4] + triangle_coord[5]).getValue());
            Edge e3 = alledges.get(edgerbtree.search(String.valueOf(triangle_coord[6]) + triangle_coord[7] + triangle_coord[8] + triangle_coord[0] + triangle_coord[1] + triangle_coord[2]).getValue());
            Edge rev_e3 = alledges.get(edgerbtree.search(String.valueOf(triangle_coord[0]) + triangle_coord[1] + triangle_coord[2] + triangle_coord[6] + triangle_coord[7] + triangle_coord[8]).getValue());

            Triangle triangle = new Triangle(p1, p2, p3, e1, e2, e3, this.triangle_count, area(triangle_coord));
            alltriangles.add(triangle);
            trianglerbtree.insert(triangle.getArea(), this.triangle_count);
            this.triangle_count += 1;

            p1.getPoint_point_list().add(p2);
            p1.getPoint_point_list().add(p3);
            p1.getPoint_edge_list().add(e1);
           // p1.getPoint_edge_list().add(rev_e1);
            p1.getPoint_edge_list().add(e3);
            //p1.getPoint_edge_list().add(rev_e3);
            p1.getPoint_triangle_list().add(triangle);

            p2.getPoint_point_list().add(p1);
            p2.getPoint_point_list().add(p3);
            p2.getPoint_edge_list().add(e1);
            //p2.getPoint_edge_list().add(rev_e1);
            p2.getPoint_edge_list().add(e2);
            //p2.getPoint_edge_list().add(rev_e2);
            p2.getPoint_triangle_list().add(triangle);

            p3.getPoint_point_list().add(p1);
            p3.getPoint_point_list().add(p2);
            p3.getPoint_edge_list().add(e2);
            //p3.getPoint_edge_list().add(rev_e2);
            p3.getPoint_edge_list().add(e3);
            //p3.getPoint_edge_list().add(rev_e3);
            p3.getPoint_triangle_list().add(triangle);

            e1.getEdge_edge_list().add(e2);
            e1.getEdge_edge_list().add(rev_e2);
            e1.getEdge_edge_list().add(e3);
            e1.getEdge_edge_list().add(rev_e3);
            e1.getEdge_triangle_list().add(triangle);

            rev_e1.getEdge_edge_list().add(e2);
            rev_e1.getEdge_edge_list().add(rev_e2);
            rev_e1.getEdge_edge_list().add(e3);
            rev_e1.getEdge_edge_list().add(rev_e3);
            rev_e1.getEdge_triangle_list().add(triangle);

            e2.getEdge_edge_list().add(e1);
            e2.getEdge_edge_list().add(rev_e1);
            e2.getEdge_edge_list().add(e3);
            e2.getEdge_edge_list().add(rev_e3);
            e2.getEdge_triangle_list().add(triangle);

            rev_e2.getEdge_edge_list().add(e1);
            rev_e2.getEdge_edge_list().add(rev_e1);
            rev_e2.getEdge_edge_list().add(e3);
            rev_e2.getEdge_edge_list().add(rev_e3);
            rev_e2.getEdge_triangle_list().add(triangle);

            e3.getEdge_edge_list().add(e1);
            e3.getEdge_edge_list().add(rev_e1);
            e3.getEdge_edge_list().add(e2);
            e3.getEdge_edge_list().add(rev_e2);
            e3.getEdge_triangle_list().add(triangle);

            rev_e3.getEdge_edge_list().add(e1);
            rev_e3.getEdge_edge_list().add(rev_e1);
            rev_e3.getEdge_edge_list().add(e2);
            rev_e3.getEdge_edge_list().add(rev_e2);
            rev_e3.getEdge_triangle_list().add(triangle);

            return true;
        } else return false;
    }

    public boolean checktriangle(float[] triangle_coord) {
        float[] cross_product = cross_product(triangle_coord);

        if (cross_product[0] == 0.0 && cross_product[1] == 0.0 && cross_product[2] == 0.0)
            return false;
        else return true;
    }

    public float[] cross_product(float[] triangle_coord) {
        float[] vector1 = new float[]{triangle_coord[3] - triangle_coord[0], triangle_coord[4] - triangle_coord[1], triangle_coord[5] - triangle_coord[2]};
        float[] vector2 = new float[]{triangle_coord[6] - triangle_coord[0], triangle_coord[7] - triangle_coord[1], triangle_coord[8] - triangle_coord[2]};
        float[] cross_product = new float[]{(vector1[1] * vector2[2]) - (vector1[2] * vector2[1]), (vector1[2] * vector2[0]) - (vector1[0] * vector2[2]), (vector1[0] * vector2[1]) - (vector1[1] * vector2[0])};
        return cross_product;
    }

    public int area(float[] triangle_coord) {
        float[] cross_product = cross_product(triangle_coord);
        float area = (float) Math.sqrt(Math.pow(cross_product[0], 2) + Math.pow(cross_product[1], 2) + Math.pow(cross_product[2], 2));
        return (int) area / 2;
    }

    public void sort_triangles(Triangle[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort_triangles(array, left, middle);
            sort_triangles(array, middle + 1, right);
            merge_triangles(array, left, middle, right);
        }
    }

    public void merge_triangles(Triangle[] array, int left, int middle, int right) {
        int s1 = middle - left + 1;
        int s2 = right - middle;

        Triangle[] left_array = new Triangle[s1];
        Triangle[] right_array = new Triangle[s2];

        for (int i = 0; i < s1; i++)
            left_array[i]=array[left+i];
        for (int j = 0; j < s2; j++)
            right_array[j]=array[middle+1+j];

        int i = 0, j = 0;
        int k = left;

        while (i < s1 && j < s2) {
            if (left_array[i].compare(right_array[j])>0) {
                array[k] = left_array[i];
                i += 1;
            } else {
                array[k] = right_array[j];
                j += 1;
            }
            k+=1;
        }
        while (i < s1) {
            array[k] = left_array[i];
            i += 1;
            k += 1;
        }
        while (j < s2) {
            array[k] = right_array[j];
            j += 1;
            k += 1;
        }
    }

    public Triangle triangle_search(float[] triangle_coord) {
        RedBlackNode<Integer, Integer> node = trianglerbtree.search(area(triangle_coord));
        if (node == null) {
            return null;
        }
        else {
            DynamicArray<Integer> arr = node.getValues();
            for (int i = 0; i < arr.size; i++) {
                Triangle triangle = alltriangles.get(arr.get(i));
                if (triangle.hasvertex(triangle_coord[0], triangle_coord[1], triangle_coord[2]) == true && triangle.hasvertex(triangle_coord[3], triangle_coord[4], triangle_coord[5]) == true && triangle.hasvertex(triangle_coord[6], triangle_coord[7], triangle_coord[8]) == true) {
                    return triangle;
                }
            }
            return null;
        }
    }

    public int TYPE_MESH(){
        boolean semi_proper =false;
        for(int i=0;i<alltriangles.size;i++){
            Triangle triangle = alltriangles.get(i);
            int a = triangle.getE1().get_size_edge_triangle_list();
            int b = triangle.getE2().get_size_edge_triangle_list();
            int c = triangle.getE3().get_size_edge_triangle_list();
            if( a>2 || b>2 || c>2)
                return 3;
            else if( a==1 || b==1 || c==1){
                semi_proper=true;
            }
        }
        if(semi_proper==true)
            return 2;
        else return 1;
    }

    public EdgeInterface [] BOUNDARY_EDGES(){
        int x = TYPE_MESH();
        if(x==1 || x==3)
            return null;
        else {
        	 DynamicArray<Edge> boundary_edges = new DynamicArray<>();
          	 boolean[] edge_signal = new boolean[alledges.size]; 
            for(int i=0;i<alledges.size;i++){
            	if(edge_signal[alledges.get(i).index]==false){
            		if(alledges.get(i).get_size_edge_triangle_list()==1){
            			Edge e = alledges.get(i);
            			edge_signal[e.index]=true;
            			edge_signal[e.rev_index]=true;
            			boundary_edges.add(alledges.get(i));
            		}
           		}
           }
            Edge[] arr = new Edge[boundary_edges.size];
            for(int i=0;i<boundary_edges.size;i++){
                arr[i]=boundary_edges.get(i);
            }
            sort_edges(arr,0,boundary_edges.size-1);
            return arr;
        }
    }

    public void sort_edges(Edge[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort_edges(array, left, middle);
            sort_edges(array, middle + 1, right);
            merge_edges(array, left, middle, right);
        }
    }

    public void merge_edges(Edge[] array, int left, int middle, int right) {
        int s1 = middle - left + 1;
        int s2 = right - middle;

        Edge[] left_array = new Edge[s1];
        Edge[] right_array = new Edge[s2];

        for (int i = 0; i < s1; i++)
            left_array[i]=array[left+i];
        for (int j = 0; j < s2; j++)
            right_array[j]=array[middle+1+j];

        int i = 0, j = 0;
        int k = left;

        while (i < s1 && j < s2) {
            if (left_array[i].compare(right_array[j])>0) {
                array[k] = left_array[i];
                i += 1;
            } else {
                array[k] = right_array[j];
                j += 1;
            }
            k+=1;
        }
        while (i < s1) {
            array[k] = left_array[i];
            i += 1;
            k += 1;
        }
        while (j < s2) {
            array[k] = right_array[j];
            j += 1;
            k += 1;
        }
    }


    public TriangleInterface[] NEIGHBORS_OF_TRIANGLE(float[] triangle_coord) {

        Triangle triangle = triangle_search(triangle_coord);
        if (triangle == null)
            return null;
        else {
            DynamicArray<Triangle> arr1 = triangle.getE1().getEdge_triangle_list();
            DynamicArray<Triangle> arr2 = triangle.getE2().getEdge_triangle_list();
            DynamicArray<Triangle> arr3 = triangle.getE3().getEdge_triangle_list();
            DynamicArray<Triangle> array = new DynamicArray<>(arr1.size+arr2.size+arr3.size-3);
            for(int i=0;i<arr1.size;i++){
                if(arr1.get(i).equality_of_triangles(triangle)!=0){
                    array.add(arr1.get(i));
                }
            }
            for(int i=0;i<arr2.size;i++){
                if(arr2.get(i).equality_of_triangles(triangle)!=0){
                    array.add(arr2.get(i));
                }
            }
            for(int i=0;i<arr3.size;i++){
                if(arr3.get(i).equality_of_triangles(triangle)!=0){
                    array.add(arr3.get(i));
                }
            }
            if (array.size != 0) {
                Triangle[] arr = new Triangle[array.size];
                for(int i=0;i<array.size;i++){
                    arr[i]=array.get(i);
                }
                sort_triangles(arr, 0, array.size - 1);
                return arr;
            } else return null;
        }
    }

    public EdgeInterface[] EDGE_NEIGHBOR_TRIANGLE(float[] triangle_coord) {

        Triangle triangle = triangle_search(triangle_coord);
        if (triangle == null)
            return null;
        else {
            return triangle.triangle_edges;
        }
    }

    public PointInterface[] VERTEX_NEIGHBOR_TRIANGLE(float[] triangle_coord) {

        Triangle triangle = triangle_search(triangle_coord);
        if (triangle == null)
            return null;
        else {
            return triangle.triangle_coord();
        }
    }

    public TriangleInterface[] EXTENDED_NEIGHBOR_TRIANGLE(float[] triangle_coord) {

        Triangle triangle = triangle_search(triangle_coord);
        if (triangle == null)
            return null;
        else {
            boolean[] signal = new boolean[alltriangles.size];

            DynamicArray<Triangle> arr1 = triangle.getP1().getPoint_triangle_list();
            DynamicArray<Triangle> arr2 = triangle.getP2().getPoint_triangle_list();
            DynamicArray<Triangle> arr3 = triangle.getP3().getPoint_triangle_list();
            DynamicArray<Triangle> array = new DynamicArray<>(arr1.size + arr2.size + arr3.size);

            for(int i=0;i<arr1.size;i++){
                if(arr1.get(i).equality_of_triangles(triangle)!=0 && signal[arr1.get(i).timestap]==false){
                    array.add(arr1.get(i));
                    signal[arr1.get(i).timestap]=true;
                }
            }
            for(int i=0;i<arr2.size;i++){
                if(arr2.get(i).equality_of_triangles(triangle)!=0 && signal[arr2.get(i).timestap]==false){
                    array.add(arr2.get(i));
                    signal[arr2.get(i).timestap]=true;
                }
            }
            for(int i=0;i<arr3.size;i++){
                if(arr3.get(i).equality_of_triangles(triangle)!=0 && signal[arr3.get(i).timestap]==false){
                    array.add(arr3.get(i));
                    signal[arr3.get(i).timestap]=true;
                }
            }
            if (array.size != 0) {
                Triangle[] arr = new Triangle[array.size];
                for(int i=0;i<array.size;i++){
                    arr[i]=array.get(i);
                }
                sort_triangles(arr, 0, array.size - 1);
                return arr;
            } else return null;
        }

    }

    public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates) {
        RedBlackNode<String, Integer> node = pointrbtree.search(String.valueOf(point_coordinates[0]) + point_coordinates[1] + point_coordinates[2]);
        if (node == null)
            return null;
        else {
            DynamicArray<Triangle> myarray = allpoints.get(node.getValue()).getPoint_triangle_list();
            Triangle[] arra = new Triangle[myarray.size];
            for(int i=0;i<myarray.size;i++){
                arra[i]=myarray.get(i);
            }
            return arra;
        }
    }

    public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
        RedBlackNode<String,Integer> node = pointrbtree.search(String.valueOf(point_coordinates[0])+point_coordinates[1]+point_coordinates[2]);
        if(node==null)
            return null;
        else {
            boolean[] signal = new boolean[allpoints.size];

            DynamicArray<Point> myarray = allpoints.get(node.getValue()).getPoint_point_list();
            DynamicArray<Point> arr = new DynamicArray<>(myarray.size);
            for(int i=0;i<myarray.size;i++){
                if(signal[myarray.get(i).index]==false) {
                    arr.add(myarray.get(i));
                    signal[myarray.get(i).index]=true;
                }
            }
            Point[] arra = new Point[arr.size];
            for(int i=0;i<arr.size;i++)
                arra[i]=arr.get(i);
            return arra;
        }
    }

    public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
        RedBlackNode<String,Integer> node = pointrbtree.search(String.valueOf(point_coordinates[0])+point_coordinates[1]+point_coordinates[2]);
        if(node==null)
            return null;
        else {
            boolean[] signal = new boolean[alledges.size];

            DynamicArray<Edge> myarray = allpoints.get(node.getValue()).getPoint_edge_list();
            DynamicArray<Edge> arr = new DynamicArray<>(myarray.size);
            for(int i=0;i<myarray.size;i++){
                if(signal[myarray.get(i).index]==false) {
                    arr.add(myarray.get(i));
                    signal[myarray.get(i).index]=true;
                }
            }
            Edge[] arra = new Edge[arr.size];
            for(int i=0;i<arr.size;i++){
                arra[i]=arr.get(i);
            }
            return arra;
        }
    }

    public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){
        return INCIDENT_TRIANGLES(point_coordinates);
    }

    public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){
        RedBlackNode<String,Integer> node = edgerbtree.search(String.valueOf(edge_coordinates[0])+edge_coordinates[1]+edge_coordinates[2]+edge_coordinates[3]+edge_coordinates[4]+edge_coordinates[5]);
        if(node==null)
            return null;
        else{
            DynamicArray<Triangle> myarray = alledges.get(node.getValue()).getEdge_triangle_list();
            Triangle[] arra = new Triangle[myarray.size];
            for(int i=0;i<myarray.size;i++){
                arra[i]=myarray.get(i);
            }
            return arra;
        }
    }

    public void bfs_count_connected_components (Triangle triangle,boolean[] triangle_signal,boolean[] edge_signal){

        Queue<Triangle> queue = new Queue<>(100);
        triangle_signal[triangle.timestap]=true;
        queue.enqueue(triangle);
        while(queue.currentSize != 0){

            Triangle triangle1 = queue.dequeue();
            Edge e1 = triangle1.getE1();
            Edge e2 = triangle1.getE2();
            Edge e3 = triangle1.getE3();

            if(edge_signal[e1.index]==false){
                for(int i=0;i<e1.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e1.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e1.getEdge_triangle_list().get(i).timestap]=true;
                        queue.enqueue(e1.getEdge_triangle_list().get(i));
                    }
                }
            }
            if(edge_signal[e2.index]==false){
                for(int i=0;i<e2.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e2.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e2.getEdge_triangle_list().get(i).timestap]=true;
                        queue.enqueue(e2.getEdge_triangle_list().get(i));
                    }
                }
            }
            if(edge_signal[e3.index]==false){
                for(int i=0;i<e3.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e3.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e3.getEdge_triangle_list().get(i).timestap]=true;
                        queue.enqueue(e3.getEdge_triangle_list().get(i));
                    }
                }
            }
            edge_signal[e1.index]=true;
            edge_signal[e1.rev_index]=true;
            edge_signal[e2.index]=true;
            edge_signal[e2.rev_index]=true;
            edge_signal[e3.index]=true;
            edge_signal[e3.rev_index]=true;
        }
    }

    public int COUNT_CONNECTED_COMPONENTS(){
        int count_connected_components=0;
        boolean[] triangle_signal = new boolean[alltriangles.size];
        boolean[] edge_signal = new boolean[alledges.size];

        for(int i=0;i<alltriangles.size;i++){
            if(triangle_signal[i]==false){
                bfs_count_connected_components(alltriangles.get(i),triangle_signal,edge_signal);
                count_connected_components+=1;
            }
        }
        return count_connected_components;
    }

    public boolean bfs_is_connected (Triangle triangle,Triangle triangle2, boolean[] triangle_signal,boolean[] edge_signal){

        Queue<Triangle> queue = new Queue<>(100);
        triangle_signal[triangle.timestap]=true;
        queue.enqueue(triangle);
        while(queue.currentSize != 0){

            Triangle triangle1 = queue.dequeue();
            Edge e1 = triangle1.getE1();
            Edge e2 = triangle1.getE2();
            Edge e3 = triangle1.getE3();

            if(edge_signal[e1.index]==false){
                for(int i=0;i<e1.getEdge_triangle_list().size;i++){
                    if(e1.getEdge_triangle_list().get(i).equality_of_triangles(triangle2)==0)
                        return true;
                    if(triangle_signal[e1.getEdge_triangle_list().get(i).timestap]==false ){
                        triangle_signal[e1.getEdge_triangle_list().get(i).timestap]=true;
                        queue.enqueue(e1.getEdge_triangle_list().get(i));
                    }
                }
            }
            if(edge_signal[e2.index]==false){
                for(int i=0;i<e2.getEdge_triangle_list().size;i++){
                    if(e2.getEdge_triangle_list().get(i).equality_of_triangles(triangle2)==0)
                        return true;
                    if(triangle_signal[e2.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e2.getEdge_triangle_list().get(i).timestap]=true;
                        queue.enqueue(e2.getEdge_triangle_list().get(i));
                    }
                }
            }
            if(edge_signal[e3.index]==false){
                for(int i=0;i<e3.getEdge_triangle_list().size;i++){
                    if(e3.getEdge_triangle_list().get(i).equality_of_triangles(triangle2)==0)
                        return true;
                    if(triangle_signal[e3.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e3.getEdge_triangle_list().get(i).timestap]=true;
                        queue.enqueue(e3.getEdge_triangle_list().get(i));
                    }
                }
            }
            edge_signal[e1.index]=true;
            edge_signal[e1.rev_index]=true;
            edge_signal[e2.index]=true;
            edge_signal[e2.rev_index]=true;
            edge_signal[e3.index]=true;
            edge_signal[e3.rev_index]=true;
        }
        return false;
    }

    public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
        Triangle triangle = triangle_search(triangle_coord_1);
        Triangle triangle1 = triangle_search(triangle_coord_2);
        boolean[] triangle_signal = new boolean[alltriangles.size];
        boolean[] edge_signal = new boolean[alledges.size];

        if(triangle==null || triangle1==null)
            return false;

        else{
            return bfs_is_connected(triangle,triangle1,triangle_signal,edge_signal);
        }
    }

    public Point bfs_centroid (Triangle triangle, boolean[] triangle_signal,boolean[] edge_signal){

        Point centroid;
        float x=0,y=0,z=0;
        int number_of_triangles=0;

        Queue<Triangle> queue = new Queue<>(100);
        triangle_signal[triangle.timestap]=true;
        queue.enqueue(triangle);
        x+=triangle.getP1().getX()+triangle.getP2().getX()+triangle.getP3().getX();
        y+=triangle.getP1().getY()+triangle.getP2().getY()+triangle.getP3().getY();
        z+=triangle.getP1().getZ()+triangle.getP2().getZ()+triangle.getP3().getZ();
        number_of_triangles+=1;

        while(queue.currentSize != 0){

            Triangle triangle1 = queue.dequeue();
            Edge e1 = triangle1.getE1();
            Edge e2 = triangle1.getE2();
            Edge e3 = triangle1.getE3();

            if(edge_signal[e1.index]==false){
                for(int i=0;i<e1.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e1.getEdge_triangle_list().get(i).timestap]==false ){
                        triangle_signal[e1.getEdge_triangle_list().get(i).timestap]=true;
                        Triangle triangle2 = e1.getEdge_triangle_list().get(i);
                        queue.enqueue(triangle2);
                        x+= triangle2.getP1().getX()+triangle2.getP2().getX()+triangle2.getP3().getX();
                        y+= triangle2.getP1().getY()+triangle2.getP2().getY()+triangle2.getP3().getY();
                        z+= triangle2.getP1().getZ()+triangle2.getP2().getZ()+triangle2.getP3().getZ();
                        number_of_triangles+=1;
                    }
                }
            }
            if(edge_signal[e2.index]==false){
                for(int i=0;i<e2.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e2.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e2.getEdge_triangle_list().get(i).timestap]=true;
                        Triangle triangle2 = e2.getEdge_triangle_list().get(i);
                        queue.enqueue(triangle2);
                        x+= triangle2.getP1().getX()+triangle2.getP2().getX()+triangle2.getP3().getX();
                        y+= triangle2.getP1().getY()+triangle2.getP2().getY()+triangle2.getP3().getY();
                        z+= triangle2.getP1().getZ()+triangle2.getP2().getZ()+triangle2.getP3().getZ();
                        number_of_triangles+=1;
                    }
                }
            }
            if(edge_signal[e3.index]==false){
                for(int i=0;i<e3.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e3.getEdge_triangle_list().get(i).timestap]==false){
                        triangle_signal[e3.getEdge_triangle_list().get(i).timestap]=true;
                        Triangle triangle2 = e3.getEdge_triangle_list().get(i);
                        queue.enqueue(triangle2);
                        x+= triangle2.getP1().getX()+triangle2.getP2().getX()+triangle2.getP3().getX();
                        y+= triangle2.getP1().getY()+triangle2.getP2().getY()+triangle2.getP3().getY();
                        z+= triangle2.getP1().getZ()+triangle2.getP2().getZ()+triangle2.getP3().getZ();
                        number_of_triangles+=1;
                    }
                }
            }
            edge_signal[e1.index]=true;
            edge_signal[e1.rev_index]=true;
            edge_signal[e2.index]=true;
            edge_signal[e2.rev_index]=true;
            edge_signal[e3.index]=true;
            edge_signal[e3.rev_index]=true;
        }
        int total_count = number_of_triangles*3;
        centroid = new Point(x/total_count,y/total_count,z/total_count,-1);
        return centroid;
    }

    public void sort_points(Point[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort_points(array, left, middle);
            sort_points(array, middle + 1, right);
            merge_points(array, left, middle, right);
        }
    }

    public void merge_points(Point[] array, int left, int middle, int right) {
        int s1 = middle - left + 1;
        int s2 = right - middle;

        Point[] left_array = new Point[s1];
        Point[] right_array = new Point[s2];

        for (int i = 0; i < s1; i++)
            left_array[i]=array[left+i];
        for (int j = 0; j < s2; j++)
            right_array[j]=array[middle+1+j];

        int i = 0, j = 0;
        int k = left;

        while (i < s1 && j < s2) {
            if (left_array[i].compare(right_array[j])>0) {
                array[k] = left_array[i];
                i += 1;
            } else {
                array[k] = right_array[j];
                j += 1;
            }
            k+=1;
        }
        while (i < s1) {
            array[k] = left_array[i];
            i += 1;
            k += 1;
        }
        while (j < s2) {
            array[k] = right_array[j];
            j += 1;
            k += 1;
        }
    }

    public PointInterface [] CENTROID (){
        boolean[] triangle_signal = new boolean[alltriangles.size];
        boolean[] edge_signal = new boolean[alledges.size];
        DynamicArray<Point> centroid_array = new DynamicArray<>();

        for(int i=0;i<alltriangles.size;i++){
            if(triangle_signal[i]==false){
                centroid_array.add(bfs_centroid(alltriangles.get(i),triangle_signal,edge_signal));
            }
        }
        Point[] arr = new Point[centroid_array.size];
        for(int i=0;i<centroid_array.size;i++)
            arr[i]=centroid_array.get(i);
        //System.out.println(centroid_array.size-1);
        sort_points(arr,0,centroid_array.size-1);
        return arr;
    }

    public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
        RedBlackNode<String,Integer> node = pointrbtree.search(String.valueOf(point_coordinates[0])+point_coordinates[1]+point_coordinates[2]);
        if(node==null)
            return null;
        else {
            Point p = allpoints.get(node.getValue());
            Triangle triangle = p.getPoint_triangle_list().get(0);
            boolean[] triangle_signal = new boolean[alltriangles.size];
            boolean[] edge_signal = new boolean[alledges.size];
            return bfs_centroid(triangle,triangle_signal,edge_signal);
        }
    }

    public void sort_diameters(Triangle[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort_diameters(array, left, middle);
            sort_diameters(array, middle + 1, right);
            merge_diameters(array, left, middle, right);
        }
    }

    public void merge_diameters(Triangle[] array, int left, int middle, int right) {
        int s1 = middle - left + 1;
        int s2 = right - middle;

        Triangle[] left_array = new Triangle[s1];
        Triangle[] right_array = new Triangle[s2];

        for (int i = 0; i < s1; i++)
            left_array[i]=array[left+i];
        for (int j = 0; j < s2; j++)
            right_array[j]=array[middle+1+j];

        int i = 0, j = 0;
        int k = left;

        while (i < s1 && j < s2) {
            if (left_array[i].compare1(right_array[j])>0) {
                array[k] = left_array[i];
                i += 1;
            } else {
                array[k] = right_array[j];
                j += 1;
            }
            k+=1;
        }
        while (i < s1) {
            array[k] = left_array[i];
            i += 1;
            k += 1;
        }
        while (j < s2) {
            array[k] = right_array[j];
            j += 1;
            k += 1;
        }
    }

    public int MAXIMUM_DIAMETER(){
        int[][] Shortest_paths = new int[alltriangles.size][alltriangles.size];
        Triangle[] temp_alltriangles = new Triangle[alltriangles.size];
        for(int i=0;i<alltriangles.size;i++){
            int number_of_triangles=0;
            boolean[] triangle_signal = new boolean[alltriangles.size];
            boolean[] edge_signal = new boolean[alledges.size];
            Queue<Triangle> queue = new Queue<>(100);
            MaxHeap<Integer> priority_queue = new MaxHeap<>();
            Triangle source = alltriangles.get(i);
            triangle_signal[source.timestap]=true;
            queue.enqueue(source);
            number_of_triangles+=1;
            Shortest_paths[source.timestap][source.timestap]=0;
            priority_queue.insert(0);
            while(!queue.isEmpty()){
                Triangle triangle = queue.dequeue();
                Edge e1 = triangle.getE1();
                Edge e2 = triangle.getE2();
                Edge e3 = triangle.getE3();

                if(edge_signal[e1.index]==false) {
                    for (int j = 0; j < e1.getEdge_triangle_list().size; j++) {
                        if (triangle_signal[e1.getEdge_triangle_list().get(j).timestap] == false) {
                            Triangle triangle1 = e1.getEdge_triangle_list().get(j);
                            triangle_signal[triangle1.timestap]=true;
                            Shortest_paths[source.timestap][triangle1.timestap] = Shortest_paths[source.timestap][triangle.timestap]+1;
                            queue.enqueue(triangle1);
                            number_of_triangles+=1;
                            priority_queue.insert(Shortest_paths[source.timestap][triangle1.timestap]);
                        }
                    }
                }
                if(edge_signal[e2.index]==false){
                    for(int j=0;j<e2.getEdge_triangle_list().size;j++){
                        if(triangle_signal[e2.getEdge_triangle_list().get(j).timestap]==false){
                            Triangle triangle1 = e2.getEdge_triangle_list().get(j);
                            triangle_signal[triangle1.timestap]=true;
                            Shortest_paths[source.timestap][triangle1.timestap] = Shortest_paths[source.timestap][triangle.timestap]+1;
                            queue.enqueue(triangle1);
                            number_of_triangles+=1;
                            priority_queue.insert(Shortest_paths[source.timestap][triangle1.timestap]);
                        }
                    }
                }
                if(edge_signal[e3.index]==false){
                    for(int j=0;j<e3.getEdge_triangle_list().size;j++){
                        if(triangle_signal[e3.getEdge_triangle_list().get(j).timestap]==false){
                            Triangle triangle1 = e3.getEdge_triangle_list().get(j);
                            triangle_signal[triangle1.timestap]=true;
                            Shortest_paths[source.timestap][triangle1.timestap] = Shortest_paths[source.timestap][triangle.timestap]+1;
                            queue.enqueue(triangle1);
                            number_of_triangles+=1;
                            priority_queue.insert(Shortest_paths[source.timestap][triangle1.timestap]);
                        }
                    }
                }
                edge_signal[e1.index]=true;
                edge_signal[e1.rev_index]=true;
                edge_signal[e2.index]=true;
                edge_signal[e2.rev_index]=true;
                edge_signal[e3.index]=true;
                edge_signal[e3.rev_index]=true;
            }
            source.number_triangles_of_component=number_of_triangles;
            if(priority_queue.extractMax()==null)
                source.max_shortest_distance=0;
            else
                source.max_shortest_distance=priority_queue.extractMax();
            temp_alltriangles[i]=alltriangles.get(i);
        }
        sort_diameters(temp_alltriangles,0,alltriangles.size-1);
        return temp_alltriangles[0].max_shortest_distance;
    }

    public Point[] bfs_closest_component(Triangle triangle,boolean[] triangle_signal,boolean[] edge_signal){

        DynamicArray<Point> points = new DynamicArray<>();

        Queue<Triangle> queue = new Queue<>(100);
        triangle_signal[triangle.timestap]=true;
        queue.enqueue(triangle);
        points.add(triangle.getP1());
        points.add(triangle.getP2());
        points.add(triangle.getP3());

        while(!queue.isEmpty()){

            Triangle triangle1 = queue.dequeue();
            Edge e1 = triangle1.getE1();
            Edge e2 = triangle1.getE2();
            Edge e3 = triangle1.getE3();

            if(edge_signal[e1.index]==false){
                for(int i=0;i<e1.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e1.getEdge_triangle_list().get(i).timestap]==false){
                        Triangle triangle2 = e1.getEdge_triangle_list().get(i);
                        triangle_signal[triangle2.timestap]=true;
                        queue.enqueue(triangle2);
                        points.add(triangle2.getP1());
                        points.add(triangle2.getP2());
                        points.add(triangle2.getP3());
                    }
                }
            }

            if(edge_signal[e2.index]==false){
                for(int i=0;i<e2.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e2.getEdge_triangle_list().get(i).timestap]==false){
                        Triangle triangle2 = e2.getEdge_triangle_list().get(i);
                        triangle_signal[triangle2.timestap]=true;
                        queue.enqueue(triangle2);
                        points.add(triangle2.getP1());
                        points.add(triangle2.getP2());
                        points.add(triangle2.getP3());
                    }
                }
            }

            if(edge_signal[e3.index]==false){
                for(int i=0;i<e3.getEdge_triangle_list().size;i++){
                    if(triangle_signal[e3.getEdge_triangle_list().get(i).timestap]==false){
                        Triangle triangle2 = e3.getEdge_triangle_list().get(i);
                        triangle_signal[triangle2.timestap]=true;
                        queue.enqueue(triangle2);
                        points.add(triangle2.getP1());
                        points.add(triangle2.getP2());
                        points.add(triangle2.getP3());
                    }
                }
            }
        }
        Point[] arr = new Point[points.size];
        for(int i=0;i<points.size;i++){
            arr[i]=points.get(i);
        }
        return arr;
    }

    public 	PointInterface [] CLOSEST_COMPONENTS(){
        Point[] point = new Point[2];
        float dist= Float.MAX_VALUE;
        DynamicArray<Point[]> shape_points_array = new DynamicArray<>();
        boolean[] triangle_signal = new boolean[alltriangles.size];
        boolean[] edge_signal = new boolean[alledges.size];
        for(int i=0;i<alltriangles.size;i++){
            if(triangle_signal[alltriangles.get(i).timestap]==false) {
                shape_points_array.add(bfs_closest_component(alltriangles.get(i), triangle_signal, edge_signal));
            }
        }

        for(int i=0;i<shape_points_array.size-1;i++){
            for(int j=i+1;j<shape_points_array.size;j++){
                for(int k=0;k<shape_points_array.get(i).length;k++){
                    for(int l=0;l<shape_points_array.get(j).length;l++){
                        if(shape_points_array.get(i)[k].distance(shape_points_array.get(j)[l]) <= dist){
                            point[0]=shape_points_array.get(i)[k];
                            point[1]=shape_points_array.get(j)[l];
                            dist=shape_points_array.get(i)[k].distance(shape_points_array.get(j)[l]);
                        }
                    }
                }
            }
        }
        return point;
    }
}