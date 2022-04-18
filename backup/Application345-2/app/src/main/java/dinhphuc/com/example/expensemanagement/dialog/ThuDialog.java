package dinhphuc.com.example.expensemanagement.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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

import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.adapter.LoaiThuRecyclerViewApdapter;
import dinhphuc.com.example.expensemanagement.adapter.LoaiThuSpinnerAdapter;
import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.entity.Thu;
import dinhphuc.com.example.expensemanagement.ui.thu.KhoanthuFragment;
import dinhphuc.com.example.expensemanagement.ui.thu.KhoanthuViewModel;
import dinhphuc.com.example.expensemanagement.ui.thu.LoaithuViewModel;

public class ThuDialog {
    private KhoanthuViewModel mViewModel ;
    private LayoutInflater mLayoutInflater;
    private Dialog mDialog ;

    private TextInputEditText  etName, etAmount, etNote, edtDate ;
    private int etId;
    private Button btnSave, btnCancle;
    private Spinner spType ;
    private boolean mEitMode ;
    private LoaiThuSpinnerAdapter mAdapter ;

    public ThuDialog(final Context context , KhoanthuFragment fragment, Thu ... thu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_thu,  null) ;

        mDialog = new Dialog(context);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);

        Window window=mDialog.getWindow();
        if (window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


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

        mAdapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
                    @Override
                    public void onChanged(List<LoaiThu> loaiThus) {
                        mAdapter.setList(loaiThus);
                    }
                });
        spType.setAdapter(mAdapter);
        if (thu != null && thu.length>0){

            etName.setText(thu[0].ten);
            etId=thu[0].tid;
            etAmount.setText(""+thu[0].sotien);
            etNote.setText(""+thu[0].ghichu);
            edtDate.setText(""+thu[0].ngay);
            mEitMode=true;

        }

        else mEitMode=false;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tien,ten,ngay;

                ten=etName.getText().toString();
                ngay=edtDate.getText().toString();
                tien=etAmount.getText().toString();

                if (tien.isEmpty()|| ten.isEmpty()||ngay.isEmpty())
                {
                    Toast.makeText(mDialog.getContext(), "Vui lòng nhập đủ thông tin cần thiết",Toast.LENGTH_SHORT).show();
                    return;
                }
                Float sotien=Float.parseFloat(tien);
                Thu kt = new Thu();
                kt.ten = ten;
                kt.sotien = sotien;
                kt.ghichu = etNote.getText().toString();

                kt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                kt.ngay=ngay;
                if (mEitMode) {
                    kt.tid=etId;
                    mViewModel.update(kt);
                    Toast.makeText(context, "Khoản thu được cập nhật thành công", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else {
                    mViewModel.insert(kt);
                    Toast.makeText(context, "Khoản thu được lưu", Toast.LENGTH_SHORT).show();
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

        /*AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Thu kt = new Thu();
                        kt.ten = etName.getText().toString();
                        kt.sotien = Float.parseFloat(etAmount.getText().toString());
                        kt.ghichu = etNote.getText().toString();
                        kt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                        kt.ngay=edtDate.getText().toString();
                        if (mEitMode) {
                            kt.tid = Integer.parseInt(etId.getText().toString());
                            mViewModel.update(kt);
                        } else {
                            mViewModel.insert(kt);
                            Toast.makeText(context, "Khoan thu được lưu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create() ;*/
    }
    public void show() {
        mDialog.show();
    }
}
