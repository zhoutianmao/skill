package com.ztm.exception;

public class RepeatKillException  extends RuntimeException{

    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message, Throwable cause){
        super(message, cause);
    }
}
