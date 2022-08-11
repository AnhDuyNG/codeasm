package com.example.asm_nguyenconganhduy_ps25332.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.asm_nguyenconganhduy_ps25332.R;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_thongke;

public class fragthongke extends Fragment {
    TextView txtTongtien,txtTongchi,txtTotal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke,container,false);
        txtTongtien = view.findViewById(R.id.tongthu);
        txtTongchi = view.findViewById(R.id.tongchi);
        float tongtien = dao_thongke.tongtien_Trangthai(getContext(),"Receipt");
        txtTongtien.setText("Tổng thu: "+tongtien);
        return view;
    }
}
