package org.forten.sample.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.forten.sample.aspect.SwitchStatus;
import org.forten.sample.bo.LaboratoryNameBo;
import org.forten.sample.bo.StudentBo;
import org.forten.sample.entity.Student;
import org.forten.sample.entity.StudentLaboratoryRelation;
import org.forten.sample.vo.LaboratoryNameVo;
import org.forten.sample.vo.LoginedAdminVo;
import org.forten.sample.vo.MessageVo;
import org.forten.sample.vo.StudentLaboratoryRelationVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class StudentAction {
	@Resource(name = "studentBo")
	private StudentBo bo;
	
	@Resource(name = "laboratoryNameBo")
	private LaboratoryNameBo laboratoryNameBo;
	
	@RequestMapping("student/laboratoryName/listForSelect")
	public ModelAndView listForSelect(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<LaboratoryNameVo> nameVoList = laboratoryNameBo.queryAll();
		mav.addObject("nameVoList", nameVoList);
		mav.setViewName("/student/student.jsp");
		return mav;
	}
	
	@RequestMapping("register")
	public String register(Student student) {
		bo.register(student);
		return "redirect:/index.html";
	}

	@RequestMapping("student/save")
	public @ResponseBody MessageVo save(@RequestBody StudentLaboratoryRelation studentLaboratoryRelation,
			HttpSession session) {
		LoginedAdminVo loginedAdmin = (LoginedAdminVo) session.getAttribute("loginedAdmin");
		studentLaboratoryRelation.setStudentId(loginedAdmin.getId());
		return bo.doSave(studentLaboratoryRelation);
	}

	@RequestMapping("student/delete")
	public @ResponseBody MessageVo delete(@RequestBody StudentLaboratoryRelation studentLaboratoryRelation) {
		return bo.doDelete(studentLaboratoryRelation.getId());
	}

	@SwitchStatus
	@RequestMapping("student/list")
	public @ResponseBody List<StudentLaboratoryRelationVo> list(HttpSession session) {
		LoginedAdminVo loginedAdmin = (LoginedAdminVo) session.getAttribute("loginedAdmin");
		return bo.queryAll(loginedAdmin.getId());
	}

}
