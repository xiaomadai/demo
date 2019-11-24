package com.xmd.firstBoot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = {"/api/xmd/demo/v1/DemoController"}, produces = {"application/json;charset=UTF-8"})
public class DemoController {
	
	/**
	 * 测试工程
	 * @return
	 */
	@GetMapping({"/demo"})
	public String demo() {
		return "demo";
	}
	
}	
