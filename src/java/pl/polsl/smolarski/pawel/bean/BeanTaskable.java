/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean;

/**
 *
 * @author psmolarski
 */
public interface BeanTaskable<T>
{

    /**
     * Clearing local task
     */
    void clearTask();

    /**
     * Method which use DAO to delete task
     *
     * @param task to delete
     */
    void delete(T task);

    /**
     * Method which use DAO to save task
     *
     * @param task to save
     */
    void save(T task);

    /**
     * Method to update present task
     */
    void update();

    /**
     * Method validation of user choose
     */
    void validate();

}
