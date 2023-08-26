package com.example.myapp.datacount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class StatusAdapter extends BaseAdapter {

    Context context;
    List<String> mDatas;
    private static final String TAG = "mysql-myapp-StatusAdapter";

    public StatusAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        StatusAdapter.ViewHolder holder = null;
        View convertView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_yunjistatus,null);
            holder = new StatusAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (StatusAdapter.ViewHolder) convertView.getTag();
        }
        String id = mDatas.get(i);
        holder.id.setText(id);
        String [] status = getStatus(id);
        holder.a1.setText(status[0]);
        holder.a2.setText(status[1]);
        holder.a3.setText(status[2]);
        holder.a4.setText(status[3]);
        holder.a5.setText(status[4]);
        holder.a6.setText(status[5]);


        return convertView;
    }
    static class ViewHolder {
        TextView a1, a2, a3, a4, a5, a6,id;

        public ViewHolder(View itemView) {
            id = itemView.findViewById(R.id.item_id);
            a1 = itemView.findViewById(R.id.item_a1);
            a2 = itemView.findViewById(R.id.item_a2);
            a3 = itemView.findViewById(R.id.item_a3);
            a4 = itemView.findViewById(R.id.item_a4);
            a5 = itemView.findViewById(R.id.item_a5);
            a6 = itemView.findViewById(R.id.item_a6);

        }

    }
    public String [] getStatus(String id){
        try {

            String [] res = new String [6];
            // 连接数据库

            // 获取机器人状态
            String apiUrl = "https://api.yunjichina.com.cn";
            JSONObject status = getRobotStatus(apiUrl, id);



            if (status != null) {
                res[0] = String.valueOf(status.getInt("currentFloor"));
                res[1] = (status.getInt("powerPercent") + "%");
                res[2] =  (status.getInt("chargeState") == 1 ? "是" : "否");
                res[3] = (status.getBoolean("estop") ?"是" : "否");
                res[4] = status.getBoolean("idle")?"是" : "否";
                res[5] = String.valueOf(status.getLong("ts"));

                return res;
            } else {
                //res[0] = "未能获取机器人状态";
                return null;
            }
            } catch (JSONException | IOException ex) {
            throw new RuntimeException(ex);
        }



    }
    static JSONObject getRobotStatus(String url, String robotId) throws IOException {
        // ... 省略原有的 getRobotStatus 实现 ...
        return null;
    }
}
