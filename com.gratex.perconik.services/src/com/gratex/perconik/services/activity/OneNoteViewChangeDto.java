
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OneNoteViewChangeDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OneNoteViewChangeDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteEventDto">
 *       &lt;sequence>
 *         &lt;element name="IsSideNote" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ViewType" type="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteViewTypeEnum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OneNoteViewChangeDto", propOrder = {
    "isSideNote",
    "viewType"
})
public class OneNoteViewChangeDto
    extends OneNoteEventDto
{

    @XmlElement(name = "IsSideNote")
    protected boolean isSideNote;
    @XmlElement(name = "ViewType", required = true)
    protected OneNoteViewTypeEnum viewType;

    /**
     * Gets the value of the isSideNote property.
     * 
     */
    public boolean isIsSideNote() {
        return isSideNote;
    }

    /**
     * Sets the value of the isSideNote property.
     * 
     */
    public void setIsSideNote(boolean value) {
        this.isSideNote = value;
    }

    /**
     * Gets the value of the viewType property.
     * 
     * @return
     *     possible object is
     *     {@link OneNoteViewTypeEnum }
     *     
     */
    public OneNoteViewTypeEnum getViewType() {
        return viewType;
    }

    /**
     * Sets the value of the viewType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OneNoteViewTypeEnum }
     *     
     */
    public void setViewType(OneNoteViewTypeEnum value) {
        this.viewType = value;
    }

}
