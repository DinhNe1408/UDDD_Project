package com.example.uddd_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.ArrayList;

public class QLSPAdapter extends RecyclerView.Adapter<QLSPAdapter.viewHolder> implements Filterable {
    Context context;
    public ArrayList<SanPhamDomain> listSP, listSPold;
    public static int position;

    public QLSPAdapter(Context context, ArrayList<SanPhamDomain> listSP) {
        this.context = context;
        this.listSP = listSP;
        this.listSPold = listSP;
    }

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qlsp,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SanPhamDomain sanPham = listSP.get(position);

        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getHinhSP(),0,sanPham.getHinhSP().length);
        holder.imgV_HinhSP_qlsp.setImageBitmap(bitmap);
        holder.txtV_TenSP_qlsp.setText(sanPham.getTenSP());
        holder.itemView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(holder.getPosition());
                holder.itemView .performLongClick();
                //Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listSP != null){
            return listSP.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search_key = charSequence.toString();
                if(search_key.isEmpty()){
                    listSP = listSPold;
                } else {
                    ArrayList<SanPhamDomain> list = new ArrayList<>();
                    for(SanPhamDomain sanPham:listSPold){
                        if(sanPham.getTenSP().toLowerCase().contains(search_key.toLowerCase())){
                            list.add(sanPham);
                        }
                    }
                    listSP = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listSP;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listSP = (ArrayList<SanPhamDomain>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView txtV_TenSP_qlsp;
        ImageView imgV_HinhSP_qlsp;
        CardView layout_qlsp;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imgV_HinhSP_qlsp = itemView.findViewById(R.id.imgV_HinhSP_qlsp);
            txtV_TenSP_qlsp = itemView.findViewById(R.id.txtV_TenSP_qlsp);
            layout_qlsp = itemView.findViewById(R.id.layout_qlsp);

            itemView.setOnCreateContextMenuListener(this);


            layout_qlsp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layout_qlsp.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(contextMenu.NONE, R.id.iSua, contextMenu.NONE,"Sửa sản phẩm");
            contextMenu.add(contextMenu.NONE, R.id.iXoa, contextMenu.NONE,"Xóa sản phẩm");
        }
    }
}
