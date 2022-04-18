package dinhphuc.com.example.expensemanagement.ui.chi;

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


public class chiFragment extends Fragment {


    private ViewPager2 mVp;
    private TabLayout mTl;

    public chiFragment() {
        // Required empty public constructor
    }
    public static chiFragment newInstance (String param1, String param2){
        chiFragment fragment=new chiFragment();
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

        ChiViewPager2Adapter adapter=new ChiViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                if(position==0)
                {
                    tab.setText("Khoản chi");
                    tab.setIcon(R.drawable.ic_menu_camera);
                }else {
                    tab.setText("Loại Khoản Chi");
                    tab.setIcon(R.drawable.ic_menu_camera);
                }
            }
        }).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }
}