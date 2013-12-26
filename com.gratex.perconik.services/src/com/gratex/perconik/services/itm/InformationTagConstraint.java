
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for InformationTagConstraint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InformationTagConstraint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AstRcsContextConstraint" type="{http://perconik.fiit.stuba.sk/ITM}AstRcsContextConstraint" minOccurs="0"/>
 *         &lt;element name="AttributeQuery" type="{http://perconik.fiit.stuba.sk/ITM}BaseAttributeQuery" minOccurs="0"/>
 *         &lt;element name="Authors" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="CreatedAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CreatedBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="GeneratedAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="GeneratedBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IsCreatedAfterOpened" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsGeneratedAfterOpened" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsSavedAfterOpened" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SavedAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SavedBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Types" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformationTagConstraint", propOrder = {
    "astRcsContextConstraint",
    "attributeQuery",
    "authors",
    "createdAfter",
    "createdBefore",
    "generatedAfter",
    "generatedBefore",
    "isCreatedAfterOpened",
    "isGeneratedAfterOpened",
    "isSavedAfterOpened",
    "savedAfter",
    "savedBefore",
    "types"
})
public class InformationTagConstraint {

    @XmlElementRef(name = "AstRcsContextConstraint", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<AstRcsContextConstraint> astRcsContextConstraint;
    @XmlElementRef(name = "AttributeQuery", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<BaseAttributeQuery> attributeQuery;
    @XmlElementRef(name = "Authors", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> authors;
    @XmlElementRef(name = "CreatedAfter", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> createdAfter;
    @XmlElementRef(name = "CreatedBefore", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> createdBefore;
    @XmlElementRef(name = "GeneratedAfter", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> generatedAfter;
    @XmlElementRef(name = "GeneratedBefore", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> generatedBefore;
    @XmlElement(name = "IsCreatedAfterOpened")
    protected Boolean isCreatedAfterOpened;
    @XmlElement(name = "IsGeneratedAfterOpened")
    protected Boolean isGeneratedAfterOpened;
    @XmlElement(name = "IsSavedAfterOpened")
    protected Boolean isSavedAfterOpened;
    @XmlElementRef(name = "SavedAfter", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> savedAfter;
    @XmlElementRef(name = "SavedBefore", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> savedBefore;
    @XmlElementRef(name = "Types", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> types;

    /**
     * Gets the value of the astRcsContextConstraint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AstRcsContextConstraint }{@code >}
     *     
     */
    public JAXBElement<AstRcsContextConstraint> getAstRcsContextConstraint() {
        return astRcsContextConstraint;
    }

    /**
     * Sets the value of the astRcsContextConstraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AstRcsContextConstraint }{@code >}
     *     
     */
    public void setAstRcsContextConstraint(JAXBElement<AstRcsContextConstraint> value) {
        this.astRcsContextConstraint = value;
    }

    /**
     * Gets the value of the attributeQuery property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}
     *     
     */
    public JAXBElement<BaseAttributeQuery> getAttributeQuery() {
        return attributeQuery;
    }

    /**
     * Sets the value of the attributeQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}
     *     
     */
    public void setAttributeQuery(JAXBElement<BaseAttributeQuery> value) {
        this.attributeQuery = value;
    }

    /**
     * Gets the value of the authors property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getAuthors() {
        return authors;
    }

    /**
     * Sets the value of the authors property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setAuthors(JAXBElement<ArrayOfstring> value) {
        this.authors = value;
    }

    /**
     * Gets the value of the createdAfter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getCreatedAfter() {
        return createdAfter;
    }

    /**
     * Sets the value of the createdAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setCreatedAfter(JAXBElement<XMLGregorianCalendar> value) {
        this.createdAfter = value;
    }

    /**
     * Gets the value of the createdBefore property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getCreatedBefore() {
        return createdBefore;
    }

    /**
     * Sets the value of the createdBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setCreatedBefore(JAXBElement<XMLGregorianCalendar> value) {
        this.createdBefore = value;
    }

    /**
     * Gets the value of the generatedAfter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getGeneratedAfter() {
        return generatedAfter;
    }

    /**
     * Sets the value of the generatedAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setGeneratedAfter(JAXBElement<XMLGregorianCalendar> value) {
        this.generatedAfter = value;
    }

    /**
     * Gets the value of the generatedBefore property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getGeneratedBefore() {
        return generatedBefore;
    }

    /**
     * Sets the value of the generatedBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setGeneratedBefore(JAXBElement<XMLGregorianCalendar> value) {
        this.generatedBefore = value;
    }

    /**
     * Gets the value of the isCreatedAfterOpened property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCreatedAfterOpened() {
        return isCreatedAfterOpened;
    }

    /**
     * Sets the value of the isCreatedAfterOpened property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCreatedAfterOpened(Boolean value) {
        this.isCreatedAfterOpened = value;
    }

    /**
     * Gets the value of the isGeneratedAfterOpened property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsGeneratedAfterOpened() {
        return isGeneratedAfterOpened;
    }

    /**
     * Sets the value of the isGeneratedAfterOpened property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsGeneratedAfterOpened(Boolean value) {
        this.isGeneratedAfterOpened = value;
    }

    /**
     * Gets the value of the isSavedAfterOpened property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSavedAfterOpened() {
        return isSavedAfterOpened;
    }

    /**
     * Sets the value of the isSavedAfterOpened property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSavedAfterOpened(Boolean value) {
        this.isSavedAfterOpened = value;
    }

    /**
     * Gets the value of the savedAfter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getSavedAfter() {
        return savedAfter;
    }

    /**
     * Sets the value of the savedAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setSavedAfter(JAXBElement<XMLGregorianCalendar> value) {
        this.savedAfter = value;
    }

    /**
     * Gets the value of the savedBefore property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getSavedBefore() {
        return savedBefore;
    }

    /**
     * Sets the value of the savedBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setSavedBefore(JAXBElement<XMLGregorianCalendar> value) {
        this.savedBefore = value;
    }

    /**
     * Gets the value of the types property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getTypes() {
        return types;
    }

    /**
     * Sets the value of the types property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setTypes(JAXBElement<ArrayOfstring> value) {
        this.types = value;
    }

}
