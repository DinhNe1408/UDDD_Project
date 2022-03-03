package com.example.uddd_project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.uddd_project.CTHoaDon;
import com.example.uddd_project.HoaDon;
import com.example.uddd_project.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    ArrayList<HoaDon> listHD;
    Map<HoaDon,ArrayList<CTHoaDon>> listCTHD;

    public ExpandableAdapter(ArrayList<HoaDon> listHD, Map<HoaDon, ArrayList<CTHoaDon>> listCTHD) {
        this.listHD = listHD;
        this.listCTHD = listCTHD;
    }

    @Override
    public int getGroupCount() {
        if(listHD != null){
            return listHD.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        if(listHD != null && listCTHD != null){
            return listCTHD.get(listHD.get(i)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return listHD.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listCTHD.get(listHD.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        HoaDon hoaDon = listHD.get(i);
        return hoaDon.getIDHD();
    }

    @Override
    public long getChildId(int i, int i1) {
        CTHoaDon CTHoaDon = listCTHD.get(listHD.get(i)).get(i1);
        return CTHoaDon.getIDCTHD();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view ==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_hoa_don, viewGroup,false);
        }
        HoaDon hoaDon = listHD.get(i);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");

        TextView txtV_TenNN_lsmh = view.findViewById(R.id.txtV_TenNN_lsmh);
        TextView txtV_SDTNN_lsmh = view.findViewById(R.id.txtV_SDTNN_lsmh);
        TextView txtV_DCNN_lsmh = view.findViewById(R.id.txtV_DCNN_lsmh);
        TextView txtV_TGM_lsmh = view.findViewById(R.id.txtV_TGM_lsmh);
        TextView txtV_TongTien_lsmh = view.findViewById(R.id.txtV_TongTien_lsmh);
        TextView txtV_TTHD_lsmh = view.findViewById(R.id.txtV_TTHD_lsmh);

        txtV_TenNN_lsmh.setText(hoaDon.getTenNN());
        txtV_SDTNN_lsmh.setText(hoaDon.getSDTNN());
        txtV_DCNN_lsmh.setText(hoaDon.getDiaChiNN());
        txtV_TGM_lsmh.setText(hoaDon.getTGM());
        txtV_TongTien_lsmh.setText(decimalFormat.format(hoaDon.getTongTien()));
        if(hoaDon.getTTHD().equals("CGH")){
            txtV_TTHD_lsmh.setText("Chưa giao hàng");
        } else {
            txtV_TTHD_lsmh.setText("Đã giao hàng");
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view ==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_ct_hoa_don, viewGroup,false);
        }
        CTHoaDon CTHoaDon = listCTHD.get(listHD.get(i)).get(i1);

        TextView txtV_Gia_lsmh = view.findViewById(R.id.txtV_Gia_lsmh);
        TextView txtV_TenSP_lsmh = view.findViewById(R.id.txtV_TenSP_lsmh);
        TextView txtV_SL_lsmh = view.findViewById(R.id.txtV_SL_lsmh);
        TextView txtV_TongTien = view.findViewById(R.id.txtV_TongTien);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");
        txtV_TenSP_lsmh.setText(CTHoaDon.getTenSP());
        txtV_SL_lsmh.setText(String.valueOf(CTHoaDon.getSLMSP()));
        txtV_TongTien.setText(decimalFormat.format(CTHoaDon.getGiaSP() * CTHoaDon.getSLMSP()));
        txtV_Gia_lsmh.setText(decimalFormat.format(CTHoaDon.getGiaSP()));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
