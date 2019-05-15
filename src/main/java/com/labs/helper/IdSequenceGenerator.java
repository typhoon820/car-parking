package com.labs.helper;

public class IdSequenceGenerator {

    private long id;

    private static IdSequenceGenerator generator;

    private IdSequenceGenerator(){
        this.id = 0;
    }

    public static IdSequenceGenerator getInstance(){
        if(generator ==null){
            generator = new IdSequenceGenerator();
        }
        return generator;
    }

    public long getId(){
        return id++;
    }
}
