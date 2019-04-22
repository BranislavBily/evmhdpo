package sample.vodici;

import javafx.scene.control.Button;

import java.util.Date;

public class TableModelVod {
    private int idVodica;
    private String menoVodica;
    private String priezviskoVodica;
    private int vozidlo;
    private String stavVodica;
    private Date prehliadka;
    private Button update;
    private Button delete;


    public TableModelVod(int idVodica, String menoVodica, String priezviskoVodica, int vozidlo, String stavVodica, Date prehliadka , Button update, Button delete) {
        this.idVodica = idVodica;
        this.menoVodica = menoVodica;
        this.priezviskoVodica = priezviskoVodica;
        this.vozidlo = vozidlo;
        this.stavVodica = stavVodica;
        this.prehliadka = prehliadka;
        this.update = update;
        this.delete = delete;
        this.delete.setText("Delete");
        this.update.setText("Update");
    }




    public int getIdVodica() {
        return idVodica;
    }

    public void setIdVodica(int idVodica) {
        this.idVodica = idVodica;
    }

    public String getMenoVodica() {
        return menoVodica;
    }

    public void setMenoVodica(String menoVodica) {
        this.menoVodica = menoVodica;
    }

    public String getPriezviskoVodica() {
        return priezviskoVodica;
    }

    public void setPriezviskoVodica(String priezviskoVodica) {
        this.priezviskoVodica = priezviskoVodica;
    }

    public int getVozidlo() {
        return vozidlo;
    }

    public void setVozidlo(int vozidlo) {
        this.vozidlo = vozidlo;
    }

    public String getStavVodica() {
        return stavVodica;
    }

    public void setStavVodica(String stavVodica) {
        this.stavVodica = stavVodica;
    }

    public Date getPrehliadka() {
        return prehliadka;
    }

    public void setPrehliadka(Date prehliadka) {
        this.prehliadka = prehliadka;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}


