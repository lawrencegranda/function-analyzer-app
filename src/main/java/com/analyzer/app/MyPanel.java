package com.analyzer.app;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
	private int x ;
	private int y ;
	private int width ;
	private int height ;
	private Color color ;

    // Constructor
    MyPanel (int x, int y, int width, int height, Color color){
        setPanel(x, y, width, height, color) ;
    }
    MyPanel (int width, int height, Color color){
        setPanel(0, 0, width, height, color) ;
    }
    MyPanel (int width, int height){
        setPanel(0, 0, width, height, Color.white) ;
    }
    MyPanel (int x, int y, int width, int height){
    	setPanel(x, y, width, height, Color.white);
    }

    public void setPanel (int x, int y, int width, int height, Color color) {
    	this.x = x ;
    	this.y = y ;
    	this.width = width ;
    	this.height = height ;
    	this.color = color ;
        this.setBounds(this.x, this.y, this.width, this.height) ;
        this.setBackground(this.color) ;

    }

    public void borderLayout() {
    	 this.setLayout( new BorderLayout() ) ;
    }


}
