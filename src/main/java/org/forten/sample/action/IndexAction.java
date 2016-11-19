package org.forten.sample.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.forten.sample.bo.IndexBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexAction {
	@Resource
	private IndexBo bo;

	@RequestMapping("orderTimesForIndex")
	public @ResponseBody Map<Integer, List<String>> orderTimesForIndex() {
		return bo.orderTimesForIndex();
	}
}
