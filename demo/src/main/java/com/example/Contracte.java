package com.example;

public class Contracte {
    private int id, id_client, id_personal, filiala;
    private double plata;

    private String tipContract, client, personal, dataBegin, dataEnd;

    Contracte(int id, String tipContract, String client, String personal,
            int filiala, String dataBegin, String dataEnd, double plata, int id_personal, int id_client) {
        this.id = id;
        this.id_personal = id_personal;
        this.id_client = id_client;
        this.tipContract = tipContract;
        this.client = client;
        this.personal = personal;
        this.filiala = filiala;
        this.dataBegin = dataBegin;
        this.dataEnd = dataEnd;
        this.plata = plata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public String getTipContract() {
        return tipContract;
    }

    public void setTipContract(String tipContract) {
        this.tipContract = tipContract;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public int getFiliala() {
        return filiala;
    }

    public void setFiliala(int filiala) {
        this.filiala = filiala;
    }

    public String getDataBegin() {
        return dataBegin;
    }

    public void setDataBegin(String dataBegin) {
        this.dataBegin = dataBegin;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public String toString() {
        return "- Client deservit: " + client + "\n- Personal: " + personal + "\n- ID filiala: " + filiala
                + "\n- Data inscrierii: " + dataBegin + "\n- Data incheierii: " + dataEnd + "\n- Plata contract: "
                + plata + " lei" + "\n- Plata pentru " + personal + ": " + (plata * 0.10) + " lei";
    }
}
