package com.example.connecthlete;

public class Player {

    private String password;
    private String sport;
    private String uni;
    public Player() {}
    public Player(String password, String sport,String uni) {


        this.password = password;
        this.sport = sport;
        this.uni=uni;

    }
    public String getPassword(){
        return password;
    }


    public void setPassword(String password){
        this.password = password;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
