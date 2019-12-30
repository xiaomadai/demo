package com.xmd.firstBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xmd.firstBoot.mapper")//用于扫描mybatis中的mapper文件，要是mapper接口的具体路径
public class DemoApplication {
	
	
	// 我们应用程序的最后一部分是main方法。这只是遵循Java约定的应用程序入口点的标准方法。
	// 我们的main方法SpringApplication通过调用委托给Spring Boot的类run。 
	// SpringApplication引导我们的应用程序，启动Spring，这反过来又启动自动配置的Tomcat Web服务器。
	// 我们需要将Example.class一个参数传递给该run方法，以判断SpringApplication哪个是主要的Spring组件。该args数组也通过传递以公开任何命令行参数。

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
