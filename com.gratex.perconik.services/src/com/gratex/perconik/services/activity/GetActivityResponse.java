
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetActivityResult" type="{http://www.gratex.com/PerConIk/IActivitySvc}ActivityDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getActivityResult"
})
@XmlRootElement(name = "GetActivityResponse")
public class GetActivityResponse {

    @XmlElement(name = "GetActivityResult")
    protected ActivityDto getActivityResult;

    /**
     * Gets the value of the getActivityResult property.
     * 
     * @return
     *     possible object is
     *     {@link ActivityDto }
     *     
     */
    public ActivityDto getGetActivityResult() {
        return getActivityResult;
    }

    /**
     * Sets the value of the getActivityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityDto }
     *     
     */
    public void setGetActivityResult(ActivityDto value) {
        this.getActivityResult = value;
    }

}
