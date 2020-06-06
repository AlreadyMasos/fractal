import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.JComponent;


 class JImageDisplay extends JComponent{
     private static final long serialVersionUID = 1L;
     private BufferedImage buf;

     public JImageDisplay(int widht, int height){
        buf = new BufferedImage(widht, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(widht,height));
        return;
     }

     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage (buf, 0, 0, buf.getWidth(), buf.getHeight(), null);
         return;
     }

     public void clearImage(){
         int widht = getWidth();
         int height = getHeight();
         int[] rgbArray = new int[widht*height];
         buf.setRGB(0,0,widht,height,rgbArray,0,1);

     }

     public void drawPixel(int x, int y, int rgbColor){
         buf.setRGB(x,y,rgbColor);
         return;
     }
 }
