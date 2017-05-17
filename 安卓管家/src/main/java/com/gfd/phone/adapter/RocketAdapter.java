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
import com.gfd.phone.activity.RocketActivity;
import com.gfd.phone.entity.RunInfo;

import java.util.List;

/**
 * Created by 张志龙 on 2017/4/21.
 */

public class RocketAdapter extends BaseAdapter {

    private List<RunInfo> datas;

    public RocketAdapter(List<RunInfo> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //适配器进行的优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rocket_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RunInfo runInfo=datas.get(position);
        holder.ioce.setImageDrawable(runInfo.getIcon());
        holder.select.setChecked(runInfo.isselect());
        holder.tvName.setText(runInfo.getAppname());
        holder.tvsize.setText(runInfo.getUsemomory());
        holder.packname.setText(runInfo.getPackname());
        //这个是全选按钮的设置
        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              runInfo.setIsselect(isChecked);
                if (!isChecked){
                    RocketActivity.mCbAllSelect.setChecked(false);
                    return;
                }
                for (RunInfo data : datas) {
                   if (!data.isselect())
                    return;
                }
                RocketActivity.mCbAllSelect.setChecked(true);
            }
        });
        holder.select.setChecked(runInfo.isselect());
        return holder.convertView;
    }

    public class ViewHolder {

        public ImageView ioce;
        public View convertView;
        public TextView tvName;
        public TextView tvsize;
        public   CheckBox select;
        public TextView packname;

        public ViewHolder(View convertView) {

            this.convertView = convertView;
            select = (CheckBox) convertView.findViewById(R.id.cb_item_rocket_select);
            ioce = (ImageView) convertView.findViewById(R.id.img_item_rocket_ioce);
            tvName = (TextView) convertView.findViewById(R.id.tv_item__rocket_name);
            tvsize = (TextView) convertView.findViewById(R.id.tv_tv_item__rocket_size);
            packname = (TextView) convertView.findViewById(R.id.tv_tv_item__rocket_packname);
        }
    }
}
