package dinhphuc.com.example.expensemanagement.ui.thu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.ChiViewPager2Adapter;
import dinhphuc.com.example.expensemanagement.adapter.ThuViewPager2Adapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link thuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class thuFragment extends Fragment {

    private ViewPager2 mVp;
    private TabLayout mTl;

    public thuFragment() {
        // Required empty public constructor
    }


    public static thuFragment newInstance(String param1, String param2) {
        thuFragment fragment = new thuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVp=view.findViewById(R.id.viewPager2);
        mTl=view.findViewById(R.id.tabLayout);

        ThuViewPager2Adapter adapter=new ThuViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                if(position==0)
                {
                    tab.setText("Khoản thu");
                    tab.setIcon(R.drawable.ic_menu_camera);
                }else {
                    tab.setText("Loại Khoản Thu");
                    tab.setIcon(R.drawable.ic_menu_camera);
                }
            }
        }).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thu, container, false);
    }
}