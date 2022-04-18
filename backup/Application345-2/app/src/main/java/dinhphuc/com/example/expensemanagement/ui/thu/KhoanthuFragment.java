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
import dinhphuc.com.example.expensemanagement.adapter.ThuRecyclerViewApdapter;
import dinhphuc.com.example.expensemanagement.dialog.KhoanThuDetailDialog;
import dinhphuc.com.example.expensemanagement.dialog.ThuDialog;
import dinhphuc.com.example.expensemanagement.entity.Thu;

public class KhoanthuFragment extends Fragment {

    private KhoanthuViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRecyclerViewApdapter mAdapter;

    public static KhoanthuFragment newInstance() {
        return new KhoanthuFragment();
    }

    public KhoanthuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoanthu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView_kt);
        mAdapter = new ThuRecyclerViewApdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanthuFragment currentfragment=this;

        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu= (Thu) mAdapter.getItem(position);
                KhoanThuDetailDialog dialog=new KhoanThuDetailDialog(getActivity(),currentfragment,thu);
                dialog.show();
            }
        });
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = (Thu) mAdapter.getItem(position);
                ThuDialog dialog=new ThuDialog(getActivity(),currentfragment,thu);
                dialog.show();
            }
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
                Thu lt = (Thu) mAdapter.getItem(position);

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
        mViewModel = new ViewModelProvider(this).get(KhoanthuViewModel.class);
        mViewModel.getmAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }

        });

    }
}