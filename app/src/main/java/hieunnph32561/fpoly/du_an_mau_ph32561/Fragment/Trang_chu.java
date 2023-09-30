package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung.Dang_Nhap;
import hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung.MainActivity;

public class Trang_chu extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);


        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        NavigationView navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        // Xóa đoạn mã này
        Fragment defaultFragment = new QlPhieuMuon(); // Thay thế QlPhieuMuonFragment bằng Fragment mặc định bạn muốn hiển thị
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, defaultFragment)
                .commit();
        toolbar.setTitle("Quản lý phiếu mượn"); // Thay "Tên Fragment mặc định" bằng tiêu đề bạn muốn hiển thị


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.qlphieumuon) {
                    fragment = new QlPhieuMuon();
                }

                if (item.getItemId() == R.id.qlloaisach) {
                    fragment = new QlLoaiSach();
                }
                if (item.getItemId() == R.id.qlsach) {
                    fragment = new QLSach();
                }
                if (item.getItemId() == R.id.qlthanhvien) {
                    fragment = new QLThanhVien();
                }
                if (item.getItemId() == R.id.doimatkhau) {
                    fragment = new Doimatkhau();
                }
                if(item.getItemId()==R.id.doanhthu){
                    fragment=new DoanhThu();
                }
                if(item.getItemId()==R.id.topsach){
                    fragment=new Top10();
                }
                if(item.getItemId()==R.id.dangxuat){
                    Intent intent = new Intent(Trang_chu.this, Dang_Nhap.class);
                    startActivity(intent);
                    
                    finish();
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayout, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
