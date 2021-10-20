package com.example.uddd_project.Fragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uddd_project.R;

public class DangKyTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        return inflater.inflate(R.layout.tab_dang_ky, container, false);
    }
}
