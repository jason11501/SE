package dinhphuc.com.example.expensemanagement.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.ui.thu.LoaithuFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.LoaithuViewModel;

public class LoaiThuDetailDialog {
    private LoaithuViewModel mViewModel ;
    private LayoutInflater mLayoutInflater;
    private Dialog mDialog ;
    private Button btnCanle;

    private TextView tvId , tvName ;

    public LoaiThuDetailDialog(Context context , LoaithuFragment fragment, LoaiThu loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_thu,  null) ;

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tvId = mDialog.findViewById(R.id.tvLtName);
        tvName = mDialog.findViewById(R.id.tvName);
        btnCanle=mDialog.findViewById(R.id.btnCancle);

        tvId.setText(""+loaiThu.lid);
        tvName.setText(loaiThu.ten);
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create() ;*/
        btnCanle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }
    public void show() {
        mDialog.show();
    }
}
