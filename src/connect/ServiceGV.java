package connect;
import Model.GiaoVien;
import Model.SinhVien;

import java.sql.*;
import java.util.Vector;

public class ServiceGV extends Connect {
    public static Vector<GiaoVien> layDanhSachGiaoVien(){
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        Vector<GiaoVien> dsGiaoVien = new Vector<>();
        try{
            String sql = "select * from teacher";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                GiaoVien giaoVien = new GiaoVien();
                giaoVien.setTen(result.getString(1));
                giaoVien.setTuoi(result.getInt(2));
                giaoVien.setMail(result.getString(3));
                giaoVien.setSdt(result.getString(4));
                giaoVien.setMaGiaoVien(result.getString(5));
                giaoVien.setVienDaoTao(result.getString(6));;
                dsGiaoVien.add(giaoVien);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsGiaoVien;
    }

    /**
     * Phương thức xoaGiaoVien được dùng để xoá giáo viên theo mã giáo viên trong danh sách giáo viên
     * tại bảng teacher trong cơ sở dữ liệu
     *
     * @param maGiaoVien mã giáo viên cần xoá
     * @return true nếu xoá giáo viên thành công hoặc false nếu xoá giáo viên không thành công
     */
    public static boolean xoaGiaoVien(String maGiaoVien) {
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        try {
            String sql = "delete from teacher where maGiaoVien=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, maGiaoVien);
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
     * Phương thức themGiaoVien được dùng để thêm giáo viên vào danh sách giáo viên
     * trong cơ sở dữ liệu
     * @param giaoVien giáo viên cần thêm
     * @return true nếu thêm giáo viên thành công hoặc false nếu thêm giáo viên thất bại
     */
    public static boolean themGiaoVien(GiaoVien giaoVien) {
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        try {
            String sql = "insert into teacher values (?,?,?,?,?,?)";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, giaoVien.getTen());
            preStatement.setInt(2, giaoVien.getTuoi());
            preStatement.setString(3, giaoVien.getMail());
            preStatement.setString(4, giaoVien.getSdt());
            preStatement.setString(5, giaoVien.getMaGiaoVien());
            preStatement.setString(6, giaoVien.getVienDaoTao());
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
     * Phương thức chinhSuaThongTinGiaoVien được dùng để thay đổi lại thông tin giáo viên
     * trong cơ sở dữ liệu
     * @param giaoVien giáo viên cần thay đổi thông tin
     * @return trả về true nếu thay đổi thông tin thành công và false nếu thay đổi không thành công
     */
    public static boolean chinhSuaThongTinGiaoVien(GiaoVien giaoVien) {
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        try {
            String sql = "update teacher set ten=?, tuoi=?, mail=?, sdt=?, vienDaoTao=? where maGiaoVien=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
//            CallableStatement preStatement = conn.prepareCall("{ CALL sp_Information_Update(?,?,?,?,?,?) }");
            preStatement.setString(1, giaoVien.getTen());
            preStatement.setInt(2, giaoVien.getTuoi());
            preStatement.setString(6, giaoVien.getMaGiaoVien());
            preStatement.setString(3, giaoVien.getMail());
            preStatement.setString(4, giaoVien.getSdt());
            preStatement.setString(5, giaoVien.getVienDaoTao());
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
        Vector<GiaoVien> list = layDanhSachGiaoVien();
        for(GiaoVien gv:list){
            System.out.println(gv);
        }

        GiaoVien giaoVien = new GiaoVien("abasdc", 20, "anjasc", "123456789", "123", "CNTT");
        System.out.println(xoaGiaoVien("123"));
    }

}
