package dinhphuc.com.example.expensemanagement.ui.home;

import android.app.ActionBar;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.HomeViewPagerAdapter;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dao.ChiDao;
import dinhphuc.com.example.expensemanagement.dao.ThuDao;
import dinhphuc.com.example.expensemanagement.databinding.FragmentHomeBinding;
import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.Thu;
import dinhphuc.com.example.expensemanagement.ui.chi.chiFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.thuFragment;

public class HomeFragment extends Fragment {

    private ViewPager mviewpager;
    private BottomNavigationView mbottomNavigationView;

    private View mview;



    public HomeFragment()
    {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mview=inflater.inflate(R.layout.fragment_home,container,false);
        initUI();
        return mview;
    }
    private void initUI(){

        mbottomNavigationView=mview.findViewById(R.id.btNav);

        Fragment fragment=new thuFragment();
        loadFragment(fragment);
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_thu:
                        Fragment fragment=new thuFragment();
                        loadFragment(fragment);

                        break;
                    case R.id.tab_chi:
                        Fragment fragment1=new chiFragment();
                        loadFragment(fragment1);

                        break;
                }
                    return true;
            }
        });


    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}