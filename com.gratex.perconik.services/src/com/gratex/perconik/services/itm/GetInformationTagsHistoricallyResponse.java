
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
 *         &lt;element name="GetInformationTagsHistoricallyResult" type="{http://perconik.fiit.stuba.sk/ITM}ArrayOfInformationTag" minOccurs="0"/>
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
    "getInformationTagsHistoricallyResult"
})
@XmlRootElement(name = "GetInformationTagsHistoricallyResponse")
public class GetInformationTagsHistoricallyResponse {

    @XmlElementRef(name = "GetInformationTagsHistoricallyResult", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfInformationTag> getInformationTagsHistoricallyResult;

    /**
     * Gets the value of the getInformationTagsHistoricallyResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInformationTag }{@code >}
     *     
     */
    public JAXBElement<ArrayOfInformationTag> getGetInformationTagsHistoricallyResult() {
        return getInformationTagsHistoricallyResult;
    }

    /**
     * Sets the value of the getInformationTagsHistoricallyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInformationTag }{@code >}
     *     
     */
    public void setGetInformationTagsHistoricallyResult(JAXBElement<ArrayOfInformationTag> value) {
        this.getInformationTagsHistoricallyResult = value;
    }

}
