package hieunnph32561.fpoly.du_an_mau_ph32561.model;

public class Thuthu {
    private String matt;
    private String hoten;
    private String matkhau;

    public Thuthu() {
    }

    public Thuthu(String matt, String hoten, String matkhau) {
        this.matt = matt;
        this.hoten = hoten;
        this.matkhau = matkhau;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
