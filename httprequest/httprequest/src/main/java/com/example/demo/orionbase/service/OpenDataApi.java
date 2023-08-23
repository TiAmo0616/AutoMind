package com.example.demo.orionbase.service;

import com.example.demo.orionbase.vo.BaseOpenDataResultVO;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 数据查询相关接口
 */
@Component
@ComponentScan("com.example")
public class OpenDataApi {


    /**
     * 客流\招揽明细
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void flowList(HttpSession session, String compare, String product_line,
                                String devices, String pageIndex, String pageSize) {
        String url = "dataopen/flow_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 唤醒明细
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void wakeupDetailList(HttpSession session, String compare, String product_line,
                                String devices, String pageIndex, String pageSize) {
        String url = "dataopen/wakeup_detail_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 交互明细
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machineInteractionList(HttpSession session, String compare, String product_line,
                                        String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_interaction_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 导览（分导览名称汇总）
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machineGuidIntroductionList(HttpSession session, String compare, String product_line,
                                              String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_guide_introduction_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 巡逻次数（每⽇6点前更新前⼀⽇）
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machinePatrolIntroductionList(HttpSession session, String compare, String product_line,
                                                   String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_patrol_introduction_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 引领次数明细（分引领地点）
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machineGuideList(HttpSession session, String compare, String product_line,
                                                     String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_guide_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 总问路次数明细
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machineAskList(HttpSession session, String compare, String product_line,
                                        String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_ask_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 来访接待
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machineReceptionIntroductionList(HttpSession session, String compare, String product_line,
                                      String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_reception_introduction_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 对话次数
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void machineDialogList(HttpSession session, String compare, String product_line,
                                                        String devices, String pageIndex, String pageSize) {
        String url = "dataopen/machine_dialog_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 对话领域分布
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void percentDialogModeCount(HttpSession session, String compare, String product_line,
                                 String devices, String pageIndex, String pageSize) {
        String url = "dataopen/percent_dialog_mode_count?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);

        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }


    /**
     * 对话明细
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void voiceList(HttpSession session, String compare, String product_line,
                                         String devices, String pageIndex, String pageSize) {
        String url = "dataopen/voice_list?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 关键词词云
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void entityWordsCloudOpen(HttpSession session, String compare, String product_line,
                                 String devices, String pageIndex, String pageSize) {
        String url = "dataopen/entity_words_cloud_open?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }

    /**
     * 不同交互类型占⽐
     *
     * compare	    是	compare=2021-01-02,2021-02-01 ：查询时间段，⽇期半 ⻆逗号隔开，也⽀持只传2021-01-02 查询⼀天的数据。
     * product_line	否	CM-GB01N,CM-GB01D,CM-GB01L,CM-GB02D,CMGB01K,CM-GB01C,OS-R-SA03S,OS-R-SD01B,CMGB03D,OS-R-DR01S 设备类别，⾮必传，
     * devices	    否	【设备sn】 ⾮必传，⽀持多个sn，半⻆逗号隔开，只能获 取企业绑定的sn数据。
     * pageIndex	否	pageIndex= 1 【明细list ⻚码】，⾮必传默认1
     * pageSize	    否	pageSize = 10 【明细list，每⻚记录条数】，默认10，不 建议超过50
     * @return
     */
    public static void percentInteractionModeCount(HttpSession session, String compare, String product_line,
                                            String devices, String pageIndex, String pageSize) {
        String url = "dataopen/percent_interaction_mode_count?compare="+ compare + "&product_line="+product_line+ "&devices="
                +devices+ "&pageIndex="+pageIndex+ "&pageSize="+pageSize;
        BaseOpenDataResultVO vo = ApiAccessApi.sendGetForOpen(session, url);
        // TODO 做数据解析
        // 根据 BaseOpenDataResultVO 实体类解析数据
    }





    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();


    }


}
