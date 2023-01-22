package com.game.ws;

import com.alibaba.fastjson.JSONObject;
import com.game.core.call.WSCall;
import com.game.core.Connection;
import com.game.core.Node;
import com.game.core.Port;
import com.game.core.config.ServerConfig;
import com.game.core.util.HumanObjectUtil;
import com.game.core.util.PortUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;


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

        port.addConn(conn);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        WSCall WSCall = new WSCall();
        WSCall.setFromNodeId(ServerConfig.NODE_ID);
        WSCall.setFromPortId(portId);
        WSCall.setToPortId(portId);
        WSCall.setHumanId(humanId);
        // 解析并设置 param function等
        String message = textWebSocketFrame.text();
        JSONObject jsonObject = JSONObject.parseObject(message);
        String msgHandlerId = jsonObject.getString("msgHandlerId");
        JSONObject jsonParam = jsonObject.getJSONObject("jsonParam");
        WSCall.setMsgHandlerId(msgHandlerId);
        WSCall.setJsonParam(jsonParam.toJSONString());
        // 投入node队列
        Node.instance().addQueue(WSCall);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        // 定时任务 移除数据
        port.removeConn(humanId);
    }
}
