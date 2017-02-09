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
    private String romanNumeral = "";
    
    /**
     * Initializes the chord with a root note and intervals.
     * @param rootNote the root note of the chord.
     * @param intervals an array of interval symbols for the chord.
     */
    public Chord(Note rootNote, String[] intervals) {
        super(rootNote, intervals);
        if(!Music.ChordsInverse.containsKey(intervals)){
            String intervalsString = " ";
            for(String str : intervals)
                intervalsString += str + " ";
            
            throw new MusicTheoryException("Unknown chord with intervals '" + intervalsString + "'");
        }
        String chordType = Music.ChordsInverse.get(intervals);
        if(chordType.equals("maj"))
            chordType = "";
        name = rootNote.getName() + chordType;
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
     *  Sets Roman numeral string
     * @param romanNumeral 
     */
    public void setRomanNumeral(String romanNumeral){
        this.romanNumeral = romanNumeral;
    }
    
    /**
     * Gets the chord name
     * @return chord name
     */
    public String getName(){
        return name;
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
    
    /**
     * Gets Roman numeral of the chord (works only if was created from the key)
     * @return Roman Numeral (e.g. iv7, bV, IV)
     */
    public String getTheoryDegree(){
        return romanNumeral;
    }
    
    @Override
    public String toString() {
        String returnString = name + " " + getTheoryDegree() + " inversion[" + String.valueOf(position) + "] {";
        for(Note note : notes){
            returnString += note.toString() + ", ";
        }
        returnString = returnString.substring(0, returnString.length()-2);
        returnString += "}";
        return returnString;
    }    

    

}
