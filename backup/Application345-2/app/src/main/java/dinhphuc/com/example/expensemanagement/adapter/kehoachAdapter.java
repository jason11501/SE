package dinhphuc.com.example.expensemanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import dinhphuc.com.example.expensemanagement.R;
import dinhphuc.com.example.expensemanagement.entity.kehoach;

public class kehoachAdapter extends RecyclerView.Adapter<kehoachAdapter.kehoachHolder>{
    private List<kehoach> mListKehoach;

    private ClickItemKehoach clickItemKehoach;

    public interface ClickItemKehoach{
        void updateKehoach(kehoach kehoach);
    }

    public kehoachAdapter(ClickItemKehoach clickItemKehoach) {
        this.clickItemKehoach = clickItemKehoach;
    }

    public void setData(List<kehoach> mList){
        this.mListKehoach=mList;
        notifyDataSetChanged();
    }
    @NonNull
    @NotNull
    @Override
    public kehoachHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kehoach,parent,false);
        return new kehoachHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull kehoachHolder holder, int position) {
        final kehoach kehoach= mListKehoach.get(position);
        if (kehoach==null)
            return;
        holder.tvThang.setText(""+kehoach.thang);
        holder.tvNam.setText(""+kehoach.nam);
        holder.tvThu.setText("Tổng thu dự kiến: "+kehoach.tongthu);
        holder.tvChi.setText("Tổng chi dự kiến: "+kehoach.tongchi);
        holder.tvSodu.setText("Số dư dự kiến: "+(kehoach.tongthu-kehoach.tongchi));

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemKehoach.updateKehoach(kehoach);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListKehoach != null)
            return mListKehoach.size();
        return 0;
    }

    public class kehoachHolder extends RecyclerView.ViewHolder{
        private TextView tvThang,tvNam,tvChi,tvThu,tvSodu;
        private Button btnUpdate;


        public kehoachHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvThang=itemView.findViewById(R.id.tvThang);
            tvNam=itemView.findViewById(R.id.tvNam);
            tvThu=itemView.findViewById(R.id.tvThu);
            tvChi=itemView.findViewById(R.id.tvChi);
            tvSodu=itemView.findViewById(R.id.tvSodu);
            btnUpdate=itemView.findViewById(R.id.btnUpdate);

        }
    }

}
