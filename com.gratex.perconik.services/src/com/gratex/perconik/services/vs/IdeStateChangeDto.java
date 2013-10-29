
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeStateChangeDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeStateChangeDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeSlnPrjEventDto">
 *       &lt;sequence>
 *         &lt;element name="StateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeStateChangeDto", propOrder = {
    "stateType"
})
public class IdeStateChangeDto
    extends IdeSlnPrjEventDto
{

    @XmlElement(name = "StateType")
    protected String stateType;

    /**
     * Gets the value of the stateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateType() {
        return stateType;
    }

    /**
     * Sets the value of the stateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateType(String value) {
        this.stateType = value;
    }

}
