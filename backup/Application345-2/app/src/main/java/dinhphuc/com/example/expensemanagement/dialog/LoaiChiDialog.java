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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.ui.chi.LoaichiFragment;
import dinhphuc.com.example.expensemanagement.ui.chi.LoaichiViewModel;

public class LoaiChiDialog {
    private LoaichiViewModel mViewModel ;
    private LayoutInflater mLayoutInflater;
    private Dialog mDialog ;
    private Button btnCancle,btnSave;

    private TextInputEditText  etName ;
    private int etId ;
    private boolean mEitMode ;

    public LoaiChiDialog(Context context , LoaichiFragment fragment, LoaiChi... loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_chi,  null) ;

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        etName = mDialog.findViewById(R.id.etName);
        btnCancle=mDialog.findViewById(R.id.btnCancle);
        btnSave=mDialog.findViewById(R.id.btnSave);

        if (loaiChi!=null && loaiChi.length>0){

            etName.setText(loaiChi[0].ten);
            etId=loaiChi[0].lid;
            mEitMode=true;
        }
        else mEitMode=false;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi lc = new LoaiChi();
                lc.ten = etName.getText().toString();
                if (mEitMode){
                    lc.lid=etId;
                    mViewModel.update(lc);
                    Toast.makeText(context, "Loại chi được cập nhật thành công", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }else {
                    mViewModel.insert(lc);
                    Toast.makeText(context, "Loại chi được lưu", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
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
