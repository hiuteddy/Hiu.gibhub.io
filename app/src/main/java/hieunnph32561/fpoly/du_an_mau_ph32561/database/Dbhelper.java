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
        // Create THUTHU table
        String createTableThuthu = "CREATE TABLE IF NOT EXISTS THUTHU (" +
                "maTT TEXT PRIMARY KEY, " +
                "hoTen TEXT, " +
                "matKhau TEXT)";
        db.execSQL(createTableThuthu);

        // Create THANHVIEN table
        String createTableThanhvien = "CREATE TABLE IF NOT EXISTS THANHVIEN (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "namSinh INTEGER NOT NULL)";
        db.execSQL(createTableThanhvien);

        // Create LOAISACH table
        String createTableLoaisach = "CREATE TABLE IF NOT EXISTS LOAISACH (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "theLoai TEXT)";
        db.execSQL(createTableLoaisach);

        // Create SACH table
        String createTableSach = "CREATE TABLE IF NOT EXISTS SACH (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSach TEXT NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "maLoai INTEGER REFERENCES LOAISACH(maLoai), " +
                "namXB INTEGER NOT NULL)";
        db.execSQL(createTableSach);

        // Create PHIEUMUON table
        String createTablePhieuMuon = "CREATE TABLE IF NOT EXISTS PHIEUMUON (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maTV INTEGER REFERENCES THANHVIEN(maTV), " +
                "maTT TEXT REFERENCES THUTHU(maTT), " +
                "maSach INTEGER REFERENCES SACH(maSach), " +
                "ngay DATE NOT NULL, " +
                "traSach INTEGER NOT NULL, " +
                "tienThue INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);

        // Insert initial data

        // Insert data into THUTHU table
        String insertThuthu = "INSERT INTO THUTHU (maTT, hoTen, matKhau) VALUES " +
                "('TT001', 'Nguyen Van A', 'password123'), " +
                "('TT002', 'Tran Thi B', 'abc123'), " +
                "('TT003', 'Le Van C', 'xyz987')";
        db.execSQL(insertThuthu);

        // Insert data into SACH table
        String insertSach = "INSERT INTO SACH (tenSach, giaThue, maLoai, namXB) VALUES " +
                "('Doremon', 45000, 2, 2000), " +
                "('Snack', 8000, 1, 2015), " +
                "('Anime', 25000, 2, 2017)";
        db.execSQL(insertSach);

        // Insert data into LOAISACH table
        String insertLoaiSach = "INSERT INTO LOAISACH (theLoai) VALUES " +
                "('Tình yêu'), " +
                "('Tội phạm'), " +
                "('Hài')";
        db.execSQL(insertLoaiSach);

        // Insert data into THANHVIEN table
        String insertThanhVien = "INSERT INTO THANHVIEN (hoTen, namSinh) VALUES " +
                "('Nguyen Van X', 1990), " +
                "('Tran Thi Y', 1985), " +
                "('Le Van Z', 1995)";
        db.execSQL(insertThanhVien);

        // Insert data into PHIEUMUON table
        String insertPhieuMuon = "INSERT INTO PHIEUMUON (maTV, maTT, maSach, ngay, traSach, tienThue) VALUES " +
                "(1, 'TT001', 1, '2023-09-27', 0, 30000), " +
                "(2, 'TT002', 2, '2023-09-28', 0, 40000), " +
                "(3, 'TT003', 3, '2023-09-29', 0, 50000)";
        db.execSQL(insertPhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old tables if they exist
        db.execSQL("DROP TABLE IF EXISTS THUTHU");
        db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
        db.execSQL("DROP TABLE IF EXISTS LOAISACH");
        db.execSQL("DROP TABLE IF EXISTS SACH");
        db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");

        // Create new tables
        onCreate(db);
    }
}