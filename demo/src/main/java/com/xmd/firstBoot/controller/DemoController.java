package com.xmd.firstBoot.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = {"/api/xmd/demo/v1/DemoController"}, produces = {"application/json;charset=UTF-8"})
@Api(tags = {"DemoController"})
public class DemoController {

	/**
	 * 使用sts上传代码到远程github账户
	 */
	//https://blog.csdn.net/o_darling/article/details/80941173
	
	/**
	 * 测试工程
	 * @return
	 */
	@GetMapping({"/demo"})
	public String demo() {
		return "demo";
	}
	
}	
