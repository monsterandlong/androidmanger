package com.gfd.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.entity.TelInfo;

import java.util.List;


public class TelDetailAdapter extends BaseAdapter {

    private List<TelInfo> datas;

    public TelDetailAdapter(List<TelInfo> datas) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teldetail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TelInfo telInfo = datas.get(position);
        holder.tvName.setText(telInfo.getName());
        holder.tvNumber.setText(telInfo.getTelNum());
        return holder.convertView;
    }

    public class ViewHolder {

        public View convertView;
        public TextView tvName;
        public TextView tvNumber;

        public ViewHolder(View convertView) {

            this.convertView = convertView;
            tvName = (TextView) convertView.findViewById(R.id.tv_teldetail_item_name);
            tvNumber = (TextView) convertView.findViewById(R.id.tv_teldetail_item);
        }
    }
}
