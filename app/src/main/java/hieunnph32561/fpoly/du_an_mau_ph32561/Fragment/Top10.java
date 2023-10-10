package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_top10;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thongkeDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Top;

public class Top10 extends Fragment {
    private List<Top> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private adapter_top10 adapterTop10;
    private thongkeDAO thongkeDAO;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_top10, container, false);
        thongkeDAO=new thongkeDAO(getContext());
        list=thongkeDAO.getTop();
        recyclerView=view.findViewById(R.id.rcltop10);
        adapterTop10=new adapter_top10(getContext(), (ArrayList<Top>) list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterTop10);
        return view;
    }
}
