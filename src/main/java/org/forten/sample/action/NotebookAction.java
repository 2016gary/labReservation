package org.forten.sample.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.forten.sample.bo.NotebookBo;
import org.forten.sample.entity.Notebook;
import org.forten.sample.entity.NotebookResponse;
import org.forten.sample.vo.MsgVo;
import org.forten.sample.vo.NotebookVo;
import org.forten.sample.vo.NotebookVoForUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class NotebookAction {
	@Resource
	private NotebookBo bo;

	@RequestMapping("save")
	public @ResponseBody MsgVo save(@RequestBody Notebook nb) {
		MsgVo vo = bo.doSave(nb);
		return vo;
	}

	@RequestMapping("response")
	public @ResponseBody MsgVo response(@RequestBody NotebookResponse nr) {
		MsgVo vo = bo.doResponse(nr);
		return vo;
	}

	@RequestMapping("list")
	public @ResponseBody Map<Integer,List<NotebookVo>> list() {
		return bo.queryAll();
	}

	@RequestMapping("gotoUpdate")
	public @ResponseBody NotebookVoForUpdate gotoUpdate(int id) {
		return bo.queryForUpdate(id);
	}

	@RequestMapping("update")
	public @ResponseBody MsgVo update(@RequestBody NotebookVoForUpdate vo) {
		return bo.doUpdate(vo);
	}

	@RequestMapping("delete")
	public @ResponseBody MsgVo delete(int id) {
		MsgVo vo = bo.doDelete(id);
		return vo;
	}

}
