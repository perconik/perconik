
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ElementMatchQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ElementMatchQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://perconik.fiit.stuba.sk/ITM}AttributeQuery">
 *       &lt;sequence>
 *         &lt;element name="SubQuery" type="{http://perconik.fiit.stuba.sk/ITM}BaseAttributeQuery" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElementMatchQuery", propOrder = {
    "subQuery"
})
public class ElementMatchQuery
    extends AttributeQuery
{

    @XmlElementRef(name = "SubQuery", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<BaseAttributeQuery> subQuery;

    /**
     * Gets the value of the subQuery property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}
     *     
     */
    public JAXBElement<BaseAttributeQuery> getSubQuery() {
        return subQuery;
    }

    /**
     * Sets the value of the subQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}
     *     
     */
    public void setSubQuery(JAXBElement<BaseAttributeQuery> value) {
        this.subQuery = value;
    }

}
