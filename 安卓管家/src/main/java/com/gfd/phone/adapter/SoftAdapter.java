package com.gfd.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.activity.SoftcleanActivity;
import com.gfd.phone.entity.RunInfo;

import java.util.List;

/**
 * Created by 张志龙 on 2017/4/25.
 */

public class SoftAdapter extends BaseAdapter {

    private List<RunInfo> datas;

    public SoftAdapter(List<RunInfo> datas) {
        this.datas = datas;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.softadapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RunInfo runInfo=datas.get(position);
        holder.ioce.setImageDrawable(runInfo.getIcon());
        holder.select.setChecked(runInfo.isselect());
        holder.tvName.setText(runInfo.getAppname());
        holder.tvversion.setText(runInfo.getVersionName());
        holder.packname.setText(runInfo.getPackname());
        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                runInfo.setIsselect(isChecked);
                if (!isChecked){
                    SoftcleanActivity.mCbAllselect.setChecked(false);
                    return;
                }
                for (RunInfo data : datas) {
                    if (!data.isselect())
                        return;
                }
                SoftcleanActivity.mCbAllselect.setChecked(true);
            }
        });
        holder.select.setChecked(runInfo.isselect());
        return holder.convertView;
    }

    public class ViewHolder {

        public ImageView ioce;
        public View convertView;
        public TextView tvName;
        public TextView tvversion;
        public CheckBox select;
        public TextView packname;

        public ViewHolder(View convertView) {

            this.convertView = convertView;
            select = (CheckBox) convertView.findViewById(R.id.cb_item_soft_select);
            ioce = (ImageView) convertView.findViewById(R.id.img_item_soft_ioce);
            tvName = (TextView) convertView.findViewById(R.id.tv_item__soft_name);
            tvversion = (TextView) convertView.findViewById(R.id.tv_tv_item__soft_version);
            packname = (TextView) convertView.findViewById(R.id.tv_tv_item__soft_packname);
        }
    }
    public  void refresh(List<RunInfo> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
}
