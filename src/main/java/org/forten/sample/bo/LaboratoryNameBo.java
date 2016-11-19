package org.forten.sample.bo;

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
import org.forten.sample.entity.LaboratoryName;
import org.forten.sample.vo.LaboratoryNameVo;
import org.forten.sample.vo.MessageVo;
import org.forten.utils.system.BeanPropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("laboratoryNameBo")
public class LaboratoryNameBo {
	private static final Logger log = Logger.getLogger(LaboratoryNameBo.class);

	@Resource
	private HibernateDao dao;

	@Transactional(readOnly = true)
	public List<LaboratoryNameVo> queryAll() {
		String hql = "SELECT new org.forten.sample.vo.LaboratoryNameVo(id,laboratoryName) " + "FROM LaboratoryName ";
		return dao.findBy(hql);
	}

	@Transactional
	public MessageVo doSave(LaboratoryName laboratoryName) {
		try {
			dao.save(laboratoryName);
			return new MessageVo("添加成功！");
		} catch (Exception e) {
			log.error("添加实验室时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("添加失败！");
		}
	}

	@Transactional
	public MessageVo doDelete(Integer... ids) {
		try {
			String hql = "DELETE FROM LaboratoryName WHERE id IN (:ids)";
			Map<String, Object> params = new HashMap<>();
			params.put("ids", ids);
			dao.executeUpdate(hql, params);
			return new MessageVo("删除成功！");
		} catch (Exception e) {
			log.error("删除实验室时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("删除失败！");
		}
	}

	@Transactional
	public MessageVo doUpdate(LaboratoryNameVo vo) {
		try {
			LaboratoryName laboratoryName = dao.findByLoad(LaboratoryName.class, vo.getId());
			BeanPropertyUtil.copy(laboratoryName, vo);
			dao.update(laboratoryName);
			return new MessageVo("修改成功！");
		} catch (Exception e) {
			log.error("修改实验室时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("修改失败！");
		}
	}

	@Transactional(readOnly = true)
	public Workbook exportData() {
		String hql = "SELECT new org.forten.sample.vo.LaboratoryNameVo(id,laboratoryName) "
				+ "FROM LaboratoryName ORDER BY id ";
		List<LaboratoryNameVo> voList = dao.findBy(hql);
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("实验室列表");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("序号");
		header.createCell(1).setCellValue("实验室名称");
		for (LaboratoryNameVo vo : voList) {
			Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(sheet.getLastRowNum());
			dataRow.createCell(1).setCellValue(vo.getLaboratoryName());
		}
		return wb;
	}
}
