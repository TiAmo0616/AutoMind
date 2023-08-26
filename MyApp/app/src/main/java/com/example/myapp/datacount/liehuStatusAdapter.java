package com.example.myapp.datacount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.utils.ApiAccessApi;
import com.google.gson.JsonObject;

import java.util.List;

public class liehuStatusAdapter extends BaseAdapter {
    static Context context;
    List<String> mDatas;
    private static final String TAG = "mysql-myapp-liehuStatusAdapter";

    public liehuStatusAdapter(Context context, List<String> mDatas) {
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
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        liehuStatusAdapter.ViewHolder holder = null;
        View convertView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_liehustatus,null);
            holder = new liehuStatusAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (liehuStatusAdapter.ViewHolder) convertView.getTag();
        }
        String id = mDatas.get(i);
        holder.id.setText(id);
        String [] status = robotInfo(null,id,"1","1","1");
        holder.a1.setText(status[0]);
        holder.a2.setText(status[1]);
        holder.a3.setText(status[2]);
        holder.a4.setText(status[3]);
        holder.a5.setText(status[4]);
        holder.a6.setText(status[5]);
        holder.a7.setText(status[6]);
        holder.a8.setText(status[7]);
        holder.a9.setText(status[8]);

        return convertView;
    }
    static class ViewHolder {
        TextView a1, a2, a3, a4, a5, a6,a7,a8,a9,a10,id;

        public ViewHolder(View itemView) {
            id = itemView.findViewById(R.id.item_id);
            a1 = itemView.findViewById(R.id.item_a1);
            a2 = itemView.findViewById(R.id.item_a2);
            a3 = itemView.findViewById(R.id.item_a3);
            a4 = itemView.findViewById(R.id.item_a4);
            a5 = itemView.findViewById(R.id.item_a5);
            a6 = itemView.findViewById(R.id.item_a6);
            a7 = itemView.findViewById(R.id.item_a7);
            a8 = itemView.findViewById(R.id.item_a8);
            a9 = itemView.findViewById(R.id.item_a9);
            a10 = itemView.findViewById(R.id.item_a10);

        }

    }
    public static String[] robotInfo(String robot_sn, String robot_uuid, String is_report_status,
                                 String is_report_task_event, String report_task_type) {
        String url = "robot/robot_info?robot_sn=" + robot_sn + "&robot_uuid=" + robot_uuid + "&is_report_status="
                + is_report_status + "&is_report_task_event=" + is_report_task_event + "&report_task_type=" + report_task_type;
        JsonObject DATA = ApiAccessApi.sendGet(url,context);
        assert DATA != null;
        return getdata(DATA);

    }
    public static String[] getdata(JsonObject DATA){
        String [] res = new String [9];
        JsonObject data = (JsonObject) DATA.get("data");
        JsonObject robert = (JsonObject) data.get("robert");
        String robot_uuid = String.valueOf(robert.get("robot_uuid"));
        String robot_name = String.valueOf(robert.get("robot_name"));
        String robot_version = String.valueOf(robert.get("robot_version"));
        String robot_model = String.valueOf(robert.get("robot_model"));
        String robot_mode = String.valueOf(robert.get("robot_mode"));

        JsonObject robot_report_status = (JsonObject) DATA.get("robot_report_status");
        JsonObject battery = (JsonObject) robot_report_status.get("battery");
        String battery_rate = String.valueOf(battery.get("battery_rate"));
        String is_charging = String.valueOf(battery.get("is_charging"));

        JsonObject location = (JsonObject) robot_report_status.get("location");
        String pos_name = String.valueOf(location.get("pos_name"));
        String emergency = String.valueOf(location.get("emergency"));

        res[0] = robot_uuid;
        res[1] = robot_name;
        res[2] = robot_version;
        res[3] = robot_model;
        res[4] = robot_mode;
        res[5] = battery_rate;
        res[6] = is_charging;
        res[7] = pos_name;
        res[8] = emergency;
        return res;

    }

}
