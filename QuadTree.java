/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro;

/*
    dörtlu agac olusturmak icin
     @param root kökü gösterir
     @param x girilen koordinatın yatay koordinatı
     @param y girilen koordinatın kuzey
     @param NW kuzey batıdan alınan dalları temsil eder
     @param NE Kuzey doğudan alınan dalları temsil eder
     @param SE Güney doğudan alınan dalları temsil eder
     @param SW Güney batıdan alınan dalları temsil eder
      
*/
public class QuadTree  {
    public Node root;

    
    public class Node {
        public int x, y;              // x- and y- coordinates
        public Node NW, NE, SE, SW;   // four subtrees
                  // associated data
        
        public Node(int x , int y) {
            this.x = x;
            this.y = y;
            
        }
    }


  /*
    dörtlü ağaca veri eklemek için 
    @param x girilecek koordinatların yatay olanı
    @param y girilecek koordinatların dikey olanı
    @param h dallar arasında gezinmek ve eleman eklemeyi kolaylaştırmak için 
    ağaçta hareketi sağlayan araç
    */
    public void insert(int x, int y) {
        root = insert(root, x, y);
        
    }

    public Node insert(Node h, int x, int y) {
        if (h == null) return new Node(x, y);
        
        else if(less(x, h.x) &&  less(y, h.y)){
             h.NW = insert(h.NW, x, y);
             
             
        }
        else if ( less(x, h.x) && !less(y, h.y)) {
            h.SW = insert(h.SW, x, y);
            
        }
        else if (!less(x, h.x) &&  less(y, h.y)) {
            h.NE = insert(h.NE, x, y );
            
        }
        else if (!less(x, h.x) && !less(y, h.y)) {
            h.SE = insert(h.SE, x, y);
            
        }
        
        return h;
        
    }


  
    


   
    /*
    daha az ve eşit olmayı ölçebilen iki farklı işi kolaylaştıran fonksiyon 
    @param k1 kullanıcının verdiği veri ışığında sonucu bulmamızda yardımcı bir değişken
    @param k2 yardımcı bir değişken
    */
    public boolean less(int k1, int k2) { return k1<k2; }
    public boolean eq  (int k1, int k2) { return k1==k2; }
}   
