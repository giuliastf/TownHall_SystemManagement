package org.example;

public class ExceptieCerere extends Exception{
    String mesaj;
    public ExceptieCerere(String s) {
        this.mesaj = s; // mesaj
    }
}