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

import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.entity.kehoach;
import dinhphuc.com.example.expensemanagement.entity.user;

public class changepwd_dialog {

    private Dialog mDialog;

    LayoutInflater mInflater;

    private EditText edtOP,edtNP,edtNP1;
    Button btnXacnhan,btnHuy;

    public changepwd_dialog(final Context context, user u)
    {
        mInflater= LayoutInflater.from(context);
        View view;
        view=mInflater.inflate(R.layout.changepwd_dialog,null);

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtOP=mDialog.findViewById(R.id.edtOldPwd);
        edtNP=mDialog.findViewById(R.id.edtNewPwd);
        edtNP1=mDialog.findViewById(R.id.edtNewPwd1);


        btnXacnhan=mDialog.findViewById(R.id.btnXacnhan);
        btnHuy=mDialog.findViewById(R.id.btnhuy);




        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPwd,newPwd,newPwd1;
                oldPwd=edtOP.getText().toString();
                newPwd=edtNP.getText().toString();
                newPwd1=edtNP1.getText().toString();

                if (oldPwd.isEmpty()||newPwd.isEmpty()||newPwd1.isEmpty())
                {
                    Toast.makeText(context,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();

                }
                else {
                    if(!oldPwd.equals(u.getPassword())){
                        Toast.makeText(context,"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                    }
                    else if (!newPwd.equals(newPwd1)){
                        Toast.makeText(context,"Mật khẩu mới không khớp",Toast.LENGTH_SHORT).show();
                    }
                    else if (newPwd.length()<8)
                    {
                        Toast.makeText(context,"Mật khẩu phải tối thiểu 8 kí tự",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        u.setPassword(newPwd);
                        AppDatabase.getINSTANCE(context).userDao().update(u);
                        List<user> u1=AppDatabase.getINSTANCE(context).userDao().getUserById(MainActivity.id);
                        Toast.makeText(context,"Mật khẩu thay đổi thành công",Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }
//                String strChi=edtChi.getText().toString().trim();
//                String strThu=edtThu.getText().toString().trim();
//                String strThang=edtThang.getText().toString().trim();
//                String strNam=edtNam.getText().toString().trim();
//
//                int nam,thang;
//                nam=Integer.parseInt(strNam);
//                thang=Integer.parseInt(strThang);
//
//                if (TextUtils.isEmpty(strChi) || TextUtils.isEmpty(strThang) || TextUtils.isEmpty(strThu)||TextUtils.isEmpty(strNam))
//
//                {
//                    Toast.makeText(context,"Vui lòng, nhập đủ thông tin cần thiết",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                kehoach.setThang(Integer.parseInt(strThang));
//                kehoach.setNam(Integer.parseInt(strNam));
//                kehoach.setTongthu(Float.parseFloat(strThu));
//                kehoach.setTongchi(Float.parseFloat(strChi));
//
//                AppDatabase.getINSTANCE(context).kehoachDao().update(kehoach);
//
//
//                Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();

                //
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
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
