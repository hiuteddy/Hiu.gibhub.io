package hieunnph32561.fpoly.du_an_mau_ph32561.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Duanmau";
    private static final int DATABASE_VERSION = 1;

    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng THUTHU

        String createTABLEthuthu = "CREATE TABLE THUTHU (" +
                "MATT TEXT PRIMARY KEY, " +
                "HOTEN TEXT, " +
                "MATKHAU TEXT)";
        db.execSQL(createTABLEthuthu);

        // Tạo bảng THANHVIEN

        String createTABLEthanhvien = "CREATE TABLE THANHVIEN (" +
                "MATV INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "HOTEN TEXT NOT NULL, " +
                "NAMSINH INTEGER NOT NULL)";
        db.execSQL(createTABLEthanhvien);



        String createTABLEloaisach = "CREATE TABLE LOAISACH (" +
                "MALOAI INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "THELOAI TEXT)";
        db.execSQL(createTABLEloaisach);

        // Tạo bảng SACH
        String createTABLEsach = "CREATE TABLE SACH (" +
                "MASACH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TENSACH TEXT NOT NULL, " +
                "GIATHUE INTEGER NOT NULL," +
                "NAMXB INTEGER NOT NULL," +
                "MALOAI INTEGER REFERENCES LOAISACH(MALOAI))";
        db.execSQL(createTABLEsach);


        // Tạo bảng PhieuMuon

        String createTablePhieuMuon = "CREATE TABLE PHIEUMUON (" +
                "MAPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MATV INTEGER REFERENCES THANHVIEN(MATV), " +
                "MATT TEXT REFERENCES THUTHU(MATT), " +
                "MASACH INTEGER REFERENCES SACH(MASACH), " +
                "NGAY DATE NOT NULL, " +
                "TRASACH INTEGER NOT NULL, " +
                "TIENTHUE INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);

        //---Thêm dữ liệu vào bảng

        //Thêm bảng Thủ Thư
//        String insertThuthu = "INSERT INTO THUTHU (MATT, HOTEN, MATKHAU) VALUES " +
//                "('TT001', 'Nguyen Van A', 'password123'), " +
//                "('TT002', 'Tran Thi B', 'abc123'), " +
//                "('TT003', 'Le Van C', 'xyz987')";
//        db.execSQL(insertThuthu);
//
//        //Thêm bảng sách
//
//        String insertSach = "INSERT INTO SACH (TENSACH, GIATHUE, MALOAI) VALUES " +
//                "('Doremon', 45000, 2), " +
//                "('Snack', 8000, 1), " +
//                "('Anime', 25000, 2)";
//        db.execSQL(insertSach);
//
//        //Thêm bảng loại sách
//
//        String insertLoaiSach = "INSERT INTO LOAISACH (THELOAI) VALUES " +
//                "( 'Tình yêu'), " +
//                "( 'Tội phạm'), " +
//                "( 'Hài')";
//        db.execSQL(insertLoaiSach);
//
//        //Thêm bảng thành viên
//
//        String insertThanhVien1 = "INSERT INTO THANHVIEN (MATV, HOTEN, NAMSINH) VALUES " +
//                "(1, 'Nguyen Van X', 1990)," +
//                "(2, 'Tran Thi Y', 1985)," +
//                "(3, 'Le Van Z', 1995)";
//        db.execSQL(insertThanhVien1);
//
//        //Thêm bảng phiếu mượn
//
//        String insertPhieuMuon1 = "INSERT INTO PHIEUMUON (MATV, MATT, MASACH, NGAY, TRASACH, TIENTHUE) VALUES " +
//                "(1, 'TT001', 1, '2023-09-27', 0, 30000)," +
//                "(2, 'TT002', 2, '2023-09-28', 0, 40000)," +
//                "(3, 'TT003', 3, '2023-09-29', 0, 50000)";
//
//        db.execSQL(insertPhieuMuon1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        String dropTABLEthuthu = "DROP TABLE IF EXISTS THUTHU";
        db.execSQL(dropTABLEthuthu);

        String dropTABLEthanhvien = "DROP TABLE IF EXISTS THANHVIEN";
        db.execSQL(dropTABLEthanhvien);

        String dropTABLEls = "DROP TABLE IF EXISTS LOAISACH";
        db.execSQL(dropTABLEls);

        String dropTABLEsach = "DROP TABLE IF EXISTS SACH";
        db.execSQL(dropTABLEsach);

        String dropTABLEphieumuon = "DROP TABLE IF EXISTS PhieuMuon";
        db.execSQL(dropTABLEphieumuon);

        onCreate(db);
    }
}
