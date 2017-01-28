/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kekstudio.musictheorytest;

import com.kekstudio.musictheory.Chord;
import com.kekstudio.musictheory.Key;
import com.kekstudio.musictheory.Note;
import com.kekstudio.musictheory.Scale;
import java.util.Arrays;

/**
 *
 * @author Andy671
 */
public class Sample {
    public static void main(String[] args) {
        Note dSharpNote = new Note("D#");
        System.out.println(dSharpNote); // D#4[63]

        dSharpNote = new Note("D#", 5);
        System.out.println(dSharpNote); // D#5[75]
        
        Chord dSharpm7Chord = dSharpNote.chord("m7"); 
        System.out.println(dSharpm7Chord); //{D#5[75], F#5[78], A#5[82], C#6[85]}
        
        Scale dSharpScale = dSharpNote.scale("minor");
        System.out.println(dSharpScale); //{D#5[75], E#5[77], F#5[78], G#5[80], A#5[82], B5[83], C#6[85]}
        
        Key cFlatMajorKey = new Key("Cb", "major");
        
        Chord thirdFlatDegreeAugChord = cFlatMajorKey.chord("bIII", "aug");
        System.out.println(thirdFlatDegreeAugChord); //{Ebb4[62], Gb4[66], Bb4[70]}
        
        thirdFlatDegreeAugChord.setPosition(1);
        System.out.println(thirdFlatDegreeAugChord); //{Bb5[82], Ebb4[62], Gb4[66]}
        
        thirdFlatDegreeAugChord.sort();
        System.out.println(thirdFlatDegreeAugChord); //{Ebb4[62], Gb4[66], Bb5[82]}
    }
   
}
