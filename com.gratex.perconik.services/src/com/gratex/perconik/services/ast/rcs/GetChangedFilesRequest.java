
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetChangedFilesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetChangedFilesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChangesetId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IncludeDeletedFiles" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetChangedFilesRequest", propOrder = {
    "changesetId",
    "includeDeletedFiles"
})
public class GetChangedFilesRequest {

    @XmlElement(name = "ChangesetId")
    protected Integer changesetId;
    @XmlElement(name = "IncludeDeletedFiles")
    protected Boolean includeDeletedFiles;

    /**
     * Gets the value of the changesetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChangesetId() {
        return changesetId;
    }

    /**
     * Sets the value of the changesetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChangesetId(Integer value) {
        this.changesetId = value;
    }

    /**
     * Gets the value of the includeDeletedFiles property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeDeletedFiles() {
        return includeDeletedFiles;
    }

    /**
     * Sets the value of the includeDeletedFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeDeletedFiles(Boolean value) {
        this.includeDeletedFiles = value;
    }

}
