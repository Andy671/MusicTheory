package com.kekstudio.musictheory;

import java.util.Arrays;

/**
 * Musical Scale.
 * Consists of the Notes.
 * @author Andy671
 */
public class Scale {
    
    protected Note[] notes;
    protected int octave;
    
    private String[] intervals;
    
    
    /**
     * Initializes the scale with a root note and intervals.
     * @param rootNote the root note of the scale.
     * @param intervals an array of interval symbols for the scale.
     */
    public Scale(Note rootNote, String[] intervals){
        this.intervals = intervals;
        
        notes = new Note[intervals.length + 1];
        notes[0] = rootNote;
        
        for(int i = 1; i < notes.length; i++){
            notes[i] = rootNote.add(intervals[i-1]);
        }
        
        octave = rootNote.getOctave();
    }
    
    /**
     * Initializes the scale with notes.
     * @param notes
     */
    public Scale(Note[] notes){
        this.notes = notes;
        
        octave = notes[0].getOctave();
    }
    
    /**
     * Sets octave of the scale/chord.
     * @param octave
     */
    public void setOctave(int octave){
        this.octave = octave;
        
        int octaveDifference = octave - getRoot().getOctave();
        
        for(Note note : notes){
            note.setOctave(note.getOctave() + octaveDifference);
        }
    }
    
    /**
     * Returns the root note.
     * @return the root note.
     */
    public Note[] getNotes(){
        return notes;
    }
    
    /**
     * @return the root note.
     */
    public Note getRoot(){
        return notes[0];
    }
    
    /**
     * Gets the octave of the scale/chord
     * @return
     */
    public int getOctave(){
        return octave;
    }  
    
    /**
     * @return the copy.
     */
    public Scale copy(){
        Note[] newNotes = new Note[notes.length];
        for(int i = 0; i < notes.length; i++){
            newNotes[i] = notes[i].copy();
        }
        return new Scale(newNotes);
    }  
    
    /**
     * Sorts notes in scale
     */
    public void sort(){
        Arrays.sort(notes);
    }
    
    @Override
    public String toString() {
        String returnString  = getRoot().getName() + " ";
        if(Music.ScalesInverse.containsKey(intervals)){
            returnString += Music.ScalesInverse.get(intervals) + " ";
        }
        returnString += "scale {";
        for(Note note : notes){
            returnString += note.toString() + ", ";
        }
        returnString = returnString.substring(0, returnString.length()-2);
        returnString += "}";
        return returnString;
    }    
 
}