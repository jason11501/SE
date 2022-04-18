package dinhphuc.com.example.expensemanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import dinhphuc.com.example.expensemanagement.ui.thu.KhoanthuFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.LoaithuFragment;

public class ThuViewPager2Adapter extends FragmentStateAdapter {
    public ThuViewPager2Adapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0)
        {
            fragment= KhoanthuFragment.newInstance();
        }
        else{
            fragment= LoaithuFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
