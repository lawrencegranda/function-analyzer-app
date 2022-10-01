package com.analyzer.app;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame() {
        this.setTitle(" Root Finding Algorithm") ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setLayout(null) ;
        this.setSize(800, 600) ;
        this.setBackground(Color.white) ;

        this.setResizable(false) ;
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/logo.png") ;
        this.setIconImage( icon ) ;
    }
}
