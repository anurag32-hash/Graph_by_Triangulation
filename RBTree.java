public class RBTree<T extends Comparable, E>   {
    public RedBlackNode<T,E> root;
   public int rbtreesize;


    public RBTree(){
        this.root=null;
        this.rbtreesize=0;
    }

    public boolean isRed (RedBlackNode<T,E> x){
        if(x==null)
            return false;
        else{
            if(x.color.compareTo("red")==0)
                return true;
            return false;
        }
    }
    public int size(RedBlackNode<T,E> x){
        if(x==null)
            return 0;
        return x.size;
    }
    public int size(){
        return size(root);
    }

    public void flipcolors(RedBlackNode h) {
        if(h.color.equals("black"))
            h.color="red";
        else h.color="black";

        if(h.right.color.equals("black"))
            h.right.color="red";
        else
            h.right.color="black";

        if(h.left.color.equals("black"))
            h.left.color="red";
        else
            h.left.color="black";
    }


    public void insert(T key, E value) {
        root=reccinsert(root,key,value);
        root.color="black";
    }
    public RedBlackNode<T,E> reccinsert(RedBlackNode<T,E> node,T key,E value){
        /*if(h==null)
            return new RedBlackNode<>(key,value);
        int cmp = key.compareTo(h.key);
        if(cmp<0)
            h.left=reccinsert(h.left,key,value);
        else if(cmp>0)
            h.right=reccinsert(h.right,key,value);
        else
            h.value.add(value);

        if(isRed(h.right) && !isRed(h.left))
            h=leftrotation(h);
        if(isRed(h.left) && isRed(h.left.left))
            h=rightrotation(h);
        if(isRed(h.left) && isRed(h.right))
            flipcolors(h);
        h.size=size(h.left)+size(h.right)+1;

        return h;*/
        if (node == null)
            return new RedBlackNode<T, E>(key, value);
        if (key.compareTo(node.key) == 0)
        {
            node.getValues().add(value);
            return node;
        }
        if (key.compareTo(node.key) < 0)
            node.left = reccinsert(node.left, key, value);
        if (key.compareTo(node.key) > 0)
            node.right = reccinsert(node.right, key, value);

        //To balance the tree
        if (isRed(node.right) && isRed(node.right.left))
        {
            if (isRed(node.left))
                flipcolors(node);
            else
            {
                node.right = rightrotation(node.right);
                node = leftrotation(node);
            }
        }
        if (isRed(node.left) && isRed(node.left.right))
        {
            if (isRed(node.right))
                flipcolors(node);
            else
            {
                node.left = leftrotation(node.left);
                node = rightrotation(node);
            }
        }
        if (isRed(node.right) && isRed(node.right.right))
        {
            if (isRed(node.left))
                flipcolors(node);
            else
                node = leftrotation(node);
        }
        if (isRed(node.left) && isRed(node.left.left))
        {
            if (isRed(node.right))
                flipcolors(node);
            else
                node = rightrotation(node);
        }


        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private void fixtree(RedBlackNode<T,E> node) {
        if (node.parent == null) {
            node.color = "black";
            return;
        }
        while (node.parent.color.equals("red")) {

            RedBlackNode uncle = null;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != null && uncle.color.equals("red")) {
                    node.parent.color = "black";
                    uncle.color = "black";
                    node.parent.parent.color = "red";
                    node = node.parent.parent;
                    if (node.parent == null)
                        break;
                    else
                        continue;
                }
                if (node == node.parent.right) {
                    node = node.parent;
                    leftrotation(node);
                }
                node.parent.color = "black";
                node.parent.parent.color = "red";
                rightrotation(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                if (uncle != null && uncle.color.equals("red")) {
                    node.parent.color = "black";
                    uncle.color = "black";
                    node.parent.parent.color = "red";
                    node = node.parent.parent;
                    if (node.parent == null)
                        break;
                    else
                        continue;
                }
                if (node == node.parent.left) {
                    node = node.parent;
                    rightrotation(node);
                }
                node.parent.color = "black";
                node.parent.parent.color = "red";
                leftrotation(node.parent.parent);
            }
        }
        root.color = "black";
        if (node.parent == null) {
            node.color = "black";
            return;
        } else if (node.parent.color.equals("black"))
            return;
        else if ((node.parent) != null && node != null) {
            //System.out.println("going to start while loop");
            while (node.parent.color == "red") {
                // System.out.println("while going on");
                if (node.parent == node.parent.parent.left) {
                    if (node.parent.parent.right != null) {
                        if (node.parent.parent.right.color.equals("red")) {
                            node.parent.color = "black";
                            node.parent.parent.right.color = "black";
                            node.parent.parent.color = "red";
                            node = node.parent.parent;
                            // System.out.println("recursive call");
                            //System.out.println(node.color);
                            fixtree(node);
                            return;
                        } else {
                            if (node == node.parent.left) {
                                rightrotation(node.parent.parent);
                                swapcolors(node.parent, node.parent.right);
                                break;
                            } else {
                                leftrotation(node.parent);
                                rightrotation(node.parent);
                                swapcolors(node, node.right);
                                break;
                            }
                        }
                    } else {
                        if (node == node.parent.left) {
                            rightrotation(node.parent.parent);
                            swapcolors(node.parent, node.parent.right);
                            break;
                        } else {
                            leftrotation(node.parent);
                            rightrotation(node.parent);
                            swapcolors(node, node.right);
                            break;
                        }
                    }
                } else {
                    if (node.parent.parent.left != null) {
                        if (node.parent.parent.left.color.equals("red")) {
                            node.parent.color = "black";
                            node.parent.parent.left.color = "black";
                            node.parent.parent.color = "red";
                            node = node.parent.parent;
                            fixtree(node);
                            return;
                        } else {
                            if (node == node.parent.right) {
                                leftrotation(node.parent.parent);
                                swapcolors(node.parent, node.parent.left);
                                break;
                            } else {
                                rightrotation(node.parent);
                                leftrotation(node.parent);
                                swapcolors(node, node.left);
                                break;
                            }
                        }
                    } else {
                        if (node == node.parent.right) {
                            leftrotation(node.parent.parent);
                            swapcolors(node.parent, node.parent.left);
                            break;
                        } else {
                            rightrotation(node.parent);
                            leftrotation(node.parent);
                            swapcolors(node, node.left);
                            break;
                        }
                    }
                }
            }
        }
    }


    private RedBlackNode<T, E> rightrotation (RedBlackNode<T,E> h){
        RedBlackNode<T,E> x = h.left;
        h.left=x.right;
        x.right=h;
        x.color=x.right.color;
        x.right.color="red";
        x.size=h.size;
        h.size=size(h.left)+size(h.right)+1;
        return x;
    }


    private RedBlackNode<T,E> leftrotation (RedBlackNode<T,E> h) {
        RedBlackNode<T,E> x = h.right;
        h.right=x.left;
        x.left=h;
        x.color=x.left.color;
        x.left.color="red";
        x.size=h.size;
        h.size=size(h.left)+size(h.right)+1;
        return x;
    }

    private void swapcolors (RedBlackNode<T,E> node1,RedBlackNode<T,E> node2){
        String str = node1.color;
        node1.color = node2.color;
        node2.color = str;
    }

    public void display (RedBlackNode<T,E> root){
        if(root==null)
            return;

        display(root.left);

        System.out.print(root.key+",");
        DynamicArray<Integer> arr = (DynamicArray<Integer>) root.getValues();
        for(int i=0;i<arr.size;i++)
            System.out.print(arr.get(i)+",");

        System.out.println("");
        display(root.right);
    }


    public RedBlackNode<T, E> search(T key) {
        RedBlackNode<T,E> temp = root;
        RedBlackNode<T,E> dnode = new RedBlackNode<>(key);
        while (true) {
            if (temp == null) {
                return null;
            } else if (dnode.compareTo(temp) == 0) {
                return temp;
            } else if (dnode.compareTo(temp) > 0) {
                temp = temp.right;
            } else if (dnode.compareTo(temp) < 0) {
                temp = temp.left;
            }
        }
    }

    public RedBlackNode<T,E> search1 (T key){
        RedBlackNode<T,E> temp = root;
        RedBlackNode<T,E> dnode = new RedBlackNode<>(key);
        while(true){
            if(temp==null)
                return null;
            else if(dnode.compareTo(temp)==0)
                return temp;
           else if(dnode.compareTo(temp)>0)
               temp=temp.right;
           while(dnode.compareTo(temp)<0){
               if(temp.left == null)
                   return temp;
               temp=temp.left;
               if(dnode.compareTo(temp)<0)
                   continue;
               else if(temp==null){
                   temp=temp.parent;
                   return temp;
               }
               else if(dnode.compareTo(temp)>0){
                   temp=temp.parent;
                   return temp;
               }
            }

        }
    }

    /*public ArrayList<E> reccsearch (T t1,T t2){
        ArrayList<E> arr;
        arr = reccsearch1(root,t1,t2);
        return arr;
    }*/

    /*public ArrayList<E> reccsearch1 (RedBlackNode<T,E> node, T t1, T t2){
        ArrayList<E> arr = new ArrayList<>();

        if(node != null) {

             if (t1.compareTo(node.key) <= 0)
                arr.add(reccsearch1(node.left, t1, t2));
              if (t1.compareTo(node.key) <= 0 && t2.compareTo(node.key) >= 0)
                arr.add(node.value);
              if (t2.compareTo(node.key) >= 0)
                arr.add(reccsearch1(node.right, t1, t2));
        }
        return arr;
    }*/
}