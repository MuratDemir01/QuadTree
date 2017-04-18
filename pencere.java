
package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;

/**
 * @author Murat's PC
 */
/*
 çizimleri denetlemeleri yazıları halleden ve en önemli sınıftır
 @param x1 yatay bir değişkendir
 @param y1 dikey bir değişkendir
 @param sayac dizinin kaç elamanının dolu olduğunu belirten bir değerdir
 @param i  for döngülerinin dönmesi için bir değişken
 @param sol çizgilerin sol noktasını tutan değişken
 @param sag çizgilerin sağ noktasını tutan değişken
 @param yukari çizgilerin yukari noktasını tutan değişken
 @param alfa alfabenin 26'dan yukarı kullanılmamasını sıfırlanmasını sağlayan değer
 @param yasak aynı değer girildiğinde fark edip engellenmesini sağlayan değer
 @param çember çemberin oluşturulup oluşturulmayacağını belirleyen değer
 @param çap çemberin çapını tutup işe yarayacağı şekilde değerini değiştirebileceğimiz değer
*/
public class pencere extends JFrame implements MouseListener,KeyListener {
    public int x1,y1,sayac,i,sol,sag,yukari,asagi,alfa,yasak,çember,cap;
    int x2[]= new int[100];
    int y2[]= new int[100];
    public Random rast;
    public QuadTree st= new QuadTree();
    public QuadTree ks= new QuadTree();
    public QuadTree yrd=  new QuadTree();
    /*
    pencere sınıfının kurucu fonksiyonu olmakla birlikte çoğu değerin ilk değerinin 
    girildiği veya tanımlandığı yer
    */
    public pencere(){
        
        super();
        
        rast = new Random();
        addMouseListener(this);
        addKeyListener(this);
        x1=0;
        y1=0;
        cap=50;
        sayac=-1;
    }
    @Override
    /*
    pencere sınıfında her bir şey yapılığında buraya geri dönüp tekrar her yeri 
    çizmemize yarayan yer
    @param g grafik sınıfının bir nevi yapay adı
    @alfabe harfleri farklı olarak kullanabilmemizi sağlayan karakter dizisi 
    */
    public void paint(Graphics g){
        super.paint(g);
        char alfabe[] ={  'A' , 'B', 'C' , 'D', 'E', 'F' , 'G', 'H', 'I', 'J', 'K', 'L', 'M','N','O',
        'P' ,'Q' , 'R', 'S', 'T', 'U', 'W', 'V', 'X', 'Y', 'Z'};
        /*
        
        */
        i=0;
        while(i<=sayac){
                asagi=0;
                yukari=512;
                sag=512;
                sol=0;
                g.setColor(Color.blue);
                bul(x2[i],y2[i]);
                g.drawLine(x2[i],asagi , x2[i], yukari);
                g.drawLine(sol, y2[i], sag, y2[i]);
                g.fillOval(x2[i]-2, y2[i]-2, 5, 5);
                alfa= i%26;
                g.setColor(Color.red);
                g.drawString("" +alfabe[alfa], x2[i], y2[i]);
                i++;
                
        
                
        }
        
        if(çember==1){
            g.setColor(Color.black);
            g.drawOval(x1, y1, cap, cap);
        }
        
        
    }
    /* 
    Her bir node çizileceğinde o node'un ailesini bulup oradan kendisini ağaç içinde 
    bulup sınırları yani asagi yukari sag sol degerlerini değiştirmemizi sağlayan 
    bu sınıfın en önemli fonksiyonu 
    @param x dizide ki değeri ağaçta bulmamız için verilmiş kopya bir x değeri
    @param y dizide ki değeri ağaçta bulmamız için verilmiş kopya bir y değeri
    */
    public void bul(int x,int y){
        ks.root=st.root;
        yrd.root=ks.root;
        int denet;
        while(i!=0){
            denet=0;
            
            if(yrd.root.x>sol){
               if(yrd.root.x<x)
                   sol=yrd.root.x;
           }
           if(yrd.root.x<sag){
               if(yrd.root.x>x){
                   sag=yrd.root.x;
               }
           }
           if(yrd.root.y>asagi){
               if(yrd.root.y<y)
                   asagi=yrd.root.y;
           }
           if(yrd.root.y<yukari){
               if(yrd.root.y>y){
                   yukari=yrd.root.y;
               }
           }
            
             if(x==ks.root.x){
                if(y==ks.root.y){                
                    break;
                }
             }
           
             
              if(x<ks.root.x){
                
                if(y<ks.root.y){
                    yrd.root=ks.root;
                    ks.root=ks.root.NW;
                    denet++;
                            
                
                }
            }
              if(denet==0){
              if(x>ks.root.x){
                
                if(y<ks.root.y){
                yrd.root=ks.root;
                ks.root=ks.root.NE;
                denet++;
                }
            }
              if(denet==0){
              if(x<ks.root.x){
                  
                if(y>ks.root.y){
                yrd.root=ks.root;
                ks.root=ks.root.SW;
               denet++;
                }
            }
              if(denet==0){
           if(x>ks.root.x){
                
                if(y>ks.root.y){
                yrd.root=ks.root;
                ks.root=ks.root.SE;
                }
            }
              }
              }
              }
           
            
            
            
        }
                ks.root=st.root;
        
    }
         
    /*
    mouse'a tıklandığında alınan konumlara ve tuşlara göre hangi elle 
    ağaca eleman ekleyebilir ya da hepsini silip yerine veri ekleyebilir
    çember çizip onları kontrol ettirebilir çemberin boyutunu değiştirebilir
    @param e farenin komutlarını içeren java class'larından birinin takma adı
    */

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if(e.getButton()== e.BUTTON1){
        çember=0;
        yasak=0;
        x1= e.getX();
        y1= e.getY();
        
        for(int j=0;j<=sayac;j++){
            if(x1==x2[j]){
                yasak=1;
            }
            if(y1==y2[j]){
                   yasak=1; 
                }
        }
        if(yasak==0){
        sayac++;
        st.insert(x1,y1);
        x2[sayac]=x1;
        y2[sayac]=y1;
        }
        }
        if(e.getButton()==e.BUTTON2){
          
          st.root=null;            
          sayac=-1;
          
           for (int m = 0; i < 20; i++) {
            sayac++;
            int otox = (int) (512 * Math.random());
            int otoy = (int) (512 * Math.random());
            for(int j=0;j<=sayac-1;j++){
            if(otox==x2[j]){
                otox = (int) (512 * Math.random());
                     otoy = (int) (512 * Math.random());
                     j=0;
            }
            if(otoy==y2[j]){
                     otox = (int) (512 * Math.random());
                     otoy = (int) (512 * Math.random());
                     j=0;
                }
            }
            
            
               
            
            x2[sayac]=otox;
            y2[sayac]=otoy;
            st.insert(otox,otoy);
               
            
           }
            
           }
           
        
        if(e.getButton()==e.BUTTON3){
            x1=e.getX();
            y1=e.getY();
            çember=1;
            
            
        }
        
            
        repaint();
        

        
        
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    /*
    klavyede A tuşuna basıldığında çemberin yarı çapını d tuşuna bastığında 
    çemberin yarı çapını büyülten fonksiyon 
    @param e javanın hazır klavye sınıfının kodlarını içeren ve o kodları kullanmamızı 
    sağlayan bir kısaltma
    */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==e.VK_A){
           cap=cap-20;
       }
       if(e.getKeyCode()==e.VK_D){
           cap=cap+20;
       }
       if(e.getKeyCode()==e.VK_S){
           st.root=null;            
          sayac=-1;
       }
       repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
