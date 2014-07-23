
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpecificTarget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpecificTarget">
 *   &lt;complexContent>
 *     &lt;extension base="{http://perconik.fiit.stuba.sk/ITM}ITarget">
 *       &lt;sequence>
 *         &lt;element name="HasSelector" type="{http://perconik.fiit.stuba.sk/ITM}Selector" minOccurs="0"/>
 *         &lt;element name="HasSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecificTarget", propOrder = {
    "hasSelector",
    "hasSource"
})
public class SpecificTarget
    extends ITarget
{

    @XmlElementRef(name = "HasSelector", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<Selector> hasSelector;
    @XmlElementRef(name = "HasSource", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hasSource;

    /**
     * Gets the value of the hasSelector property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Selector }{@code >}
     *     
     */
    public JAXBElement<Selector> getHasSelector() {
        return hasSelector;
    }

    /**
     * Sets the value of the hasSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Selector }{@code >}
     *     
     */
    public void setHasSelector(JAXBElement<Selector> value) {
        this.hasSelector = value;
    }

    /**
     * Gets the value of the hasSource property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHasSource() {
        return hasSource;
    }

    /**
     * Sets the value of the hasSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHasSource(JAXBElement<String> value) {
        this.hasSource = value;
    }

}
