package com.example.myapplication;

public class User {

    private String name;
    private String surname;
    private String nickname;
    private String mail;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public User(String name, String surname, String mail){
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public User(){

    }

    public String getMail(){
        return mail;
    }

    public String getPassword(){
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
