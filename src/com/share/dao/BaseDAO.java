package com.share.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
	/**
	 * ����һ������
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * ɾ��һ������
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * ����һ������
	 * 
	 * @param o
	 */
	public void update(T o);
	
	/**
	 * ����
	 * 
	 * @param hql
	 * @return
	 */
	public int update(String hql, Object[] param);

	/**
	 * �������¶���
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * ��ѯ
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);
	
	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @param fr
	 * @param maxr
	 * @return
	 */
	public List<T> find_top(String hql, Object[] param, int fr, int maxr);

	/**
	 * ��ѯ����(����ҳ)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            ��ѯ�ڼ�ҳ
	 * @param rows
	 *            ÿҳ��ʾ������¼
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * ��ѯ����(����ҳ)
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
	 * ���һ������
	 * 
	 * @param c
	 *            ��������
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * ���һ������
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * ���һ������
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @return ��Ӧ��Ŀ
	 */
	public Integer executeHql(String hql);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param param
	 * @return ��Ӧ��Ŀ
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * ִ��ԭ��sQL���
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Object> findJDBCSql(String sql,  List<Object> param);
	
	/**
	 * ִ��ԭ��sQL���
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Object> findJDBCSql(String sql,  Object[] param);

	/**
	 * ִ��ԭ��sQL��� ��ѯ����
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	
	public List<Object[]> findJDBCSql(String sql,  Object[] param, Object[] clz);
	
	/**
	 * ִ��ԭ��sQL��� ��ѯ����
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	
	public List<T> findJDBCSql(String sql,  Object[] param, Class<T> clz);
	/**
	 * ִ��ԭ��sQL��� ��ѯ����
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	
	public Long countJDBCsql(String sql,  List<Object> param);
	/**
	 * ִ��ԭ��sQL��� ��ѯ����
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * 
	 */
	public Long countJDBCsql(String sql, Object[] param);
}
