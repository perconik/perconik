
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetCodeEntityFullContextResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCodeEntityFullContextResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Children" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ArrayOfCodeEntityVersionIdDto" minOccurs="0"/>
 *         &lt;element name="FileId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="FileVersionId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Parents" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ArrayOfCodeEntityVersionIdDto" minOccurs="0"/>
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
@XmlType(name = "GetCodeEntityFullContextResponse", propOrder = {
    "children",
    "fileId",
    "fileVersionId",
    "parents",
    "rcsProjectId"
})
public class GetCodeEntityFullContextResponse {

    @XmlElementRef(name = "Children", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfCodeEntityVersionIdDto> children;
    @XmlElement(name = "FileId")
    protected Integer fileId;
    @XmlElement(name = "FileVersionId")
    protected Integer fileVersionId;
    @XmlElementRef(name = "Parents", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfCodeEntityVersionIdDto> parents;
    @XmlElement(name = "RcsProjectId")
    protected Integer rcsProjectId;

    /**
     * Gets the value of the children property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCodeEntityVersionIdDto> getChildren() {
        return children;
    }

    /**
     * Sets the value of the children property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}
     *     
     */
    public void setChildren(JAXBElement<ArrayOfCodeEntityVersionIdDto> value) {
        this.children = value;
    }

    /**
     * Gets the value of the fileId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * Sets the value of the fileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFileId(Integer value) {
        this.fileId = value;
    }

    /**
     * Gets the value of the fileVersionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFileVersionId() {
        return fileVersionId;
    }

    /**
     * Sets the value of the fileVersionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFileVersionId(Integer value) {
        this.fileVersionId = value;
    }

    /**
     * Gets the value of the parents property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCodeEntityVersionIdDto> getParents() {
        return parents;
    }

    /**
     * Sets the value of the parents property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}
     *     
     */
    public void setParents(JAXBElement<ArrayOfCodeEntityVersionIdDto> value) {
        this.parents = value;
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
