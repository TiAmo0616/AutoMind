package com.example.demo.orionbase.service;

import com.example.demo.orionbase.vo.RobotCmdParamsVO;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Push通知栏推送接口
 */
@Component
@ComponentScan("com.example")
public class PushMsgApi {

    /**
     * Push消息payload数据包格式
     * 参数	        类型	    必需	 参数说明
     * pkg_name	    string	是	消息发送目标 app 的包名，不能超过 256 字节。
     * message_id	string	否	发送方自定义的消息 id，不能超过 64 字节。我方会透传 并记录日志，但是不会做校验。默认是空串。
     * message_type	string	是	消息类型 notify：通知栏消息，会展示在通知栏。 message：静默透传消息，不会展示在通知栏。
     * message_time	int	    否	消息的时间戳。整数时间戳，单位是毫秒。默认是我方系 统当前时间。
     * expire_time	int	    否	消息的有效期，单位是毫秒。如果是 0 则为永不过期。默 认是 0。例如 60000 则是 1 分钟有效期。
     * message	    string	是	消息体，格式详见 Push 消息 message 消息体格式
     */


    /**
     * Push消息message消息体格式
     *
     * 参数	             类型	  必需	默认	参数说明
     * title	        string	    是	无	消息标题，不能超过 256 个英文或者汉字。
     * content	        string	    是	无	消息内容，不能超过 512 个英文或汉字。
     * ring	            int	        否	1	是否有铃声。 0：没有铃声； 1：有铃声；
     * ring_src	        string	    否	无	铃声文件名，不能超过 128 字节。
     * icon_type	    int	        否	1	图标 icon 类型。 1：应用内的图标； 2：图标 url；
     * icon_src	        string	    否	空	icon_type 是应用内的图标，不能超过 256 字节 ● icon_src 为图标文件名，如果为空则使用应 用默认图标； icon_type 是图标 url ● icon_src 为图标 url
     * actions	        object[] 	否	无	消息到达之后自动执行的行为列表，列表中的每个 元素都是一个行为，机器人会依次执行，最多支持 5 个行为。每个行为的格式详见 消息行为格式 请注意，传递给此参数的行为会在消息到达之后立 即执行，即使用户不点击通知栏的消息也会自动执 行。如果需要用户点击之后再执行行为，请传递到 click_actions 参数中。
     * click_actions	object[] 	否	无	 点击通知栏消息的行为列表，列表中的每个元素都 是一个行为，机器人会依次执行，最多支持 5 个行 为。每个行为的格式详见 消息行为格式
     * custom	        string	    否	无	自定义内容，不能超过 1024 字节。
     */

    /**
     * 静默透传消息格式
     *
     * 参数	    类型	       必需	默认	参数说明
     * title	string	    是	无	消息标题，不能超过 256 个英文或者汉字。
     * content	string	    是	无	消息内容，不能超过 512 个英文或汉字。
     * actions	object[] 	否	无	消息到达之后自动执行的行为列表，列表中的每个 元素都是一个行为，机器人会依次执行，最多支持 5 个行为。每个行为的格式详见 消息行为格式
     * custom	string	    否	无	自定义内容，不能超过 1024 字节。
     */

    /**
     * 消息行为格式
     *
     * 参数	类型	必需	默认	参数说明
     * action_type	string	是	无
     * 行为类型
     *
     * ● open_deeplink
     *
     *         ○ 打开 deeplink
     *
     * ● play_tts
     *
     *         ○ 播放 tts
     *
     * ● update_config
     *
     *         ○ 更新配置文件
     *
     * ● update_resource
     *
     *         ○ 更新资源文件
     *
     * deeplink	string	否	无	[action_type=open_deeplink] deeplink 的 url，不能超过 512 字节。
     * tts	string 	否	无	[action_type=play_tts] 引导用户播放的 tts，不能超过 128 个英文或汉字。
     * config_id	string	否	无	[action_type=update_config]配置文件 id 或类型，不能超过 128 字节。
     *
     * config_url	string	否	无	[action_type=update_config] 配置文件 url 地址，不能超过 256 字节。
     * resource_id	string	否	无	[action_type=update_resource] 资源文件 id 或类型，不能超过 128 字节。
     * resource_url	string	否	无	[action_type=update_resource] 资源文件 url 地址，不能超过 256 字节。
     *
     */




    /**
     * 此接口用于给机器人下发 Push 推送
     *
     * robot_sn	    string	否	机器人 sn，多个用英文逗号分隔。此参数和 robot_uuid 参 数只传递其中一个即可。最多一次支持 1000 个机器人。
     * robot_uuid	string	否	机器人 uuid，多个用英文逗号分隔。此参数和 robot_sn 参 数只传递其中一个即可。最多一次支持 1000 个机器人。
     * payload	    string	是	Push 消息 payload 数据包，json 对象字符串。具体格式详 见 Push 消息 payload 数据包格式
     * @return
     */
    public static void send(HttpSession session, String robot_sn, String robot_uuid, String payload) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        if(StringUtils.isNotBlank(robot_sn)) {
            formData.put("robot_sn", robot_sn);
        }else {
            formData.put("robot_uuid", robot_uuid);
        }
        formData.put("payload", payload);
        //下发机器人执行语音指令接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/push/send", formData);
        // TODO 需要存储返回的信息id 便于查询
        // ApplicationMessageResultVO 解析参数
    }

    /**
     * 查询Push通知栏推送结果接口
     *
     * 查询机给器人下发 Push 的结果
     * msg_id	string	是	从推送接口获取到的 Push 消息 id，多个使用英文逗号分 隔。
     * @return
     */
    public static void sendResult(HttpSession session, String msg_id) {
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_id", msg_id);
        //下发机器人执行语音指令接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/push/send/result", formData);
        // TODO 需要存储返回的信息id 便于查询
        // MessageQueryListResultVO 解析参数
    }



    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();


    }


}
