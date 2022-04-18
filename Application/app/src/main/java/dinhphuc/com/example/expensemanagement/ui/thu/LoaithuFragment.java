package dinhphuc.com.example.expensemanagement.ui.thu;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dinhphuc.com.example.expensemanagement.R;

public class LoaithuFragment extends Fragment {

    private LoaithuViewModel mViewModel;

    public static LoaithuFragment newInstance() {
        return new LoaithuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaithu_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaithuViewModel.class);
        // TODO: Use the ViewModel
    }

}