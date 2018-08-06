
import java.awt.*;


public class Hole2
{
    
    // scene is 1500 wide, 700 height
    //create custom color (red,green,blue) each value is 0-255
    private static Color LIGHT_BLUE = new Color(200, 255, 255);
    private static Color LIGHT_GREEN = new Color(200, 255, 200);
    
    public static Ball createBallOnTee () {
        Ball b = new Ball(1200, 550);
        return b;
    }
    
    public static void paint(Graphics g, Ball b) {
        //draw the sky
        g.setColor(LIGHT_BLUE);
        g.fillRect(0,0,1500,400);
        //draw ground
        g.setColor(LIGHT_GREEN);
        g.fillRect(0,400,1500,100);
        //draw trees
        g.setColor(Color.MAGENTA);
        g.fillPolygon(new int[] {150, 175, 200}, new int[] {450, 200, 450}, 3);
        g.fillRect(170, 450, 10, 30);
        g.fillPolygon(new int[] {250, 275, 300}, new int[] {450, 200, 450}, 3);
        g.fillRect(270, 450, 10, 30);
        g.fillPolygon(new int[] {1150, 1175, 1200}, new int[] {450, 200, 450}, 3);
        g.fillRect(1170, 450, 10, 30);
        //draw tee off area
        g.setColor(Color.GREEN);
        g.fillRect(1150, 500, 100, 100);
        //draw green area
        g.fillOval(200, 500, 400, 100);
        //draw hole
        g.setColor(Color.BLACK);
        g.fillOval(350, 550, 15, 5);
        //draw flag
        g.setColor(Color.RED);
        g.drawLine(357, 555, 357, 455);
        g.fillPolygon(new int[] { 357, 377, 357}, new int[] { 455, 460, 470}, 3);
        //draw ball
        g.setColor(Color.BLACK);
        g.fillOval(b.getX(), b.getY(), 5, 5);
        
        // hole in one?
        if (b.onGround() && Math.abs( b.getX() - 157) <= 9) 
            g.drawString("HOLE IN ONE!!!", 700, 100);
    }
}
