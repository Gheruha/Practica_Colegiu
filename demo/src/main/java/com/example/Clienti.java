package com.example;

public class Clienti {
    private String nume, prenume, idnp, oras, telefon;
    private int id;

    Clienti() {

    }

    Clienti(int id, String nume, String prenume, String idnp, String oras, String telefon) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.oras = oras;
        this.telefon = telefon;
    }

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
                + ",    Oras: " + oras + ",    Telefon: " + telefon;
    }
}
