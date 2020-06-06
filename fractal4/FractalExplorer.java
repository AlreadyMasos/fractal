import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FractalExplorer {

    private int dimensionDisplay;
    private JImageDisplay image;
    private FractalGenerator generator;
    private Rectangle2D.Double compRange;

    public static void main(String[] args) {
        FractalExplorer start = new FractalExplorer(800);
        start.createAndShowGUI();
        start.drawFractal();
    }

    public FractalExplorer(int dimension){
        dimensionDisplay = dimension;
        compRange = new Rectangle2D.Double();
        generator = new Mandelbrot();
        generator.getInitialRange(compRange);
        image = new JImageDisplay(dimensionDisplay, dimensionDisplay);
        return;
    }

    public void createAndShowGUI(){
        image.setLayout(new BorderLayout());

        JFrame frame = new JFrame("Фракталище");
        frame.add(image,BorderLayout.CENTER);
        JButton resetImageButton = new JButton("Ресет");
        Reset handl = new Reset();
        resetImageButton.addActionListener(handl);
        frame.add(resetImageButton, BorderLayout.SOUTH);

        Mouse click = new Mouse();
        image.addMouseListener(click);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        return;

    }

    public void drawFractal(){
        for (int x = 0; x < dimensionDisplay; x+=1){
            for (int y = 0; y < dimensionDisplay; y+=1){
               double xCoord = FractalGenerator.getCoord(compRange.x, compRange.x + compRange.width, dimensionDisplay, x);
               double yCoord = FractalGenerator.getCoord(compRange.y, compRange.y + compRange.height, dimensionDisplay, y);
               int numIters = generator.numIterations(xCoord,yCoord);
               if (numIters == -1){
                   image.drawPixel(x, y, 0);
               }else {
                   float hue = 0.7f + (float) numIters / 200f;
                   int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                   image.drawPixel(x, y, rgbColor);
               }
            }
        }
        image.repaint();
    }

    class Reset implements ActionListener{
        public void actionPerformed(ActionEvent e){
            generator.getInitialRange(compRange);
            drawFractal();
        }
    }

    class Mouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCoord = FractalGenerator.getCoord(compRange.x, compRange.x + compRange.width,
                    dimensionDisplay, x);
            int y = e.getY();
            double yCoord = FractalGenerator.getCoord(compRange.y, compRange.y + compRange.height,
                    dimensionDisplay, y);

            generator.recenterAndZoomRange(compRange, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }







}
