package com.analyzer.app;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    MyButton(String text) {
        this.setText(text);
        this.setFont(new Font("Montserrat", Font.BOLD, 20));
        this.setForeground(Color.black);
        this.setBackground(new Color(0x8698b5));
        this.setSize(100,50);
    }
}
