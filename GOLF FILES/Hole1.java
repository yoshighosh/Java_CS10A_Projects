
import java.awt.*;


public class Hole1
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
        //draw tee off area
        g.setColor(Color.GREEN);
        g.fillRect(1150, 500, 100, 100);
        //draw green area
        g.fillOval(100, 500, 400, 100);
        //draw hole
        g.setColor(Color.BLACK);
        g.fillOval(150, 550, 15, 5);
        //draw flag
        g.setColor(Color.RED);
        g.drawLine(157, 555, 157, 455);
        g.fillPolygon(new int[] { 157, 177, 157}, new int[] { 455, 460, 470}, 3);
        //draw ball
        g.setColor(Color.BLACK);
        g.fillOval(b.getX(), b.getY(), 5, 5);
        
        // hole in one?
        if (b.onGround() && Math.abs( b.getX() - 157) <= 9) 
            g.drawString("HOLE IN ONE!!!", 700, 100);
    }
}
