# Music Theory
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/java-v1.7-red.svg)](https://www.oracle.com/index.html)

## Introduction
Java music theory library inspired by the [Music Theory Library for Swift OS X and iOS apps.](https://github.com/danielbreves/MusicTheory).
I am still working on it, so it will become better eventually.
Project uses Java 1.7, so you can use it in your Android projects without any problems.

## Installation

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```  
Step 2. Add the dependency
```gradle
	dependencies {
	        compile 'com.github.Andy671:MusicTheory:v0.6.4'
	}
```

## Usage

```java

	Note dSharpNote = new Note("D#");
	System.out.println(dSharpNote); // D#4[63]

	dSharpNote = new Note("D#", 5);
	System.out.println(dSharpNote); // D#5[75]

	Chord dSharpm7Chord = dSharpNote.chord("m7");
	System.out.println(dSharpm7Chord); // D#m7 inversion[0] {D#5[75], F#5[78], A#5[82], C#6[85]}

	Scale dSharpScale = dSharpNote.scale("minor");
	System.out.println(dSharpScale); // D# minor scale {D#5[75], E#5[77], F#5[78], G#5[80], A#5[82], B5[83], C#6[85]}

	Key cFlatMajorKey = new Key("Cb", "major");
	System.out.println(cFlatMajorKey); // Cb major key

	Chord thirdFlatDegreeAugChord = cFlatMajorKey.chord("bIII", "aug");
	System.out.println(thirdFlatDegreeAugChord); // Ebbaug inversion[0] {Ebb4[62], Gb4[66], Bb4[70]}

	thirdFlatDegreeAugChord.setPosition(1);
	System.out.println(thirdFlatDegreeAugChord); // Ebbaug inversion[1] {Bb5[82], Ebb4[62], Gb4[66]}

	thirdFlatDegreeAugChord.sort();
	System.out.println(thirdFlatDegreeAugChord); // Ebbaug inversion[1] {Ebb4[62], Gb4[66], Bb5[82]}

```

For more info check out JavaDoc in the source code


## Contribution
* Feel free to fork the repo, make pull requests or fix existing bug
* Feel free to open issues if you find some bug or unexpected behaviour
