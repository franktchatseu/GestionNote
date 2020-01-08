package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller


@RequestMapping("content")public class ContentControlle {

	   @RequestMapping("")     public String loadContent() {         return "ajax1"; 
	    
	    }

	    @RequestMapping("content1")     public String getContent1() {         return "ajax :: content1";

	}

	    @RequestMapping("content2")     public String getContent2() {         return "ajax :: content2";

	}
	
}
