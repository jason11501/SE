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
import dinhphuc.com.example.expensemanagement.adapter.ItemClickListener;
import dinhphuc.com.example.expensemanagement.adapter.ChiRecyclerViewApdapter;
import dinhphuc.com.example.expensemanagement.dialog.KhoanChiDetailDialog;
import dinhphuc.com.example.expensemanagement.dialog.ChiDialog;
import dinhphuc.com.example.expensemanagement.entity.Chi;

public class KhoanchiFragment extends Fragment {

    private KhoanchiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecyclerViewApdapter mAdapter;

    public static KhoanchiFragment newInstance() {
        return new KhoanchiFragment();
    }

    public KhoanchiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoanchi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView_kt);
        mAdapter = new ChiRecyclerViewApdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanchiFragment currentfragment=this;

        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi Chi= (Chi) mAdapter.getItem(position);
                KhoanChiDetailDialog dialog=new KhoanChiDetailDialog(getActivity(),currentfragment,Chi);
                dialog.show();
            }
        });
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi Chi = (Chi) mAdapter.getItem(position);
                ChiDialog dialog=new ChiDialog(getActivity(),currentfragment,Chi);
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
                Chi lt = (Chi) mAdapter.getItem(position);

                Toast.makeText(getActivity(),"Lo???i Chi ???? ???????c x??a", Toast.LENGTH_SHORT).show();
                mViewModel.delete(lt);


            }
        }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanchiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> Chis) {
                mAdapter.setList(Chis);
            }

        });

    }
}