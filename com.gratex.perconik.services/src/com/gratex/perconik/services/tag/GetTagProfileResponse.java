
package com.gratex.perconik.services.tag;

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
 *         &lt;element name="GetTagProfileResult" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}GetTagProfileResponse" minOccurs="0"/>
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
    "getTagProfileResult"
})
@XmlRootElement(name = "GetTagProfileResponse")
public class GetTagProfileResponse {

    @XmlElement(name = "GetTagProfileResult")
    protected GetTagProfileResponse2 getTagProfileResult;

    /**
     * Gets the value of the getTagProfileResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetTagProfileResponse2 }
     *     
     */
    public GetTagProfileResponse2 getGetTagProfileResult() {
        return getTagProfileResult;
    }

    /**
     * Sets the value of the getTagProfileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTagProfileResponse2 }
     *     
     */
    public void setGetTagProfileResult(GetTagProfileResponse2 value) {
        this.getTagProfileResult = value;
    }

}
