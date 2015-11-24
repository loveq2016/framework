package org.apache.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
 
/**
 * Dao层基接口，数据访问逻辑.这里只提供基本的操作增、删、查、改和分页的方法
 * @author willenfoo
 *
 * @param <M>
 * @param <E>
 */
public interface BaseDao<M,E>  {

	/**
	 * 统计总行数
	 * @param example
	 * @return
	 */
	int countByExample(E example);

	/**
	 * 动态删除数据，通过example工具类
	 * @param example
	 * @return
	 */
	int deleteByExample(E example);

	/**
	 * 通过ID删除model
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 通过ID删除model
	 * @param id
	 * @return
	 */
	int deleteById(String userId);
	
	/**
	 * 通过ID删除model
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 动态插入model
	 * @param model
	 * @return
	 */
	int insert(M record);

	/**
	 * 动态查询数据，返回list，把要查询的条件放入到example中
	 * 
	 * @param example
	 * @return
	 */
	List<M> selectByExample(E example);

	/**
	 * 通过ID查询model
	 * @param id
	 * @return
	 */
	M selectById(Integer Id);
	
	/**
	 * 通过ID查询model
	 * @param id
	 * @return
	 */
	M selectById(String Id);
	
	/**
	 * 通过ID查询model
	 * @param id
	 * @return
	 */
	M selectById(Long Id);

	/**
	 * 动态更新数据，
	 * 
	 * @param model
	 * @param example
	 * @return
	 */
	int updateByExample(@Param("record") M record,
			@Param("example") E example);

	/**
	 * 通过id更新数据
	 * 
	 * @param model
	 * @return
	 */
	int updateById(M record);

	/**
	 * 动态查询model，把要查询的条件放入到model中
	 * @param model
	 * @return
	 */
	M selectByModel(M user);

	/**
	 * 动态查询数据，返回list，把要查询的条件放入到example中
	 * @param example
	 * @return
	 */
	List<M> selectByExample(E example, RowBounds row);

	/**
	 * 去除重复行
	 * 
	 * @param example
	 * @return
	 */
	List<M> distinct(E example);

	/**
	 * 得到数据相加值
	 * @param example
	 * @return
	 */
	List<M> sumList(E example);

	/**
	 * 得到最小值
	 * 
	 * @param example
	 * @return
	 */
	String min(E example);

	/**
	 * 得到最大值
	 * 
	 * @param example
	 * @return
	 */
	String max(E example);

	/**
	 * 得到平均值
	 * 
	 * @param example
	 * @return
	 */
	String avg(E example);

	/**
	 * 查询多条数据，以map返回
	 * @param example
	 * @return
	 */
	Map<String, Object> selectByExampleForMap(E example);
	
	/**
	 * 查询多条数据，以map返回
	 * @param example
	 * @return
	 */
	List<Map<String, Object>> selectByExampleForListMap(E example);
	
	/**
	 * 查询多条数据，以map返回,支持分页
	 * @param example
	 * @return
	 */
	List<Map<String, Object>> selectByExampleForListMap(E example,RowBounds row);
	
	
	/**
	 * 批量插入数据
	 * @param models
	 * @return
	 */
	int batchInsert(Map<String, Object> map);

	/**
	 * 批量更新数据
	 * @param models
	 * @return
	 */
	int batchUpdate(List<M> list);
	 
}
