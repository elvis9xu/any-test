在pom.xml中加入以下配置
			<plugin>
				<groupId>org.mortbay.jetty</groupId>
				<artifactId>maven-jetty-plugin</artifactId>
				<version>6.1.26</version>
				<configuration>
					<useTestClasspath>true</useTestClasspath>
					<webAppSourceDirectory>${basedir}/src/main/resources/com/xjd/test/jsonrpc4j/server</webAppSourceDirectory>
					<webXml>${basedir}/src/main/resources/com/xjd/test/jsonrpc4j/server/web.xml</webXml>
				</configuration>
			</plugin>
			
运行命令: mvn jetty:run

运行各客户端程序查看效果