package com.example.myapplication;

import java.util.Date;

public class Message {
    String text_message;
    String name;



    String email;


    public Message(){

    }

    public Message(String email, String text_message){
        this.text_message = text_message;
        this.email = email;
    }


    public String getText_message() {
        return text_message;
    }

    public void setText_message(String test_message) {
        this.text_message = test_message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
