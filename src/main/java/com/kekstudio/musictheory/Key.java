package com.kekstudio.musictheory;

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
            if(noteName.charAt(1) == Music.FLAT){
                index = (index-1)%12;
                if(index < 0) index += 12;
            }else if(noteName.charAt(1) == Music.SHARP){
                index = (index+1)%12;
            }else {
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
        returnChord.setTheoryDegree(Chord.toTheoryDegree(degree, type));
        
        return returnChord;
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
