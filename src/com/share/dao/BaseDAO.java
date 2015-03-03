package com.share.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);
	
	/**
	 * 更新
	 * 
	 * @param hql
	 * @return
	 */
	public int update(String hql, Object[] param);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);
	
	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @param fr
	 * @param maxr
	 * @return
	 */
	public List<T> find_top(String hql, Object[] param, int fr, int maxr);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行原生sQL语句
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Object> findJDBCSql(String sql,  List<Object> param);
	
	/**
	 * 执行原生sQL语句
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Object> findJDBCSql(String sql,  Object[] param);

	/**
	 * 执行原生sQL语句 查询总数
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	
	public List<Object[]> findJDBCSql(String sql,  Object[] param, Object[] clz);
	
	/**
	 * 执行原生sQL语句 查询总数
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	
	public List<T> findJDBCSql(String sql,  Object[] param, Class<T> clz);
	/**
	 * 执行原生sQL语句 查询总数
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	
	public Long countJDBCsql(String sql,  List<Object> param);
	/**
	 * 执行原生sQL语句 查询总数
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	public Long countJDBCsql(String sql, Object[] param);
}
