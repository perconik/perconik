
package com.gratex.perconik.iactivitysvc;

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
 *         &lt;element name="StateType" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdeStateTypeEnum"/>
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

    @XmlElement(name = "StateType", required = true)
    protected IdeStateTypeEnum stateType;

    /**
     * Gets the value of the stateType property.
     * 
     * @return
     *     possible object is
     *     {@link IdeStateTypeEnum }
     *     
     */
    public IdeStateTypeEnum getStateType() {
        return stateType;
    }

    /**
     * Sets the value of the stateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdeStateTypeEnum }
     *     
     */
    public void setStateType(IdeStateTypeEnum value) {
        this.stateType = value;
    }

}
