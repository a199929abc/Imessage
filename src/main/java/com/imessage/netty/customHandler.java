package com.imessage.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @Descrption: custom handler
 */
//相当于 入站
public class customHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg)
            throws Exception {
        //get channel
        Channel channel = ctx.channel();

        if (msg instanceof HttpRequest){
            System.out.println(channel.remoteAddress());

            // setup sending text
            ByteBuf content = Unpooled.copiedBuffer("Hello netty ", CharsetUtil.UTF_8);

            // construct http response

            FullHttpMessage response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    content);

            // 为相应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //push response to client
            ctx.writeAndFlush(response);
        }

    }


}
