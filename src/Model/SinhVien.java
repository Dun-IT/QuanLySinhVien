package Model;

public class SinhVien {
    private String ten;
    private int khoa;
    private String mssv;
    private String lop;
    private double gpa;
    private int drl;

    public SinhVien(){

    }

    public SinhVien(String ten, int khoa, String mssv, String lop, double gpa, int drl) {
        this.ten = ten;
        this.khoa = khoa;
        this.mssv = mssv;
        this.lop = lop;
        this.gpa = gpa;
        this.drl = drl;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getKhoa() {
        return khoa;
    }

    public void setKhoa(int khoa) {
        this.khoa = khoa;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getDrl() {
        return drl;
    }

    public void setDrl(int drl) {
        this.drl = drl;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "ten='" + ten + '\'' +
                ", khoa=" + khoa +
                ", mssv='" + mssv + '\'' +
                ", lop='" + lop + '\'' +
                ", gpa=" + gpa +
                ", drl=" + drl +
                '}';
    }
}
