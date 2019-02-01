import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Activator extends JFrame {

    private JButton jb;
    private boolean activated;
    private Thread t;
    private Robot rob;
    private Random random;

    // 0 = x, 1 = y, 2 = timeout milliseconds
    private static final int[][] data = {
            {523, 267, 6750}, {338, 305, 7530}, {439, 470, 9400}, {548, 438, 5400},
            {316, 416, 6090}, {527, 397, 4730}, {994, 87, 9160}, {632, 188, 5940},
    };

    private Activator() {
        super("Smart Click!");
        setup();
        initFields();
        addListener();
    }

    private void setup() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));
        setVisible(true);
        pack();
    }

    private void initFields() {
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(400, 300));
        add(jp);

        jb = new JButton("Activate");
        jb.setPreferredSize(new Dimension(400, 300));
        jb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        jb.setForeground(Color.black);
        jb.setBackground(Color.red);
        jp.add(jb);

        activated = false;
        try { rob = new Robot(); } catch (AWTException e) { e.printStackTrace(); }
        random = new Random();
    }


    private void addListener() {
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!activated) {
                    activated = true;
                    jb.setBackground(Color.green);
                    Runnable r = new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                while (true) {
                                    for (int[] dataSet : data) {
                                        for (int count = 0; (MouseInfo.getPointerInfo().getLocation().getX() != dataSet[0] * 3 / 2 ||
                                                MouseInfo.getPointerInfo().getLocation().getY() != dataSet[1] * 3 / 2) &&
                                                count < 15; count++) {
                                            rob.mouseMove(dataSet[0] * 3 / 2, dataSet[1] * 3 / 2);
                                        }
                                        Thread.sleep(100);
                                        rob.mousePress(InputEvent.BUTTON1_MASK);
                                        rob.mouseRelease(InputEvent.BUTTON1_MASK);
                                        Thread.sleep(random.nextInt((dataSet[2] + 500 - dataSet[2])) + dataSet[2]);
                                    }
                                }
                            } catch (Exception exc) { exc.printStackTrace(); }
                        }
                    };
                    t = new Thread(r);
                    t.start();
                } else {
                    activated = false;
                    jb.setBackground(Color.red);
                    t.interrupt();
                }
            }
        });
    }

    public static void main(String[] args) {
        Activator activator = new Activator();
    }
}
