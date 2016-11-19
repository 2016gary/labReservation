package org.forten.sample.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.forten.sample.bo.LoginBo;
import org.forten.sample.vo.LoginedAdminVo;
import org.forten.sample.vo.MessageVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes({ "loginedAdmin" })
public class LoginAction {
	@Resource
	private LoginBo bo;

	@RequestMapping("login")
	public ModelAndView login(LoginedAdminVo loginedAdminVo) {
		LoginedAdminVo loginedAdmin = bo.login(loginedAdminVo);
		ModelAndView mav = new ModelAndView();
		if (loginedAdmin == null) {
			String errorMsg = "请核对用户名或密码！";
			mav.addObject("errorMsg", errorMsg);
			mav.setViewName("login.jsp");
			return mav;
		} else if (loginedAdmin.getRoleName().equals("student")) {
			mav.addObject("loginedAdmin", loginedAdmin);
			mav.setViewName("redirect:/student/laboratoryName/listForSelect.do");
			return mav;
		} else {
			mav.addObject("loginedAdmin", loginedAdmin);
			mav.setViewName("redirect:/teacher/laboratoryName/listForSelect.do");
			return mav;
		}
	}

	@RequestMapping("logout")
	public String logout(SessionStatus session) {
		session.setComplete();
		return "redirect:/index.html";
	}

	@RequestMapping("personalUpdate")
	public @ResponseBody MessageVo update(@RequestBody LoginedAdminVo vo, HttpSession session) {
		LoginedAdminVo loginedAdmin = (LoginedAdminVo) session.getAttribute("loginedAdmin");
		vo.setId(loginedAdmin.getId());
		vo.setRoleName(loginedAdmin.getRoleName());
		return bo.doUpdate(vo);
	}

	@RequestMapping("showName")
	public @ResponseBody LoginedAdminVo showName(HttpSession session) {
		return (LoginedAdminVo) session.getAttribute("loginedAdmin");

	}
}
