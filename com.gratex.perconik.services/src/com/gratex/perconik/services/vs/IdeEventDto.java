
package com.gratex.perconik.services.vs;

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
 *       &lt;attribute name="ApplicationName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ApplicationVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    IdeSlnPrjEventDto.class,
    IdeCheckinDto.class
})
public abstract class IdeEventDto
    extends EventDto
{

    @XmlAttribute(name = "IdePid", required = true)
    protected int idePid;
    @XmlAttribute(name = "ApplicationName")
    protected String applicationName;
    @XmlAttribute(name = "ApplicationVersion")
    protected String applicationVersion;

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

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the applicationVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Sets the value of the applicationVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationVersion(String value) {
        this.applicationVersion = value;
    }

}
