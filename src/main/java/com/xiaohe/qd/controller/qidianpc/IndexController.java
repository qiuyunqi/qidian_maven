package com.xiaohe.qd.controller.qidianpc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class IndexController {

	
	@RequestMapping("/index.html")
	public String index() {
		return "qdWeb/index";
	}
}
