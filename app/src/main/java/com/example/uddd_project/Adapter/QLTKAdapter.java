package com.example.uddd_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.QL.UpTaiKhoan;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.ArrayList;

public class QLTKAdapter extends RecyclerView.Adapter<QLTKAdapter.viewHolder> implements Filterable {

    Context context;
    public ArrayList<TaiKhoanDomain> listTK,listTKold;
    public static int position;

    public QLTKAdapter(Context context, ArrayList<TaiKhoanDomain> listTK) {
        this.context = context;
        this.listTK = listTK;
        this.listTKold = listTK;
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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qltk,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TaiKhoanDomain taiKhoan = listTK.get(position);
        holder.txtV_IDTK_qltk.setText(String.valueOf(taiKhoan.getIDTK()));
        holder.txtV_SDT_qltk.setText(taiKhoan.getSDT());
        holder.txtV_HoTen_qltk.setText(taiKhoan.getHoTen());
        holder.txtV_MauKhau_qltk.setText(taiKhoan.getMatKhau());
        holder.txtV_DiaChi_qltk.setText(taiKhoan.getDiaChi());
        holder.txtV_Quyen_qltk.setText(taiKhoan.getQuyen());
        holder.itemView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(holder.getPosition());
                holder.itemView .performLongClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listTK != null){
            return listTK.size();
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
                    listTK = listTKold;
                } else {
                    ArrayList<TaiKhoanDomain> list = new ArrayList<>();
                    for(TaiKhoanDomain taiKhoan:listTKold){
                        if(taiKhoan.getSDT().toLowerCase().contains(search_key.toLowerCase())){
                            list.add(taiKhoan);
                        }
                    }
                    listTK = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listTK;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listTK = (ArrayList<TaiKhoanDomain>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView txtV_IDTK_qltk, txtV_SDT_qltk, txtV_HoTen_qltk, txtV_MauKhau_qltk, txtV_DiaChi_qltk, txtV_Quyen_qltk ;
        CardView layout_qltk;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txtV_IDTK_qltk = itemView.findViewById(R.id.txtV_IDTK_qltk);
            txtV_SDT_qltk = itemView.findViewById(R.id.txtV_SDT_qltk);
            txtV_HoTen_qltk = itemView.findViewById(R.id.txtV_HoTen_qltk);
            txtV_MauKhau_qltk = itemView.findViewById(R.id.txtV_MauKhau_qltk);
            txtV_DiaChi_qltk = itemView.findViewById(R.id.txtV_DiaChi_qltk);
            txtV_Quyen_qltk = itemView.findViewById(R.id.txtV_Quyen_qltk);

            layout_qltk = itemView.findViewById(R.id.layout_qltk);
            itemView.setOnCreateContextMenuListener(this);

            layout_qltk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layout_qltk.showContextMenu();
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(contextMenu.NONE, R.id.iSua, contextMenu.NONE,"Sửa tài khoản");
            contextMenu.add(contextMenu.NONE, R.id.iXoa, contextMenu.NONE,"Xóa tài khoản");
        }
    }
}
