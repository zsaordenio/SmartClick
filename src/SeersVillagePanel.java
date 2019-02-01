import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class SeersVillagePanel extends AbstractPanel {


    // 0 = x, 1 = y, 2 = timeout milliseconds
    private final int[][] data = {
            {523, 267, 6750}, {338, 305, 7530}, {439, 470, 9400}, {548, 438, 5400},
            {316, 416, 6090}, {527, 397, 4730}, {994, 87, 9160}, {632, 188, 5940},
    };

    SeersVillagePanel() {
        super("Seer's Village");
    }


}
