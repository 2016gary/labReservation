package org.forten.sample.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.forten.sample.dao.HibernateDao;
import org.forten.sample.entity.Notebook;
import org.forten.sample.entity.NotebookResponse;
import org.forten.sample.vo.MsgVo;
import org.forten.sample.vo.NotebookVo;
import org.forten.sample.vo.NotebookVoForUpdate;
import org.forten.utils.system.BeanPropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("notebookBo")
public class NotebookBo {
	private static final Log log = LogFactory.getLog(NotebookBo.class);

	@Resource
	private HibernateDao dao;

	@Transactional
	public MsgVo doSave(Notebook nb) {
		try {
			dao.save(nb);
			return new MsgVo("留言成功");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("留言失败:" + nb.toString(), e);
			}
			return new MsgVo("留言失败");
		}
	}

	@Transactional
	public MsgVo doResponse(NotebookResponse nr) {
		try {
			dao.save(nr);
			return new MsgVo("留言成功");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("留言失败:" + nr.toString(), e);
			}
			return new MsgVo("留言失败");
		}
	}

	@Transactional(readOnly = true)
	public Map<Integer,List<NotebookVo>> queryAll() {
		String hql1 = "SELECT new org.forten.sample.vo.NotebookVo(id,title,username,contentText,createTime) "
				+ "FROM Notebook ORDER BY createTime DESC";
		List<NotebookVo> list1=dao.findBy(hql1);
		String hql2 = "SELECT new org.forten.sample.vo.NotebookVo(id,contentText,notebookId) "
				+ "FROM NotebookResponse";
		List<NotebookVo> list2=dao.findBy(hql2);
		Map<Integer,List<NotebookVo>> map=new HashMap<Integer,List<NotebookVo>>();
		map.put(0, list1);
		map.put(1, list2);
		return map;
	}

	@Transactional(readOnly = true)
	public NotebookVoForUpdate queryForUpdate(int id) {
		Notebook n = dao.findByLoad(Notebook.class, id);
		NotebookVoForUpdate vo = new NotebookVoForUpdate();
		BeanPropertyUtil.copy(vo, n);
		return vo;
	}

	@Transactional
	public MsgVo doUpdate(NotebookVoForUpdate vo) {
		Notebook n = dao.findByLoad(Notebook.class, vo.getId());
		BeanPropertyUtil.copy(n, vo);
		dao.update(n);
		return new MsgVo("修改成功");
	}

	@Transactional
	public MsgVo doDelete(int id) {
		dao.delete(Notebook.class, id);
		return new MsgVo("留言删除成功");
	}
}
