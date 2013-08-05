
package com.gratex.perconik.iactivitysvc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeEventDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeEventDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}EventDto">
 *       &lt;attribute name="IdePid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeEventDto")
@XmlSeeAlso({
    IdeCheckinDto.class,
    IdeSlnPrjEventDto.class
})
public abstract class IdeEventDto
    extends EventDto
{

    @XmlAttribute(name = "IdePid", required = true)
    protected int idePid;

    /**
     * Gets the value of the idePid property.
     * 
     */
    public int getIdePid() {
        return idePid;
    }

    /**
     * Sets the value of the idePid property.
     * 
     */
    public void setIdePid(int value) {
        this.idePid = value;
    }

}
