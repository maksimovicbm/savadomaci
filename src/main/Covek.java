/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Branko
 */
public class Covek {
    
    int rednibroj_id;
    String ime;
    String prezime;
    String mail;

    public Covek(int rednibroj_id, String ime, String prezime, String mail) {
        this.rednibroj_id = rednibroj_id;
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
    }

    public int getRednibroj_id() {
        return rednibroj_id;
    }

    public void setRednibroj_id(int rednibroj_id) {
        this.rednibroj_id = rednibroj_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime + " " + this.rednibroj_id;
    }
    
    
}
