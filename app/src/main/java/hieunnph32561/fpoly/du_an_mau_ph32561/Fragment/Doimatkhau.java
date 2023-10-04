package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung.Dang_Nhap;

public class Doimatkhau extends Fragment {
    private EditText oldPasswordEditText, newPasswordEditText, confirmPasswordEditText;
    private Button changePasswordButton;
    private SQLiteDatabase sqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_doi_matkhau, container, false);

        oldPasswordEditText = view.findViewById(R.id.edit_current_password);
        newPasswordEditText = view.findViewById(R.id.edit_new_password);
        confirmPasswordEditText = view.findViewById(R.id.edit_confirm_password);
        changePasswordButton = view.findViewById(R.id.btnsave);

        // Khởi tạo database hoặc mở kết nối đến database đã tạo
        Dbhelper dbhelper = new Dbhelper(getActivity());
        sqLiteDatabase = dbhelper.getWritableDatabase();

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = oldPasswordEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "Mật khẩu mới và xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Kiểm tra mật khẩu cũ có đúng không
                    if (checkOldPassword(oldPassword)) {
                        // Mật khẩu cũ đúng, thay đổi mật khẩu mới
                        changePassword(newPassword);
                    } else {
                        Toast.makeText(getActivity(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private boolean checkOldPassword(String oldPassword) {
        // Kiểm tra mật khẩu cũ trong cơ sở dữ liệu (điều này cần phải được điều chỉnh dựa trên cách bạn lưu mật khẩu)
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT MATKHAU FROM THUTHU WHERE MATT = ?", new String[]{oldPassword});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String storedPassword = cursor.getString(cursor.getColumnIndex("MATKHAU"));
            cursor.close();
            return oldPassword.equals(storedPassword);
        }
        cursor.close();
        return false;
    }

    private void changePassword(String newPassword) {
        // Cập nhật mật khẩu mới vào cơ sở dữ liệu (điều này cần phải được điều chỉnh dựa trên cách bạn lưu mật khẩu)
        ContentValues values = new ContentValues();
        values.put("MATKHAU", newPassword);
        int rowsUpdated = sqLiteDatabase.update("THUTHU", values, "MATT = ?", new String[]{oldPasswordEditText.getText().toString()});
        if (rowsUpdated > 0) {
            // Đổi mật khẩu thành công
            Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            oldPasswordEditText.setText("");
            newPasswordEditText.setText("");
            confirmPasswordEditText.setText("");
            Intent intent = new Intent(getActivity(), Dang_Nhap.class);
            startActivity(intent);
        } else {
            // Mật khẩu cũ không khớp
            Toast.makeText(getActivity(), "Mật khẩu cũ không khớp", Toast.LENGTH_SHORT).show();
        }
    }
}
