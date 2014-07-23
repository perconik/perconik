
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfstring;


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
 *         &lt;element name="targetUries" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="constraint" type="{http://perconik.fiit.stuba.sk/ITM}InformationTagConstraint" minOccurs="0"/>
 *         &lt;element name="onlyNewestVersions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "targetUries",
    "constraint",
    "onlyNewestVersions"
})
@XmlRootElement(name = "GetInformationTagsHistorically")
public class GetInformationTagsHistorically {

    @XmlElementRef(name = "targetUries", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> targetUries;
    @XmlElementRef(name = "constraint", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<InformationTagConstraint> constraint;
    protected Boolean onlyNewestVersions;

    /**
     * Gets the value of the targetUries property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getTargetUries() {
        return targetUries;
    }

    /**
     * Sets the value of the targetUries property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setTargetUries(JAXBElement<ArrayOfstring> value) {
        this.targetUries = value;
    }

    /**
     * Gets the value of the constraint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}
     *     
     */
    public JAXBElement<InformationTagConstraint> getConstraint() {
        return constraint;
    }

    /**
     * Sets the value of the constraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}
     *     
     */
    public void setConstraint(JAXBElement<InformationTagConstraint> value) {
        this.constraint = value;
    }

    /**
     * Gets the value of the onlyNewestVersions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnlyNewestVersions() {
        return onlyNewestVersions;
    }

    /**
     * Sets the value of the onlyNewestVersions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnlyNewestVersions(Boolean value) {
        this.onlyNewestVersions = value;
    }

}
