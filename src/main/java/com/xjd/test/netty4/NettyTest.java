package com.xjd.test.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.CookieDecoder;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyTest {
	public static String[] cs = new String[2];
	public static HttpRequest[] hs = new HttpRequest[2];
	public static CompositeByteBuf[] bs = new CompositeByteBuf[]{Unpooled.compositeBuffer(), Unpooled.compositeBuffer()};
	public static int i = 0;
	private static Logger log = LoggerFactory.getLogger(NettyTest.class);

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new HttpChannelInitializer());

			Channel channel = sb.bind(8095).sync().channel();

			log.info("server started at: 8095");

			channel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

		protected void initChannel(SocketChannel ch) throws Exception {
			ChannelPipeline pipeline = ch.pipeline();

			pipeline.addLast("httpRequestDecoder", new HttpRequestDecoder());
			pipeline.addLast("httpResponseEncoder", new HttpResponseEncoder());
			pipeline.addLast("httpContentCompressor", new HttpContentCompressor());
			pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
			pipeline.addLast("httpHandler", new HttpHandler());
		}

	}

	public static class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

		protected HttpPostRequestDecoder httpPostRequestDecoder;

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			if (httpPostRequestDecoder != null) {
				try {
					httpPostRequestDecoder.cleanFiles();
					httpPostRequestDecoder = null;
				} catch (Exception e) {
					log.warn("", e);
				}
			}
		}

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
			log.trace("messaage received: {}", msg.getClass().getName());

			if (msg instanceof HttpRequest) {
				HttpRequest httpRequest = (HttpRequest) msg;
				if (i < 2) {
					hs[i] = httpRequest;
				}

				log.trace("uri: {}", httpRequest.getUri());
				log.trace("protocol: {}", httpRequest.getProtocolVersion().toString());
				log.trace("method: {}", httpRequest.getMethod().toString());
				log.trace("decoderResult: {}", httpRequest.getDecoderResult().toString());
				log.trace("remoteAddress: {}", ctx.channel().remoteAddress());
				log.trace("localAddress: {}", ctx.channel().localAddress());

				log.trace("[[headers]]");
				HttpHeaders headers = httpRequest.headers();
				for (Entry<String, String> header : headers.entries()) {
					log.trace("{}:{}", header.getKey(), header.getValue());
				}

				log.trace("[[cookies]]");
				Set<Cookie> cookies = null;
				String cookieStr = headers.get(HttpHeaders.Names.COOKIE);
				if (cookieStr == null) {
					cookies = Collections.emptySet();
				} else {
					cookies = CookieDecoder.decode(cookieStr);
				}
				for (Cookie cookie : cookies) {
					log.trace("Cookie: {}", cookie.toString());
				}

				log.trace("[[parameters]]");
				QueryStringDecoder queryStringDecoder = new QueryStringDecoder(httpRequest.getUri());
				log.trace("uri: {}", queryStringDecoder.uri());
				log.trace("path: {}", queryStringDecoder.path());
				Map<String, List<String>> params = queryStringDecoder.parameters();
				for (Entry<String, List<String>> entry : params.entrySet()) {
					log.trace("{}:{}", entry.getKey(), Arrays.toString(entry.getValue().toArray()));
				}

				if (httpRequest.getMethod() == HttpMethod.POST) {
					log.trace("isChunked: {}", HttpHeaders.isTransferEncodingChunked(httpRequest));
					log.trace("isMulti: {}", HttpPostRequestDecoder.isMultipart(httpRequest));

					HttpDataFactory httpDataFactory;
					if (HttpPostRequestDecoder.isMultipart(httpRequest)) {
						httpDataFactory = new DefaultHttpDataFactory(true); // use disk
					} else {
						httpDataFactory = new DefaultHttpDataFactory(); // use mixed
					}
					try {
						httpPostRequestDecoder = new HttpPostRequestDecoder(httpDataFactory, httpRequest);
					} catch (ErrorDataDecoderException e) {
						log.error("cannot reolve request.");
						resolveError(ctx);
						return;
					}
				}

			} else if (msg instanceof HttpContent) {
				HttpContent chunk = (HttpContent) msg;
				// try {
				// httpPostRequestDecoder.offer(chunk);
				// } catch (ErrorDataDecoderException e) {
				// log.error("cannot reolve request.");
				// resolveError(ctx);
				// return;
				// }

				// log.trace("[[DATA]]");
				// while (httpPostRequestDecoder.hasNext()) {
				// InterfaceHttpData interfaceHttpData = httpPostRequestDecoder.next();
				// if (interfaceHttpData.getHttpDataType() == HttpDataType.Attribute) {
				// Attribute attribute = (Attribute) interfaceHttpData;
				// log.trace("attribute: {}", attribute.toString());
				// } else if (interfaceHttpData.getHttpDataType() == HttpDataType.FileUpload) {
				// FileUpload fileUpload = (FileUpload) interfaceHttpData;
				// log.trace("fileupload: {}", fileUpload.toString());
				// }
				// }
				if (i < 2) {
					bs[i].addComponent(chunk.content());
					chunk.content().retain();
				}

				if (chunk instanceof LastHttpContent) {
					log.trace("OK");
					// 应答
					// 应答完成后
					// DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
					// HttpResponseStatus.OK);
					// response.headers().set(HttpHeaders.Names.TRANSFER_ENCODING,
					// HttpHeaders.Values.CHUNKED);
					// ctx.write(response);
					// HttpChunkedInput httpChunkWriter;
					// try {
					// httpChunkWriter = new HttpChunkedInput(new ChunkedFile(new
					// File("/tmp/tmp.txt")));
					// ctx.write(httpChunkWriter, ctx.newProgressivePromise()).addListener(
					// new ChannelProgressiveFutureListener() {
					//
					// @Override
					// public void operationComplete(ChannelProgressiveFuture future) throws
					// Exception {
					// System.out.println("FINISH!");
					// }
					//
					// @Override
					// public void operationProgressed(ChannelProgressiveFuture future, long
					// progress, long total)
					// throws Exception {
					// System.out.println(progress + ":" + total);
					// }
					//
					// });
					//
					// } catch (IOException e) {
					// e.printStackTrace();
					// }
					// ctx.write(new
					// DefaultHttpContent(Unpooled.wrappedBuffer("HELLO".getBytes(Charset.forName("utf8")))));
					// ctx.write(LastHttpContent.EMPTY_LAST_CONTENT);
					final ChannelHandlerContext ctx2 = ctx;
					new Thread() {
						public void run() {
							int m = i;
							i++;
							try {
								Thread.sleep(30000L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
							// response.headers().set(HttpHeaders.Names.CONTENT_TYPE,
							// "application/json; charset=UTF-8");
							response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, 0);
							response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
							response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
							response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_METHODS,
									"GET, POST, PUT, DELETE, HEAD, OPTIONS");
							response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_EXPOSE_HEADERS,
									"Origin, X-Requested-With, Content-Type, Accept");
							ctx2.write(response);
							String s = "HELLO WORLD!";
							if (m < 2) {
								CompositeByteBuf buf = bs[m == 0 ? 1 : 0];
								int len = 0;
								for (int i = 0; i < buf.numComponents(); i++) {
									len += buf.component(i).readableBytes();
								}
								byte[] bs = new byte[len];
								len = 0;
								for (int i = 0; i < buf.numComponents(); i++) {
									int r = buf.component(i).readableBytes();
									buf.component(i).readBytes(bs, len, r);
									buf.component(i).release();
									len += r;
								}
								s = new String(bs, Charset.forName("UTF-8"));
							}
							DefaultHttpContent dhc = new DefaultHttpContent(Unpooled.wrappedBuffer(s.getBytes()));
							ctx2.write(dhc);
							ctx2.flush();
							System.out.println("FIRST!");
							try {
								Thread.sleep(5000L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ctx2.write(LastHttpContent.EMPTY_LAST_CONTENT);
							ctx2.flush();
						}
					}.start();
					// reset();
				}
			}
		}

		protected void resolveError(ChannelHandlerContext ctx) {
			// 返回错误
			reset();
			ctx.channel().close();
		}

		protected void reset() {
			if (httpPostRequestDecoder != null) {
				try {
					httpPostRequestDecoder.destroy();
					httpPostRequestDecoder = null;
				} catch (Exception e) {
					log.warn("", e);
				}
			}
		}
	}

}
