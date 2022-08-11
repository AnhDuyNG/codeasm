package com.example.asm_nguyenconganhduy_ps25332.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_helper extends SQLiteOpenHelper {
    public db_helper(Context context){
        super(context,"MyDB",null,4); // tao database cp ten la MyDB
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table PhanLoai(Maloai integer primary key autoincrement,"+
                "Tenloai text,"+"Trangthai text)";
        db.execSQL(sql);
        sql = "Create table Giaodich(Ma_GD integer primary key autoincrement,"+
                "Tieude text,"+"Ngay Date,"+"Tien float,"+"Mo_ta text,"+
                "Maloai integer references PhanLoai(Maloai))";
        db.execSQL(sql);
        // insert du lieu mau
        sql = "insert into PhanLoai values (null,'Lương','Receipt'),"+
                "(null,'Trợ cấp covid','Receipt'),"+
                "(null,'Sinh hoạt hằng ngày','Payment')";
        db.execSQL(sql);
        sql= "insert into Giaodich values (null,'Lương','2022-07-07','100000','Receipt',1),"+
                "(null,'Trợ cấp covid','2021-01-01','3000000','Receipt',2),"+
                "(null,'Trợ cấp covid 02','2021-03-03','3000000','Payment',3)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Giaodich");
        db.execSQL("Drop table if exists PhanLoai");
        onCreate(db);

    }
}
