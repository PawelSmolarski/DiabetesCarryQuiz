/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author psmolarski
 */
public class ModelUtils
{

    /**
     * Static method for checking if two list are equal
     *
     * @param one
     * @param two
     * @return
     */
    public static boolean areEqualLists(List<String> one, List<String> two)
    {

        if (one.size() != two.size())
        {
            return false;
        }

        one = new ArrayList<String>(one);
        two = new ArrayList<String>(two);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

}
