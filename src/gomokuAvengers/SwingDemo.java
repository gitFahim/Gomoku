package gomokuAvengers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class SwingDemo extends javax.swing.JFrame {
   Image img = Toolkit.getDefaultToolkit().getImage("E:/GomokuAvengers/src/gomokuAvengers/ai5.jpg");
   JFrame f = new JFrame();
   public SwingDemo() throws IOException {
      this.setContentPane(new JPanel() {
         @Override
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
            //f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            //f.setUndecorated(true);
           // f.setVisible(true);
         }
      });
      pack();
      setVisible(true);
   }
   public static void main(String[] args) throws Exception {
      new SwingDemo();
   }
}
