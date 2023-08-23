package com.example.demo.orionbase.service;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 机器人信息相关接口
 */
@Component
@ComponentScan("com.example")
public class RobotInfoApi {


    /**
     * 获取机器人信息
     *
     * 获取一个机器人信息
     * robot_sn	            string	是	机器人 sn。此参数和 robot_uuid 参数只传递其中一个即 可，如果都传递了优先使用 robot_uuid。
     * robot_uuid	        string	否	机器人 uuid。此参数和 robot_sn 参数只传递其中一个即 可，如果都传递了优先使用 robot_uuid。
     * is_report_status	    string	否	是否获取机器人上报的电量等信息，默认不获取。 0：不获取 1：获取
     * is_report_task_event	string	否	是否获取机器人上报的任务事件，默认不获取。0：不获取 1：获取。（仅能获取24小时内的任务事件）
     * report_task_type	    string	否	获取机器人上报的事件任务类型，多个用英文逗号分隔。仅当is_report_task_event = 1时有效。目前可选值：
     *                                  送餐任务：meals_deliver_task-deliver
     * @return
     * 返回值中包含机器人当前运行任务（task）的描述。可通过下表查询taskid对应的意思
     * 5LyR55yg         休眠	低功耗待机，但仍然耗电和联网。此时不能执行任何任务，除非解除休眠。
     * 562J5b6F6YCB6aSQ	等待送餐	此时可以认为空闲。但不排除服务员可能正在装在食物
     * 5oCl5YGc	        急停	此时机器人不会执行任何命令，除非有人帮她解除急停旋钮
     * 5Y2H57qn	        升级	此时请勿向机器人发送任何命令，直到升级完成
     * 5qyi6L-O	        欢迎	揽客模式下，处于欢迎宾客的状态
     * 6YCB6aSQ5Lit	    送餐中	送餐任务正在进行中，请勿打断
     * 5YWF55S15Lit	    充电中	此时请勿向机器人发送任何指令
     * 6Lez6Iie	        跳舞	揽客模式下，机器人正在表演跳舞
     * 6aKG5L2N5Lit	    领位中	引领任务正再进行中
     * 5beh6Iiq	        巡航	正处于巡航模式之中
     */
    public static void robotInfo(HttpSession session, String robot_sn, String robot_uuid, String is_report_status,
                                String is_report_task_event, String report_task_type) {
        String url = "";
        url = "robot/robot_info?robot_sn="+ robot_sn + "&robot_uuid="+robot_uuid +"&is_report_status="
                            +is_report_status+"&is_report_task_event="+is_report_task_event+"&report_task_type="+report_task_type;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 RobotInfoResultVO 实体类解析数据
    }

    /**
     * 修改机器人信息
     *
     * 修改一个机器人的信息
     * robot_sn	    string	否	机器人 sn。此参数和 robot_uuid 参数只传递其中一个即可， 如果都传递了优先使用 robot_uuid。
     * robot_uuid	string	否	机器人 uuid。此参数和 robot_sn 参数只传递其中一个即可， 如果都传递了优先使用 robot_uuid。
     * robot	    string	是	机器人信息对象 josn 串，详见oRobotMod 机器人信息对象 - 修改 例如：{“robot_name”:”new_robot_name”}
     *
     */
    public static void modifyRobot(HttpSession session, String robot_sn, String robot_uuid, String robot) {
        String url = "";
        url = "robot/modify_robot?robot_sn="+ robot_sn + "&robot_uuid="+robot_uuid +"&robot="+robot;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 RobotInfoResultVO 实体类解析数据
    }

    /**
     * 获取机器人列表
     *
     * 获取机器人列表
     * agency_id	string	否	代理商 id。只支持传递 1 个代理商 id。此参数和 corpid / ov_corpid 参数至少传递 1 个。
     * corpid	    string	否	企业 uuid。多个用英文逗号分隔。此参数和 agency_id / ov_corpid 参数至少传递 1 个。
     * ov_corpid	string	否	语音链路企业 id。多个用英文逗号分隔。此参数和 agency_id / corpid 参数至少传递 1 个。
     * robot_uuid	string	否	机器人 uuid。多个用英文逗号分隔。
     * robot_sn	    string	否	机器人 sn。多个用英文逗号分隔。
     * page	        string	否	分页参数。第几页，从 1 开始。
     * page_rows	string	否	分页参数。每页多少条数据。
     *
     */
    public static void robotList(HttpSession session, String agency_id, String corpid, String ov_corpid, String robot_uuid, String robot_sn
            , String page, String page_rows) {
        String url = "";
        url = "robot/robot_list?agency_id="+ agency_id + "&corpid="+corpid +"&ov_corpid="+ov_corpid+"&robot_uuid="
                +robot_uuid+"&robot_sn="+robot_sn+"&page="+page+"&page_rows="+page_rows;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 RobotListResultVO 实体类解析数据
    }

    /**
     * 获取机器人楼层地图点位列表
     *
     * 获取机器人列表
     * robot_sn	    string	否	机器人 sn。此参数和 robot_uuid 参数只传递其中一个即 可，如果都传递了优先使用 robot_uuid。
     * robot_uuid	string	否	机器人 uuid。此参数和 robot_sn 参数只传递其中一个即 可，如果都传递了优先使用 robot_uuid。
     * corpid	    string	是	企业id
     * floor_number	string	否	楼层编号。不传递则不限制。
     * floor_id	    string	否	楼层 id。不传递则不限制。
     * pos_name	    string	否	点位名称，完全匹配。不传递则不限制。
     * lang	        string	否	语言，默认是 zh_CN。例如 zh_CN / en_US 等。
     *
     */
    public static void positionList(HttpSession session, String robot_sn, String robot_uuid, String corpid, String floor_number, String floor_id
            , String pos_name, String lang) {
        String url = "";
        url = "robot/map/position_list?robot_sn="+ robot_sn + "&robot_uuid="+robot_uuid +"&corpid="+corpid+"&floor_number="
                +floor_number+"&floor_id="+floor_id+"&pos_name="+pos_name+"&lang="+lang;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据  实体类解析数据
    }





    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();


    }


}
