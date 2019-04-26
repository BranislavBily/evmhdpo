package sample.servis;

import javafx.scene.control.Button;

import java.util.Date;

public class TableModelServ {

    private int evcVozidla;
    private Date odstavene;
    private String zavada;
    private String hala;
    private String stav;
    private Date odovzdanie;
    private Button update;
    private Button delete;

    public TableModelServ(int evcVozidla, Date odstavene, String zavada, String hala, String stav, Date odovzdanie, Button update, Button delete) {
        this.evcVozidla = evcVozidla;
        this.odstavene = odstavene;
        this.zavada = zavada;
        this.hala = hala;
        this.stav = stav;
        this.odovzdanie = odovzdanie;
        this.update = update;
        this.delete = delete;
        this.delete.setText("Delete");
        this.update.setText("Update");
        this.delete.setId("tableViewDelete");
        this.update.setId("tableViewUpdate");
    }

    public int getEvcVozidla() {
        return evcVozidla;
    }

    public void setEvcVozidla(int evcVozidla) {
        this.evcVozidla = evcVozidla;
    }

    public Date getOdstavene() {
        return odstavene;
    }

    public void setOdstavene(Date odstavene) {
        this.odstavene = odstavene;
    }

    public String getZavada() {
        return zavada;
    }

    public void setZavada(String zavada) {
        this.zavada = zavada;
    }

    public String getHala() {
        return hala;
    }

    public void setHala(String hala) {
        this.hala = hala;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public Date getOdovzdanie() {
        return odovzdanie;
    }

    public void setOdovzdanie(Date odovzdanie) {
        this.odovzdanie = odovzdanie;
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
