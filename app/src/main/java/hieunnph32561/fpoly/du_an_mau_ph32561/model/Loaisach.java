package hieunnph32561.fpoly.du_an_mau_ph32561.model;


public class Loaisach {
    private int maLoai;
    private String tenLoai;


    public Loaisach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Loaisach(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return maLoai + "|" + tenLoai;
    }
}


