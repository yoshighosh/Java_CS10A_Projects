import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Golf  extends JFrame {

    /* 
     * Action Commands:  P for Play Button pressed, 
     *                   T for timer event, update simulation
     *                   H for user entered a new hole number
     */
    private static final String ACTION_PLAY = "P";
    private static final String ACTION_TIMER = "T";
    private static final String ACTION_HOLE = "H";

    private static final int MAX_HOLES = 2;

    private Scene scene;     // JPanel for drawing the scene
    private Ball ball;
    private int hole;        // hole number being played. 

    public static void main(String[] args) {
        Golf g = new Golf();
        g.setVisible(true);
    }

    public Golf() {
        // set window title, close operation and size
        super("Golf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500,700);
        // game starts with hole #1
        hole = 1;   
        ball = createBallOnTee();
        // create a border layout and add hole #1 and control panels
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        scene = new Scene();
        panel.add(scene, BorderLayout.CENTER);
        ControlPanel control = new ControlPanel();
        panel.add(control, BorderLayout.SOUTH);
        getContentPane().add(panel); //add the border layout panel to the Window frame.
    }

    /**
     * create a ball positioned on the tee
     */
    public  Ball  createBallOnTee() {
        switch (hole) {
            case 1:
            return Hole1.createBallOnTee();
            case 2:
            return Hole2.createBallOnTee();
            default:
            return Hole1.createBallOnTee();
        }
    }

    public  class Scene extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            switch (hole) {
                case 1: 
                Hole1.paint(g, ball);
                break;
                case 2:
                Hole2.paint(g, ball);
                break;
                default:
                Hole1.paint(g, ball);
            }
        }
    }

    public class ControlPanel extends JPanel implements ActionListener {

        private JTextField hole_TextField, xv_TextField, yv_TextField;
        private Timer timer;

        public ControlPanel() {
            setLayout( new FlowLayout());
            add( new Label("Hole"));
            hole_TextField = new JTextField(5);
            hole_TextField.setActionCommand(ACTION_HOLE);
            hole_TextField.addActionListener(this);
            add(hole_TextField);
            add( new Label("x velocity"));
            xv_TextField = new JTextField(10);
            add( xv_TextField);
            add( new Label("y velocity"));
            yv_TextField = new JTextField(10);
            add( yv_TextField);
            JButton play = new JButton("Play");
            play.setActionCommand(ACTION_PLAY);
            add(play);
            play.addActionListener(this);
        }

        public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand() ) {
                
                case ACTION_PLAY:
                // user has entered ball speeds and clicked on PLAY button
                double xvelocity = Double.parseDouble(xv_TextField.getText());
                double yvelocity = Double.parseDouble(yv_TextField.getText());
                ball = createBallOnTee();
                ball.setVelocity(xvelocity, -yvelocity);
                scene.repaint();
                timer = new Timer(33, this);   // timer for 0.033 seconds
                timer.setActionCommand(ACTION_TIMER);
                timer.start();
                break;

                case ACTION_HOLE:
                 // user has entered new hole number and pressed ENTER
                String s = hole_TextField.getText();
                int number = Integer.parseInt(s);
                if (number < 1 || number > MAX_HOLES ) {
                    hole_TextField.setText("Invalid");
                } else {
                    // switch scenes 
                    hole = number; 
                    ball = createBallOnTee();
                    scene.repaint();
                }
                break;
                
                
                case ACTION_TIMER:
                // update position of ball
                ball.passTime(.033);
                scene.repaint();
                // stop timer when ball is on ground
                if (ball.onGround()) {
                    timer.stop();  
                }
                break;
            }
        }
    } 
} // end of Golf

