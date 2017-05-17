package com.gfd.phone.adapter;

import android.content.res.Resources;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.entity.FileInfo;

import java.util.List;

/**
 * Created by 张志龙 on 2017/4/27.
 */

public class FileShowAdapter extends BaseAdapter {

    private List<FileInfo> datas;

    public FileShowAdapter(List<FileInfo> datas) {

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
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fileshow,null);
            hordler=new ViewHordler(convertView);
            convertView.setTag(hordler);
        }else {
           hordler= (ViewHordler) convertView.getTag();
        }
       final FileInfo fileInfo=datas.get(position);
        String ioceName=fileInfo.getTybeName();
        Resources resources = parent.getContext().getResources();
        int ioceId = resources.getIdentifier(ioceName, "mipmap", parent.getContext().getPackageName());
        hordler.mIoce.setBackgroundResource(ioceId);
        hordler.mTvName.setText(fileInfo.getName().getName());
        hordler.mTvData.setText(fileInfo.getTime());
        hordler.mTvSize.setText(Formatter.formatFileSize(parent.getContext(),fileInfo.getName().length()));
        hordler.mCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fileInfo.setSelect(isChecked);
            }
        });
        hordler.mCbSelect.setChecked(fileInfo.isSelect());

        return hordler.convertView;
    }
    public class ViewHordler{
        private View convertView;
        private final CheckBox mCbSelect;
        private final TextView mTvName;
        private final ImageView mIoce;
        private final TextView mTvData;
        private final TextView mTvSize;

        public ViewHordler(View convertView) {
            this.convertView = convertView;
            mCbSelect = (CheckBox) convertView.findViewById(R.id.cb_item_fileshow);
            mTvName = (TextView) convertView.findViewById(R.id.tv_item_fileshow_name);
            mIoce = (ImageView) convertView.findViewById(R.id.img_item_fileshow_ioce);
            mTvData = (TextView) convertView.findViewById(R.id.tv_item_fileshow_date);
            mTvSize = (TextView) convertView.findViewById(R.id.tv_item_fileshow_size);
        }

    }

}
