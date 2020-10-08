public class DynamicArray<T> {

    T[] arr;
    int capacity,size;

    public DynamicArray(int capacity){
        this.capacity=capacity;
        this.size=0;
        arr = (T[]) new Object[capacity];
    }

    public DynamicArray(){
        arr=(T[]) new Object [100];
        this.size=0;
        this.capacity=50;
    }

    public void add(T element){
        if(this.size >= this.capacity)
            this.growsize();
        this.arr[this.size]=element;
        this.size+=1;
    }
    public T get (int i){
        return this.arr[i];
    }

    public void growsize(){
        boolean flag=false;
        T[] temp = null;
        if(this.size==this.capacity){
            if(2*this.capacity >= Integer.MAX_VALUE-8){
                temp=(T[]) new Object[Integer.MAX_VALUE-8];
                System.arraycopy(this.arr,0,temp,0,this.capacity);
                flag=true;
            }
            else{
                temp=(T[]) new Object[2*this.capacity];
                System.arraycopy(this.arr,0,temp,0,this.capacity);
            }
        }
        this.arr=temp;
        if(flag==true)
            this.capacity=Integer.MAX_VALUE-8;
        else this.capacity=2*this.capacity;
    }

    public void set (int i,T element){
        if(i<this.size){
            this.arr[i]=element;
        }
    }
}
