package dinhphuc.com.example.expensemanagement.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.kehoachAdapter;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.entity.kehoach;

public class UpdateKehoachDialog {

    private Dialog mDialog;

    LayoutInflater mInflater;

    private EditText edtThang, edtNam, edtThu, edtChi;
    Button btnUpdate, btnCancle;

    public UpdateKehoachDialog(final Context context, kehoach kehoach)
    {
        mInflater= LayoutInflater.from(context);
        View view;
        view=mInflater.inflate(R.layout.dialog_update_kehoach,null);

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtChi=mDialog.findViewById(R.id.edtChi);
        edtThu=mDialog.findViewById(R.id.edtThu);
        edtThang=mDialog.findViewById(R.id.edtThang);
        edtNam=mDialog.findViewById(R.id.edtNam);

        btnUpdate=mDialog.findViewById(R.id.btnAdd);
        btnCancle=mDialog.findViewById(R.id.btnCancle);

        edtNam.setText(""+kehoach.nam);
        edtThang.setText(""+kehoach.thang);
        edtChi.setText(""+kehoach.tongchi);
        edtThu.setText(""+kehoach.tongthu);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strChi=edtChi.getText().toString().trim();
                String strThu=edtThu.getText().toString().trim();
                String strThang=edtThang.getText().toString().trim();
                String strNam=edtNam.getText().toString().trim();

                int nam,thang;
                nam=Integer.parseInt(strNam);
                thang=Integer.parseInt(strThang);

                if (TextUtils.isEmpty(strChi) || TextUtils.isEmpty(strThang) || TextUtils.isEmpty(strThu)||TextUtils.isEmpty(strNam))

                {
                    Toast.makeText(context,"Vui lòng, nhập đủ thông tin cần thiết",Toast.LENGTH_SHORT).show();
                    return;
                }
                kehoach.setThang(Integer.parseInt(strThang));
                kehoach.setNam(Integer.parseInt(strNam));
                kehoach.setTongthu(Float.parseFloat(strThu));
                kehoach.setTongchi(Float.parseFloat(strChi));

                AppDatabase.getINSTANCE(context).kehoachDao().update(kehoach);


                Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();

                //
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

    }
    public void show(){
        mDialog.show();
    }



}
