package com.example.asm_nguyenconganhduy_ps25332.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_nguyenconganhduy_ps25332.adapter.phanloaiadapter;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_pl;
import com.example.asm_nguyenconganhduy_ps25332.phanloai_activity;
import com.example.asm_nguyenconganhduy_ps25332.R;
import com.example.asm_nguyenconganhduy_ps25332.adapter.phanloaiadapter;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_pl;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frag_loaithu extends Fragment {
    RecyclerView recyclerView;
    dao_pl dao_pl;
    ArrayList<phanloai> list = new ArrayList<>();
    phanloaiadapter adapter;
    FloatingActionButton floatbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_phanloai,container,false);
        floatbtn = view.findViewById(R.id.Btn_FloatFL);
        recyclerView = view.findViewById(R.id.recycle_PL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        dao_pl = new dao_pl(getContext());
        list = dao_pl.getAll("Receipt");
        adapter = new phanloaiadapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
        return view;
    }
    private void openDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thempl,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Spinner spinner = view.findViewById(R.id.spinner_status);
        EditText txtname = view.findViewById(R.id.text_type);
        Button btnadd = view.findViewById(R.id.btn_addPhanLoai);
        Button btncancel = view.findViewById(R.id.btn_canclePhanLoai);
        // tao du lieu spinner
        String[] status= {"Receipt","Payment"} ;
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,status);
        spinner.setAdapter(spnAdapter);
        // them du lieu
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao_pl.insert(new phanloai(txtname.getText().toString(),(String)spinner.getSelectedItem()))){
                    Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao_pl.getAll("Receipt"));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // cancel
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
