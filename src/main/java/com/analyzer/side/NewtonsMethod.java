package com.analyzer.side ;

import java.util.* ;

public class NewtonsMethod {

    static Function exp ;
    static int order = 4 ;
    static double dist = 0.05 ;

    public static ArrayList<Double> solve(Function function) {
        exp = function ;
        exp.setOrder(order) ;
        double end = exp.getEnd() ;
        double start = exp.getStart() ;

        TreeSet<Double> roots = new TreeSet<>() ;
        if (exp.isRoot(end))
            roots.add( end ) ;

        for (int i=0; i< (int)( (end-start) / dist ) ; i++) {
            double pRoot = start-10.5 ;
            double x1 = start + i*dist ;
            double x2 = x1 + dist ;

            if (exp.isRoot(x1))
                pRoot = x1 ;
            else if ( exp.f(x1)*exp.f(x2) < 0 || exp.d(x1)*exp.d(x2) < 0 )
                pRoot = newtonIterations( (x1 + x2) / 2.0 ) ;
            if ( function.isRoot(pRoot) && pRoot <= end && pRoot >= start )
                roots.add( round(pRoot) ) ;

        } return new ArrayList<>(roots) ;
    }

    public static double newtonIterations (double x) {
        int itMax = 1000 ;
        int a = order+5 ;

        for (int i=0; i<=itMax; i++) {
            double x1 = x - ( exp.f(x)/exp.d(x) ) ;
            double x2 = x1 - ( exp.f(x1)/exp.d(x1) ) ;
            if ( round(x,a) == round(x1,a) && round(x1, a) == round(x2, a) ) {
                return x2 ;
            } x = x2 ;
        }//System.out.println("Max iterations:: 	" + (itMax) ) ;
        return Double.NaN ;
    }

    public static double round(double num, int digits) {
        return Math.round( num * Math.pow(10, digits) ) / Math.pow(10, digits) ;
    } public static double round(double num) {
        return Math.round( num * Math.pow(10, order) ) / Math.pow(10, order) ;
    }
}