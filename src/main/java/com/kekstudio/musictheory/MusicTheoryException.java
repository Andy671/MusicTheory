/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kekstudio.musictheory;

/**
 *
 * @author Andy671
 */
public class MusicTheoryException extends RuntimeException{
    public MusicTheoryException(){
        super();
    }

    public MusicTheoryException(String message){
        super(message);
    }

    public MusicTheoryException(String message, Throwable cause){
        super(message, cause);
    }

    public MusicTheoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MusicTheoryException(Throwable cause){
        super(cause);
    }
}