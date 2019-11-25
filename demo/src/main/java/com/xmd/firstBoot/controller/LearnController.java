package com.xmd.firstBoot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class LearnController {
	
	/**
	 *  
	 * @RestController:(第一个类级别的注释)被称为构造型注释,它为人们阅读代码提供了提示，对于Spring来说，类扮演了特定角色。在这种情况下，我们的类是web @Controller，因此Spring在处理传入的Web请求时会考虑使用它
	 * 
	 * @RequestMapping:它告诉Spring任何具有 '/helloWorld' 路径的HTTP请求都应映射到该home方法。该@RestController注解告诉Spring使得到的字符串直接返回给调用者
	 * 
	 * @EnableAutoConfiguration:(第二个类级别的注释) 告诉Spring Boot根据所添加的jar依赖关系“猜测”您如何配置Spring。由于spring-boot-starter-web添加了Tomcat和Spring MVC，因此自动配置假定您正在开发Web应用程序并相应地设置Spring。
	 */
	
	//启动器和自动配置
	//自动配置旨在与“启动器”配合使用，但是这两个概念并没有直接联系在一起。您可以在启动程序之外自由选择jar依赖项。Spring Boot仍会尽其所能自动配置您的应用程序。
	
	
	@RequestMapping("/helloWorld")
    String home() {
        return "Hello World!";
    }
	
	
	
}
