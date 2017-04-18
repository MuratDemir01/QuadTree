package quadtree;

public class QuadTree  {
    public Node root;

    // helper node data type
    public class Node {
        int x, y;              // x- and y- coordinates
        public Node NW, NE, SE, SW;   // four subtrees
                  // associated data
        
        public Node(int x , int y) {
            this.x = x;
            this.y = y;
            
        }
    }


  /***********************************************************************
    *  Insert (x, y) into appropriate quadrant
    ***************************************************************************/
    public void insert(int x, int y) {
        root = insert(root, x, y);
    }

    private Node insert(Node h, int x, int y) {
        if (h == null) {
            System.out.println("node oldu");
            return new Node(x, y);
            
        }
        //// if (eq(x, h.x) && eq(y, h.y)) h.value = value;  // duplicate
        
        else if(less(x, h.x) &&  less(y, h.y)){
             h.SW = insert(h.SW, x, y);
             System.out.println("1");
        }
        else if ( less(x, h.x) && !less(y, h.y)) {
            h.NW = insert(h.NW, x, y);
            System.out.println("2");
        }
        else if (!less(x, h.x) &&  less(y, h.y)) {
            h.SE = insert(h.SE, x, y );
            System.out.println("3");
        }
        else if (!less(x, h.x) && !less(y, h.y)) {
            h.NE = insert(h.NE, x, y);
            System.out.println("4");
        }
        
        return h;
    }


  /***********************************************************************
    *  Range search.
    ***************************************************************************/

    


   /***************************************************************************
    *  helper comparison functions
    ***************************************************************************/

    public boolean less(int k1, int k2) { return k1<k2; }
    public boolean eq  (int k1, int k2) { return k1==k2; }


   /***************************************************************************
    *  test client
    ***************************************************************************/
    public static void main(String[] args) {
        
        int N=20;
        
        QuadTree st= new QuadTree();

        // insert N random points in the unit square
        for (int i = 0; i < N; i++) {
            int x = (int) (512 * Math.random());
            int y = (int) (512 * Math.random());
            // StdOut.println("(" + x + ", " + y + ")");
            
            st.insert(x, y);
            System.out.println(x + " " + y);
            
        }
        System.out.println("Done preprocessing " + N + " points");
        //System.out.println(st.root.NE.NE.x);
        //if((st.root.NE.NE.NE)==null)
          //  System.out.println("hey");
        // do some range searches
        QuadTree ks=new QuadTree();
        ks.root=st.root;
        st.root=null;
        st.insert(250,250);
        
        
        //System.out.println(st.root.NE.x);
        
        }
    
   
    }
