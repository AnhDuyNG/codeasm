package com.example.asm_nguyenconganhduy_ps25332;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asm_nguyenconganhduy_ps25332.dao.dao_gd;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_pl;
import com.example.asm_nguyenconganhduy_ps25332.fragment.frag_chi;
import com.example.asm_nguyenconganhduy_ps25332.fragment.fragthongke;
import com.example.asm_nguyenconganhduy_ps25332.fragment.frag_thu;
import com.example.asm_nguyenconganhduy_ps25332.model.giaodich;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    dao_pl dao_pl;
    dao_gd dao_gd;
    Toolbar toolbar;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_baseline_flaky_24);
        // drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        // icon drawer
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        // navigation action
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menu_item) {
                Fragment fragment = null;
                switch (menu_item.getItemId()){
                    case R.id.thu_header:
                        fragment = new frag_thu();
                        break;
                    case R.id.chi_header:
                        fragment = new frag_chi();
                        break;
                    case R.id.thongke_header:
                        fragment = new fragthongke();
                        break;
                    case R.id.gioi_thieu:
                        // chua co
                        break;
                    case R.id.cai_dat:
                        // chua co
                        break;
                    case R.id.exit:
                        Toast.makeText(MainActivity.this, "Thoát", Toast.LENGTH_SHORT).show();
                        break;
                }
                if(savedInstanceState == null){
                    getSupportFragmentManager().beginTransaction().
                            add(R.id.fragment_frame,fragment).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_frame,fragment).commit();
                }



                drawer.setSelected(true);
                setTitle(menu_item.getTitle());
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        // demo dao
        dao_gd = new dao_gd(
                MainActivity.this);
        ArrayList<giaodich> lists = dao_gd.getAll();
        for(int i =0 ; i<lists.size();i++){
            Log.i("Tên",lists.get(i).getTieude());
        }


    }
}