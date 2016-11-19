package org.forten.sample.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.forten.sample.dao.JDBCDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("indexBo")
public class IndexBo {
	@Resource
	private JDBCDao dao;

	@Transactional(readOnly = true)
	public Map<Integer, List<String>> orderTimesForIndex() {
		String name;
		String sql = "SELECT order_time FROM test_laboratory WHERE laboratory_name=:n";
		Map<Integer, List<String>> results = new HashMap<>();
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				name = "单片机实验室";
			} else if (i == 1) {
				name = "嵌入式实验室";
			} else {
				name = "电机拖动实验室";
			}
			Map<String, Object> params = new HashMap<>();
			params.put("n", name);
			List<String> orderTimes = dao.findBy(sql, params, new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String orderTime = rs.getString("order_time");
					String year = orderTime.split(" ")[0].split("-")[0];
					String month = orderTime.split(" ")[0].split("-")[1];
					Calendar calendar = Calendar.getInstance();
					String localYear = String.valueOf(calendar.get(Calendar.YEAR));
					String localMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
					if (localMonth.length() == 1) {
						localMonth = "0"+localMonth;
					}
					if (year.equals(localYear) && month.equals(localMonth)) {
						String result=orderTime.split(" ")[0].split("-")[2];
						if(result.subSequence(0, 1).equals("0")){
							result=result.substring(1,result.length());
						}
						return result;
					}
					return "";
				}
			});
			results.put(i, orderTimes);
		}
		return results;
	}
}
