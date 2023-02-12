package Model;

public class GiaoVien {
    private String ten;
    private int tuoi;
    private String mail;
    private String sdt;
    private String maGiaoVien;
    private String vienDaoTao;

    public GiaoVien(){

    }

    public GiaoVien(String ten, int tuoi, String mail, String sdt, String maGiaoVien, String vienDaoTao) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.mail = mail;
        this.sdt = sdt;
        this.maGiaoVien = maGiaoVien;
        this.vienDaoTao = vienDaoTao;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getVienDaoTao() {
        return vienDaoTao;
    }

    public void setVienDaoTao(String vienDaoTao) {
        this.vienDaoTao = vienDaoTao;
    }

    @Override
    public String toString() {
        return "GiaoVien{" +
                "ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                ", mail='" + mail + '\'' +
                ", sdt='" + sdt + '\'' +
                ", maGiaoVien='" + maGiaoVien + '\'' +
                ", vienDaoTao='" + vienDaoTao + '\'' +
                '}';
    }
}
