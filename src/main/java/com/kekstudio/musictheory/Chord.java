package com.kekstudio.musictheory;

/**
 * Music Chord.
 * Chord contains notes and can have different octaves and inversions.
 * @author Andy671
 */
public class Chord extends Scale{
    
    private String name;
    private int octave;
    private int position;
    
    /**
     * Initializes the chord with a root note and intervals.
     * @param rootNote the root note of the chord.
     * @param intervals an array of interval symbols for the chord.
     */
    public Chord(Note rootNote, String[] intervals) {
        super(rootNote, intervals);
        name = rootNote.getName() + Music.ChordsInverse.get(intervals);
        octave = rootNote.getOctave();
        position = 0;
    }
    
    /**
     * Sets octave of the chord.
     * @param octave
     */
    public void setOctave(int octave){
        this.octave = octave;
        for(Note note : notes){
            note.setOctave(octave);
        }
    }
    
    /**
     * Sets the inversion of the chord.
     * @param position
     */
    public void setPosition(int position){
        int steps = this.position - position;
        
        if(steps > 0){
            for(int i = 0; i < steps; i++){
                Note first = notes[0];
                first.setOctave(first.getOctave()+1);
                for(int j = 0; j < notes.length-1; j++){
                    notes[j] = notes[j+1];
                }
                notes[notes.length-1] = first;
            }
        }else if (steps < 0){
            for(int i = 0; i < Math.abs(steps); i++){
                Note last = notes[notes.length-1];
                last.setOctave(last.getOctave()-1);
                for(int j = notes.length-1; j > 0; j--){
                    notes[j] = notes[j-1];
                }
                notes[0] = last;
            }
        }
        
        this.position = position;
    }
      
    /**
     * Gets the octave of the chord
     * @return
     */
    public int getOctave(){
        return octave;
    }    
    
    /**
     * Gets the inversion of the chord
     * @return
     */
    public int getPosition(){
        return position;
    }

}
