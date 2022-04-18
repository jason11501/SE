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

import dinhphuc.com.example.expensemanagement.BuildConfig;
import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiFragment;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiViewModel;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiFragment;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiViewModel;

public class KhoanChiDetailDialog {
    private KhoanchiViewModel mViewModel ;
    private LayoutInflater mLayoutInflater;
    private Dialog mDialog ;
    private Button btnCancle;

    private TextView tvTid , tvName, tvAmount, tvNote;

    public KhoanChiDetailDialog(Context context , KhoanchiFragment fragment, Chi Chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_khoan_chi,  null) ;

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tvTid = mDialog.findViewById(R.id.tvTid);
        tvName = mDialog.findViewById(R.id.tvName);
        tvAmount=mDialog.findViewById(R.id.tvAmount);
        tvNote=mDialog.findViewById(R.id.tvNote);
        btnCancle=mDialog.findViewById(R.id.btnCancle);
        tvTid.setText(""+Chi.tid);
        tvName.setText(Chi.ten);
        tvAmount.setText(""+Chi.sotien+"đồng");
        tvNote.setText(Chi.ghichu);
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create() ;*/
        btnCancle.setOnClickListener(new View.OnClickListener() {
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
