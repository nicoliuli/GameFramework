package com.game.ws;

import com.game.core.config.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class WS {
	public void startup() {


		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new MyWebSocketChannelHandler());
			ChannelFuture channelFuture = b.bind(ServerConfig.WS_PORT).sync();
			Channel channel = channelFuture.channel();
			channel.closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//优雅的退出程序
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}

	}
}
