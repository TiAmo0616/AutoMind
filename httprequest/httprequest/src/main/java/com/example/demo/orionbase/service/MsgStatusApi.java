package com.example.demo.orionbase.service;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 企业信息接口
 */
@Component
@ComponentScan("com.example")
public class MsgStatusApi {


    /**
     * 获取一个企业信息
     *
     * @param corpid
     * @param ov_corpid
     * corpid	    string	是	要获取的企业 uuid，只支持获取 1 个企业。
     * ov_corpid	string	是	要获取的企业语音链路 id，只支持获取 1 个企业。
     * @return
     */
    public static void corpInfo(HttpSession session, String corpid, String ov_corpid) {
        String url = "";
        url = "corp/corp_info?corpid="+ corpid + "&ov_corpid="+ov_corpid;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CompanyInfoVO 实体类解析数据
    }

    /**
     * 获取企业列表
     *
     * @param corpid
     * @param ov_corpid
     * corpid	    string	否	企业 uuid，多个用英文逗号分隔。
     * ov_corpid	string	否	企业语音链路 id，多个用英文逗号分隔。
     * agency_id	string	否	代理商 id，多个用英文逗号分隔。
     * page	        string	否	分页参数。第几页，从 1 开始。
     * page_rows	string	否	分页参数。每页多少条数据。
     * @return
     */
    public static void corpInfoList(HttpSession session, String corpid, String ov_corpid, String agency_id, String page, String page_rows) {
        String url = "corp/corp_list?corpid=&ov_corpid=&agency_id=&page=&page_rows=";
        if(StringUtils.isNotBlank(corpid)) {
            url.replace("corpid=", "corpid="+corpid);
        }

        if(StringUtils.isNotBlank(ov_corpid)) {
            url.replace("ov_corpid=", "ov_corpid="+ov_corpid);
        }

        if(StringUtils.isNotBlank(agency_id)) {
            url.replace("agency_id=", "agency_id="+agency_id);
        }

        if(StringUtils.isNotBlank(page)) {
            url.replace("page=", "page="+page);
        }

        if(StringUtils.isNotBlank(page_rows)) {
            url.replace("page_rows=", "page_rows="+page_rows);
        }

        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CompanyListVO 实体类解析数据
    }

    /**
     * 获取代理商下的企业列表
     *
     * @param agency_id
     * agency_id	string	否	代理商 id
     * @return
     */
    public static void agencyCorpList(HttpSession session, String agency_id) {
        String url = "corp/agency_corp_list?agency_id=";
        if(StringUtils.isNotBlank(agency_id)) {
            url.replace("agency_id=", "agency_id=" + agency_id);
        }
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CompanyListVO 实体类解析数据
    }

    /**
     * 获取行业列表
     *
     * @return
     */
    public static void cateList(HttpSession session) {
        String url = "corp/cate_list";
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CateListVO 实体类解析数据
    }

    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();


    }


}
