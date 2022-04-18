package dinhphuc.com.example.expensemanagement.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import dinhphuc.com.example.expensemanagement.entity.user;

public class changeusr_dialog {

    private Dialog mDialog;

    LayoutInflater mInflater;

    private EditText edtP,edtU;
    Button btnXacnhan,btnHuy;

    public changeusr_dialog(final Context context, user u)
    {
        mInflater= LayoutInflater.from(context);
        View view;
        view=mInflater.inflate(R.layout.changeusr_dialog,null);

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtP=mDialog.findViewById(R.id.edtP);
        edtU=mDialog.findViewById(R.id.edtU);

        btnXacnhan=mDialog.findViewById(R.id.btnXacnhan);
        btnHuy=mDialog.findViewById(R.id.btnhuy);




        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pwd,Usr;
                Pwd=edtP.getText().toString();
                Usr=edtU.getText().toString();


                if (Pwd.isEmpty()||Usr.isEmpty())
                {
                    Toast.makeText(context,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!Pwd.equals(u.getPassword())){
                        Toast.makeText(context,"Mật khẩu sai",Toast.LENGTH_SHORT).show();
                    }

                    else if (Usr.length()<10){
                        Toast.makeText(context,"Tên đăng nhập phải tối thiểu 10 kí tự",Toast.LENGTH_SHORT).show();
                    }

                    else {
                        u.setUsername(Usr);
                        AppDatabase.getINSTANCE(context).userDao().update(u);

                        Toast.makeText(context,"Tên đăng nhập thay đổi thành công",Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

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
