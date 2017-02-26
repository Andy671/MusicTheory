package com.kekstudio.musictheory;

import java.util.Arrays;

/**
 * Music Key.
 * @author Andy671
 */
public class Key implements Comparable<Key> {
    
    private Note note;
    private String scaleKeyType;
    private String name;
    private Scale scale;
    
    /**
     * Initializes the key with a name and a quality. Automatic alteration is enabled(based on Music.ScaleAlterations).
     * If you want to set custom alteration - use constructor with autoAlteration == false.
     * @param noteName the key note.
     * @param scaleKeyType
     */
    public Key(String noteName, String scaleKeyType){
        this(noteName,  scaleKeyType, true);
    }
    
    /**
     * Initializes the key with a name and a quality. 
     * @param noteName the key note.
     * @param scaleKeyType
     * @param autoAlteration enable alteration based on Music.ScaleAlterations.
     */
    public Key(String noteName, String scaleKeyType, boolean autoAlteration){
        if(autoAlteration && noteName.length() > 1){
            if(!Music.ScaleAlterations.containsKey(scaleKeyType)){
                throw new MusicTheoryException("Unknown key/scale type name '" + scaleKeyType + "'");
            }
            
            String letter = noteName.substring(0,1);
            
            if(!Music.Notes.containsKey(letter)){
                throw new MusicTheoryException("Wrong note name '" + letter + "' in '" + noteName + "'");
            }
            
            int index = Music.Notes.get(letter);
            
            switch (noteName.charAt(1)) {
                case Music.FLAT:
                    index = (index-1)%12;
                    if(index < 0) index += 12;
                    break;
                case Music.SHARP:
                    index = (index+1)%12;
                    break;
                default:
                    throw new MusicTheoryException("Wrong note name '" + noteName + "'");
            }
            
            noteName = Music.ScaleAlterations.get(scaleKeyType)[index];
        }
        init(new Note(noteName), scaleKeyType);
    }
    
    /**
     * Initializes the key with a note and a quality.
     * @param note the key note.
     * @param scaleKeyType the quality of the key .
     */
    public Key(Note note, String scaleKeyType){
        init(note, scaleKeyType);
    }

    private void init(Note note, String scaleKeyType){
        this.note = note;
        this.name = note.getName() + " " + scaleKeyType;
        this.scaleKeyType = scaleKeyType;
        scale = note.scale(scaleKeyType);
    }
    
    /**
     * Sets the octave to the key scale
     * @param octave 
     */
    public void setOctave(int octave){
        getScale().setOctave(octave);
    }

    /**
     * Initializes the chord with a degree and a type.
     * @param degree degree of the chord (e.g. bV, I, IV).
     * @param type type of the chord.
     * @return the chord.
     */
    public Chord chord(String degree, String type){
        String degreeSymbol = degree;
        char flatOrSharp = degree.charAt(0);

        if (flatOrSharp == Music.FLAT || flatOrSharp == Music.SHARP) {
          degreeSymbol = degree.substring(1);
        }

        if(!Music.Degrees.contains(degreeSymbol)){
            throw new MusicTheoryException("Unknown degree symbol name '" + degreeSymbol + "'");
        }
        int scaleIndex = Music.Degrees.indexOf(degreeSymbol);
        Note rootNote = this.scale.notes[scaleIndex].copy();
        
        if (flatOrSharp == Music.FLAT) {
          Note.semitoneDown(rootNote);
        } else if (flatOrSharp == Music.SHARP) {
          Note.semitoneUp(rootNote);
        }
        
        Chord returnChord = rootNote.chord(type);
        returnChord.setRomanNumeral(Degree.toRomanNumeral(degree, type));
        
        return returnChord;
    } 
    
    /**
     * Initializes the chord with a traditional degree (case-sensitive).
     * @param traditionalDegree traditional degree of the chord (e.g. iv, V, i7).
     * @return
     */
    public Chord chord(String traditionalDegree){
        return chord(Degree.toBaseRomanCaps(traditionalDegree), Degree.toChordType(traditionalDegree));
    }
    
    public Chord identifyTriad(int noteInt){
        return identifyChord(new int[]{noteInt, noteInt+2, noteInt+4});
    }
    
    public Chord identifySeventh(int noteInt){
        return identifyChord(new int[]{noteInt, noteInt+2, noteInt+4, noteInt+6});
    }
    
    private Chord identifyChord(int[] noteIndexes){
        String[] intervalStrings = new String[noteIndexes.length-1];
        for(int i = 0; i < noteIndexes.length-1 ; i++){
            Interval tempInterval = identifyInterval(noteIndexes[0], noteIndexes[i + 1]);
            intervalStrings[i] = tempInterval.toString();
        }
        String chordType = "";
        for(String[] key : Music.ChordsInverse.keySet()){
            if(Arrays.equals(intervalStrings, key)){
                chordType = Music.ChordsInverse.get(key);
            }
        }
        
        return scale.getNotes()[noteIndexes[0]].chord(chordType);
    }
    
    private Interval identifyInterval(int firstNoteIndex, int secondNoteIndex){
        int degree = Math.abs(firstNoteIndex - secondNoteIndex);
        int octaves = secondNoteIndex / 7;
        
        int midiValue = scale.getNotes()[secondNoteIndex % 7].getMidiValue() + octaves*12;
      
        int steps = Math.abs(scale.getNotes()[firstNoteIndex].getMidiValue() - midiValue);
        
        return new Interval(degree, steps);
    }
    
    /**
     * Returns the name of the Key (e.g. Cb, E, A#).
     * @return name of the key.
     */
    public String getName(){
        return getScale().getRoot().getName();
    }
    
    /**
     * Returns the scale of this key.
     * @return scale
     */
    public Scale getScale(){
        return scale;
    }
    
    /**
     * Returns the type of the key (e.g. major, minor, dorian..)
     * @return key type
     */
    public String getType(){
        return scaleKeyType;
    }
    
    /**
     * Returns the octave number of the key scale.
     * @return octave number.
     */
    public int getOctave(){
        return getScale().getOctave();
    }
    
    @Override
    public int compareTo(Key otherKey) {
        return this.note.getMidiValue() - otherKey.note.getMidiValue();
    }

    @Override
    public String toString() {
        return name + " key";
    }

}
