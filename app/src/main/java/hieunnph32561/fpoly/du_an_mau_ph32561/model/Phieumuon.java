package hieunnph32561.fpoly.du_an_mau_ph32561.model;

import java.util.Date;

public class Phieumuon {
    private int mapm;
    private String matt;
    private int matv;
    private int masach;
    private Date ngay;
    private int trasach;
    private int tienthue;

    public Phieumuon() {
    }

    public Phieumuon(int mapm, int matv, String matt, int masach, Date ngay, int trasach, int tienthue) {
        this.mapm = mapm;
        this.matt = matt;
        this.matv = matv;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;

    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }
}
