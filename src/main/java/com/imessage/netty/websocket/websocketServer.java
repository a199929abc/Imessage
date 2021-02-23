package com.imessage.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class websocketServer {
  public static void main(String[] args) throws Exception {
      EventLoopGroup main = new NioEventLoopGroup();
      EventLoopGroup sub  = new NioEventLoopGroup();
      try{
          ServerBootstrap server = new ServerBootstrap();
          server.group(main,sub).channel(NioServerSocketChannel.class)
                  .childHandler(new webserverInit());
          ChannelFuture future= server.bind(8080).sync();
          future.channel().closeFuture().sync();
      }finally{
          main.shutdownGracefully();
          sub.shutdownGracefully();
      }


    //
  }
}
