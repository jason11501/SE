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
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;

public class LoaiChiRecyclerViewApdapter extends  RecyclerView.Adapter<LoaiChiRecyclerViewApdapter.LoaiChiViewHolder> {

    private LayoutInflater mLayoutInflater ;
    private List<LoaiChi> mList ;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiChiRecyclerViewApdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context) ;
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener){
        LoaiChiRecyclerViewApdapter.itemEditClickListener=itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiChiRecyclerViewApdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mLayoutInflater.inflate(R.layout.recyclerview_loai_chi, parent , false) ;
        return new LoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LoaiChiViewHolder holder, int position) {

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

    public LoaiChi getItem(int position) {
        if(mList == null  || position >= mList.size())
            return null ;
        return mList.get(position);
    }


    public void setList (List<LoaiChi> mList) {
        this.mList  = mList ;
        notifyDataSetChanged();
    }

    public static  class  LoaiChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName ;
        public ImageView ivEdit,  ivView  ;
        public CardView cv ;
        public int position;
        public LoaiChiViewHolder(@NonNull   View itemView) {

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