package sample.vozidla;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class TableModel {

    private String tableEvc;
    private String tableSpz;
    private String tableVodici;
    private String tableStk;
    private String tableTyp;
    private String tableStav;
    private String tableReklama;

    public TableModel(String tableEvc, String tableSpz, String tableVodici, String tableStk, String tableTyp, String tableStav, String tableReklama) {
        this.tableEvc = tableEvc;
        this.tableSpz = tableSpz;
        this.tableVodici = tableVodici;
        this.tableStk = tableStk;
        this.tableTyp = tableTyp;
        this.tableStav = tableStav;
        this.tableReklama = tableReklama;
    }

    public String getTableEvc() {
        return tableEvc;
    }

    public void setTableEvc(String tableEvc) {
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

    public String getTableStk() {
        return tableStk;
    }

    public void setTableStk(String tableStk) {
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
}
