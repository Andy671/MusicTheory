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
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.degree;
        hash = 23 * hash + this.steps;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Interval other = (Interval) obj;
        if (this.degree != other.degree) {
            return false;
        }
        if (this.steps != other.steps) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return Music.IntervalsInverse.get(this);
    }
}
