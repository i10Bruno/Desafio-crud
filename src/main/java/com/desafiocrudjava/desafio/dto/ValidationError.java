package com.desafiocrudjava.desafio.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError  extends  CustomError{

    private List<FielMessage> err = new ArrayList<>();



    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }


    public List<FielMessage> getErr() {
        return err;
    }


    public void addError (String Fieldname , String message){
        err.add(new FielMessage(Fieldname,message));
    }


}
