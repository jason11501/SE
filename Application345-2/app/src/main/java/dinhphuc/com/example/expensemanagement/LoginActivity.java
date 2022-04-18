package dinhphuc.com.example.expensemanagement;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.entity.user;

public class LoginActivity extends AppCompatActivity {
    //public static int id;
    EditText edtuser, edtpassword;
    Button btndangnhap,btndangki,btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Bạn có chắc muốn thoát không");
                builder.setMessage("Hãy đưa ra lựa chọn");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten, mk;
                ten= edtuser.getText().toString();
                mk=edtpassword.getText().toString();
                if(ten.length()==0||mk.length()==0)
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin!!!", Toast.LENGTH_SHORT).show();
                else
                {

                    List<user> users= AppDatabase.getINSTANCE(LoginActivity.this).userDao().getUserByUsernameAndPassword(ten,mk);

                    if (users.size()>0)
                    {
                        int userid=users.get(0).getId();
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("UserID",userid);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setTitle("Đăng kí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dangki);

                EditText edtdkuser=dialog.findViewById(R.id.edtdkuser);
                EditText edtdkpassword=dialog.findViewById(R.id.edtdkpassword);
                Button btndkhuy=dialog.findViewById(R.id.btndkhuy);
                Button btndkxacnhan=dialog.findViewById(R.id.btndkxacnhan);


                btndkxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mk,ten;
                        ten=edtdkuser.getText().toString();
                        mk=edtdkpassword.getText().toString();

                        List<user> users = AppDatabase.getINSTANCE(LoginActivity.this).userDao().getUserByUsername(ten);

                        if (users.size()>0)
                        {
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        user user= new user(ten,mk);
                        AppDatabase.getINSTANCE(LoginActivity.this).userDao().insert(user);

                        edtuser.setText(ten);
                        edtpassword.setText(mk);

                        mk=edtdkpassword.getText().toString();
                        Toast.makeText(LoginActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                });
                btndkhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }
    private void Anhxa()
    {
        edtuser=findViewById(R.id.edtuser);
        edtpassword=findViewById(R.id.edtpassword);
        btndangnhap=findViewById(R.id.btndangnhap);
        btndangki=findViewById(R.id.btndangki);
        btnthoat=findViewById(R.id.btnthoat);
    }
}