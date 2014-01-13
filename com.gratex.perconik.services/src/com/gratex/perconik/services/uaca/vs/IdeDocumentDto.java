
package com.gratex.perconik.services.uaca.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeDocumentDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeDocumentDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChangesetIdInRcs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RcsServer" type="{http://www.gratex.com/PerConIk/IActivitySvc}RcsServerDto" minOccurs="0"/>
 *         &lt;element name="Path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PathType" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdePathTypeEnum"/>
 *         &lt;element name="BranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeDocumentDto", propOrder = {
    "changesetIdInRcs",
    "rcsServer",
    "path",
    "pathType",
    "branchName",
    "id"
})
public class IdeDocumentDto {

    @XmlElement(name = "ChangesetIdInRcs")
    protected String changesetIdInRcs;
    @XmlElement(name = "RcsServer")
    protected RcsServerDto rcsServer;
    @XmlElement(name = "Path")
    protected String path;
    @XmlElement(name = "PathType", required = true)
    protected IdePathTypeEnum pathType;
    @XmlElement(name = "BranchName")
    protected String branchName;
    @XmlElement(name = "Id")
    protected int id;

    /**
     * Gets the value of the changesetIdInRcs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangesetIdInRcs() {
        return changesetIdInRcs;
    }

    /**
     * Sets the value of the changesetIdInRcs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangesetIdInRcs(String value) {
        this.changesetIdInRcs = value;
    }

    /**
     * Gets the value of the rcsServer property.
     * 
     * @return
     *     possible object is
     *     {@link RcsServerDto }
     *     
     */
    public RcsServerDto getRcsServer() {
        return rcsServer;
    }

    /**
     * Sets the value of the rcsServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link RcsServerDto }
     *     
     */
    public void setRcsServer(RcsServerDto value) {
        this.rcsServer = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the pathType property.
     * 
     * @return
     *     possible object is
     *     {@link IdePathTypeEnum }
     *     
     */
    public IdePathTypeEnum getPathType() {
        return pathType;
    }

    /**
     * Sets the value of the pathType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdePathTypeEnum }
     *     
     */
    public void setPathType(IdePathTypeEnum value) {
        this.pathType = value;
    }

    /**
     * Gets the value of the branchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the value of the branchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}
