import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public abstract class AbstractPanel extends JPanel {

    private JPanel topPanel, midPanel;
    private JTextArea topText;
    private JButton jb;

    private boolean activated;
    private Thread t;
    private Robot rob;
    private Random random;

    private int[][] data;


    AbstractPanel(String title) {
        super(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));

        generateTopPanel(title);
        generateMidPanel();

        add(BorderLayout.NORTH, topPanel);
        add(BorderLayout.CENTER, midPanel);
        repaint();
    }

    private void generateTopPanel(String title) {
        topText = new JTextArea(title);
        topText.setEditable(false);
        topText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        topText.setForeground(Color.black);
        topText.setBackground(Color.red);

        topPanel = new JPanel();
        topPanel.add(topText);
    }


    private void generateMidPanel() {
        jb = new JButton("Activate");
        jb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        jb.setForeground(Color.black);
        jb.setBackground(Color.red);

        midPanel = new JPanel();
        midPanel.add(jb);
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
}
