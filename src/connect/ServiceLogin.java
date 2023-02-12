package connect;
import Model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ServiceLogin extends Connect{
    public static Vector<Account> layDanhSachTaiKhoan(){
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        Vector<Account> dsTaiKhoan = new Vector<>();
        try{
            String sql = "select * from login";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                Account account = new Account();
                account.setUsername(result.getString(1));
                account.setPassword(result.getString(2));
                dsTaiKhoan.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }

    public static void main(String[] args) throws SQLException {
        Vector<Account> list = layDanhSachTaiKhoan();
        for(Account account : list){
            System.out.println(account);
        }
    }
}
