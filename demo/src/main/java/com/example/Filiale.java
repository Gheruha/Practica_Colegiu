package com.example;

public class Filiale {
    private String nume, telefon, adresa;
    private int id;

    // Constructor with parameters
    Filiale(int id, String nume, String telefon, String adresa) {
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
        this.adresa = adresa;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    // ToString
    public String toString() {
        return "ID: " + id + ",    Nume: " + nume + "\n- Adresa: " + adresa + ",    Telefon: " + telefon;
    }
}
