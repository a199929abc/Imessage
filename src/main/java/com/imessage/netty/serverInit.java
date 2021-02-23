package com.imessage.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *
 * @Descrption: Init
 */

public class serverInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //get pipline through channel
        ChannelPipeline pipeline=channel.pipeline();

        // using pipeline add handler
        pipeline.addLast("HTTPServerCodec",new HttpServerCodec());//solve Http request
        pipeline.addLast("custom ",new customHandler());


    }
}
