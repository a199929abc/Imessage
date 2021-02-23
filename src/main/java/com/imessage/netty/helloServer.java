package com.imessage.netty;

import io.netty.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class helloServer {
    public static void main( String[] args ) throws Exception
    {   //only receive connect doesm't process

        EventLoopGroup bossGroup=new NioEventLoopGroup();//main thread

        EventLoopGroup workerGroup=new NioEventLoopGroup();// worker group
        // boss group will offer connect to workergroup and process

        try{
        // server start class
        ServerBootstrap serverBootstrap= new ServerBootstrap();//NIO boostrap

        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)//set 2 way transaction
                .childHandler(new serverInit());//child handler
        //setup main and work thread,init

        // start server and bind to port 8088 asyncable
        ChannelFuture future =serverBootstrap.bind(8088).sync();

        future.channel().closeFuture().sync();//
            }
            finally{
             bossGroup.shutdownGracefully();
             workerGroup.shutdownGracefully();
            }




    }

}
