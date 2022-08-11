package com.example.asm_nguyenconganhduy_ps25332;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asm_nguyenconganhduy_ps25332.adapter.phanloaiadapter;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_pl;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.asm_nguyenconganhduy_ps25332.R;

import java.util.ArrayList;

public class phanloai_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    dao_pl dao_pl;
    ArrayList<phanloai>list = new ArrayList<>();
    phanloaiadapter adapter;
    FloatingActionButton floatbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanloai);
        floatbtn = findViewById(R.id.Btn_FloatFL);
        recyclerView = findViewById(R.id.recycle_PL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(phanloai_activity.this);
        recyclerView.setLayoutManager(layoutManager);
        dao_pl = new dao_pl(phanloai_activity.this);
        list = dao_pl.getAll();
        adapter = new phanloaiadapter(phanloai_activity.this,list);
        recyclerView.setAdapter(adapter);
        // floatting listener
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
    }

    private void openDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(phanloai_activity.this);
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
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(phanloai_activity.this,android.R.layout.simple_list_item_1,status);
        spinner.setAdapter(spnAdapter);
        // them du lieu
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao_pl.insert(new phanloai(txtname.getText().toString(),(String)spinner.getSelectedItem()))){
                    Toast.makeText(phanloai_activity.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao_pl.getAll());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(phanloai_activity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
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