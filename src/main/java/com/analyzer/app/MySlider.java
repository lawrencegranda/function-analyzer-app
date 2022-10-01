package com.analyzer.app;

import javax.swing.*;
import java.awt.*;

public class MySlider extends JSlider {
    private int max ;
    private int min ;
    public int height ;
    public int width ;

    // Constructor
    MySlider (int min, int max, int width, int height) {
        setVariables(min, max, width, height) ;
        this.setMinimum(this.min) ;
        this.setMaximum(this.max) ;
        this.setValue( (this.max+this.min)/2 ) ;
        this.setPreferredSize(new Dimension(this.width, this.height)) ;
        this.setBackground(Color.white) ;
        this.setPaintTicks(true) ;
        this.setMinorTickSpacing(1) ;
        this.setMajorTickSpacing(2) ;
        this.setPaintLabels(true) ;
        this.setFont(new Font("MV Boli", Font.PLAIN, 12)) ;
        this.setOrientation(SwingConstants.HORIZONTAL) ;
        this.setBounds(0, 0, this.width, this.height);
    }

    public void setVariables (int min, int max, int width, int height) {
        this.min = min ;
        this.max = max ;
        this.width = width ;
        this.height = height ;
    }


}
