
package com.gratex.perconik.services.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchTagProfileRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchTagProfileRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}SearchRequest">
 *       &lt;sequence>
 *         &lt;element name="NameStartPart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchTagProfileRequest", propOrder = {
    "nameStartPart",
    "creator"
})
public class SearchTagProfileRequest
    extends SearchRequest
{

    @XmlElement(name = "NameStartPart")
    protected String nameStartPart;
    @XmlElement(name = "Creator")
    protected String creator;

    /**
     * Gets the value of the nameStartPart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameStartPart() {
        return nameStartPart;
    }

    /**
     * Sets the value of the nameStartPart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameStartPart(String value) {
        this.nameStartPart = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreator(String value) {
        this.creator = value;
    }

}
