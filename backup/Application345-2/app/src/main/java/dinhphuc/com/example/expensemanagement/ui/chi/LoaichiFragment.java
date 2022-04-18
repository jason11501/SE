package dinhphuc.com.example.expensemanagement.ui.chi;

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
import dinhphuc.com.example.expensemanagement.adapter.LoaiChiRecyclerViewApdapter;
import dinhphuc.com.example.expensemanagement.dialog.LoaiChiDetailDialog;
import dinhphuc.com.example.expensemanagement.dialog.LoaiChiDialog;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.ui.chi.LoaichiFragment;

public class  LoaichiFragment extends Fragment {
    private RecyclerView mRv ;
    private LoaichiViewModel mViewModel;
    private LoaiChiRecyclerViewApdapter mAdapter ;

    public static LoaichiFragment newInstance() {
        return new LoaichiFragment();
    }

    public LoaichiViewModel getViewModel() {
        return mViewModel ;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaichi_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView) ;
        mAdapter = new LoaiChiRecyclerViewApdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final LoaichiFragment currentfragment=this;


        mAdapter.setOnItemViewClickListener(position -> {
            LoaiChi loaiChi=mAdapter.getItem(position);
            LoaiChiDetailDialog dialog=new LoaiChiDetailDialog(getActivity(),currentfragment,loaiChi);
            dialog.show();
        });
        mAdapter.setOnItemEditClickListener(position -> {
            LoaiChi loaiChi=mAdapter.getItem(position);
            LoaiChiDialog dialog=new LoaiChiDialog(getActivity(),currentfragment,loaiChi);
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
                LoaiChi lt = mAdapter.getItem(position);

                Toast.makeText(getActivity(),"Loại Chi đã được xóa", Toast.LENGTH_SHORT).show();
                mViewModel.delete(lt);


            }
        }
        );
        helper.attachToRecyclerView(mRv);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaichiViewModel.class);
        mViewModel.getmAllLoaiChi().observe(getActivity(), loaiChis -> mAdapter.setList(loaiChis));
        // TODO: Use the ViewModel
    }

}