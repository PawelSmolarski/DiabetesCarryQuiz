/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.smolarski.pawel.bean.dragdroptask;

/**
 *
 * @author psmolarski
 */
public class SpecificCase
{

    /**
     * Variable contains value of specific case
     */
    private String caseValue;

    /**
     * Variable contains value of answer for specific case
     */
    private String whichAnswer;

    public SpecificCase(String caseValue, String whichAnswer)
    {
        this.caseValue = caseValue;
        this.whichAnswer = whichAnswer;
    }

    public String getCaseValue()
    {
        return caseValue;
    }

    public void setCaseValue(String caseValue)
    {
        this.caseValue = caseValue;
    }

    public String getWhichAnswer()
    {
        return whichAnswer;
    }

    public void setWhichAnswer(String whichAnswer)
    {
        this.whichAnswer = whichAnswer;
    }

}
