package dinhphuc.com.example.expensemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;

public class LoaiThuRecyclerViewApdapter extends  RecyclerView.Adapter<LoaiThuRecyclerViewApdapter.LoaiThuViewHolder> {

    private LayoutInflater mLayoutInflater ;
    private List<LoaiThu> mList ;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiThuRecyclerViewApdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context) ;
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener){
        LoaiThuRecyclerViewApdapter.itemEditClickListener=itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiThuRecyclerViewApdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mLayoutInflater.inflate(R.layout.recyclerview_loai_thu, parent , false) ;
        return new LoaiThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LoaiThuViewHolder holder, int position) {

        if(mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.position=position;
            }
    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0 ;
        return mList.size() ;
    }

    public LoaiThu getItem(int position) {
        if(mList == null  || position >= mList.size())
            return null ;
        return mList.get(position);
    }


    public void setList (List<LoaiThu> mList) {
        this.mList  = mList ;
        notifyDataSetChanged();
    }

    public static  class  LoaiThuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName ;
        public ImageView ivEdit,  ivView  ;
        public CardView cv ;
        public int position;
        public LoaiThuViewHolder(@NonNull   View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView ;
            ivView.setOnClickListener(v -> {
                if (itemViewClickListener!=null)
                {
                    itemViewClickListener.onItemClick(position);
                }
            });
            ivEdit.setOnClickListener(v -> {
                if (itemEditClickListener!=null){
                    itemEditClickListener.onItemClick(position);
                }
            });
        }
    }
}