package org.apache.framework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.framework.dao.BaseDao;
import org.apache.framework.domain.Pager;
import org.apache.framework.domain.RowBounds;
import org.apache.framework.service.BaseService;
import org.apache.framework.util.ListUtils;
import org.apache.framework.util.ReflectUtils;

/**
 * 基层Service的实现类，在开发中，每个模块的Service层操作都必须继承这个类
 * 
 * @author willenfoo
 * @param <M>
 * @param <E>
 * @param <GeneralDAO>
 */
public class BaseServiceImpl<M, E> implements BaseService<M, E> {

	private BaseDao<M, E> baseDao;

	public void setBaseDao(BaseDao<M, E> baseDao) {
		this.baseDao = baseDao;
	}

	private Map<String, Object> getInsertData() {
		Map<String, Object> insertData = new HashMap<String, Object>();
		insertData.put("createTime", new Date());
		insertData.put("createUserId", 101);
		return insertData;
	}

	private void setInsertData(M model) {
		Map<String, Object> insertData = getInsertData();
		for (Map.Entry<String, Object> entry : insertData.entrySet()) {
			ReflectUtils.setProperty(model, entry.getKey(), entry.getValue());
		}
	}

	private void setInsertData(List<M> list) {
		Map<String, Object> insertData = getInsertData();
		for (M m : list) {
			for (Map.Entry<String, Object> entry : insertData.entrySet()) {
				ReflectUtils.setProperty(m, entry.getKey(), entry.getValue());
			}
		}
	}
	
	private Map<String, Object> getUpdateData() {
		Map<String, Object> updateData = new HashMap<String, Object>();
		updateData.put("updateTime", new Date());
		updateData.put("updateUserId", 101);
		return updateData;
	}

	private void setUpdateData(M model) {
		Map<String, Object> updateData = getUpdateData();
		for (Map.Entry<String, Object> entry : updateData.entrySet()) {
			ReflectUtils.setProperty(model, entry.getKey(), entry.getValue());
		}
	}
	
	public final int insert(M model) {
		setInsertData(model);
		return baseDao.insert(model);
	}

	public final M selectByModel(M model) {
		return baseDao.selectByModel(model);
	}

	public final M selectById(Integer id) {
		return baseDao.selectById(id);
	}

	public final M selectById(String id) {
		return baseDao.selectById(id);
	}

	public final M selectById(Long id) {
		return baseDao.selectById(id);
	}

	public final List<M> selectByExample(E example) {
		return baseDao.selectByExample(example);
	}

	public final int countByExample(E example) {
		return baseDao.countByExample(example);
	}

	public final int deleteByExample(E example) {
		return baseDao.deleteByExample(example);
	}

	public final int deleteById(Integer id) {
		return baseDao.deleteById(id);
	}

	public final int deleteById(String id) {
		return baseDao.deleteById(id);
	}

	public final int deleteById(Long id) {
		return baseDao.deleteById(id);
	}

	public final int updateByExample(M model, E example) {
		setUpdateData(model);
		return baseDao.updateByExample(model, example);
	}

	public final int updateById(M model) {
		setUpdateData(model);
		return baseDao.updateById(model);
	}

	public final List<M> distinct(E example) {
		return baseDao.distinct(example);
	}

	public final M sum(E example) {
		List<M> list = sumList(example);
		if (ListUtils.isNotEmpty(list) && list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public final List<M> sumList(E example) {
		return baseDao.sumList(example);
	}

	public final String min(E example) {
		return baseDao.min(example);
	}

	public final String max(E example) {
		return baseDao.max(example);
	}

	public final String avg(E example) {
		return baseDao.avg(example);
	}

	public final Map<String, Object> selectByExampleForMap(E example) {
		return baseDao.selectByExampleForMap(example);
	}

	public final List<Map<String, Object>> selectByExampleForListMap(E example) {
		return baseDao.selectByExampleForListMap(example);
	}

	public final List<Map<String, Object>> selectByExampleForListMap(E example,
			Integer offset, Integer pageSize) {
		return baseDao.selectByExampleForListMap(example, new RowBounds(offset,
				pageSize));
	}

	public final Pager selectByExample(E example, Integer offset,
			Integer pageSize) {
		if (example == null || offset == null || pageSize == null) {
			throw new RuntimeException("参数不能为空!");
		}
		RowBounds rowBounds = new RowBounds(offset, pageSize);

		try {
			Object distinct = PropertyUtils.getProperty(example, "distinct");
			if ((Boolean) distinct) {
				Object column = PropertyUtils.getProperty(example, "column");
				if (column == null) {
					throw new RuntimeException("如果要设置distinct关键字,必须设置column!");
				}
			}
			PropertyUtils.setProperty(example, "offset",
					Integer.valueOf(rowBounds.getOffset()));

			PropertyUtils.setProperty(example, "pageSize",
					Integer.valueOf(rowBounds.getLimit()));

			Object orderByClauseValue = PropertyUtils.getProperty(example,
					"orderByClause");
			if (orderByClauseValue == null) {
				orderByClauseValue = " id desc ";
				PropertyUtils.setProperty(example, "orderByClause", orderByClauseValue);
				PropertyUtils.setProperty(example, "pagerKey", "id");
			} else {
				PropertyUtils.setProperty(example, "pagerKey", "other");
			}
			if (orderByClauseValue.toString().lastIndexOf("desc") > 0) {
				PropertyUtils.setProperty(example, "sortKey", "desc");
			} else {
				PropertyUtils.setProperty(example, "sortKey", "asc");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		List<M> list = baseDao.selectByExample(example);
		Pager pager = new Pager(this.countByExample(example), offset, pageSize);
		pager.setList(list);
		return pager;
	}

	public final M selectByExampleOne(E example) {
		List<M> list = selectByExample(example);
		if (list != null && !list.isEmpty()) {
			if (list.size() == 1) {
				return list.get(0);
			} else {
				throw new RuntimeException("selectByExampleOne方法得到的数据不止一条");
			}
		}
		return null;
	}

	public final int batchInsert(List<M> list) {
		setInsertData(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		int insertCount = baseDao.batchInsert(map);
		if (insertCount == list.size()) {
			Object insertId = map.get("id");
			if (insertId != null) {
				Long id = Long.valueOf(insertId.toString());
				for (int i = 0; i < insertCount; i++) {
					M m = list.get(i);
					try {
						if (PropertyUtils.isWriteable(m, "id")) {
							PropertyUtils.setProperty(m, "id", id + i);
						} else {
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return insertCount;
	}

	@Override
	public int batchUpdate(List<M> list) {
		return baseDao.batchUpdate(list);
	}

}
