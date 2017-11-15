/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.utils;

/**
 * Interface to get type of specific task
 * 
 * @author psmolarski
 */
public interface Taskable
{

    /**
     * Get type of specific task
     * 
     * @return type of task
     */
    TaskType getType();
}
