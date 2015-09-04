/**
 * 
 */
package com.sjsu.quizzme.dao;

import java.util.List;

import com.sjsu.quizzme.model.Result;

/**
 * @author Maitreyee
 *
 */
public interface IQuizzMeDao<T> {
	
	/**
	 * Add a new entity
	 * @param entity: generic class
	 * @return: object with entity added and the result code
	 */
	public Result<T> add(T entity);
	/**
	 * Update an entity
	 * @param entity
	 * @return: object with entity updated and the result code
	 */
	public T update(T entity);
	/**
	 * Delete an entity
	 * @param id
	 * @return: object with the deleted entity and the result code
	 */
	public Result<T> delete(int id);
	/**
	 * Get a certain entity
	 * @param id
	 * @return: entity with the primary key
	 */
	public T getEntity(int id);
	/**
	 * Returns the list of requested entities
	 * @return
	 */
	public List<T> getList();

}
