
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
 *         &lt;element name="SearchTagProfileResult" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}SearchTagProfileResponse" minOccurs="0"/>
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
    "searchTagProfileResult"
})
@XmlRootElement(name = "SearchTagProfileResponse")
public class AnonymousSearchTagProfileResponse {

    @XmlElement(name = "SearchTagProfileResult")
    protected SearchTagProfileResponse searchTagProfileResult;

    /**
     * Gets the value of the searchTagProfileResult property.
     * 
     * @return
     *     possible object is
     *     {@link SearchTagProfileResponse }
     *     
     */
    public SearchTagProfileResponse getSearchTagProfileResult() {
        return searchTagProfileResult;
    }

    /**
     * Sets the value of the searchTagProfileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchTagProfileResponse }
     *     
     */
    public void setSearchTagProfileResult(SearchTagProfileResponse value) {
        this.searchTagProfileResult = value;
    }

}
