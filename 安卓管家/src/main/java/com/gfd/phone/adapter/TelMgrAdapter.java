package com.gfd.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.entity.Tel;

import java.util.List;


public class TelMgrAdapter extends BaseAdapter {

    private List<Tel> datas;
    private int[] rootBgs = {R.drawable.shape_telmgr_itembg_red, R.drawable.shape_telmgr_itembg_yellow,
            R.drawable.shape_telmgr_itembg_green};

    public TelMgrAdapter(List<Tel> datas) {
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
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_telmgr_tel, null);
        RelativeLayout relayBg = (RelativeLayout) itemView.findViewById(R.id.relay_telmgr_root);
        TextView tvName = (TextView) itemView.findViewById(R.id.tv_telmgr_item);
        Tel tel = datas.get(position);
        tvName.setText(tel.getName());
        relayBg.setBackgroundResource(rootBgs[position % rootBgs.length]);
        return itemView;
    }
}
