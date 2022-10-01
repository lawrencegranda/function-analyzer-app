package com.analyzer.app;

import com.analyzer.side.Function;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Gui implements ChangeListener, ActionListener {
	MyFrame frame = new MyFrame() ;
	Function f ;

	MyPanel titlePanel ;
	JLabel title ;

	MyPanel polyPanel ;
	MyPanel subPanelPoly ;
	MyPanel subPanelYesPol ;
	MyPanel subPanelNotPol ;
	JLabel labelPoly ;
	MyButton bYesPolynomial = new MyButton("YES") ;
	MyButton bNotPolynomial = new MyButton("NO") ;

	MyPanel intPanel ;
	MyPanel subPanelInt ;
	MyPanel subPanelYesInt ;
	MyPanel subPanelNotInt ;
	JLabel labelInt ;
	MyButton bYesInterval = new MyButton("YES") ;
	MyButton bNotInterval = new MyButton("NO") ;

	MyPanel stringFunc ;
	MyPanel subPanelFunction ;
	MyPanel subPanelInput ;
	JLabel labelFunction ;
	JTextField textStrFunc ;
	MyButton bStrInt = new MyButton("NEXT") ;

	MyPanel defIntPanel ;
	MyPanel subPanelDefInt ;
	MyPanel subPanelInputMin ;
	MyPanel subPanelInputMax ;
	JLabel labelDefInt ;
	JLabel labelDefMin ;
	JLabel labelDefMax ;
	JTextField textIntMin ;
	JTextField textIntMax ;
	MyButton bDefintRoots = new MyButton("NEXT") ;

    MyPanel degPanel ;
	JLabel labelDeg ;
	MyPanel subPanelSl ;
	MyPanel subPanelDeg ;
	MySlider slider ;
	int deg = 0 ;
	MyButton bDegCoeff = new MyButton("NEXT") ;

	MyPanel coeffPanel ;
	JLabel labelCf ;
	MyPanel subPanelCf ;
	MyPanel subPanelTbCf ;
	MyTable tableCf ;
	ArrayList<Double> cf ;
	MyButton bCoeffInt = new MyButton("NEXT") ;

	MyPanel rootsPanel ;
	JLabel labelRoots ;
	MyPanel subPanelRoot ;
	MyPanel subPanelTbRoots ;
	MyTable tableRoots ;
	String[][] data ;

	MyPanel panelBottom ;


    public Gui(){
        //titlePanel
        titlePanel = new MyPanel( frame.getWidth(), 100, new Color(0x8698b5) ) ;
		titlePanel.borderLayout() ;

        title = new JLabel("Root Finding Algorithm");
        title.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30)) ;
		title.setForeground(Color.black) ;
        title.setVerticalAlignment(JLabel.CENTER) ;
		title.setHorizontalAlignment(JLabel.CENTER) ;

		titlePanel.add(title);



		//polyPanel
		polyPanel = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), frame.getHeight()-titlePanel.getHeight() ) ;

		//subPanelPoly -- Label of Polynomial
		subPanelPoly = new MyPanel( 0, 25, polyPanel.getWidth(), polyPanel.getHeight()/4 ) ;
		subPanelPoly.borderLayout() ;
		labelPoly = new JLabel("Is your function a Polynomial?") ;
		labelPoly.setFont(new Font("Verdana", Font.BOLD, 25)) ;
		labelPoly.setForeground(Color.black) ;
		labelPoly.setHorizontalAlignment(JLabel.CENTER) ;

		subPanelYesPol = new MyPanel( 0, subPanelPoly.getHeight(), subPanelPoly.getWidth()/2,polyPanel.getHeight()-subPanelPoly.getHeight() ) ;
		subPanelYesPol.setLayout(null) ;
		bYesPolynomial.setBounds(200, subPanelYesPol.getHeight()/2-100, bYesPolynomial.getWidth(), bYesPolynomial.getHeight()) ;
		bYesPolynomial.addActionListener(this) ;
		subPanelYesPol.add(bYesPolynomial) ;

		subPanelNotPol = new MyPanel( subPanelPoly.getWidth()/2, subPanelPoly.getHeight(), subPanelPoly.getWidth()/2,polyPanel.getHeight()-subPanelPoly.getHeight() ) ;
		subPanelNotPol.setLayout(null) ;
		bNotPolynomial.setBounds(subPanelNotPol.getWidth()-bNotPolynomial.getWidth()-200, subPanelNotPol.getHeight()/2-100, bNotPolynomial.getWidth(), bNotPolynomial.getHeight()) ;
		bNotPolynomial.addActionListener(this) ;
		subPanelNotPol.add(bNotPolynomial) ;

		//Add Objects
		subPanelNotPol.add(bNotPolynomial) ;
		subPanelYesPol.add(bYesPolynomial) ;
		subPanelPoly.add(labelPoly) ;
		polyPanel.add(subPanelYesPol) ;
		polyPanel.add(subPanelNotPol) ;
		polyPanel.add(subPanelPoly) ;
		polyPanel.borderLayout() ;
		frame.add(polyPanel) ;



		//degPanel
		degPanel = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), 400 ) ;

		//subPanelDeg -- Label of Degree
		subPanelDeg = new MyPanel( 0, 25, degPanel.getWidth(), degPanel.getHeight()/4 ) ;
		subPanelDeg.borderLayout() ;
		labelDeg = new JLabel() ;
		labelDeg.setFont(new Font("Verdana", Font.BOLD, 25)) ;
		labelDeg.setForeground(Color.black) ;
		labelDeg.setHorizontalAlignment(JLabel.CENTER) ;

		//Slider
		int sliderWidth = 600 ;
		subPanelSl = new MyPanel( (degPanel.getWidth()-sliderWidth)/2, subPanelDeg.getHeight()+100, sliderWidth, 100 ) ;
		slider = new MySlider(1,20,sliderWidth, 100) ;
		slider.addChangeListener(this) ;
		subPanelSl.borderLayout() ;

		//Add Objects
		subPanelDeg.add(labelDeg) ;
		subPanelSl.add(slider) ;
		degPanel.add(subPanelSl) ;
		degPanel.add(subPanelDeg) ;
		degPanel.borderLayout() ;



		//coeffPanel
		coeffPanel = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), 400 ) ;

		//subPanelCf -- Label of Coefficients
		subPanelCf = new MyPanel( coeffPanel.getWidth(), coeffPanel.getHeight()/4 ) ;
		subPanelCf.borderLayout() ;
		labelCf = new JLabel();
		labelCf.setFont(new Font("Verdana", Font.BOLD, 20)) ;
		labelCf.setForeground(Color.black) ;
		labelCf.setHorizontalAlignment(JLabel.CENTER) ;
		labelCf.setVerticalAlignment(JLabel.CENTER) ;

		//subPanelTbCf -- Table of Coefficients
		subPanelTbCf = new MyPanel( 0, subPanelCf.getHeight(), coeffPanel.getWidth(), coeffPanel.getHeight()-subPanelCf.getHeight() ) ;
		tableCf = new MyTable() ;

		//Add Objects
		subPanelCf.add(labelCf) ;
		coeffPanel.add(subPanelCf) ;
		coeffPanel.add(subPanelTbCf) ;
		coeffPanel.borderLayout() ;



		//intPanel
		intPanel = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), frame.getHeight()-titlePanel.getHeight() ) ;

		//subPanelInt -- Label of Polynomial
		subPanelInt = new MyPanel( 0, 25, intPanel.getWidth(), intPanel.getHeight()/4 ) ;
		subPanelInt.borderLayout() ;
		labelInt = new JLabel() ;
		labelInt.setFont(new Font("Verdana", Font.BOLD, 25)) ;
		labelInt.setForeground(Color.black) ;
		labelInt.setHorizontalAlignment(JLabel.CENTER) ;

		subPanelYesInt = new MyPanel( 0, subPanelInt.getHeight(), subPanelInt.getWidth()/2,intPanel.getHeight()-subPanelInt.getHeight() ) ;
		subPanelYesInt.setLayout(null) ;
		bYesInterval.setBounds(200, subPanelYesInt.getHeight()/2-100, bYesInterval.getWidth(), bYesInterval.getHeight()) ;
		bYesInterval.addActionListener(this) ;
		subPanelYesInt.add(bYesInterval) ;

		subPanelNotInt = new MyPanel( subPanelInt.getWidth()/2, subPanelInt.getHeight(), subPanelInt.getWidth()/2,intPanel.getHeight()-subPanelInt.getHeight() ) ;
		subPanelNotInt.setLayout(null) ;
		bNotInterval.setBounds(subPanelNotInt.getWidth()-bNotInterval.getWidth()-200, subPanelNotInt.getHeight()/2-100, bNotInterval.getWidth(), bNotInterval.getHeight()) ;
		bNotInterval.addActionListener(this) ;
		subPanelNotInt.add(bNotInterval) ;

		//Add Objects
		subPanelNotInt.add(bNotInterval) ;
		subPanelYesInt.add(bYesInterval) ;
		subPanelInt.add(labelInt) ;
		intPanel.add(subPanelYesInt) ;
		intPanel.add(subPanelNotInt) ;
		intPanel.add(subPanelInt) ;
		intPanel.borderLayout() ;



		//stringFunc -- Panel in which the function is provided as a String
		stringFunc = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), 400 ) ;

		//subPanelFunction -- Title of input function
		subPanelFunction = new MyPanel( 0, 25, stringFunc.getWidth(), stringFunc.getHeight()/5 ) ;
		subPanelFunction.borderLayout() ;
		labelFunction = new JLabel() ;
		labelFunction.setFont(new Font("Verdana", Font.BOLD, 25)) ;
		labelFunction.setForeground(Color.black) ;
		labelFunction.setHorizontalAlignment(JLabel.CENTER) ;

		subPanelInput = new MyPanel( 0, subPanelFunction.getHeight(), stringFunc.getWidth(),(stringFunc.getHeight()-subPanelFunction.getHeight())/3 ) ;
		subPanelInput.setLayout(null) ;
		textStrFunc = new JTextField("[ function ]", 40) ;
		textStrFunc.setHorizontalAlignment(JTextField.CENTER) ;
		textStrFunc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)) ;
		textStrFunc.setFont(new Font("Verdana", Font.ITALIC, 15)) ;
		textStrFunc.setPreferredSize( new Dimension( subPanelInput.getWidth() - 200, 50) ) ;
		textStrFunc.setBounds( (subPanelInput.getWidth() - (int)textStrFunc.getPreferredSize().getWidth() )/2, 50, (int)textStrFunc.getPreferredSize().getWidth(), (int)textStrFunc.getPreferredSize().getHeight() ) ;

		//Add Objects
		stringFunc.add(subPanelFunction) ;
		stringFunc.add(subPanelInput) ;
		subPanelInput.add(textStrFunc) ;
		subPanelFunction.add(labelFunction) ;
		stringFunc.borderLayout() ;



		//defIntPanel -- Panel in which the interval is defined
		defIntPanel = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), 400 ) ;

		//subPanelDefInt -- Title of Define Interval
		subPanelDefInt = new MyPanel( 0, 25, defIntPanel.getWidth(), defIntPanel.getHeight()/5 ) ;
		subPanelDefInt.borderLayout() ;
		labelDefInt = new JLabel() ;
		labelDefInt.setFont(new Font("Verdana", Font.BOLD, 25)) ;
		labelDefInt.setForeground(Color.black) ;
		labelDefInt.setHorizontalAlignment(JLabel.CENTER) ;

		subPanelInputMin = new MyPanel( 0, subPanelDefInt.getHeight(), defIntPanel.getWidth(),(defIntPanel.getHeight()-subPanelDefInt.getHeight())/3 ) ;
		subPanelInputMin.setLayout(null) ;
		labelDefMin = new JLabel("From") ;
		labelDefMin.setFont(new Font("Verdana", Font.BOLD, 20)) ;
		labelDefMin.setForeground(Color.black) ;
		labelDefMin.setHorizontalAlignment(JLabel.CENTER) ;
		labelDefMin.setPreferredSize( new Dimension(100, 100) ) ;
		labelDefMin.setBounds( subPanelInputMin.getWidth()/2-( (int)labelDefMin.getPreferredSize().getWidth() )-25, 25, (int)labelDefMin.getPreferredSize().getWidth(), (int)labelDefMin.getPreferredSize().getHeight() ) ;
		textIntMin = new JTextField("[ number ]", 40) ;
		textIntMin.setHorizontalAlignment(JTextField.CENTER) ;
		textIntMin.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)) ;
		textIntMin.setFont(new Font("Verdana", Font.ITALIC, 15)) ;
		textIntMin.setPreferredSize( new Dimension( 400, 50) ) ;
		textIntMin.setBounds( subPanelInputMin.getWidth()/2+25, 50, 100, (int)textIntMin.getPreferredSize().getHeight() ) ;

		subPanelInputMax = new MyPanel( 0, subPanelDefInt.getHeight()+subPanelInputMin.getHeight(), defIntPanel.getWidth(),(defIntPanel.getHeight()-subPanelDefInt.getHeight())/2 ) ;
		subPanelInputMax.setLayout(null) ;
		labelDefMax = new JLabel("To") ;
		labelDefMax.setFont(new Font("Verdana", Font.BOLD, 20)) ;
		labelDefMax.setForeground(Color.black) ;
		labelDefMax.setHorizontalAlignment(JLabel.CENTER) ;
		labelDefMax.setPreferredSize( new Dimension(100, 100) ) ;
		labelDefMax.setBounds( subPanelInputMax.getWidth()/2-( (int)labelDefMax.getPreferredSize().getWidth() )-25, 25, (int)labelDefMax.getPreferredSize().getWidth(), (int)labelDefMax.getPreferredSize().getHeight() ) ;
		textIntMax = new JTextField("[ number ]", 40) ;
		textIntMax.setHorizontalAlignment(JTextField.CENTER) ;
		textIntMax.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)) ;
		textIntMax.setFont(new Font("Verdana", Font.ITALIC, 15)) ;
		textIntMax.setPreferredSize( new Dimension( 400, 50) ) ;
		textIntMax.setBounds( subPanelInputMax.getWidth()/2+25, 50, 100, (int)textIntMax.getPreferredSize().getHeight() ) ;

		//Add Objects
		defIntPanel.add( subPanelDefInt ) ;
		defIntPanel.add( subPanelInputMin ) ;
		defIntPanel.add( subPanelInputMax ) ;
		subPanelInputMin.add( labelDefMin ) ;
		subPanelInputMin.add( textIntMin ) ;
		subPanelDefInt.add( labelDefInt ) ;
		subPanelInputMax.add( labelDefMax ) ;
		subPanelInputMax.add( textIntMax ) ;
		defIntPanel.borderLayout() ;



		//RootsPanel
		rootsPanel = new MyPanel( 0, titlePanel.getHeight(), frame.getWidth(), 400 ) ;

		//subPanelRoot -- Label of Roots
		subPanelRoot = new MyPanel( rootsPanel.getWidth(), rootsPanel.getHeight()/4 ) ;
		subPanelRoot.borderLayout() ;
		labelRoots = new JLabel() ;
		labelRoots.setFont(new Font("Verdana", Font.BOLD, 20)) ;
		labelRoots.setForeground(Color.black) ;
		labelRoots.setHorizontalAlignment(JLabel.CENTER) ;
		labelRoots.setVerticalAlignment(JLabel.CENTER) ;

		//subPanelTbRoot -- Table of Roots
		subPanelTbRoots = new MyPanel( 0, subPanelRoot.getHeight(), rootsPanel.getWidth(), rootsPanel.getHeight()-subPanelRoot.getHeight() ) ;
		tableRoots = new MyTable() ;

		//Add Objects
		subPanelRoot.add(labelRoots) ;
		rootsPanel.add(subPanelRoot) ;
		rootsPanel.add(subPanelTbRoots) ;
		rootsPanel.borderLayout() ;



		//panelBottom -- "next" buttons are here
		panelBottom = new MyPanel( 0, titlePanel.getHeight()+400, frame.getWidth(),frame.getHeight()-titlePanel.getHeight()-400 ) ;
		panelBottom.setLayout(null) ;

		bDegCoeff.setBounds(frame.getWidth()-bDegCoeff.getWidth()-25, 5, bDegCoeff.getWidth(), bDegCoeff.getHeight()) ;
		bDegCoeff.addActionListener(this) ;
		panelBottom.add(bDegCoeff) ;

		bCoeffInt.setBounds(frame.getWidth()-bCoeffInt.getWidth()-25, 5, bCoeffInt.getWidth(), bCoeffInt.getHeight()) ;
		bCoeffInt.addActionListener(this) ;

		bDefintRoots.setBounds(frame.getWidth()-bDefintRoots.getWidth()-25, 5, bDefintRoots.getWidth(), bDefintRoots.getHeight()) ;
		bDefintRoots.addActionListener(this) ;

		bStrInt.setBounds(frame.getWidth()-bStrInt.getWidth()-25, 5, bStrInt.getWidth(), bStrInt.getHeight()) ;
		bStrInt.addActionListener(this) ;


        //Frame
        frame.add(titlePanel) ;
		frame.setVisible(true) ;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        labelDeg.setText( "Degree of Polynomial:    "+slider.getValue() ) ;
    }

	@Override
	public void actionPerformed(ActionEvent e) {

		//Button from Polynomial to Degree
		if(e.getSource()==bYesPolynomial) {
			frame.remove(polyPanel) ;
			frame.add(degPanel) ;
			frame.add(panelBottom) ;

			subPanelCf.revalidate() ;
			frame.repaint() ;
			labelDeg.setText( "Degree of Polynomial:    "+slider.getValue() ) ;
		}


		//Button from Degree to Coefficients
		if(e.getSource()==bDegCoeff){
			deg = slider.getValue() ;
			labelCf.setText("Enter the coefficients your polynomial of degree "+deg) ;

            data = new String[2][deg + 1] ;
            for (int i = deg; i >= 0; i--) {
				data[0][i] = "x^" + (deg-i) ;
                data[1][i] = "" ;
            }
            String[] columnNames = {"Term", "Coefficient"} ;
			tableCf.setValues(data, columnNames, 1) ;
            tableCf.setRowHeight(subPanelTbCf.getHeight()/tableCf.getRowCount() ) ;
            int tblCfDist = 25 ;
			int columnWidth = tableCf.getColumnModel().getColumn(0).getWidth() ;
            tableCf.setBounds(subPanelTbCf.getWidth()/2 - columnWidth, tblCfDist, 0, subPanelTbCf.getHeight() - 2 * tblCfDist) ;


			subPanelTbCf.add(tableCf) ;
			panelBottom.removeAll() ;
			panelBottom.add(bCoeffInt) ;
			panelBottom.repaint() ;
			frame.remove(degPanel) ;
			frame.repaint() ;
			frame.add(coeffPanel) ;
			subPanelCf.revalidate() ;
		}


		//Button from Coefficients to Interval decision
		if(e.getSource()==bCoeffInt) {
			cf = new ArrayList<>(Collections.nCopies(deg + 1, 0.0)) ;
			for (int i = deg; i >= 0; i--) {
				if ( tableCf.getValueAt(i, 1 ).toString().isEmpty() )
					cf.set(i, 0.0 ) ;
				else
					cf.set(i, Double.parseDouble((String) tableCf.getValueAt(i, 1 )) );
			} Collections.reverse(cf) ;
			f = new Function(cf) ;

			frame.remove(coeffPanel) ;
			panelBottom.removeAll() ;
			frame.remove(panelBottom) ;
			panelBottom.repaint() ;
			frame.repaint() ;
			frame.add(intPanel) ;
			labelInt.setText("Do you want to define an interval?") ;
		}


		//Button from Not Interval to Display of Roots
		if(e.getSource()==bNotInterval) {
			data( f.getZeros() ) ;
			String[] columnNames = {"Order", "Zero"} ;
			tableRoots.setValues(this.data, columnNames, -1) ;
			tableRoots.setRowHeight(subPanelTbRoots.getHeight()/tableRoots.getRowCount()) ;
			int tblCfDist = 25 ;
			int columnWidth =tableRoots.getColumnModel().getColumn(0).getWidth() ;
			tableRoots.setBounds(subPanelTbRoots.getWidth()/2 - columnWidth , tblCfDist, subPanelTbRoots.getWidth(), subPanelTbRoots.getHeight() - 3 * tblCfDist) ;

			subPanelTbRoots.add(tableRoots) ;
			frame.remove(intPanel) ;
			frame.add(panelBottom) ;
			frame.add(rootsPanel) ;
			frame.repaint() ;
			panelBottom.removeAll() ;
			panelBottom.repaint() ;
			labelRoots.setText("The roots are:") ;
		}


		//Button from Not Polynomial to Input of Function as String
		if(e.getSource()==bNotPolynomial) {
			frame.remove(polyPanel) ;
			frame.add(stringFunc) ;
			frame.add(panelBottom) ;
			panelBottom.removeAll() ;
			panelBottom.add(bStrInt) ;
			panelBottom.repaint();
			frame.repaint() ;
			labelFunction.setText("Type the function as a String") ;
		}


		//Button from Interval Decision or String Function to Definition of Interval
		if(e.getSource()==bYesInterval || e.getSource()==bStrInt) {
			if (e.getSource() == bStrInt)
				f = new Function(textStrFunc.getText()) ;
			frame.remove(stringFunc) ;
			frame.remove(intPanel) ;
			panelBottom.removeAll() ;
			panelBottom.add(bDefintRoots) ;
			panelBottom.repaint() ;
			frame.add(panelBottom) ;
			frame.repaint() ;
			frame.add(defIntPanel) ;
			labelDefInt.setText("Define an interval") ;
		}


		//Button from Definition of Interval to Display of Roots
		if(e.getSource()==bDefintRoots) {
			double start = Double.parseDouble( textIntMin.getText() ) ;
			double end = Double.parseDouble( textIntMax.getText() ) ;
			f.setInterval(start, end) ;
			data( f.getZeros() ) ;
			String[] columnNames = {"Order", "Zero"} ;
			tableRoots.setValues(this.data, columnNames, -1) ;
			tableRoots.setRowHeight(subPanelTbRoots.getHeight()/tableRoots.getRowCount()) ;
			int tblCfDist = 25 ;
			int columnWidth =tableRoots.getColumnModel().getColumn(0).getWidth() ;
			tableRoots.setBounds(subPanelTbRoots.getWidth()/2 - columnWidth , tblCfDist, subPanelTbRoots.getWidth(), subPanelTbRoots.getHeight() - 3 * tblCfDist) ;

			subPanelTbRoots.add(tableRoots) ;
			frame.remove(defIntPanel) ;
			panelBottom.removeAll() ;
			panelBottom.repaint() ;
			frame.repaint() ;
			frame.add(rootsPanel) ;
			labelRoots.setText("The roots are:") ;
		}



	}

	public void data(ArrayList<Double> roots) {
		this.data = new String[2][roots.size()] ;
		System.out.printf( "Function:: \t\t%s\n", f ) ;
		System.out.printf( "Derivative:: \t%s\n", f.d() ) ;
		System.out.printf( "Roots:: \t\t%s\n", roots ) ;
		for (int i = 0; i < roots.size(); i++) {
			this.data[0][i] = "x" + (1+i) ;
			this.data[1][i] = ""+roots.get(i) ;
		}
	}
}
