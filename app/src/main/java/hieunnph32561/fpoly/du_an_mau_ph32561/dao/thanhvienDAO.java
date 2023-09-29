package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class thanhvienDAO {

    private Dbhelper mySQLite;
    private SQLiteDatabase db;

    public thanhvienDAO(Context context) {
        mySQLite=new Dbhelper(context);
        db=mySQLite.getWritableDatabase();
    }
    public long insert(Thanhvien t){
        ContentValues values=new ContentValues();
        values.put("hoTen",t.getHoten());
        values.put("namSinh",t.getNamsinh());
        return db.insert("ThanhVien",null,values);
    }
    public int update(Thanhvien t){
        ContentValues values=new ContentValues();
        values.put("hoTen",t.getHoten());
        values.put("namSinh",t.getNamsinh());
        return db.update("ThanhVien",values,"maTV=?",new String[]{t.getMatv()+""});
    }
    public int delete(int ma){
        return db.delete("ThanhVien","maTV=?",new String[]{ma+""});
    }
    public List<Thanhvien> getDaTa(String sql, String...selectionArgs){
        List<Thanhvien> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToNext();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                String b = c.getString(1);
                String cc = c.getString(2);
                list.add(new Thanhvien(a,b,cc));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public List<Thanhvien> getAll(){
        String sql="select * from ThanhVien";
        return getDaTa(sql);
    }
    public Thanhvien getID(String id){
        String sql="select * from ThanhVien where maTV=?";
        List<Thanhvien> list=getDaTa(sql,id);
        return list.get(0);
    }
//    private Dbhelper dBhelper;
//
//    public thanhvienDAO(Context context) {
//        dBhelper = new Dbhelper(context);
//    }
//
//
//    public ArrayList<Thanhvien> getADSTV() {
//        ArrayList<Thanhvien> list = new ArrayList<>();
//        SQLiteDatabase database = dBhelper.getReadableDatabase(); // thêm this.dBhelper
//        Cursor cursor = database.rawQuery("SELECT * FROM THANHVIEN", null);
//
//        while (cursor.moveToNext()) {
//            Thanhvien thanhvien = new Thanhvien(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2));
//            list.add(thanhvien);
//        }
//        cursor.close(); // đóng con trỏ khi hoàn thành công việc
//        return list;
//    }

}
