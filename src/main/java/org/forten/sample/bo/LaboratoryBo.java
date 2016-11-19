package org.forten.sample.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.forten.sample.dao.HibernateDao;
import org.forten.sample.dao.JDBCDao;
import org.forten.sample.entity.Laboratory;
import org.forten.sample.entity.Student;
import org.forten.sample.qo.LaboratoryQo;
import org.forten.sample.ro.RoWithPageInfo;
import org.forten.sample.vo.LaboratoryVo;
import org.forten.sample.vo.LaboratoryVoForUpdate;
import org.forten.sample.vo.MessageVo;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.utils.system.PageInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("laboratoryBo")
public class LaboratoryBo {
	private static final Logger log = Logger.getLogger(LaboratoryBo.class);

	@Resource
	private HibernateDao dao;

	@Resource
	private JDBCDao jdbcDao;

	@Transactional(readOnly = true)
	public RoWithPageInfo<LaboratoryVo> queryBy(LaboratoryQo qo) {
		String hql = "SELECT new org.forten.sample.vo.LaboratoryVo(id,laboratoryName,laboratoryAddress,orderTime,minNumber,maxNumber,classTime) "
				+ "FROM Laboratory WHERE 1=1 ";
		String countHql = "SELECT count(id) FROM Laboratory WHERE 1=1 ";
		Map<String, Object> params = new HashMap<>();
		String laboratoryName = qo.getLaboratoryName();
		Date fromOrderTime = qo.getFromOrderTime();
		Date toOrderTime = qo.getToOrderTime();
		Integer pageNo = qo.getPageNo();
		Integer pageSize = qo.getPageSize();
		if (StringUtil.hasText(laboratoryName)) {
			hql = hql + "AND laboratoryName=:laboratoryName ";
			countHql = countHql + "AND laboratoryName=:laboratoryName ";
			params.put("laboratoryName", laboratoryName);
		}
		if (fromOrderTime != null && toOrderTime != null && !fromOrderTime.after(toOrderTime)) {
			hql = hql + "AND orderTime BETWEEN :fromOrderTime AND :toOrderTime ";
			countHql = countHql + "AND orderTime BETWEEN :fromOrderTime AND :toOrderTime ";
			params.put("fromOrderTime", fromOrderTime);
			params.put("toOrderTime", toOrderTime);
		}
		long count = dao.findObjectBy(countHql, params);
		if (count == 0) {
			return new RoWithPageInfo<>();
		}
		hql = hql + " ORDER BY " + qo.getOrderByField() + " " + qo.getOrderByType();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageSize, count);
		List<LaboratoryVo> dataList = dao.findBy(hql, params, (int) pageInfo.getFirstResultNum(),
				pageInfo.getPageSize());
		return new RoWithPageInfo<>(dataList, pageInfo);
	}

	@Transactional
	public MessageVo doSave(Laboratory laboratory) {
		try {
			dao.save(laboratory);
			return new MessageVo("添加成功！");
		} catch (Exception e) {
			log.error("添加实验室信息时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("添加失败！");
		}
	}

	@Transactional
	public MessageVo doDelete(Integer... ids) {
		try {
			String hql = "DELETE FROM Laboratory WHERE id IN (:ids)";
			Map<String, Object> params = new HashMap<>();
			params.put("ids", ids);
			dao.executeUpdate(hql, params);
			return new MessageVo("删除成功！");
		} catch (Exception e) {
			log.error("删除实验室信息时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("删除失败！");
		}
	}

	@Transactional
	public MessageVo doUpdate(LaboratoryVoForUpdate vo) {
		try {
			Laboratory laboratory = dao.findByLoad(Laboratory.class, vo.getId());
			BeanPropertyUtil.copy(laboratory, vo);
			dao.update(laboratory);
			return new MessageVo("修改成功！");
		} catch (Exception e) {
			log.error("修改实验室信息时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("修改失败！");
		}
	}

	@Transactional(readOnly = true)
	public Workbook exportData() {
		String hql = "SELECT new org.forten.sample.vo.LaboratoryVo(id,laboratoryName,laboratoryAddress,orderTime,minNumber,maxNumber,classTime) "
				+ "FROM Laboratory ORDER BY id ";
		List<LaboratoryVo> voList = dao.findBy(hql);
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("实验室信息表");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("序号");
		header.createCell(1).setCellValue("实验室");
		header.createCell(2).setCellValue("实验室地点");
		header.createCell(3).setCellValue("可预约时间");
		header.createCell(4).setCellValue("人数下限");
		header.createCell(5).setCellValue("人数上限");
		header.createCell(6).setCellValue("开放时间");
		for (LaboratoryVo vo : voList) {
			Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(sheet.getLastRowNum());
			dataRow.createCell(1).setCellValue(vo.getLaboratoryName());
			dataRow.createCell(2).setCellValue(vo.getLaboratoryAddress());
			dataRow.createCell(3).setCellValue(vo.getOrderTimeStr());
			dataRow.createCell(4).setCellValue(vo.getMinNumber());
			dataRow.createCell(5).setCellValue(vo.getMaxNumber());
			dataRow.createCell(6).setCellValue(vo.getClassTime());
		}
		return wb;
	}

	@Transactional(readOnly = true)
	public Workbook exportForStudent(int id) {
		String sql = "SELECT email,gender,number,name FROM test_student WHERE id IN (SELECT student_id FROM test_student_laboratory_relation WHERE laboratory_id=:id)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		List<Student> studentList = jdbcDao.findBy(sql, params, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setEmail(rs.getString("email"));
				student.setGender(rs.getString("gender"));
				student.setName(rs.getString("name"));
				student.setNumber(rs.getString("number"));
				return student;
			}
		});
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("预约学生信息表");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("序号");
		header.createCell(1).setCellValue("姓名");
		header.createCell(2).setCellValue("学号");
		header.createCell(3).setCellValue("性别");
		header.createCell(4).setCellValue("邮箱");
		for (Student student : studentList) {
			Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(sheet.getLastRowNum());
			dataRow.createCell(1).setCellValue(student.getName());
			dataRow.createCell(2).setCellValue(student.getNumber());
			dataRow.createCell(3).setCellValue(student.getGender());
			dataRow.createCell(4).setCellValue(student.getEmail());
		}
		return wb;
	}

	@Transactional(readOnly = true)
	public List<Integer> listForEcharts() {
		String name;
		String sql = "SELECT count(id) FROM test_student_laboratory_relation WHERE laboratory_id IN (SELECT id FROM test_laboratory WHERE laboratory_name=:name)";
		List<Integer> counts = new ArrayList<Integer>();
		int count;
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				name = "单片机实验室";
			} else if (i == 1) {
				name = "嵌入式实验室";
			} else {
				name = "电机拖动实验室";
			}
			Map<String, Object> params = new HashMap<>();
			params.put("name", name);
			count = jdbcDao.findSingleObjectBy(sql, params, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt("count(id)");
				}
			});
			counts.add(count);
		}
		return counts;
	}

	@Transactional(readOnly = true)
	public List<Student> studentList(int id) {
		String sql = "SELECT email,gender,number,name FROM test_student WHERE id IN (SELECT student_id FROM test_student_laboratory_relation WHERE laboratory_id=:id AND laboratoryOrderStatus=2)";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbcDao.findBy(sql, params, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setEmail(rs.getString("email"));
				student.setGender(rs.getString("gender"));
				student.setName(rs.getString("name"));
				student.setNumber(rs.getString("number"));
				return student;
			}
		});
	}
}
