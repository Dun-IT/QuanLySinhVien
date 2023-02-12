package connect;
import Model.SinhVien;

import java.sql.*;
import java.util.Vector;

public class ServiceSV extends Connect {
    public static Vector<SinhVien> layDanhSachSinhVien(){
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        Vector<SinhVien> dsSinhVien = new Vector<>();
        try{
            String sql = "select * from information";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setTen(result.getString(1));
                sinhVien.setKhoa(result.getInt(2));
                sinhVien.setMssv(result.getString(3));
                sinhVien.setLop(result.getString(4));
                sinhVien.setGpa(result.getDouble(5));
                sinhVien.setDrl(result.getInt(6));;
                dsSinhVien.add(sinhVien);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsSinhVien;
    }

    /**
     * Phương thức xoaSinhVien được dùng để xóa sinh viên theo mssv trong danh sách sinh viên từ
     * bảng information trong cơ sở dũ liệu
     *
     * @param mssv Mã số sinh viên cần xoá
     * @return true nếu xóa sinh viên thành công hoặc false nếu xóa sinh viên không thành công
     */
    public static boolean xoaSinhVien(String mssv) {
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        try {
            String sql = "delete from information where MSSV=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, mssv);
            int result = preStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Phương thức themSinhVien được dùng để thêm sinh viên vào danh sách sinh viên trong bảng information
     * trong cơ sở dữ liệu
     *
     * @param sinhVien Sinh viên cần thêm
     * @return true nếu thêm sinh viên thành công hoặc false nếu thêm sinh viên không thành công
     */
    public static boolean themSinhVien(SinhVien sinhVien) {
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        try {
            String sql = "insert into information values (?,?,?,?,?,?)";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, sinhVien.getTen());
            preStatement.setInt(2, sinhVien.getKhoa());
            preStatement.setString(3, sinhVien.getMssv());
            preStatement.setString(4, sinhVien.getLop());
            preStatement.setDouble(5, sinhVien.getGpa());
            preStatement.setInt(6, sinhVien.getDrl());
            int result = preStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Phương thức chinhSuaThongTinSinhVien được dùng để cập nhật lại thông tin sinh viên
     *
     * @param sinhVien Món ăn cần chỉnh sửa thông tin
     * @return true nếu cập nhật thành công hoặc false nếu không thành công
     */
    public static boolean chinhSuaThongTinSinhVien(SinhVien sinhVien) {
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        try {
            String sql = "update information set HoTen=?, Khoa=?, Lop=?, GPA=?, DRL=? where MSSV=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
//            CallableStatement preStatement = conn.prepareCall("{ CALL sp_Information_Update(?,?,?,?,?,?) }");
            preStatement.setString(1, sinhVien.getTen());
            preStatement.setInt(2, sinhVien.getKhoa());
            preStatement.setString(6, sinhVien.getMssv());
            preStatement.setString(3, sinhVien.getLop());
            preStatement.setDouble(4, sinhVien.getGpa());
            preStatement.setInt(5, sinhVien.getDrl());
            int result = preStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }

    public static void main(String[] args) throws SQLException {
        Vector<SinhVien> list = layDanhSachSinhVien();
        for(SinhVien sv:list){
            System.out.println(sv);
        }
        //System.out.println(xoaSinhVien("20207642"));
        SinhVien sinhVien = new SinhVien("xyc", 61, "ABS", "KD", 3.0, 100);
        System.out.println(chinhSuaThongTinSinhVien(sinhVien));
    }
}
