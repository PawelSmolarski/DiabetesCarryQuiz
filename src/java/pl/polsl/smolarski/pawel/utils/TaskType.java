/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.utils;

/**
 * Enum class with types of tasks
 * 
 * @author psmolarski
 * @version 1.0
 */
public enum TaskType
{

    ABCD("/game/abcd.xhtml"),
    DIAGRAM("/game/diagram.xhtml"),
    PICK_LIST("/game/picklist.xhtml"),
    DRAG_DROP("/game/dragdrop.xhtml");
    
    /**
     * URL variable to view of specific task
     */
    private final String URL;

    TaskType(String URL)
    {
        this.URL = URL;
    }

    public String getURL()
    {
        return URL;
    }

}
