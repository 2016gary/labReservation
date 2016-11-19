package org.forten.sample.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.forten.sample.bo.LaboratoryBo;
import org.forten.sample.entity.Laboratory;
import org.forten.sample.entity.Student;
import org.forten.sample.qo.LaboratoryQo;
import org.forten.sample.ro.RoWithPageInfo;
import org.forten.sample.vo.LaboratoryVo;
import org.forten.sample.vo.LaboratoryVoForUpdate;
import org.forten.sample.vo.LoginedAdminVo;
import org.forten.sample.vo.MessageVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class LaboratoryAction {
	@Resource
	private LaboratoryBo bo;

	@RequestMapping("listForEcharts")
	public @ResponseBody List<Integer> listForEcharts() {
		return bo.listForEcharts();
	}

	@RequestMapping("studentList")
	public @ResponseBody List<Student> studentList(HttpServletRequest request) {
		int id = Integer.valueOf(request.getParameter("id"));
		return bo.studentList(id);
	}

	@RequestMapping("list")
	public @ResponseBody RoWithPageInfo<LaboratoryVo> list(@RequestBody LaboratoryQo qo, HttpSession session) {
		LoginedAdminVo loginedAdmin = (LoginedAdminVo) session.getAttribute("loginedAdmin");
		RoWithPageInfo<LaboratoryVo> ro = bo.queryBy(qo);
		ro.setLoginedAdmin(loginedAdmin);
		return ro;
	}

	@RequestMapping("save")
	public @ResponseBody MessageVo save(@RequestBody Laboratory laboratory) {
		return bo.doSave(laboratory);
	}

	@RequestMapping("delete")
	public @ResponseBody MessageVo delete(@RequestBody Integer... ids) {
		return bo.doDelete(ids);
	}

	@RequestMapping("update")
	public @ResponseBody MessageVo update(@RequestBody LaboratoryVoForUpdate vo) {
		return bo.doUpdate(vo);
	}

	@RequestMapping("export")
	public void export(HttpServletResponse response) {
		try (OutputStream out = response.getOutputStream(); Workbook wb = bo.exportData()) {
			response.setContentType("application/x-msexcel");
			response.setHeader("Content-Disposition", "attachment;filename=laboratory.xls");
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("exportForStudent")
	public void exportForStudent(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.valueOf(request.getParameter("id"));
		try (OutputStream out = response.getOutputStream(); Workbook wb = bo.exportForStudent(id)) {
			response.setContentType("application/x-msexcel");
			response.setHeader("Content-Disposition", "attachment;filename=student.xls");
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
