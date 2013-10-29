
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RunningApplicationsListDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RunningApplicationsListDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}StateDto">
 *       &lt;sequence>
 *         &lt;element name="Items" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfApplicationStateDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RunningApplicationsListDto", propOrder = {
    "items"
})
public class RunningApplicationsListDto
    extends StateDto
{

    @XmlElement(name = "Items")
    protected ArrayOfApplicationStateDto items;

    /**
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfApplicationStateDto }
     *     
     */
    public ArrayOfApplicationStateDto getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfApplicationStateDto }
     *     
     */
    public void setItems(ArrayOfApplicationStateDto value) {
        this.items = value;
    }

}
