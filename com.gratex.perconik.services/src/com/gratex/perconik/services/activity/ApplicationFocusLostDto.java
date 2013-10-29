
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApplicationFocusLostDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplicationFocusLostDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}EventDto">
 *       &lt;sequence>
 *         &lt;element name="NewApplication" type="{http://www.gratex.com/PerConIk/IActivitySvc}ApplicationRunDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationFocusLostDto", propOrder = {
    "newApplication"
})
public class ApplicationFocusLostDto
    extends EventDto
{

    @XmlElement(name = "NewApplication")
    protected ApplicationRunDto newApplication;

    /**
     * Gets the value of the newApplication property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationRunDto }
     *     
     */
    public ApplicationRunDto getNewApplication() {
        return newApplication;
    }

    /**
     * Sets the value of the newApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationRunDto }
     *     
     */
    public void setNewApplication(ApplicationRunDto value) {
        this.newApplication = value;
    }

}
