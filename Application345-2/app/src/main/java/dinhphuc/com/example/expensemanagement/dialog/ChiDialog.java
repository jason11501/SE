package dinhphuc.com.example.expensemanagement.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.LoaiChiRecyclerViewApdapter;
import dinhphuc.com.example.expensemanagement.adapter.LoaiChiSpinnerAdapter;
import dinhphuc.com.example.expensemanagement.adapter.LoaiChiSpinnerAdapter;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiFragment;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiViewModel;
import dinhphuc.com.example.expensemanagement.ui.chi.KhoanchiFragment;
import dinhphuc.com.example.expensemanagement.ui.chi.LoaichiViewModel;

public class ChiDialog {
    private KhoanchiViewModel mViewModel ;
    private LayoutInflater mLayoutInflater;
    private Dialog mDialog ;

    private TextInputEditText etId , etName, etAmount, etNote, edtDate ;
    private Button btnSave, btnCancle;
    private Spinner spType ;
    private boolean mEitMode ;
    private LoaiChiSpinnerAdapter mAdapter ;

    public ChiDialog(final Context context , KhoanchiFragment fragment, Chi ... Chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi,  null) ;

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        etId = mDialog.findViewById(R.id.etIdThu);
        etName = mDialog.findViewById(R.id.etNameThu);
        etAmount = mDialog.findViewById(R.id.etAmount);
        etNote = mDialog.findViewById(R.id.etNote);
        spType = mDialog.findViewById(R.id.spType);
        edtDate=mDialog.findViewById(R.id.edtDate);
        btnCancle=mDialog.findViewById(R.id.btnCancle);
        btnSave=mDialog.findViewById(R.id.btnSave);
        edtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar= Calendar.getInstance();
                int ngay= calendar.get(Calendar.DATE);
                int thang=calendar.get(Calendar.MONTH);
                int nam=calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mDialog.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        edtDate.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });

        mAdapter = new LoaiChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
        spType.setAdapter( mAdapter);
        if (Chi != null && Chi.length>0){
            etId.setText(""+Chi[0].tid);
            etName.setText(Chi[0].ten);
            etAmount.setText(""+Chi[0].sotien);
            etNote.setText(""+Chi[0].ghichu);
            mEitMode=true;
        }

        else mEitMode=false;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chi kt = new Chi();
                kt.ten = etName.getText().toString();
                kt.sotien = Float.parseFloat(etAmount.getText().toString());
                kt.ghichu = etNote.getText().toString();
                kt.ltid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                kt.ngay= edtDate.getText().toString();
                if (mEitMode) {
                    kt.tid = Integer.parseInt(etId.getText().toString());
                    mViewModel.update(kt);
                } else {
                    mViewModel.insert(kt);
                    Toast.makeText(context, "Khoan Chi được lưu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        /*AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view);
                /*.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Chi kt = new Chi();
                        kt.ten = etName.getText().toString();
                        kt.sotien = Float.parseFloat(etAmount.getText().toString());
                        kt.ghichu = etNote.getText().toString();
                        kt.ltid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                        kt.ngay= edtDate.getText().toString();
                        if (mEitMode) {
                            kt.tid = Integer.parseInt(etId.getText().toString());
                            mViewModel.update(kt);
                        } else {
                            mViewModel.insert(kt);
                            Toast.makeText(context, "Khoan Chi được lưu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
        //mDialog = builder.create() ;

    }
    public void show() {
        mDialog.show();
    }
}
