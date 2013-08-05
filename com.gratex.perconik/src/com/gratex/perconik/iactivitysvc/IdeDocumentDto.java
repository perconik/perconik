
package com.gratex.perconik.iactivitysvc;

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
 *         &lt;element name="RcsServerPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PathType" type="{http://www.gratex.com/PerConIk/IActivitySvc}IdePathTypeEnum"/>
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
    "rcsServerPath",
    "path",
    "pathType"
})
public class IdeDocumentDto {

    @XmlElement(name = "ChangesetIdInRcs")
    protected String changesetIdInRcs;
    @XmlElement(name = "RcsServerPath")
    protected String rcsServerPath;
    @XmlElement(name = "Path")
    protected String path;
    @XmlElement(name = "PathType", required = true)
    protected IdePathTypeEnum pathType;

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
     * Gets the value of the rcsServerPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRcsServerPath() {
        return rcsServerPath;
    }

    /**
     * Sets the value of the rcsServerPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRcsServerPath(String value) {
        this.rcsServerPath = value;
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

}
