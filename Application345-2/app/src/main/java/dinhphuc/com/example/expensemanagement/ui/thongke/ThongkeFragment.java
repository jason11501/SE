package dinhphuc.com.example.expensemanagement.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dao.ThuDao;
import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.entity.Thu;


import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ThongkeFragment extends Fragment {
    private EditText edtThang,edtNam;
    private Button btnXacnhan;
    private PieChart pieChartthu;
    private PieChart pieChartchi;
    private List<Chi> mListChi;
    private List<LoaiChi> mListLoaiChi;
    private List<Thu> mListThu;
    private List<LoaiThu> mListLoaiThu;
    private List <Float> mListKhoanThu;
    private List <Float> mListKhoanChi;
    private List<String> mLisTTenLoaiThu;
    private List<String> mLisTTenLoaiChi;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thongke,container,false);

        pieChartthu = view.findViewById(R.id.piechart_thu);
        pieChartchi=view.findViewById(R.id.piechart_chi);
        edtThang=view.findViewById(R.id.edtThang);
        edtNam=view.findViewById(R.id.edtNam);
        btnXacnhan=view.findViewById(R.id.btnXacnhan);


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

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Vui lòng nhập tháng để nhận được thống kê",Toast.LENGTH_SHORT).show();
                String Thang = edtThang.getText().toString();
                String Nam = edtNam.getText().toString();
                if (Thang.isEmpty()||Nam.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(Thang)>12 || Integer.parseInt(Thang)<1){
                    Toast.makeText(getContext(),"Vui lòng nhập tháng hợp lệ",Toast.LENGTH_SHORT).show();
                }
                else {
//                    Thang = "/" + Thang + "/";
//                    LoadDataByMonth(Thang);
//                    setupPieChart(pieChartchi,"Chi");
//                    loadPieChartData(pieChartchi,mLisTTenLoaiChi,mListKhoanChi);
//                    setupPieChart(pieChartthu, "Thu");
//                    loadPieChartData(pieChartthu,mLisTTenLoaiThu,mListKhoanThu);
                    setData(Integer.parseInt(Thang),Integer.parseInt(Nam));
                }
            }
        });



                    return view;
    }
    private void setData(int thang, int nam){
        String Thang, Nam;
        Thang=String.valueOf(thang);
        if (Thang.length()==1)
            Thang="0"+Thang;
        Nam=String.valueOf(nam);
        Thang=Thang+"/"+Nam;
        LoadDataByMonth(Thang);
        setupPieChart(pieChartchi,"Chi");
        loadPieChartData(pieChartchi,mLisTTenLoaiChi,mListKhoanChi);
        setupPieChart(pieChartthu, "Thu");
        loadPieChartData(pieChartthu,mLisTTenLoaiThu,mListKhoanThu);
    }
    private void loadPieChartData(PieChart pieChart, List<String> listLoai, List<Float> listKhoan) {


        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i=0;i<listKhoan.size();i++)
        {
            entries.add(new PieEntry(listKhoan.get(i), listLoai.get(i)));
            Log.e(listLoai.get(i),listKhoan.get(i).toString());
        }
       /* entries.add(new PieEntry(0.2f, "Food & Dining"));
        entries.add(new PieEntry(0.15f, "Medical"));
        entries.add(new PieEntry(0.10f, "Entertainment"));
        entries.add(new PieEntry(0.25f, "Electricity and Gas"));
        entries.add(new PieEntry(0.3f, "Housing"));*/

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Loại");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    private void setupPieChart(PieChart pieChart, String name) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText(name);
        pieChart.setCenterTextSize(18);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void LoadDataByMonth(String month)
    {
        mListChi= AppDatabase.getINSTANCE(getContext()).chiDao().finAllChiByMonth(MainActivity.id,month);
        mListLoaiChi=AppDatabase.getINSTANCE(getContext()).loaiChiDao().finalAllLoaiChi(MainActivity.id);
        mListLoaiThu=AppDatabase.getINSTANCE(getContext()).loaiThuDao().finalAllLoaiThu(MainActivity.id);
        mListThu=AppDatabase.getINSTANCE(getContext()).thuDao().finAllThuByMonth(MainActivity.id,month);

        mListKhoanThu= new ArrayList<>();
        mListKhoanChi=new ArrayList<>();
        mLisTTenLoaiChi=new ArrayList<>();
        mLisTTenLoaiThu=new ArrayList<>();

        for (int i =0 ;i<mListLoaiThu.size();i++)
        {
            double sum=0.0;
            for (int j=0;j<mListThu.size();j++)
            {
                if (mListThu.get(j).ltid==mListLoaiThu.get(i).lid)
                    sum=sum+mListThu.get(j).sotien;
            }
            mListKhoanThu.add((float) sum);
            mLisTTenLoaiThu.add(mListLoaiThu.get(i).ten);

        }

        for (int i =0 ;i<mListLoaiChi.size();i++)
        {
            double sum=0.00;
            for (int j=0;j<mListChi.size();j++)
            {
                if (mListChi.get(j).ltid==mListLoaiChi.get(i).lid)
                    sum+=mListChi.get(j).sotien;
            }
            mListKhoanChi.add((float) sum);
            mLisTTenLoaiChi.add(mListLoaiChi.get(i).ten);
        }
    }
    private void LoadData()
    {
        mListChi= AppDatabase.getINSTANCE(getContext()).chiDao().finalAllChi(MainActivity.id);
        mListLoaiChi=AppDatabase.getINSTANCE(getContext()).loaiChiDao().finalAllLoaiChi(MainActivity.id);
        mListLoaiThu=AppDatabase.getINSTANCE(getContext()).loaiThuDao().finalAllLoaiThu(MainActivity.id);
        mListThu=AppDatabase.getINSTANCE(getContext()).thuDao().finalAllThu(MainActivity.id);

        mListKhoanThu= new ArrayList<>();
        mListKhoanChi=new ArrayList<>();
        mLisTTenLoaiChi=new ArrayList<>();
        mLisTTenLoaiThu=new ArrayList<>();

        for (int i =0 ;i<mListLoaiThu.size();i++)
        {
            double sum=0.0;
            for (int j=0;j<mListThu.size();j++)
            {
                if (mListThu.get(j).ltid==mListLoaiThu.get(i).lid)
                    sum=sum+mListThu.get(j).sotien;
            }
            mListKhoanThu.add((float) sum);
            mLisTTenLoaiThu.add(mListLoaiThu.get(i).ten);

        }

        for (int i =0 ;i<mListLoaiChi.size();i++)
        {
            double sum=0.00;
            for (int j=0;j<mListChi.size();j++)
            {
                if (mListChi.get(j).ltid==mListLoaiChi.get(i).lid)
                    sum+=mListChi.get(j).sotien;
            }
            mListKhoanChi.add((float) sum);
            mLisTTenLoaiChi.add(mListLoaiChi.get(i).ten);
        }

    }

}