package com.game.ws;

import com.game.core.Call;
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

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private Integer humanId;
    private Integer portId;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建链接
        Channel channel = ctx.channel();

        // 分派port
        portId = PortUtil.portIndex();
        Port port = Node.instance().getPort(portId);

        // 创建会话
        humanId = HumanObjectUtil.genHumanId();
        Connection conn = new Connection(humanId, channel, port);

        port.addConn(conn);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        Call call = new Call();
        call.setFromNodeId(ServerConfig.NODE_ID);
        call.setFromPortId(portId);
        call.setToPortId(portId);
        call.setHumanId(humanId);
        call.setQueueType(1);
        // 解析并设置 param function等
        String message = textWebSocketFrame.text();
        call.setMsgHandlerId(message);
        // 投入node队列
        Node.instance().addQueue(call);
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
    }
}
