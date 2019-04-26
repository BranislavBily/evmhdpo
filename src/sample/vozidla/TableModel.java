package sample.vozidla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.sql.Date;

public class TableModel {

    private int tableEvc;
    private String tableSpz;
    private String tableVodici;
    private Date tableStk;
    private String tableTyp;
    private String tableStav;
    private String tableReklama;
    private Button  update;
    private Button delete;


    public TableModel(int tableEvc, String tableSpz, String tableVodici, Date tableStk, String tableTyp, String tableStav, String tableReklama, Button delete,Button update) {
        this.tableEvc = tableEvc;
        this.tableSpz = tableSpz;
        this.tableVodici = tableVodici;
        this.tableStk = tableStk;
        this.tableTyp = tableTyp;
        this.tableStav = tableStav;
        this.tableReklama = tableReklama;
        this.update = update;
        this.delete = delete;
        this.delete.setText("Delete");
        this.update.setText("Update");
        this.delete.setId("tableViewDelete");
        this.update.setId("tableViewUpdate");

    }




    public int getTableEvc() {
        return tableEvc;
    }

    public void setTableEvc(int tableEvc) {
        this.tableEvc = tableEvc;
    }

    public String getTableSpz() {
        return tableSpz;
    }

    public void setTableSpz(String tableSpz) {
        this.tableSpz = tableSpz;
    }

    public String getTableVodici() {
        return tableVodici;
    }

    public void setTableVodici(String tableVodici) {
        this.tableVodici = tableVodici;
    }

    public Date getTableStk() {
        return tableStk;
    }

    public void setTableStk(Date tableStk) {
        this.tableStk = tableStk;
    }

    public String getTableTyp() {
        return tableTyp;
    }

    public void setTableTyp(String tableTyp) {
        this.tableTyp = tableTyp;
    }

    public String getTableStav() {
        return tableStav;
    }

    public void setTableStav(String tableStav) {
        this.tableStav = tableStav;
    }

    public String getTableReklama() {
        return tableReklama;
    }

    public void setTableReklama(String tableReklama) {
        this.tableReklama = tableReklama;
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