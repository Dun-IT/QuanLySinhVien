package code;

import Model.SinhVien;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import connect.ServiceSV;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Label;
import jxl.write.Number;

public class danhsachUI extends JFrame {
    JButton btnThoat, btnXuatFile;
    JComboBox<String> comboGPA, comboDRL, comboHocBong;
    JTextField txtSearch;
    private DefaultTableModel dtmTableDanhSach;
    private JTable tblDanhSach;
    private Vector<SinhVien> dsSinhVien;

    public danhsachUI(){
        addControls();
        addEvents();
        showWindow();
    }

    public void addControls(){
        //Tiêu đề
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnThongTin.setPreferredSize(new Dimension(0, 120));
        pnThongTin.setBackground(new Color(93, 167, 232));
        JLabel lbThongTin = new JLabel("Danh sách sinh viên");
        lbThongTin.setHorizontalTextPosition(JLabel.CENTER);
        lbThongTin.setVerticalTextPosition(JLabel.CENTER);
        lbThongTin.setHorizontalAlignment(JLabel.CENTER);
        lbThongTin.setVerticalAlignment(JLabel.CENTER);
        lbThongTin.setFont(new Font("Bahnschrift", Font.BOLD, 50));
        pnThongTin.add(lbThongTin, BorderLayout.CENTER);

        //Panel chức năng
        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));
        pnChucNang.setOpaque(false);

        //Tạo các combo box
        String[] xepGPA = {"Cao đến thấp", "Thấp đến cao"};
        String[] xepDRL = {"Cao đến thấp", "Thấp đến cao"};
        String[] xepHocBong = {"Loại A", "Loại B", "Loại C"};
        JPanel pnXepGPA = new JPanel();
        JLabel lbXepGPA = new JLabel("Sắp xếp theo GPA: ");
        lbXepGPA.setPreferredSize(new Dimension(180, 50));
        lbXepGPA.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        comboGPA = new JComboBox<>(xepGPA);
        comboGPA.setSelectedItem(null);
        customComboBox(comboGPA);
        pnXepGPA.setOpaque(false);
        pnXepGPA.add(lbXepGPA);
        pnXepGPA.add(comboGPA);
        JPanel pnXepDRL = new JPanel();
        JLabel lbXepDRL = new JLabel("Sắp xếp theo ĐRL: ");
        lbXepDRL.setPreferredSize(new Dimension(180, 50));
        lbXepDRL.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        comboDRL = new JComboBox<>(xepDRL);
        comboDRL.setSelectedItem(null);
        customComboBox(comboDRL);
        pnXepDRL.setOpaque(false);
        pnXepDRL.add(lbXepDRL);
        pnXepDRL.add(comboDRL);
        JPanel pnXepHocBong = new JPanel();
        JLabel lbXepHocBong = new JLabel("Học bổng loại: ");
        lbXepHocBong.setPreferredSize(new Dimension(180, 50));
        lbXepHocBong.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        comboHocBong = new JComboBox<>(xepHocBong);
        comboHocBong.setSelectedItem(null);
        customComboBox(comboHocBong);
        pnXepHocBong.setOpaque(false);
        pnXepHocBong.add(lbXepHocBong);
        pnXepHocBong.add(comboHocBong);

        //Tạo khu vực 2 nút xuất file và nút thoát
        JPanel pnXuat = new JPanel();
        pnXuat.setBackground(new Color(165, 213, 255));
        pnXuat.setOpaque(true);
        btnXuatFile = new JButton("Xuất file");
        btnXuatFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXuatFile.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/danhsachUI/excel_logo.svg"))));
        btnXuatFile.setFocusable(false);
        btnXuatFile.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
//        btnXuatFile.setBackground(Color.WHITE);
        btnXuatFile.setPreferredSize(new Dimension(150, 50));
        btnXuatFile.setIconTextGap(10);
//        btnXuatFile.setBorder(BorderFactory.createRaisedBevelBorder());
        pnXuat.add(btnXuatFile);

        JPanel pnThoat = new JPanel();
        pnThoat.setBackground(new Color(165, 213, 255));
        pnThoat.setOpaque(true);
        btnThoat = new JButton("Quay lại");
        btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnThoat.setFocusable(false);
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/danhsachUI/close.svg"))));
        btnThoat.setVerticalAlignment(JButton.CENTER);
        btnThoat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//        btnThoat.setBackground(Color.WHITE);
        btnThoat.setPreferredSize(new Dimension(150, 70));
