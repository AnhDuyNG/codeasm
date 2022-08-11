package com.example.asm_nguyenconganhduy_ps25332.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_nguyenconganhduy_ps25332.database.db_helper;

public class dao_thongke {
    public static float  tongtien_Trangthai(Context context, String trangthai){
        float tien  = 0;
        db_helper helper = new db_helper(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        String str = "select SUM(Tien) as TongTien "+
                " from Giaodich join PhanLoai "+
                " on Giaodich.Maloai = PhanLoai.Maloai"+
                " where Trangthai like '"+trangthai+"'";
        Cursor cs = database.rawQuery(str,null);
        cs.moveToFirst();
        if(cs.getCount() >0){
            tien = cs.getFloat(0);
        }
        return tien;
    }
}
