import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MainFrame extends JFrame {

    private static final String[] options = {"Seer's Village", "Rogue's Den"};
    private String selected;
    private AbstractPanel panel;

    MainFrame() {
        super("Smart Click!");
        setup();
        generateJOption();
        evalSelected();
    }

    private void setup() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       // setResizable(false);
        setPreferredSize(new Dimension(400, 300));
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 1.30),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 1.5));
        setVisible(true);
        pack();
    }

    private void generateJOption() {
        selected = (String) JOptionPane.showInputDialog(
                null,
                "Select a Click Option",
                "Options",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    private void evalSelected() {
        switch (selected) {
            case "Seer's Village":
                panel = new SeersVillagePanel();
                break;
            case "Rogue's Den":
                panel = new RoguesDenPanel();
                break;
        }
        add(panel);
    }

}
