package com.example;

public class Personal {
    private int id, id_filiala;

    private String nume, prenume, idnp, oras, telefon;

    // Constructor with parameters
    Personal(int id, int id_filiala, String nume, String prenume, String idnp, String oras, String telefon) {
        this.id = id;
        this.id_filiala = id_filiala;
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.oras = oras;
        this.telefon = telefon;
    }

    // Getters and setters
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getIdnp() {
        return idnp;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    // ToString
    public String toString() {
        return "ID:" + id + ",   Nume: " + nume + " " + prenume + ",    IDNP: " + idnp
                + ",    Oras: " + oras + ",    Telefon" + ",    Filiala: " + id_filiala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
