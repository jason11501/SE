package dinhphuc.com.example.expensemanagement.ui.kehoach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.kehoachAdapter;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dialog.UpdateKehoachDialog;
import dinhphuc.com.example.expensemanagement.entity.kehoach;


public class KehoachFragment extends Fragment {
    private EditText edtThu, edtChi, edtThang,edtNam;
    private Button btnAdd;
    private RecyclerView rcv_kehoach;

    private kehoachAdapter adapter;
    private List<kehoach> mListkehoach;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_kehoach, container, false);
        edtChi=view.findViewById(R.id.edtChi);
        edtThang=view.findViewById(R.id.edtThang);
        edtNam=view.findViewById(R.id.edtNam);
        edtThu=view.findViewById(R.id.edtThu);
        btnAdd=view.findViewById(R.id.btnAdd);
        rcv_kehoach=view.findViewById(R.id.rcv_Kehoach);

        adapter=new kehoachAdapter(new kehoachAdapter.ClickItemKehoach() {
            @Override
            public void updateKehoach(kehoach kehoach) {
                clickUpdateKehoach(kehoach);
            }
        });

        LoadData();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());

        rcv_kehoach.setLayoutManager(linearLayoutManager);

        rcv_kehoach.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKehoach();
            }
        });



        return view;
    }

    private void initUi(View view)
    {
        edtChi=view.findViewById(R.id.edtChi);
        edtThang=view.findViewById(R.id.edtThang);
        edtNam=view.findViewById(R.id.edtNam);
        edtThu=view.findViewById(R.id.edtThu);
        btnAdd=view.findViewById(R.id.btnAdd);
    }

    private void addKehoach()
    {

        String strChi=edtChi.getText().toString().trim();
        String strThu=edtThu.getText().toString().trim();
        String strThang=edtThang.getText().toString().trim();
        String strNam=edtNam.getText().toString().trim();

        int nam,thang;
        nam=Integer.parseInt(strNam);
        thang=Integer.parseInt(strThang);

        if (isExists(thang,nam))
        {
            Toast.makeText(getContext(),"Thông tin đã tồn tại",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(strChi) || TextUtils.isEmpty(strThang) || TextUtils.isEmpty(strThu)||TextUtils.isEmpty(strNam))

        {
            Toast.makeText(getContext(),"Vui lòng, nhập đủ thông tin cần thiết",Toast.LENGTH_SHORT).show();
            return;
        }

        if (thang<1||thang>12){
            Toast.makeText(getContext(),"Tháng bạn nhập không hợp lệ.",Toast.LENGTH_SHORT).show();
        }
        else{
        kehoach kehoach= new kehoach(Integer.parseInt(strThang),Integer.parseInt(strNam),Float.parseFloat(strThu),Float.parseFloat(strChi));


        AppDatabase.getINSTANCE(getContext()).kehoachDao().insert(kehoach);

        Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();

        edtThang.setText("");
        edtNam.setText("");
        edtThu.setText("");
        edtChi.setText("");
        LoadData();}
    }

    private void LoadData()
    {
        mListkehoach=AppDatabase.getINSTANCE(getContext()).kehoachDao().getAll(MainActivity.id);
        adapter.setData(mListkehoach);
    }

    private boolean isExists(int thang, int nam)
    {
        List<kehoach> list=AppDatabase.getINSTANCE(getContext()).kehoachDao().CheckExists(thang,nam,MainActivity.id);
        if (list.size()== 0)
            return false;
        return true;
    }

    private void clickUpdateKehoach(kehoach kehoach)
    {
        UpdateKehoachDialog updateKehoachDialog = new UpdateKehoachDialog(getContext(),kehoach);
        updateKehoachDialog.show();
        LoadData();
    }

}