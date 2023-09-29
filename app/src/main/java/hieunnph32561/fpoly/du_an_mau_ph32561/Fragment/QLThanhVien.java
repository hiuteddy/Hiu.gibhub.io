package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;

public class QLThanhVien extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.framgment_ql_thanhvien,container,false);
    }
}
