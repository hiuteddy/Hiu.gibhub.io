package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung.Dang_Nhap;
import hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung.Doimatkhau;

public class Trang_chu extends AppCompatActivity {
    DrawerLayout drawerLayout;

    View headerLayout;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);


        Toolbar toolbar = findViewById(R.id.toolbar);
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


        headerLayout = navigationView.getHeaderView(0);
        textView = headerLayout.findViewById(R.id.textViewEmail);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        textView.setText("" + user + "!");

        if (user != null && user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
        }


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
                if (item.getItemId() == R.id.doanhthu) {
                    fragment = new DoanhThu();
                }
                if (item.getItemId() == R.id.topsach) {
                    fragment = new Top10();
                }
                if (item.getItemId() == R.id.dangxuat) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Trang_chu.this);
                    builder.setMessage("Do you want to exit ?")
                            .setTitle("Message")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Trang_chu.this, Dang_Nhap.class);
                                    startActivity(intent);

                                    finish();
                                }
                            });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }
                if (item.getItemId() == R.id.sub_AddUser) {
                    fragment = new ThemND();
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
