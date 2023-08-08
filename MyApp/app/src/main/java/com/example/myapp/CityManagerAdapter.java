package com.example.myapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.utils.JDBCUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class CityManagerAdapter extends BaseAdapter {
    Context context;
    List<String> mDatas;
    private static final String TAG = "mysql-myapp-datacount";
    public CityManagerAdapter(Context context, List<String> mDatas) {
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
        ViewHolder holder = null;
        View convertView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city_manager,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String bean = mDatas.get(i);
        holder.cityTv.setText(bean);
        int msg = count_num(bean);
        String num = "数量"+String.valueOf(msg);
        holder.currentTempTv.setText(num);

        return convertView;
    }
    static class ViewHolder{
        TextView cityTv,currentTempTv,tempRangeTv;
        public ViewHolder(View itemView){
            cityTv = itemView.findViewById(R.id.item_city_tv_city);

            currentTempTv = itemView.findViewById(R.id.item_city_tv_temp);

            tempRangeTv = itemView.findViewById(R.id.item_city_temprange);

        }
    }
    public int count_num(String s){

        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        int msg = 0;
        try {
            // mysql简单的查询语句。这里是根据user表的userAccount字段来查询某条记录
            String sql = "select num from company where companyname = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    //Log.e(TAG,"账号：" + "云迹")
                    ps.setString(1, s);
                    ResultSet rs = ps.executeQuery();
                    msg = rs.getInt("num");
                    connection.close();
                    ps.close();


                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常login：" + e.getMessage());
            msg = 0;
        }
        //return msg;
        return(msg);
    }
}

