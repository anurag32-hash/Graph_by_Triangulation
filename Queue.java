public class Queue<V> {

    public V[] queue;
    public int capacity, currentSize,rear;
	
    public Queue(int capacity) {    
       queue = (V[]) new Object[capacity];
       this.capacity=capacity;
       this.rear=-1;
       this.currentSize=0;

    }

    public int size() {
       return this.currentSize;
    }

    public boolean isEmpty() {
       return (this.currentSize==0);
    }
	
    public boolean isFull() {
       return (this.currentSize==this.capacity);
    }

    public void enqueue(V node) {
       if(this.isFull()){
         this.grow_size();
         this.currentSize+=1;
         this.rear+=1;
         this.queue[this.rear]=node;
       }
       else if(this.isEmpty()){
        this.rear=0;
        this.queue[0]=node;
        this.currentSize+=1;
       
       }
       else{
        this.currentSize+=1;
        this.rear=((this.rear)+1);
        this.queue[this.rear]=node;
       }
    
    }

    public void grow_size(){
        V[] temp = null;
        if(this.currentSize==this.capacity){
            temp=(V[]) new Object[2*this.capacity];
            System.arraycopy(this.queue,0,temp,0,this.capacity);

        }
        this.queue=temp;
        this.capacity=2*this.capacity;

    }

    public V dequeue() {
        
          if(this.isEmpty()==true){
            return null;
          }
          V min = this.queue[0];
           
           for(int j=0;j<this.rear;j++){
            this.queue[j]=this.queue[j+1];
           }
           this.currentSize=this.currentSize-1;
           this.rear=this.rear-1;
           return min;
    }
    public void display(){
 
      if (this.isEmpty()) {
            System.out.println("Queue is empty");
    }
        for(int i =0; i<this.currentSize;i++){
            
            System.out.println(this.queue[i]);

        }
    }

}

