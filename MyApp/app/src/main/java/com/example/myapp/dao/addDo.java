package com.example.myapp.dao;

import android.util.Log;

import com.example.myapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addDo {
    private static final String TAG = "mysql-myapp-adddDo";
    public boolean add_(String id1,String name1,String api1,String company1){
        Connection connection = JDBCUtils.getConn();
        try {
            String sql = "insert into robert(id,name,apiurl,company) values (?,?,?,?)";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库
                    ps.setString(1,id1);
                    ps.setString(2,name1);
                    ps.setString(3,api1);
                    ps.setString(4,company1);


                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0){
                        boolean flag2 = add_num(company1);
                        return flag2;
                    }
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "机器人添加异常：" + e.getMessage());
            return false;
        }
        return false;
    }
    public boolean add_num(String company){
        Connection connection = JDBCUtils.getConn();
        try {
            String sql = "update table company set num = num+1 where companyname = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库

                    ps.setString(1,company);


                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0)
                        return true;
                    else
                        return false;
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "机器人添加异常：" + e.getMessage());
            return false;
        }
    }
    public boolean add_company(String company){
        Connection connection = JDBCUtils.getConn();
        try {
            String sql = "insert into company(companyname,num) values (?,0)";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库

                    ps.setString(1,company);


                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0)
                        return true;
                    else
                        return false;
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "机器人品牌添加异常：" + e.getMessage());
            return false;
        }
    }
}
