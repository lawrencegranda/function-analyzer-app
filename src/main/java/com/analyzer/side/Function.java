package com.analyzer.side ;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.* ;

public class Function {
    private String f ;
    public boolean isPolynomial = false ;
    public int order ;
    private double start ;
    private double end ;
    private ArrayList<Double> cf ;
    private ArrayList<Double> roots ;
    private final HashMap<Double, Double> func = new HashMap<>() ;
    private Function d ;

    public Function(String f){
        format(f) ;
    }
    public Function(String f, double a, double b){
        format(f) ;
        this.end = Math.max(a,b) ;
        this.start = a+b-this.end ;
    }
    public Function(ArrayList<Double> cf){
        this.isPolynomial = true ;
        this.cf = cf ;
        getPolynomial() ;

        this.end = (int) this.abs(0) ;
        this.start = -1*end ;
    }
    public Function(ArrayList<Double> cf, double a, double b){
        this.isPolynomial = true ;
        this.cf = cf ;
        getPolynomial() ;

        this.end = Math.max(a,b) ;
        this.start = a+b-this.end ;
    }

    public void format(String f) {
        for ( int i = 1; i < f.length(); i++ ) {
            if ( f.charAt(i) == 'x' && f.charAt(i-1) >= '0' && f.charAt(i-1) <= '9' )
                f = f.substring(0, i) + "*x" + f.substring(i + 1) ;
            if ( f.charAt(i-1) == 'x' && f.charAt(i) >= '0' && f.charAt(i) <= '9' )
                f = f.substring(0, i-1) + "x^" + f.substring(i) ;
        }
        this.f = f ;
    }

    public void getPolynomial(){
        StringBuilder sb = new StringBuilder() ;
        for (int i = this.cf.size()-1; i>0; i--)
        {
            if (this.cf.get(i)>0)
                sb.append(" +").append(this.cf.get(i)).append("*x^").append(i);
            else if (this.cf.get(i)<0)
                sb.append(" ").append(this.cf.get(i)).append("*x^").append(i);
        }
        if (this.cf.get(0)>0)
            sb.append(" +").append(this.cf.get(0));
        else if (this.cf.get(0)<0)
            sb.append(" ").append(this.cf.get(0));
        format( sb.toString() ) ;
    }

    public ArrayList<Double> getZeros(){
        if (this.roots == null)
            this.roots = NewtonsMethod.solve(this) ;
        return this.roots ;
    }

    public ArrayList<Double> getCoefficients(){
        return this.cf ;
    }
    @Override
    public String toString(){
        return this.f ;
    }
    public void setInterval(double a, double b){
        this.end = Math.max(a,b) ;
        this.start = a+b-this.end ;
    }
    public void setOrder(int order){
        this.order = order ;
    }
    public double getStart(){
        return this.start ;
    }
    public double getEnd(){
        return this.end ;
    }
    public boolean isRoot(double var) {
        return !( Math.abs(this.f(var)) > this.error(var) ) ;
    }
    public double f(double var) {
        if ( !this.func.containsKey(var) ) {
            Argument x = new Argument("x = " + var) ;
            this.func.put( var, (new Expression(this.f, x)).calculate() ) ;
        }
        return this.func.get(var) ;
    }

    public Function d() {
        if ( d == null )
            this.d = new Function("der( " + this.f + " , x )") ;
        return this.d ;
    } public double d(double var) {
        d() ;
        return this.d.f(var) ;
    }

    public double error(double x) {
        return Math.abs( f(  x - Math.pow(10, -1*order) ) - f( x + Math.pow(10, -1*order) ) )/2.0 ;
    }

    public double abs(double x){
        return Math.abs(this.f(x)) ;
    }


}
