package org.chen.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.chen.constant.WxMsgType;
import org.chen.constant.WxTemplateMsg;
import org.chen.core.OkHttpClientManager;
import org.chen.dto.*;
import org.chen.request.StringRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 陈亮
 * @Date: 2018/8/5 20:30
 */
@Slf4j
@Component
public class WxMessageUtil {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.redisexpires}")
    private int redisexpires;

    @Value("${wx.sendmsg_url}")
    private String sendmsg_url;

    @Value("${wx.base_accesstoken_url}")
    private String base_accesstoken_url;

    @Value("${wx.template_msg_url}")
    private String template_msg_url;

    @Resource()
    private OkHttpClientManager clientManager;

    @Resource
    private WxServerUtil wxServerUtil;

    //发送模板消息
    public Object sendMsgTemplate(TemplateMsg msg) {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        msg.setTemplate_id(WxTemplateMsg.ORDER_TEMPLATE);
        StringRequest req = new StringRequest(template_msg_url.replace("ACCESS_TOKEN", baseToken.getAccess_token()));
        req.post().add("template_id", msg.getTemplate_id()).add("touser", msg.getTouser()).add("url", msg.getUrl()).add("data", msg.getData()).setJsonBody(true);
        String rawBody = clientManager.send(req).getRawBody();
        log.info("发送模板消息:\n" + rawBody);
        return JSON.parse(rawBody);
    }

    //发送客服消息[文本消息]
    public Object sendMsgText(ServiceTextMsg msg) {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        StringRequest req = new StringRequest(sendmsg_url.replace("ACCESS_TOKEN", baseToken.getAccess_token()));
        req.post().add("touser", msg.getTouser()).add("msgtype", msg.getMsgtype()).add("text", msg.getText()).setJsonBody(true);
        String rawBody = clientManager.send(req).getRawBody();
        log.info("发送客服消息:\n" + rawBody);
        return JSON.parse(rawBody);
    }

    //发送客服消息[图文消息]
    public Object sendMsgNews(ServiceNewsMsg msg) {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        StringRequest req = new StringRequest(sendmsg_url.replace("ACCESS_TOKEN", baseToken.getAccess_token()));
        req.post().add("touser", msg.getTouser()).add("msgtype", msg.getMsgtype()).add("news", msg.getNews()).setJsonBody(true);
        String rawBody = clientManager.send(req).getRawBody();
        log.info("发送客服消息:\n" + rawBody);
        return JSON.parse(rawBody);
    }


    //订阅(关注)
    public Object subscribeHandle(InMsg msg, OutMsg out) {
        out.setMsgType("news");
        //图文消息
        out.setArticleCount(1);
        //图文消息体
        ArticleItem item = new ArticleItem();
        //事件类型
        String eventType = msg.getEvent();
        if ("subscribe".equals(eventType)) {
            //关注
            item.setTitle("再见");
            item.setDescription("我怕我没有机会 跟你说一声再见\n因为也许就再也见不到你\n");
            item.setPicUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=d7ce6a53eccd7b89e96c3d85371f259a/0dd7912397dda144aefd062bb8b7d0a20df486b6.jpg");
            item.setUrl("https://baike.baidu.com/item/%E5%86%8D%E8%A7%81/3102886?fr=aladdin");
            out.setItem(new ArticleItem[]{item});
            log.info("订阅(关注):\n" + JSONObject.toJSONString(out));
            return out;
        }
        return null;
    }


    //被动回复用户消息
    public Object passiveReply(InMsg msg) {
        //创建消息响应对象
        OutMsg out = new OutMsg();
        //把原来的发送方设置为接收方
        out.setToUserName(msg.getFromUserName());
        //把原来的接收方设置为发送方
        out.setFromUserName(msg.getToUserName());
        //获取接收的消息类型
        String msgType = msg.getMsgType();
        //设置消息的响应类型
        out.setMsgType(msgType);
        //设置消息创建时间
        out.setCreateTime(new Date().getTime());
        //根据类型设置不同的消息数据
        if (WxMsgType.TEXT.equals(msgType)) {
            out.setContent(msg.getContent());
        } else if (WxMsgType.IMAGE.equals(msgType)) {
            out.setMediaId(new String[]{msg.getMediaId()});
        } else if (WxMsgType.VOICE.equals(msgType)) {
            out.setMediaId(new String[]{msg.getMediaId()});
        } else if (WxMsgType.EVENT.equals(msgType)) {
            return subscribeHandle(msg, out);
        }
        log.info("被动回复用户消息:\n" + JSONObject.toJSONString(out));
        //结果返回xml
        return out;
    }
}
