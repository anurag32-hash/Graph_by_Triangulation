
public class RedBlackNode<T extends Comparable, E > {
    public T key;
    public DynamicArray<E> value = new DynamicArray<>(10) ;
    public RedBlackNode<T,E> left,right,parent;
    public String color;
    public int size;

    RedBlackNode (T key,E val){
        if(key==null && val==null)
            this.value=null;
        else {
            this.key = key;
            this.value.add(val);
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = "red";
            this.size=1;
        }
    }
    RedBlackNode(T key){
        this.key=key;
        this.left=null;
        this.right=null;
        this.parent=null;
        this.color="red";
        this.value=null;
    }



    public E getValue() {
        return this.value.get(0);
    }
    public DynamicArray<E> getValues(){
        return this.value;
    }


    public int compareTo(RedBlackNode<T,E> rb1){
        return this.key.compareTo(rb1.key);
    }
    public String toString (){
        return (String)this.key;
    }
}
