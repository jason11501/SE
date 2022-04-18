package dinhphuc.com.example.expensemanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.databinding.ActivityMainBinding;
import dinhphuc.com.example.expensemanagement.dialog.ChiDialog;
import dinhphuc.com.example.expensemanagement.dialog.LoaiChiDialog;
import dinhphuc.com.example.expensemanagement.dialog.ThuDialog;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiFragment;
import dinhphuc.com.example.expensemanagement.ui.chi.LoaichiFragment;
import dinhphuc.com.example.expensemanagement.dialog.LoaiThuDialog;
import dinhphuc.com.example.expensemanagement.ui.home.HomeFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.KhoanthuFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.LoaithuFragment;

public class MainActivity extends AppCompatActivity {

    public static int id;
    public FloatingActionButton fab;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bunble=getIntent().getExtras();

        id = bunble.getInt("UserID");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        final MainActivity currentContext=this;
        fab=findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments=getSupportFragmentManager().getFragments();
                Fragment fragment=fragments.get(fragments.size()-1);
                int i=2;
                while (!fragment.isVisible())
                {
                    fragment=fragments.get(fragments.size()-i);
                    i++;
                }
                Log.e("Posittion:", String.valueOf(fragments.size()));

                if (fragment instanceof LoaichiFragment){
                    LoaiChiDialog dialog = new LoaiChiDialog(currentContext,(LoaichiFragment) fragment);
                    dialog.show();

                }
                else if (fragment instanceof LoaithuFragment){
                    LoaiThuDialog dialog = new LoaiThuDialog(currentContext,(LoaithuFragment) fragment);
                    dialog.show();

                }
                else if (fragment instanceof KhoanthuFragment){
                    ThuDialog dialog =  new ThuDialog(currentContext, (KhoanthuFragment) fragment);
                    dialog.show();

                }
                else {
                    ChiDialog dialog =  new ChiDialog(currentContext, (KhoanchiFragment) fragment);
                    dialog.show();

                }


            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_thoat,R.id.nav_kehoach,R.id.nav_thongke,
                R.id.nav_gioithieu,R.id.nav_stat)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {
                if(destination.getId()==R.id.nav_thoat)
                {

                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();


                }
                else if (destination.getId()==R.id.nav_chi||destination.getId()==R.id.nav_thu||destination.getId()==R.id.nav_home)
                {
                    fab.show();
                }
                else fab.hide();
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        if (id==R.id.action_settings){

            Intent intent=new Intent(this,preferences.class);
            startActivity(intent);
        }
        if (id==R.id.action_add)
        {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




}