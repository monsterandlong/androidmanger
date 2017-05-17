package com.gfd.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.activity.ClearActivity;
import com.gfd.phone.entity.CleanInfo;

import java.util.List;

/**
 * Created by 张志龙 on 2017/5/4.
 */

public class CleanAdapter extends BaseAdapter{
    private List<CleanInfo> datas;

    public CleanAdapter(List<CleanInfo> datas) {

        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHordler hordler=null;
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clean,null);
            hordler=new ViewHordler(convertView);
            convertView.setTag(hordler);
        }else {
            hordler= (ViewHordler) convertView.getTag();
        }
        final CleanInfo cleanInfo=datas.get(position);
        hordler.mTvName.setText(cleanInfo.getName());
        hordler.mCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cleanInfo.setSelect(isChecked);
                if (!isChecked){
                    ClearActivity.mCbAll.setChecked(false);
                }
                for (CleanInfo data : datas) {
                    if (!data.isSelect()){
                        return;
                    }
                }
                ClearActivity.mCbAll.setChecked(true);
            }
        });
        hordler.mCbSelect.setChecked(cleanInfo.isSelect());

        return hordler.convertView;
    }
    public class ViewHordler{
        public View convertView;
        public  CheckBox mCbSelect;
        public  TextView mTvName;

        public ViewHordler(View convertView) {
            this.convertView = convertView;
            mCbSelect = (CheckBox) convertView.findViewById(R.id.cb_item_clean);
            mTvName = (TextView) convertView.findViewById(R.id.tv_item_clean);

        }

    }

}
