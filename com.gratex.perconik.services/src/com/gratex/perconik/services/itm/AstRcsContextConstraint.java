
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfint;


/**
 * <p>Java class for AstRcsContextConstraint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AstRcsContextConstraint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AstRcsChildren" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="AstRcsChildrenEntities" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="AstRcsFileIds" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="AstRcsFileVersionIds" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="AstRcsParentEntities" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="AstRcsParents" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="AstRcsProjectIds" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AstRcsContextConstraint", propOrder = {
    "astRcsChildren",
    "astRcsChildrenEntities",
    "astRcsFileIds",
    "astRcsFileVersionIds",
    "astRcsParentEntities",
    "astRcsParents",
    "astRcsProjectIds"
})
public class AstRcsContextConstraint {

    @XmlElementRef(name = "AstRcsChildren", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsChildren;
    @XmlElementRef(name = "AstRcsChildrenEntities", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsChildrenEntities;
    @XmlElementRef(name = "AstRcsFileIds", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsFileIds;
    @XmlElementRef(name = "AstRcsFileVersionIds", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsFileVersionIds;
    @XmlElementRef(name = "AstRcsParentEntities", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsParentEntities;
    @XmlElementRef(name = "AstRcsParents", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsParents;
    @XmlElementRef(name = "AstRcsProjectIds", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> astRcsProjectIds;

    /**
     * Gets the value of the astRcsChildren property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsChildren() {
        return astRcsChildren;
    }

    /**
     * Sets the value of the astRcsChildren property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsChildren(JAXBElement<ArrayOfint> value) {
        this.astRcsChildren = value;
    }

    /**
     * Gets the value of the astRcsChildrenEntities property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsChildrenEntities() {
        return astRcsChildrenEntities;
    }

    /**
     * Sets the value of the astRcsChildrenEntities property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsChildrenEntities(JAXBElement<ArrayOfint> value) {
        this.astRcsChildrenEntities = value;
    }

    /**
     * Gets the value of the astRcsFileIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsFileIds() {
        return astRcsFileIds;
    }

    /**
     * Sets the value of the astRcsFileIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsFileIds(JAXBElement<ArrayOfint> value) {
        this.astRcsFileIds = value;
    }

    /**
     * Gets the value of the astRcsFileVersionIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsFileVersionIds() {
        return astRcsFileVersionIds;
    }

    /**
     * Sets the value of the astRcsFileVersionIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsFileVersionIds(JAXBElement<ArrayOfint> value) {
        this.astRcsFileVersionIds = value;
    }

    /**
     * Gets the value of the astRcsParentEntities property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsParentEntities() {
        return astRcsParentEntities;
    }

    /**
     * Sets the value of the astRcsParentEntities property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsParentEntities(JAXBElement<ArrayOfint> value) {
        this.astRcsParentEntities = value;
    }

    /**
     * Gets the value of the astRcsParents property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsParents() {
        return astRcsParents;
    }

    /**
     * Sets the value of the astRcsParents property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsParents(JAXBElement<ArrayOfint> value) {
        this.astRcsParents = value;
    }

    /**
     * Gets the value of the astRcsProjectIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getAstRcsProjectIds() {
        return astRcsProjectIds;
    }

    /**
     * Sets the value of the astRcsProjectIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setAstRcsProjectIds(JAXBElement<ArrayOfint> value) {
        this.astRcsProjectIds = value;
    }

}
