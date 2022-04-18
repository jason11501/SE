package dinhphuc.com.example.expensemanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.ListFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import dinhphuc.com.example.expensemanagement.ui.chi.chiFragment;
import dinhphuc.com.example.expensemanagement.ui.home.InformationFragment;
import dinhphuc.com.example.expensemanagement.ui.home.SettingFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.thuFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    public HomeViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new thuFragment();
            case 1:
                return new chiFragment();

            default:return new thuFragment();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
