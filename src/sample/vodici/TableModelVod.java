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
    private Button contact;
    private String email;

    public TableModelVod(int idVodica, String menoVodica, String priezviskoVodica, int vozidlo, String stavVodica, Date prehliadka , Button update, Button delete, Button contact, String email) {
        this.idVodica = idVodica;
        this.menoVodica = menoVodica;
        this.priezviskoVodica = priezviskoVodica;
        this.vozidlo = vozidlo;
        this.stavVodica = stavVodica;
        this.prehliadka = prehliadka;
        this.update = update;
        this.delete = delete;
        this.contact = contact;
        this.delete.setText("Delete");
        this.update.setText("Update");
        this.contact.setText("Contact");
        this.delete.setId("tableViewDelete");
        this.update.setId("tableViewUpdate");
        this.contact.setId("tableViewContact");
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getContact() {
        return contact;
    }

    public void setContact(Button contact) {
        this.contact = contact;
    }
}


