package cz.tmobile.ibmd.checker;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(String title) throws HeadlessException {
        super(title);
        new GUI("ConnectionChecker");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
