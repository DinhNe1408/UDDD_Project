package com.example.uddd_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.ThongTinHoaDon;
import com.example.uddd_project.HoaDon;
import com.example.uddd_project.R;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.viewHolder> {

    ArrayList<HoaDon> listHD;
    Context context;

    public HoaDonAdapter(Context context,ArrayList<HoaDon> listHD) {
        this.listHD = listHD;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hoa_don,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HoaDon hoaDon = listHD.get(position);

        holder.txtV_TenNN_lsmh.setText(hoaDon.getTenNN());
        holder.txtV_SDTNN_lsmh.setText(hoaDon.getSDTNN());
        holder.txtV_TTTT_lsmh.setText(hoaDon.getTTTT());
        holder.txtV_DCNN_lsmh.setText(hoaDon.getDiaChiNN());
        holder.txtV_TGM_lsmh.setText(String.format(hoaDon.getTGM(), "hh:mm:ss a dd-MM-yyyy"));
        holder.txtV_TGGH_lsmh.setText(hoaDon.getTGGH());
        holder.txtV_TongTien_lsmh.setText(String.valueOf(hoaDon.getTongTien()));
        holder.txtV_TTHD_lsmh.setText(hoaDon.getTTHD());

        holder.layout_hoa_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThongTinHoaDon.class);
                intent.putExtra("IDHD",hoaDon.getIDHD());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listHD != null){
            return listHD.size();
        }
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView txtV_TenNN_lsmh, txtV_SDTNN_lsmh, txtV_TTTT_lsmh,txtV_DCNN_lsmh, txtV_TGM_lsmh, txtV_TGGH_lsmh, txtV_TongTien_lsmh, txtV_TTHD_lsmh;
        ConstraintLayout layout_hoa_don;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            layout_hoa_don = itemView.findViewById(R.id.layout_hoa_don);
            txtV_TenNN_lsmh = itemView.findViewById(R.id.txtV_TenNN_lsmh);
            txtV_SDTNN_lsmh = itemView.findViewById(R.id.txtV_SDTNN_lsmh);
            txtV_DCNN_lsmh = itemView.findViewById(R.id.txtV_DCNN_lsmh);
            txtV_TGM_lsmh = itemView.findViewById(R.id.txtV_TGM_lsmh);
            txtV_TongTien_lsmh = itemView.findViewById(R.id.txtV_TongTien_lsmh);
            txtV_TTHD_lsmh = itemView.findViewById(R.id.txtV_TTHD_lsmh);
        }
    }
}
