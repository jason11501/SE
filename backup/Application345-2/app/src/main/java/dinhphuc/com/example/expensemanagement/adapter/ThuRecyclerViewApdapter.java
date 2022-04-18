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
import dinhphuc.com.example.expensemanagement.entity.Thu;

public class ThuRecyclerViewApdapter extends  RecyclerView.Adapter<ThuRecyclerViewApdapter.ThuViewHolder> {

    private LayoutInflater mLayoutInflater ;
    private List<Thu> mList ;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public ThuRecyclerViewApdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context) ;
    }
    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener){
        ThuRecyclerViewApdapter.itemEditClickListener=itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ThuRecyclerViewApdapter.itemViewClickListener = itemViewClickListener;
    }
    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mLayoutInflater.inflate(R.layout.recyclerview_thu, parent , false) ;
        return new ThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ThuViewHolder holder, int position) {

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

    public void setList (List<Thu> mList) {
        this.mList  = mList ;
        notifyDataSetChanged();
    }

    public Object getItem(int i){
        if(mList == null)
            return null ;
        return mList.get(i) ;
    }


    public static  class  ThuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName , tvAmount ;
        public ImageView ivEdit,  ivView  ;
        public TextView tvDate;
        public CardView cv ;
        public int position;
        public ThuViewHolder(@NonNull   View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameThu);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivView = itemView.findViewById(R.id.ivViewThu);
            ivEdit = itemView.findViewById(R.id.ivEditThu);
            tvDate = itemView.findViewById(R.id.tvDate);
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