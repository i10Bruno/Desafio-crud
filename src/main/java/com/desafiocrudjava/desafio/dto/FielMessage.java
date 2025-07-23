package com.desafiocrudjava.desafio.dto;

public class FielMessage {

private  String Fieldname;
private String message;


    public FielMessage(String fieldname, String message) {
        Fieldname = fieldname;
        this.message = message;
    }

    public String getFieldname() {
        return Fieldname;
    }

    public String getMessage() {
        return message;
    }
}
