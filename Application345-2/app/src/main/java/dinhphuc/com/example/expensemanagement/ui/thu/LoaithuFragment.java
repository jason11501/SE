package dinhphuc.com.example.expensemanagement.ui.thu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.ItemClickListener;
import dinhphuc.com.example.expensemanagement.adapter.LoaiThuRecyclerViewApdapter;
import dinhphuc.com.example.expensemanagement.dialog.LoaiThuDetailDialog;
import dinhphuc.com.example.expensemanagement.dialog.LoaiThuDialog;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.ui.thu.LoaithuViewModel;


public class LoaithuFragment extends Fragment {
    private RecyclerView mRv ;
    private LoaiThuRecyclerViewApdapter mAdapter;
    private LoaithuViewModel mViewModel;

    public static LoaithuFragment newInstance() {
        return new LoaithuFragment();
    }

    public LoaithuViewModel getViewModel() {

        return mViewModel ;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaithu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerViewThu) ;
        mAdapter = new LoaiThuRecyclerViewApdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final LoaithuFragment currentfragment=this;


        mAdapter.setOnItemViewClickListener(position -> {
            LoaiThu loaiThu=mAdapter.getItem(position);
            LoaiThuDetailDialog dialog=new LoaiThuDetailDialog(getActivity(),currentfragment,loaiThu);
            dialog.show();
        });
        mAdapter.setOnItemEditClickListener(position -> {
            LoaiThu loaiThu=mAdapter.getItem(position);
            LoaiThuDialog dialog=new LoaiThuDialog(getActivity(),currentfragment,loaiThu);
            dialog.show();
        });
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                LoaiThu lt = mAdapter.getItem(position);

                Toast.makeText(getActivity(),"Loại thu đã được xóa", Toast.LENGTH_SHORT).show();
                mViewModel.delete(lt);


            }
        }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaithuViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), loaiThus -> mAdapter.setList(loaiThus));
        // TODO: Use the ViewModel
    }

}