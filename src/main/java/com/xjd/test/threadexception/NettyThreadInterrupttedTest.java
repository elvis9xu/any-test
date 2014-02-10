/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2014 ChinaPnR,Inc.All Rights Reserved.
 */
package com.xjd.test.threadexception;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.handler.timeout.ReadTimeoutException;
import org.jboss.netty.util.HashedWheelTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author elvis.xu
 * @version $Id: NettyThreadInterrupttedTest.java, v 0.1 2014-1-20 上午11:22:57 elvis.xu Exp $
 */
public class NettyThreadInterrupttedTest {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
            Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), 3));

        ChannelPipelineFactory pipelineFactory = new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("idleStateHandler", new IdleStateHandler(new HashedWheelTimer(),
                    10, 10, 0));
                pipeline.addLast("SocketStateHandler", new SocketStateHandler());
                pipeline.addLast("handler", new ExecuteHandler());
                return pipeline;
            }
        };

        bootstrap.setPipelineFactory(pipelineFactory);

        bootstrap.setOption("child.reuseAddress", true);
        bootstrap.setOption("child.tcpNoDelay", true);

        bootstrap.bind(new InetSocketAddress(6767));

        ClientBootstrap bootstrapc = new ClientBootstrap(new NioClientSocketChannelFactory(
            Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        bootstrapc.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("handler", new ClientExecuteHandler());
                return pipeline;
            }
        });

        ChannelFuture cf = bootstrapc.connect(new InetSocketAddress(6767));

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cf.getChannel().close();
        bootstrapc.releaseExternalResources();
        bootstrap.releaseExternalResources();
        System.out.println("DONE!");
        ;
    }

    public static class SocketStateHandler extends IdleStateAwareChannelHandler {
        private static final Logger logger = LoggerFactory.getLogger(SocketStateHandler.class);

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            if (e.getCause() instanceof ReadTimeoutException) {
                logger.error("ReadTimeoutException", e.getCause());
                ctx.getChannel().close();
            }
        }

        @Override
        public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
            switch (e.getState()) {
                case READER_IDLE:
                    logger.warn("READER_IDLE...Close Channel");
                    e.getChannel().close();
                    break;
                case WRITER_IDLE:
                    logger.warn("WRITER_IDLE...Close Channel");
                    e.getChannel().close();
                    break;
                case ALL_IDLE:
                    logger.warn("ALL_IDLE...Close Channel");
                    e.getChannel().close();
                    break;
                default:
                    logger.warn("channelIdle...");
                    break;
            }
        }

    }

    public static class ExecuteHandler extends SimpleChannelHandler {

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            System.out.println("starting...");
            final Channel c = e.getChannel();
            final Thread t = Thread.currentThread();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.close();
                    t.interrupt();
                    System.out.println("closed!");
                }
            }).start();
            try {
                Thread.sleep(5000L);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("end!");
        }

    }

    public static class ClientExecuteHandler extends SimpleChannelHandler {

        /**
         * @see org.jboss.netty.channel.SimpleChannelHandler#channelConnected(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ChannelStateEvent)
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            ChannelBuffer buf = ChannelBuffers.buffer(1024);
            buf.writeBytes("HELLOWORLD".getBytes());
            e.getChannel().write(buf);
            e.getChannel().close();
        }

    }

}