//        btnThoat.setBorder(BorderFactory.createRaisedBevelBorder());
        pnThoat.add(btnThoat);

        pnChucNang.add(Box.createVerticalGlue());   //Căn phía trên của vùng chức năng
        pnChucNang.add(pnXepGPA);
        pnChucNang.add(pnXepDRL);
        pnChucNang.add(pnXepHocBong);
        pnChucNang.add(pnXuat);
        pnChucNang.add(pnThoat);

        //Panel center
        JPanel pnCenter = new JPanel();
        pnCenter.setOpaque(false);
        pnCenter.setLayout(new BorderLayout());
        JPanel pnSearch = new JPanel();
        pnSearch.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        pnSearch.setOpaque(false);
        txtSearch = new JTextField(20);
        txtSearch.setPreferredSize(new Dimension(30, 35));
        txtSearch.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        JLabel lbSearch = new JLabel();
        lbSearch.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/image/danhsachUI/search.svg"))));
        pnSearch.add(txtSearch);
        pnSearch.add(lbSearch);
        pnCenter.add(pnSearch, BorderLayout.NORTH);

        JPanel pnDanhSach = new JPanel();
        pnDanhSach.setOpaque(false);
        pnDanhSach.setLayout(new BorderLayout());
        pnDanhSach.setBorder(BorderFactory.createEmptyBorder(20,30,30,30));
        dtmTableDanhSach = new DefaultTableModel();
        dtmTableDanhSach.addColumn("STT");
        dtmTableDanhSach.addColumn("Họ tên");
        dtmTableDanhSach.addColumn("Khoá");
        dtmTableDanhSach.addColumn("MSSV");
        dtmTableDanhSach.addColumn("Lớp");
        dtmTableDanhSach.addColumn("GPA");
        dtmTableDanhSach.addColumn("ĐRL");
        tblDanhSach = new JTable(dtmTableDanhSach);
        tblDanhSach.setBackground(Color.WHITE);
        tblDanhSach.setRowHeight(tblDanhSach.getRowHeight() + 15);  //Tăng chiều cao cho các hàng của bảng dữ liệu
        tblDanhSach.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,20));
        tblDanhSach.setShowVerticalLines(true);
        tblDanhSach.setShowHorizontalLines(true);
        Font headerFont = new Font(Font.SANS_SERIF, Font.BOLD,22);
        JTableHeader tblHeader = tblDanhSach.getTableHeader();
        tblHeader.setBackground(new Color(93, 167, 232));
        tblHeader.setFont(headerFont);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer = (DefaultTableCellRenderer) tblHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblDanhSach.setDefaultEditor(Object.class, null);
        tblDanhSach.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblDanhSach.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDanhSach.getColumnModel().getColumn(2).setPreferredWidth(10);
        tblDanhSach.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblDanhSach.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblDanhSach.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblDanhSach.getColumnModel().getColumn(6).setPreferredWidth(10);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i <= 6; i++){
            tblDanhSach.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollDanhSach = new JScrollPane(tblDanhSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hienThiDanhSachSinhVien();
        pnDanhSach.add(scrollDanhSach, BorderLayout.CENTER);
        pnCenter.add(pnDanhSach, BorderLayout.CENTER);


        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(20,20,20,40));
        pnMain.setBackground(new Color(165, 213, 255));
        pnMain.add(pnThongTin, BorderLayout.NORTH);
        pnMain.add(pnChucNang, BorderLayout.EAST);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        con.add(pnMain);
    }

    public void addEvents(){
        //Chức năng nút thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainUI();
            }
        });
        //Chức năng thanh tìm kiếm
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                locThongTin();
            }
        });
        //Chức năng combobox
        comboGPA.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboGPA.getSelectedIndex() != -1){
                    if (comboGPA.getSelectedIndex() == 0){
                        comboDRL.setSelectedItem(null);
                        comboHocBong.setSelectedItem(null);
                        hienThiTheoGPAGiam();
                    }else if(comboGPA.getSelectedIndex() == 1){
                        comboDRL.setSelectedItem(null);
                        comboHocBong.setSelectedItem(null);
                        hienThiTheoGPATang();
                    }
                }
            }
        });

        comboDRL.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboDRL.getSelectedIndex() != -1){
                    if (comboDRL.getSelectedIndex() == 0){
                        comboGPA.setSelectedItem(null);
                        comboHocBong.setSelectedItem(null);
                        hienThiTheoDRLGiam();
                    }else if(comboDRL.getSelectedIndex() == 1){
                        comboGPA.setSelectedItem(null);
                        comboHocBong.setSelectedItem(null);
                        hienThiTheoDRLTang();
                    }
                }
            }
        });

        hienThiHB();
        //Chức năng nút xuất file
        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuatExcel();
            }
        });
    }

    /**
     * Phương thức dùng để thay đổi các combo box có cùng kích thước trong chương trình
     * @param comboBox
     */
    public void customComboBox(JComboBox comboBox){
        comboBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        comboBox.setEditable(false);
        comboBox.setFocusable(false);
        comboBox.setPreferredSize(new Dimension(150, 30));
    }

    /**
     * Lọc thông tin khi người dùng nhập dữ liệu vào thanh tìm kiếm
     */
    public void locThongTin(){
        docDuLieu();
        String duLieuLoc = txtSearch.getText();
        Vector<SinhVien> dsSinhVienDaLoc = new Vector<>();
        for (SinhVien sinhVien : dsSinhVien){
            if (sinhVien.getTen().toUpperCase().contains(duLieuLoc.toUpperCase())
                    || sinhVien.getMssv().toUpperCase().contains(duLieuLoc.toUpperCase())){
                dsSinhVienDaLoc.add(sinhVien);
            }
            if (duLieuLoc.trim().length() > 0){
                hienThiDanhSachSinhVien(dsSinhVienDaLoc);
            }
            else {
                hienThiDanhSachSinhVien();
            }
        }
    }

    /**
     * Đọc dữ liệu từ cơ sở dữ liệu và lưu vào 1 vector dsSinhVien
     */
    public void docDuLieu(){
        dsSinhVien = ServiceSV.layDanhSachSinhVien();
    }

    /**
     * Hiển thị danh sách sinh viên
     */
    public void hienThiDanhSachSinhVien(){
        docDuLieu();
        dtmTableDanhSach.setRowCount(0);
        int i = 1;
        for (SinhVien sinhVien : dsSinhVien){
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    /**
     * Hiển thị danh sách sinh viên sau khi người dùng nhập dữ liệu vào thanh tìm kiếm
     * @param dsSinhVienDaLoc
     */
    public void hienThiDanhSachSinhVien(Vector<SinhVien> dsSinhVienDaLoc){
        dtmTableDanhSach.setRowCount(0);
        int i = 1;
        for (SinhVien sinhVien : dsSinhVienDaLoc){
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    /**
     * Hiển thị danh sách khi sắp xếp GPA
     */
    public void hienThiTheoGPATang(){
        docDuLieu();
        dtmTableDanhSach.setRowCount(0);
        SinhVien sv = new SinhVien();
        for(int i = 0; i < dsSinhVien.size()-1; i++){
            for(int j = i+1; j < dsSinhVien.size(); j++){
                if(dsSinhVien.get(i).getGpa() > dsSinhVien.get(j).getGpa()){
                    sv = dsSinhVien.get(i);
                    dsSinhVien.set(i, dsSinhVien.get(j));
                    dsSinhVien.set(j, sv);
                }
            }
        }
        int i = 1;
        for (SinhVien sinhVien : dsSinhVien){
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    public void hienThiTheoGPAGiam(){
        hienThiTheoGPATang();
        dtmTableDanhSach.setRowCount(0);
        int j = 1;
        for(int i = dsSinhVien.size()-1; i != -1; i--){
            Vector<Object> vec = new Vector<>();
            vec.add(j++);
            vec.add(dsSinhVien.get(i).getTen());
            vec.add(dsSinhVien.get(i).getKhoa());
            vec.add(dsSinhVien.get(i).getMssv());
            vec.add(dsSinhVien.get(i).getLop());
            vec.add(dsSinhVien.get(i).getGpa());
            vec.add(dsSinhVien.get(i).getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }
    /**
     * Hiển thị danh sách khi sắp xếp ĐRL
     */
    public void hienThiTheoDRLTang(){
        docDuLieu();
        dtmTableDanhSach.setRowCount(0);
        SinhVien sv = new SinhVien();
        for(int i = 0; i < dsSinhVien.size()-1; i++){
            for(int j = i+1; j < dsSinhVien.size(); j++){
                if(dsSinhVien.get(i).getDrl() > dsSinhVien.get(j).getDrl()){
                    sv = dsSinhVien.get(i);
                    dsSinhVien.set(i, dsSinhVien.get(j));
                    dsSinhVien.set(j, sv);
                }
            }
        }
        int i = 1;
        for (SinhVien sinhVien : dsSinhVien){
            Vector<Object> vec = new Vector<>();
            vec.add(i++);
            vec.add(sinhVien.getTen());
            vec.add(sinhVien.getKhoa());
            vec.add(sinhVien.getMssv());
            vec.add(sinhVien.getLop());
            vec.add(sinhVien.getGpa());
            vec.add(sinhVien.getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }

    public void hienThiTheoDRLGiam(){
        hienThiTheoDRLTang();
        dtmTableDanhSach.setRowCount(0);
        int j = 1;
        for(int i = dsSinhVien.size()-1; i != -1; i--){
            Vector<Object> vec = new Vector<>();
            vec.add(j++);
            vec.add(dsSinhVien.get(i).getTen());
            vec.add(dsSinhVien.get(i).getKhoa());
            vec.add(dsSinhVien.get(i).getMssv());
            vec.add(dsSinhVien.get(i).getLop());
            vec.add(dsSinhVien.get(i).getGpa());
            vec.add(dsSinhVien.get(i).getDrl());
            dtmTableDanhSach.addRow(vec);
        }
    }
    /**
     * Hiển thị danh sách những sinh viên đủ điều kiện đạt học bổng
     */
    public void hienThiHB(){
        docDuLieu();
        comboHocBong.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboHocBong.getSelectedIndex() == 0){
                    comboGPA.setSelectedItem(null);
                    comboDRL.setSelectedItem(null);
                    int i = 1;
                    dtmTableDanhSach.setRowCount(0);
                    for(SinhVien sinhVien : dsSinhVien){
                        if(sinhVien.getGpa() >= 3.6 && sinhVien.getDrl() >= 90){
                            Vector<Object> vec = new Vector<>();
                            vec.add(i++);
                            vec.add(sinhVien.getTen());
                            vec.add(sinhVien.getKhoa());
                            vec.add(sinhVien.getMssv());
                            vec.add(sinhVien.getLop());
                            vec.add(sinhVien.getGpa());
                            vec.add(sinhVien.getDrl());
                            dtmTableDanhSach.addRow(vec);
                        }
                    }
                }else if(comboHocBong.getSelectedIndex() == 1){
                    comboGPA.setSelectedItem(null);
                    comboDRL.setSelectedItem(null);
                    int i = 1;
                    dtmTableDanhSach.setRowCount(0);
                    for(SinhVien sinhVien : dsSinhVien){
                        if(sinhVien.getGpa() >= 3.2 && sinhVien.getDrl() >= 80 && !(sinhVien.getGpa() >= 3.6 && sinhVien.getDrl() >= 90)){
                            Vector<Object> vec = new Vector<>();
                            vec.add(i++);
                            vec.add(sinhVien.getTen());
                            vec.add(sinhVien.getKhoa());
                            vec.add(sinhVien.getMssv());
                            vec.add(sinhVien.getLop());
                            vec.add(sinhVien.getGpa());
                            vec.add(sinhVien.getDrl());
                            dtmTableDanhSach.addRow(vec);
                        }
                    }
                }else{
                    comboGPA.setSelectedItem(null);
                    comboDRL.setSelectedItem(null);
                    int i = 1;
                    dtmTableDanhSach.setRowCount(0);
                    for(SinhVien sinhVien : dsSinhVien){
                        if(sinhVien.getGpa() >= 2.6 && sinhVien.getDrl() >= 65 && !(sinhVien.getGpa() >= 3.6 && sinhVien.getDrl() >= 90)
                            && !(sinhVien.getGpa() >= 3.2 && sinhVien.getDrl() >= 80)){
                            Vector<Object> vec = new Vector<>();
                            vec.add(i++);
                            vec.add(sinhVien.getTen());
                            vec.add(sinhVien.getKhoa());
                            vec.add(sinhVien.getMssv());
                            vec.add(sinhVien.getLop());
                            vec.add(sinhVien.getGpa());
                            vec.add(sinhVien.getDrl());
                            dtmTableDanhSach.addRow(vec);
                        }
                    }
                }
            }
        });
    }

    /**
     * Xuất file excel danh sách sinh viên
     */
    public void xuatExcel(){
        docDuLieu();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showSaveDialog(this);
        File saveFile = jFileChooser.getSelectedFile();
        if(saveFile != null){
            saveFile = new File(saveFile.toString() + ".xls");
        }

        WritableWorkbook excel = null;
        try {
//            excel = Workbook.createWorkbook(new File("./Test.xls"));
            excel = Workbook.createWorkbook(saveFile);
            //tạo sheet đầu tiên
            WritableSheet excelSheet = excel.createSheet("Sheet 1", 0);
//            WritableCellFormat cellFormat = new WritableCellFormat();
//            cellFormat.setFont(new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD));
//            jxl.write.Label lbDanhSachSV = new jxl.write.Label(2, 0, "DANH SÁCH SINH VIÊN", cellFormat);
//            excelSheet.addCell(lbDanhSachSV);
            //tạo header cho file
            jxl.write.Label lbSTT = new jxl.write.Label(0, 0, "STT");
            excelSheet.addCell(lbSTT);
            jxl.write.Label lbTen = new jxl.write.Label(1, 0, "Họ và tên");
            excelSheet.addCell(lbTen);
            jxl.write.Label lbKhoa = new jxl.write.Label(2, 0, "Khoá");
            excelSheet.addCell(lbKhoa);
            jxl.write.Label lbMSSV = new jxl.write.Label(3, 0, "MSSV");
            excelSheet.addCell(lbMSSV);
            jxl.write.Label lbLop = new jxl.write.Label(4, 0, "Lớp");
            excelSheet.addCell(lbLop);
            jxl.write.Label lbGPA = new jxl.write.Label(5, 0, "GPA");
            excelSheet.addCell(lbGPA);
            jxl.write.Label lbDRL = new jxl.write.Label(6, 0, "ĐRL");
            excelSheet.addCell(lbDRL);
            //gắn STT
            for(int i = 1; i <= tblDanhSach.getRowCount(); i++){
                Number num = new Number(0, i, i);
                excelSheet.addCell(num);

            }
            //ghi thông tin sinh viên ra file excel
            for(int i = 1; i <= tblDanhSach.getRowCount(); i++){
                Label lb1 = new Label(1, i, dsSinhVien.get(i-1).getTen());
                Number lb2 = new Number(2, i, dsSinhVien.get(i-1).getKhoa());
                Label lb3 = new Label(3, i, String.valueOf(dsSinhVien.get(i-1).getMssv()));
                Label lb4 = new Label(4, i, String.valueOf(dsSinhVien.get(i-1).getLop()));
                Number lb5 = new Number(5, i, dsSinhVien.get(i-1).getGpa());
                Number lb6 = new Number(6, i, dsSinhVien.get(i-1).getDrl());
                excelSheet.addCell(lb1);
                excelSheet.addCell(lb2);
                excelSheet.addCell(lb3);
                excelSheet.addCell(lb4);
                excelSheet.addCell(lb5);
                excelSheet.addCell(lb6);
            }
            //Viết vào file excel
            excel.write();
            JOptionPane.showMessageDialog(null, "Xuất file thành công!");
        } catch (IOException | WriteException e) {
            JOptionPane.showMessageDialog(null, "Xuất file không thành công!");
            e.printStackTrace();
        }finally {
            if (excel != null) {
                try {
                    excel.close();
                } catch (IOException | WriteException e) {
                    JOptionPane.showMessageDialog(null, "Xuất file không thành công!");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Hiển thị cửa sổ
     */
    public void showWindow(){
        setTitle("Danh sách sinh viên");
        setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource("/image/danhsachUI/icon.svg"))));
        setExtendedState(MAXIMIZED_BOTH);
//        setResizable(false);    //Không cho người dùng thay đổi kích thước cửa sổ
        setLocationRelativeTo(null);    //Cửa sổ xuất hiện giữa màn hình khi chạy
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new danhsachUI();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}