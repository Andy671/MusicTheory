package com.kekstudio.musictheory;

/**
 * Music Interval.
 * The interval type definition as a tuple of a degree index and number of steps.
 * @author Andy671
 */
public class Interval{
    
    public int degree;
    public int steps;
    
    public Interval(int degree, int steps){
        this.degree = degree;
        this.steps = steps;
    }
    
    @Override
    public String toString(){
        return Music.Degrees.get(degree) + "(" + String.valueOf(steps) + ")";
    }
}
