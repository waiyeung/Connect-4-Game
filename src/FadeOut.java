import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FadeOut extends JPanel implements ActionListener {
  private Image i;
  private Timer timer;
  private float opacity;
  /*
  public static void main(String[] args) {
	  Image tempi = new ImageIcon("shell.jpg").getImage();
	  JFrame frame = new JFrame("Fade out");
	  frame.add(new FadeOut(tempi));
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setSize(600, 460);
	  frame.setVisible(true);
  }*/
  
  public FadeOut(Image img) {
	  i = img;
	  timer = new Timer(10, this);
	  opacity = 1f;
	  timer.start();
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    g2d.drawImage(i, 10, 10, null);
  }
  
  public void actionPerformed(ActionEvent e) {
    opacity += -0.01f;
    if (opacity <= 0) {
    	opacity = 0;
    	timer.stop();
    }
    repaint();
  }
  
  
}