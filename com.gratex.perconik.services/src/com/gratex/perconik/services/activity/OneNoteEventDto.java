
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OneNoteEventDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OneNoteEventDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}EventDto">
 *       &lt;attribute name="Hwnd" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OneNoteEventDto")
@XmlSeeAlso({
    OneNoteNavigateDto.class,
    OneNoteViewChangeDto.class
})
public abstract class OneNoteEventDto
    extends EventDto
{

    @XmlAttribute(name = "Hwnd", required = true)
    protected int hwnd;

    /**
     * Gets the value of the hwnd property.
     * 
     */
    public int getHwnd() {
        return hwnd;
    }

    /**
     * Sets the value of the hwnd property.
     * 
     */
    public void setHwnd(int value) {
        this.hwnd = value;
    }

}
