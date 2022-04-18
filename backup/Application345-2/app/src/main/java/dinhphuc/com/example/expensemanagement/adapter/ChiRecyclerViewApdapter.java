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
import dinhphuc.com.example.expensemanagement.entity.Chi;

public class ChiRecyclerViewApdapter extends  RecyclerView.Adapter<ChiRecyclerViewApdapter.ChiViewHolder> {

    private LayoutInflater mLayoutInflater ;
    private List<Chi> mList ;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public ChiRecyclerViewApdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context) ;
    }
    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener){
        ChiRecyclerViewApdapter.itemEditClickListener=itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ChiRecyclerViewApdapter.itemViewClickListener = itemViewClickListener;
    }
    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mLayoutInflater.inflate(R.layout.recyclerview_chi, parent , false) ;
        return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChiViewHolder holder, int position) {

        if(mList != null) {
            holder.tvDate.setText(mList.get(position).ngay);
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmount.setText(""+ mList.get(position).sotien + "đồng") ;
            holder.position=position;
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0 ;
        return mList.size() ;
    }

    public void setList (List<Chi> mList) {
        this.mList  = mList ;
        notifyDataSetChanged();
    }

    public Object getItem(int i){
        if(mList == null)
            return null ;
        return mList.get(i) ;
    }


    public static  class  ChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName , tvAmount, tvDate ;
        public ImageView ivEdit,  ivView  ;
        public CardView cv ;
        public int position;
        public ChiViewHolder(@NonNull   View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameThu);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDate=itemView.findViewById(R.id.tvDate);
            ivView = itemView.findViewById(R.id.ivViewThu);
            ivEdit = itemView.findViewById(R.id.ivEditThu);
            cv = (CardView) itemView ;

            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemViewClickListener!=null)
                    {
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemEditClickListener!=null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }


    }
}