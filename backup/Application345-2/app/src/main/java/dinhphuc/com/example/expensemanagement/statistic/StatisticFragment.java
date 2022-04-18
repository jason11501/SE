package dinhphuc.com.example.expensemanagement.statistic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
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

public class StatisticFragment extends Fragment {

    private TextView tvAmountthu;
    private TextView tvAmountchi;
    private TextView tvsodu;
    private EditText edtThang,edtNam;
    private Button btnXacnhan;

    private View mview;

    public StatisticFragment()
    {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mview=inflater.inflate(R.layout.fragment_statistic,container,false);
        //MainActivity.fab.hide();
        //((MainActivity)getActivity()).hideFab();

        tvAmountchi=mview.findViewById(R.id.tvAmountchi);
        tvAmountthu=mview.findViewById(R.id.tvAmountthu);
        tvsodu=mview.findViewById(R.id.tvSodu);

        edtThang=mview.findViewById(R.id.edtThang);
        edtNam=mview.findViewById(R.id.edtNam);
        btnXacnhan=mview.findViewById(R.id.btnXacnhan);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String date=simpleDateFormat.format(calendar.getTime());
        String[] data=date.split("/");
        int thang=Integer.parseInt(data[1]);
        int nam=Integer.parseInt(data[2]);

        if(thang<10)
            edtThang.setText("0"+thang+"");
        else edtThang.setText(thang+"");
        edtNam.setText(nam+"");
        setData(thang,nam);

//        ThuDao thuDao =AppDatabase.getINSTANCE(getContext()).thuDao();
//        List<Float> TienThu=thuDao.getTien(MainActivity.id);
//
//        double TongThu =0.0;
//
//        for (int i=0;i<TienThu.size();i++)
//        {
//            TongThu+= TienThu.get(i);
//        }
//        tvAmountthu.setText(""+TongThu);
//
//        ChiDao chiDao=AppDatabase.getINSTANCE(getContext()).chiDao();
//        List<Float> TienChi=chiDao.getTien(MainActivity.id);
//
//        double TongChi=0.0;
//        for (int i=0;i<TienChi.size();i++)
//        {
//            TongChi+= TienChi.get(i);
//        }
//        tvAmountchi.setText(""+TongChi);
//
//        tvsodu.setText(""+(TongThu-TongChi));

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Vui lòng nhập tháng để nhận được thống kê",Toast.LENGTH_SHORT).show();
                String Thang = edtThang.getText().toString();
                String Nam = edtNam.getText().toString();
                if (Thang.isEmpty()||Nam.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập tháng để nhận được thống kê", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(Thang)>12 || Integer.parseInt(Thang)<1){
                    Toast.makeText(getContext(),"Vui lòng nhập tháng hợp lệ",Toast.LENGTH_SHORT).show();
                }
                else {
//                    Thang = "/" + Thang + "/";
//                    List<Chi> chis=AppDatabase.getINSTANCE(getContext()).chiDao().finAllChiByMonth(MainActivity.id,Thang);
//                    List<Thu> thus=AppDatabase.getINSTANCE(getContext()).thuDao().finAllThuByMonth(MainActivity.id,Thang);
//
//                    double TongChi=0.0;
//                    for (int i =0; i<chis.size();i++){
//                        TongChi+=chis.get(i).sotien;
//                    }
//
//                    double TongThu=0.0;
//                    for (int i =0; i<thus.size();i++){
//                        TongThu+=thus.get(i).sotien;
//                    }
//                    tvAmountthu.setText(""+TongThu);
//
//
//                    tvAmountchi.setText(""+TongChi);
//
//                    tvsodu.setText(""+(TongThu-TongChi));
                    setData(Integer.parseInt(Thang),Integer.parseInt(Nam));

                }
            }
        });

        return mview;
    }
    private void setData(int thang, int nam)
    {
        String Thang, Nam;
        Thang=String.valueOf(thang);
        if (Thang.length()==1)
            Thang="0"+Thang;
        Nam=String.valueOf(nam);
        Thang=Thang+"/"+Nam;
        List<Chi> chis=AppDatabase.getINSTANCE(getContext()).chiDao().finAllChiByMonth(MainActivity.id,Thang);
        List<Thu> thus=AppDatabase.getINSTANCE(getContext()).thuDao().finAllThuByMonth(MainActivity.id,Thang);

        double TongChi=0.0;
        for (int i =0; i<chis.size();i++){
            TongChi+=chis.get(i).sotien;
        }

        double TongThu=0.0;
        for (int i =0; i<thus.size();i++){
            TongThu+=thus.get(i).sotien;
        }
        tvAmountthu.setText(""+TongThu);


        tvAmountchi.setText(""+TongChi);

        tvsodu.setText(""+(TongThu-TongChi));
    }

}