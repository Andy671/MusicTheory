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
     * Initializes the key with a name and a quality.
     * @param noteName the key note.
     * @param scaleKeyType
     */
    public Key(String noteName, String scaleKeyType){
        this(new Note(noteName),  scaleKeyType);
    }
    
    /**
     * Initializes the key with a note and a quality.
     * @param note the key note.
     * @param scaleKeyType the quality of the key .
     */
    public Key(Note note, String scaleKeyType){
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

        return rootNote.chord(type);
    }

    
    @Override
    public int compareTo(Key otherKey) {
        return this.note.getMidiValue() - otherKey.note.getMidiValue();
    }

    @Override
    public String toString() {
        return name + " " + scaleKeyType;
    }

}
