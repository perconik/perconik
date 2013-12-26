
package com.gratex.perconik.services.uaca.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeCodeElementEventDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeCodeElementEventDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeSlnPrjEventDto">
 *       &lt;attribute name="ElementFullName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ElementType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EventType" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeCodeElementEventTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeCodeElementEventDto")
public class IdeCodeElementEventDto
    extends IdeSlnPrjEventDto
{

    @XmlAttribute(name = "ElementFullName")
    protected String elementFullName;
    @XmlAttribute(name = "ElementType")
    protected String elementType;
    @XmlAttribute(name = "EventType", required = true)
    protected IdeCodeElementEventTypeEnum eventType;

    /**
     * Gets the value of the elementFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementFullName() {
        return elementFullName;
    }

    /**
     * Sets the value of the elementFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementFullName(String value) {
        this.elementFullName = value;
    }

    /**
     * Gets the value of the elementType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementType() {
        return elementType;
    }

    /**
     * Sets the value of the elementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementType(String value) {
        this.elementType = value;
    }

    /**
     * Gets the value of the eventType property.
     * 
     * @return
     *     possible object is
     *     {@link IdeCodeElementEventTypeEnum }
     *     
     */
    public IdeCodeElementEventTypeEnum getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeCodeElementEventTypeEnum }
     *     
     */
    public void setEventType(IdeCodeElementEventTypeEnum value) {
        this.eventType = value;
    }

}
