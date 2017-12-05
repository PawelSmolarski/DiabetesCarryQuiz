/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.dao;

import java.util.List;

/**
 * Repository for CRUD operation for tasks by Hibernate.
 *
 * @author psmolarski
 * @version 1.0
 * @param <T> type of task
 */
public interface TaskRepository<T>
{

    /**
     * Method to add task to table
     *
     * @param task to add
     *
     */
    void addTask(T task);

    /**
     * Method to delete task to table
     *
     * @param id to delete
     *
     */
    void deleteTask(int id);

    /**
     * Method to get tasks from table
     *
     * @return List of get tasks
     *
     */
    List<T> retrieveTask();

    /**
     * Method to update task
     *
     * @param task to update
     *
     */
    void updateTask(T task);

}
