package org.forten.sample.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.forten.sample.bo.LaboratoryNameBo;
import org.forten.sample.entity.LaboratoryName;
import org.forten.sample.ro.RoForLaboratoryName;
import org.forten.sample.vo.LaboratoryNameVo;
import org.forten.sample.vo.LoginedAdminVo;
import org.forten.sample.vo.MessageVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teacher/laboratoryName")
public class LaboratoryNameAction {
	@Resource(name = "laboratoryNameBo")
	private LaboratoryNameBo bo;

	@RequestMapping("list")
	public @ResponseBody RoForLaboratoryName list(HttpSession session) {
		List<LaboratoryNameVo> nameVoList = bo.queryAll();
		LoginedAdminVo loginedAdmin = (LoginedAdminVo) session.getAttribute("loginedAdmin");
		return new RoForLaboratoryName(nameVoList, loginedAdmin);
	}

	@RequestMapping("listForSelect")
	public ModelAndView listForSelect(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<LaboratoryNameVo> nameVoList = bo.queryAll();
		mav.addObject("nameVoList", nameVoList);
		mav.setViewName("/teacher/teacher.jsp");
		return mav;
	}

	@RequestMapping("save")
	public @ResponseBody MessageVo save(@RequestBody LaboratoryName laboratoryName) {
		return bo.doSave(laboratoryName);
	}

	@RequestMapping("delete")
	public @ResponseBody MessageVo delete(@RequestBody Integer... ids) {
		return bo.doDelete(ids);
	}

	@RequestMapping("update")
	public @ResponseBody MessageVo update(@RequestBody LaboratoryNameVo vo) {
		return bo.doUpdate(vo);
	}

	@RequestMapping("export")
	public void export(HttpServletResponse response) {
		try (OutputStream out = response.getOutputStream(); Workbook wb = bo.exportData()) {
			response.setContentType("application/x-msexcel");
			response.setHeader("Content-Disposition", "attachment;filename=laboratoryName.xls");
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
