/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kekstudio.musictheorytest;

import com.kekstudio.musictheory.Chord;
import com.kekstudio.musictheory.Key;
import com.kekstudio.musictheory.Note;
import java.util.Arrays;

/**
 *
 * @author Andy671
 */
public class Sample {
    public static void main(String[] args) {
//        Note cFlatNote = new Note("Cb");
        Key cFlatMajor = new Key("Cb", "major");
        Chord cFlatbII = cFlatMajor.chord("I", "dim");
        System.out.println(cFlatbII);
        cFlatbII.setPosition(2);
        cFlatbII.sort();
        System.out.println(cFlatbII);
//        XCTAssertEqual(cFlatbII.names, ["D♭♭", "F♭", "A♭♭"],
        
//              Map<String, Interval> tempIntervals = new HashMap<>();
//        tempIntervals.put("m2", new Interval(1, 1));
//        tempIntervals.put("M2", new Interval(1, 2));
//        tempIntervals.put("m3", new Interval(2, 3));
//        tempIntervals.put("M3", new Interval(2, 4));
//        tempIntervals.put("P4", new Interval(3, 5));
//        tempIntervals.put("A4", new Interval(3, 6));
//        tempIntervals.put("d5", new Interval(4, 6));
//        tempIntervals.put("P5", new Interval(4, 7));
//        tempIntervals.put("A5", new Interval(4, 8));
//        tempIntervals.put("m6", new Interval(5, 8));
//        tempIntervals.put("M6", new Interval(5, 9));
//        tempIntervals.put("d7", new Interval(6, 9));
//        tempIntervals.put("m7", new Interval(6, 10));
//        tempIntervals.put("M7", new Interval(6, 11));
//        tempIntervals.put("A7", new Interval(6, 12));
//        tempIntervals.put("P8", new Interval(7, 12));
    }
   
}
