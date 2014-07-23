
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfanyType;


/**
 * <p>Java class for DataAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PropertyUri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Values" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfanyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataAttribute", propOrder = {
    "propertyUri",
    "values"
})
public class DataAttribute {

    @XmlElementRef(name = "PropertyUri", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<String> propertyUri;
    @XmlElementRef(name = "Values", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfanyType> values;

    /**
     * Gets the value of the propertyUri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPropertyUri() {
        return propertyUri;
    }

    /**
     * Sets the value of the propertyUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPropertyUri(JAXBElement<String> value) {
        this.propertyUri = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}
     *     
     */
    public JAXBElement<ArrayOfanyType> getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}
     *     
     */
    public void setValues(JAXBElement<ArrayOfanyType> value) {
        this.values = value;
    }

}
