package com.kekstudio.musictheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Music Theory Base.
 * @author Andy
 */
public class Music {
    
    public static final char SHARP = '#';
    public static final char FLAT = 'b';

    /**
     * A dictionary of notes and an integer representing their index.
     */
    public static final Map<String, Integer> Notes;
    static{
        Map<String, Integer> tempNotes = new HashMap<>();
        tempNotes.put("C", 0);
        tempNotes.put("D", 2);
        tempNotes.put("E", 4);
        tempNotes.put("F", 5);
        tempNotes.put("G", 7);
        tempNotes.put("A", 9);
        tempNotes.put("B", 11);
        Notes = Collections.unmodifiableMap(tempNotes);
    }

    /**
     * A list of musical degrees.
     */
    public static final List<String> Degrees;
    static{
        String[] tempDegrees = {"I", "II", "III", "IV", "V", "VI", "VII"};
        Degrees = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(tempDegrees)));
    }
    
    /**
     * The letters used to represent notes sorted in alphabetical order.
     */
    public static final String[] Alphabet;
    static{
        String[] keySetArray = Notes.keySet().toArray(new String[Notes.size()]);
        Arrays.sort(keySetArray);
        
        Alphabet = keySetArray;
    }

    /**
     * A dictionary of intervals symbols and their value.
     */
    public static final Map<String, Interval> Intervals;
    static{
        Map<String, Interval> tempIntervals = new HashMap<>();
        tempIntervals.put("m2", new Interval(1, 1));
        tempIntervals.put("M2", new Interval(1, 2));
        tempIntervals.put("m3", new Interval(2, 3));
        tempIntervals.put("M3", new Interval(2, 4));
        tempIntervals.put("P4", new Interval(3, 5));
        tempIntervals.put("A4", new Interval(3, 6));
        tempIntervals.put("d5", new Interval(4, 6));
        tempIntervals.put("P5", new Interval(4, 7));
        tempIntervals.put("A5", new Interval(4, 8));
        tempIntervals.put("m6", new Interval(5, 8));
        tempIntervals.put("M6", new Interval(5, 9));
        tempIntervals.put("d7", new Interval(6, 9));
        tempIntervals.put("m7", new Interval(6, 10));
        tempIntervals.put("M7", new Interval(6, 11));
        tempIntervals.put("A7", new Interval(6, 12));
        tempIntervals.put("P8", new Interval(7, 12));
        
        Intervals = Collections.unmodifiableMap(tempIntervals);
    }

    /**
     * A dictionary of scale names and their intervals.
     */
    public static final Map<String, String[]> Scales;
    /**
     * An inverse dictionary of scale names and their intervals.
     */
    public static final Map<String[], String> ScalesInverse;
    static{
        Map<String, String[]> tempScales = new HashMap<>();
        tempScales.put("major", new String[]{"M2", "M3", "P4", "P5", "M6", "M7"});
        tempScales.put("minor", new String[]{"M2", "m3", "P4", "P5", "m6", "m7"});
        tempScales.put("dorian", new String[]{"M2", "m3", "P4", "P5", "M6", "m7"});
        tempScales.put("phrygian", new String[]{"m2", "m3", "P4", "P5", "m6", "m7"});
        tempScales.put("lydian", new String[]{"M2", "M3", "A4", "P5", "M6", "M7"});
        tempScales.put("mixolydian", new String[]{"M2", "M3", "P4", "P5", "M6","m7"});
        tempScales.put("locrian", new String[]{"m2", "m3", "P4", "d5", "m6", "m7"});
        
        Scales = Collections.unmodifiableMap(tempScales);
        
        Map<String[], String> tempScalesInverse = new HashMap<>();
        for(String key :tempScales.keySet()){
            tempScalesInverse.put(tempScales.get(key), key);
        }
        
        ScalesInverse = Collections.unmodifiableMap(tempScalesInverse);
    }

    /**
     * A dictionary of chord names and their intervals.
     */
    public static final Map<String, String[]> Chords;
    /**
     * An inverse dictionary of chord names and their intervals.
     */
    public static final Map<String[], String> ChordsInverse;
    static{
        Map<String, String[]> tempChords = new HashMap<>();
        tempChords.put("min", new String[]{"m3", "P5"});
        tempChords.put("maj", new String[]{"M3", "P5"});
        tempChords.put("dim", new String[]{"m3", "d5"});
        tempChords.put("aug", new String[]{"M3", "A5"});
        tempChords.put("sus", new String[]{"P4", "P5"});
        tempChords.put("sus2", new String[]{"M2", "P5"});
        tempChords.put("m2", new String[]{"M2", "m3", "P5"});
        tempChords.put("M2", new String[]{"M2", "M3", "P5"});
        tempChords.put("m6", new String[]{"m3", "P5", "M6"});
        tempChords.put("M6", new String[]{"M3", "P5", "M6"});
        tempChords.put("7", new String[]{"M3", "P5", "m7"});
        tempChords.put("m7", new String[]{"m3", "P5", "m7"});
        tempChords.put("M7", new String[]{"M3", "P5", "M7"});
        tempChords.put("7sus", new String[]{"P4", "P5", "m7"});
        tempChords.put("dim7", new String[]{"m3", "d5", "d7"});
        tempChords.put("m7♭5", new String[]{"m3", "d5", "m7"});
        tempChords.put("mM7", new String[]{"m3", "P5", "M7"});
        tempChords.put("aug7", new String[]{"M3", "A5", "m7"});
        tempChords.put("M7♭5", new String[]{"M3", "d5", "M7"});
        tempChords.put("M7♯5", new String[]{"M3", "A5", "M7"});
        
        Chords = Collections.unmodifiableMap(tempChords);
        
        Map<String[], String> tempChordsInverse = new HashMap<>();
        for(String key :tempChords.keySet()){
            tempChordsInverse.put(tempChords.get(key), key);
        }
        
        ChordsInverse = Collections.unmodifiableMap(tempChordsInverse);
        
    }

    /**
     * A dictionary representing the circle of fifths, both major and minor.
     */
    public static final Map<String, String[]> CircleOfFifths;
    static{
        Map<String, String[]> tempCircle = new HashMap<>();
        tempCircle.put("major", new String[]{"C", "G", "D", "A", "E", "B", "F"+SHARP, "C"+SHARP,
                                            "F", "B"+FLAT, "E"+FLAT, "A"+FLAT, "D"+FLAT, "G"+FLAT, "C"+FLAT});
        
        tempCircle.put("minor", new String[]{"A", "E", "B", "F"+SHARP, "C"+SHARP, "G"+SHARP, "D"+SHARP,
                                            "A"+SHARP, "D", "G", "C", "F", "B"+FLAT, "E"+FLAT, "A"+FLAT});
        
        CircleOfFifths = Collections.unmodifiableMap(tempCircle);
    }
    
}
