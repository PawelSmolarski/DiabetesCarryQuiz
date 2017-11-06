/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.utils;

/**
 *
 * @author psmolarski
 */
public enum TaskType
{

    ABCD("/game/abcd.xhtml"),
    DIAGRAM("/game/diagram.xhtml"),
    PICK_LIST("/game/picklist.xhtml"),
    DRAG_DROP("/game/dragdrop.xhtml");
    
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
