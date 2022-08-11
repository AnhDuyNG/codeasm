package com.example.asm_nguyenconganhduy_ps25332.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asm_nguyenconganhduy_ps25332.database.db_helper;
import com.example.asm_nguyenconganhduy_ps25332.model.giaodich;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class dao_gd {
    private db_helper sqlite;
    SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
    public dao_gd(Context context) {
        sqlite = new db_helper(context);
    }
    public ArrayList<giaodich>getAll(){
        try {
            ArrayList<giaodich> list =new ArrayList<>();
            SQLiteDatabase database = sqlite.getReadableDatabase();
            String sql = "Select * from Giaodich";
            Cursor cursor = database.rawQuery(sql,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new giaodich(cursor.getInt(0),cursor.getString(1),
                        dfm.parse(cursor.getString(2)),cursor.getFloat(3),cursor.getString(4),cursor.getInt(5)));
                cursor.moveToNext();
            }
            database.close();
            cursor.close();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }
    public ArrayList<giaodich>getAll(String trangthai){

        try {
            ArrayList<giaodich> list =new ArrayList<>();
            SQLiteDatabase database = sqlite.getReadableDatabase();
            String sql = "Select * from Giaodich Join PhanLoai On Giaodich.MaLoai=PhanLoai.MaLoai"
                    +" Where Trangthai like'"+trangthai+"'";
            Cursor cursor = database.rawQuery(sql,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new giaodich(cursor.getInt(0),cursor.getString(1),
                        dfm.parse(cursor.getString(2)),cursor.getFloat(3),cursor.getString(4),cursor.getInt(5)));
                cursor.moveToNext();
            }
            database.close();
            cursor.close();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public boolean insert(giaodich giaodich){
        try{
            SQLiteDatabase database =sqlite.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put("Tieude",giaodich.getTieude());
            contentValues.put("Ngay",dfm.format(giaodich.getNgay()));
            contentValues.put("Tien",giaodich.getTien());
            contentValues.put("Mo_ta",giaodich.getMo_Ta());
            contentValues.put("MaLoai",giaodich.getMaLoai());
            long values = database.insert("Giaodich",null,contentValues);

            return (values>0);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    };
    public boolean update(giaodich giaodich){
        try{
            SQLiteDatabase database =sqlite.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put("Tieude",giaodich.getTieude());
            contentValues.put("Ngay",dfm.format(giaodich.getNgay()));
            contentValues.put("Tien",giaodich.getTien());
            contentValues.put("Mo_ta",giaodich.getMo_Ta());
            contentValues.put("MaLoai",giaodich.getMaLoai());
            long values = database.update("Giaodich",contentValues,"Ma_GD=?",
                    new String[]{String.valueOf(giaodich.getMaGD())});

            return (values>0);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    };
    public boolean delete(int maGD){
        try {
            SQLiteDatabase database =sqlite.getWritableDatabase();
            database.delete("Giaodich","MaLoai=?",new String[]{String.valueOf(maGD)});
            return true;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    };
}

