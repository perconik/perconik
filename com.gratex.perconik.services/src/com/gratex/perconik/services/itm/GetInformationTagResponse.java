
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="GetInformationTagResult" type="{http://perconik.fiit.stuba.sk/ITM}InformationTag" minOccurs="0"/>
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
    "getInformationTagResult"
})
@XmlRootElement(name = "GetInformationTagResponse")
public class GetInformationTagResponse {

    @XmlElementRef(name = "GetInformationTagResult", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<InformationTag> getInformationTagResult;

    /**
     * Gets the value of the getInformationTagResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InformationTag }{@code >}
     *     
     */
    public JAXBElement<InformationTag> getGetInformationTagResult() {
        return getInformationTagResult;
    }

    /**
     * Sets the value of the getInformationTagResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InformationTag }{@code >}
     *     
     */
    public void setGetInformationTagResult(JAXBElement<InformationTag> value) {
        this.getInformationTagResult = value;
    }

}
