package com.zz.nettystudy.sample.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class CodecEncoder extends MessageToByteEncoder<Serializable> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) {
        byte[] body = SerializationUtils.serialize(msg); // 将对象转换为byte, 可以使用任意序列化的工具

        int dataLength = body.length;   // 读取消息的长度
        out.writeInt(dataLength);   // 先将消息长度写入，也就是消息头
        out.writeBytes(body);   // 再写入具体的消息
    }
}