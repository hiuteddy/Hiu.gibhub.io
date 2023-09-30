package hieunnph32561.fpoly.du_an_mau_ph32561.model;

public class Thanhvien {
    private String matv;
    private String hoten;
    private String namsinh;

    public Thanhvien(String matv, String hoten, String namsinh) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
    }


    public String getMatv() {
        return matv;
    }

    public void setMatv(String matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }
}
