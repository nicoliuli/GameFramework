package com.game.ws;

import com.game.core.Connection;
import com.game.core.Node;
import com.game.core.Port;
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

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 创建链接
        Channel channel = ctx.channel();

        // 分派port
        int portIndex = PortUtil.portIndex();
        Port port = Node.getNode().getPort(portIndex);


        Connection conn = new Connection(HumanObjectUtil.genHumanId(), channel, port);
        // 创建会话

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
		/*System.out.println("收到的消息："+textWebSocketFrame.text());
		ctx.channel().writeAndFlush(new TextWebSocketFrame(LocalDateTime.now()+" : " + textWebSocketFrame.text()));*/

        // 解码协议

        // 投入node队列
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
