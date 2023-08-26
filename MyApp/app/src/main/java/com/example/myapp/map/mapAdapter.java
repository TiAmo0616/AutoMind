package com.example.myapp.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;

import java.util.List;

public class mapAdapter extends BaseAdapter {

    Context context;
    List<String> mDatas;
    private static final String TAG = "mysql-myapp-mapAdapter";

    public mapAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        mapAdapter.ViewHolder holder = null;
        View convertView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_map,null);
            holder = new mapAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (mapAdapter.ViewHolder) convertView.getTag();
        }
        String map = mDatas.get(i);
        holder.map.setText(map);


        return convertView;
    }

    static class ViewHolder {
        TextView map;

        public ViewHolder(View itemView) {
            map = itemView.findViewById(R.id.item_map);
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
