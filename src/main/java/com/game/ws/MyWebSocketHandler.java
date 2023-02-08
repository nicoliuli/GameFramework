package com.game.ws;

import com.alibaba.fastjson.JSONObject;
import com.game.core.*;
import com.game.core.call.MsgCall;
import com.game.core.call.WSCall;
import com.game.core.config.ServerConfig;
import com.game.core.obj.Connection;
import com.game.core.obj.GlobalHumanObject;
import com.game.core.obj.HumanObject;
import com.game.core.util.HumanObjectUtil;
import com.game.core.util.PortUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;


public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private Integer humanId;
    private Integer portId;
    private Connection conn;
    private Port port;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建链接
        Channel channel = ctx.channel();

        // 分派port
        portId = PortUtil.portIndex();
        port = Node.instance().getPort(portId);

        // 创建会话
        humanId = HumanObjectUtil.genHumanId();
        conn = new Connection(humanId, channel, port);

        GlobalHumanObject globalHumanObj = new GlobalHumanObject(humanId,ServerConfig.NODE_ID,portId,conn);
        GlobalHumanObjectInfo.add(globalHumanObj);
        port.addConn(conn);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        WSCall wsCall = new WSCall();
        wsCall.setFromNodeId(ServerConfig.NODE_ID);
        wsCall.setFromPortId(portId);
        wsCall.setToPortId(portId);
        wsCall.setHumanId(humanId);
        // 解析并设置 param function等
        String message = textWebSocketFrame.text();
        JSONObject jsonObject = JSONObject.parseObject(message);
        String msgHandlerId = jsonObject.getString("msgHandlerId");
        JSONObject jsonParam = jsonObject.getJSONObject("jsonParam");
        wsCall.setMsgHandlerId(msgHandlerId);
        wsCall.setJsonParam(jsonParam.toJSONString());

        // 封装humanObj,如果封装过重，可以把封装过程下沉到Node里，避免阻塞NioEventLoop
        HumanObject humanObj = new HumanObject();
        humanObj.setHumanId(humanId);
        humanObj.setConnection(conn);
        MsgCall msgCall = new MsgCall();
        msgCall.setWSCall(wsCall);
        msgCall.setHumanObj(humanObj);

        // 投入node队列
        Node.instance().addQueue(msgCall);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 定时任务 移除数据
        GlobalHumanObjectInfo.remove(humanId);
        Connection connection = port.removeConn(humanId);
        connection.getChannel().close();
    }
}
