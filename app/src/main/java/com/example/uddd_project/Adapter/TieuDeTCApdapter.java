package com.example.uddd_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.TimSanPham;
import com.example.uddd_project.R;
import com.example.uddd_project.TieuDeTCDomain;

import java.util.ArrayList;

public class TieuDeTCApdapter extends RecyclerView.Adapter<TieuDeTCApdapter.tieuDeTCholder> {

    private ArrayList<TieuDeTCDomain> listDS;
    private Context context;

    public TieuDeTCApdapter(Context context, ArrayList<TieuDeTCDomain> listDS) {
        this.listDS = listDS;
        this.context = context;
    }

    @NonNull
    @Override
    public tieuDeTCholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tieu_de_tc, null);

        return new tieuDeTCholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tieuDeTCholder holder, int position) {
        final String tieuDe = listDS.get(position).getTieuDe();
        ArrayList ds = listDS.get(position).getsPDomainAL();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(context, ds);

        holder.txtV_TieuDe_tc.setText(tieuDe);
        holder.recV_DSSP_tc.setLayoutManager(gridLayoutManager);
        holder.recV_DSSP_tc.setAdapter(sanPhamAdapter);

        holder.txtV_XemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tieuDe.equals("Rau củ tươi")){
                    Intent intent = new Intent(context, TimSanPham.class);
                    intent.putExtra("idDM",2);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, "Chức năng này từ từ nha",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listDS != null) {
            return listDS.size();
        }
        return 0;
    }

    public class tieuDeTCholder extends RecyclerView.ViewHolder{

        private TextView txtV_TieuDe_tc, txtV_XemThem;
        private RecyclerView recV_DSSP_tc;
        public tieuDeTCholder(@NonNull View itemView) {
            super(itemView);

            txtV_TieuDe_tc = itemView.findViewById(R.id.txtV_TieuDe_tc);
            recV_DSSP_tc = itemView.findViewById(R.id.recV_DSSP_tc);
            txtV_XemThem = itemView.findViewById(R.id.txtV_XemThem);
        }
    }
}
