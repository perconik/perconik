
package com.gratex.perconik.services.itm;

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
 *         &lt;element name="RecoverInformationTagResult" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "recoverInformationTagResult"
})
@XmlRootElement(name = "RecoverInformationTagResponse")
public class RecoverInformationTagResponse {

    @XmlElement(name = "RecoverInformationTagResult")
    protected Boolean recoverInformationTagResult;

    /**
     * Gets the value of the recoverInformationTagResult property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecoverInformationTagResult() {
        return recoverInformationTagResult;
    }

    /**
     * Sets the value of the recoverInformationTagResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecoverInformationTagResult(Boolean value) {
        this.recoverInformationTagResult = value;
    }

}
