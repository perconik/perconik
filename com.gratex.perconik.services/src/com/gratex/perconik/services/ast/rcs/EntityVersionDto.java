
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityVersionDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityVersionDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ancestor1ChangeType" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ChangeType" minOccurs="0"/>
 *         &lt;element name="Ancestor1Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Ancestor2ChangeType" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ChangeType" minOccurs="0"/>
 *         &lt;element name="Ancestor2Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BaseChangesetId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="EntityId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RcsProjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityVersionDto", propOrder = {
    "ancestor1ChangeType",
    "ancestor1Id",
    "ancestor2ChangeType",
    "ancestor2Id",
    "baseChangesetId",
    "entityId",
    "id",
    "rcsProjectId"
})
@XmlSeeAlso({
    CodeEntityVersionDto.class,
    FileVersionDto.class
})
public class EntityVersionDto {

    @XmlElementRef(name = "Ancestor1ChangeType", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ChangeType> ancestor1ChangeType;
    @XmlElementRef(name = "Ancestor1Id", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> ancestor1Id;
    @XmlElementRef(name = "Ancestor2ChangeType", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ChangeType> ancestor2ChangeType;
    @XmlElementRef(name = "Ancestor2Id", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> ancestor2Id;
    @XmlElement(name = "BaseChangesetId")
    protected Integer baseChangesetId;
    @XmlElement(name = "EntityId")
    protected Integer entityId;
    @XmlElement(name = "Id")
    protected Integer id;
    @XmlElement(name = "RcsProjectId")
    protected Integer rcsProjectId;

    /**
     * Gets the value of the ancestor1ChangeType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ChangeType }{@code >}
     *     
     */
    public JAXBElement<ChangeType> getAncestor1ChangeType() {
        return ancestor1ChangeType;
    }

    /**
     * Sets the value of the ancestor1ChangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ChangeType }{@code >}
     *     
     */
    public void setAncestor1ChangeType(JAXBElement<ChangeType> value) {
        this.ancestor1ChangeType = value;
    }

    /**
     * Gets the value of the ancestor1Id property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getAncestor1Id() {
        return ancestor1Id;
    }

    /**
     * Sets the value of the ancestor1Id property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setAncestor1Id(JAXBElement<Integer> value) {
        this.ancestor1Id = value;
    }

    /**
     * Gets the value of the ancestor2ChangeType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ChangeType }{@code >}
     *     
     */
    public JAXBElement<ChangeType> getAncestor2ChangeType() {
        return ancestor2ChangeType;
    }

    /**
     * Sets the value of the ancestor2ChangeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ChangeType }{@code >}
     *     
     */
    public void setAncestor2ChangeType(JAXBElement<ChangeType> value) {
        this.ancestor2ChangeType = value;
    }

    /**
     * Gets the value of the ancestor2Id property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getAncestor2Id() {
        return ancestor2Id;
    }

    /**
     * Sets the value of the ancestor2Id property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setAncestor2Id(JAXBElement<Integer> value) {
        this.ancestor2Id = value;
    }

    /**
     * Gets the value of the baseChangesetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBaseChangesetId() {
        return baseChangesetId;
    }

    /**
     * Sets the value of the baseChangesetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBaseChangesetId(Integer value) {
        this.baseChangesetId = value;
    }

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEntityId(Integer value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the rcsProjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRcsProjectId() {
        return rcsProjectId;
    }

    /**
     * Sets the value of the rcsProjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRcsProjectId(Integer value) {
        this.rcsProjectId = value;
    }

}
