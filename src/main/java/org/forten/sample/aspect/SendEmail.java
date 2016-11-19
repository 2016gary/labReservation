package org.forten.sample.aspect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.forten.sample.dao.JDBCDao;
import org.forten.sample.util.PropertiesFileReader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("sendEmail")
public class SendEmail {
	private static final PropertiesFileReader reader = new PropertiesFileReader("email");
	private static final String HOST_NAME = reader.getString("HOST_NAME");
	private static final int PORT = reader.getInt("PORT");
	private static final String USER=reader.getString("USER");
	private static final String PASSWORD=reader.getString("PASSWORD");
	private static final boolean SSL_CONNECT = reader.getBoolean("SSL_CONNECT");
	private static final String FROM_ADDRESS = reader.getString("FROM_ADDRESS");
	private static final String NICKNAME = reader.getString("NICKNAME");
	
	@Resource
	private JDBCDao jdbcDao;
	
	public static void send(String subject, String msg, String to) {
		try {
			// 实例化一个简单的电子邮件对象
			Email email = new SimpleEmail();
			// 设置SMTP服务器的地址（邮件发送服务器）
			email.setHostName(HOST_NAME);
			// SMTP服务器使用的端口号
			email.setSmtpPort(PORT);
			// 邮箱的认证（用户名和密码）
			email.setAuthenticator(new DefaultAuthenticator(USER, PASSWORD));
			// 设置是否使用安全套接字连接
			email.setSSLOnConnect(SSL_CONNECT);
			// 设置发信人的邮件地址
			email.setFrom(FROM_ADDRESS, NICKNAME);
			// 设置邮件标题
			email.setSubject(subject);
			// 邮件内容
			email.setMsg(msg);
			// 收信人Email地址
			email.addTo(to);
			// 执行发送
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron="00 22 09 14 6 ?")
	public void sendEmail() {
		String subject = "您预约的实验室状态通知";
		String msg1 = "对不起，您预约的实验室所预约的时间当前人数未满，暂不能为您开放实验室学习，请您登录预约系统将状态为“已预约”的预约先进行退选操作，然后再预约其他时间段，感谢您使用中北大学实验室预约系统，本邮件仅用于通知请勿回复。";
		String msg2 = "对不起，您预约的实验室所预约的时间当前人数已满，为不影响您的学习，请您登录预约系统将状态为“排队中”的预约先进行退选操作，然后再预约其他时间段，感谢您使用中北大学实验室预约系统，本邮件仅用于通知请勿回复。";
		for (int i = 0; i < 2; i++) {
			int laboratoryOrderStatus=i;
			String sql = "SELECT student_id FROM test_student_laboratory_relation WHERE laboratoryOrderStatus=:laboratoryOrderStatus";
			Map<String, Object> params1 = new HashMap<>();
			params1.put("laboratoryOrderStatus", laboratoryOrderStatus);
			List<Integer> studentIdList = jdbcDao.findBy(sql, params1, new RowMapper<Integer>() {
				
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					int studentId = rs.getInt("student_id");
					return studentId;
				}
			});
			for (Integer studentId : studentIdList) {
				String sql2 = "SELECT email FROM test_student WHERE id=:studentId";
				Map<String, Object> params2 = new HashMap<>();
				params2.put("studentId", studentId);
				String email = jdbcDao.findSingleObjectBy(sql2, params2, new RowMapper<String>() {
					
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("email");
					}
				});
				if(laboratoryOrderStatus==0){
					send(subject, msg1, email);
				}else{
					send(subject, msg2, email);
				}
			}
		}
	}
}
