package dinhphuc.com.example.expensemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;

public class LoaiChiSpinnerAdapter extends BaseAdapter {
    private List<LoaiChi> mList ;
    private LayoutInflater mLayoutInflater ;

    public LoaiChiSpinnerAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }

    public  void setList (List<LoaiChi> mList){
        this.mList = mList ;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if(mList == null)
            return 0 ;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        KhoanChiViewHolder holder ;
        if (view == null){
            view = mLayoutInflater.inflate(R.layout.spinner_chi, null , false);
            holder = new KhoanChiViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (KhoanChiViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(position).ten);
        return view ;
    }

    public static class  KhoanChiViewHolder{

        public TextView tvName ;

        public KhoanChiViewHolder(View view) {
            tvName = view.findViewById(R.id.textview2);
        }
    }
}
