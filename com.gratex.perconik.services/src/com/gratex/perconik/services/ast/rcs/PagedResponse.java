
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PagedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PagedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PageCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PageIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagedResponse", propOrder = {
    "pageCount",
    "pageIndex"
})
@XmlSeeAlso({
    SearchUsersResponse.class,
    SearchRcsProjectsResponse.class,
    GetChildCodeEntitiesResponse.class,
    SearchTypeCodeEntitiesLightResponse.class,
    SearchChangesetsResponse.class,
    SearchRcsServersResponse.class,
    SearchFoldersResponse.class,
    SearchCodeEntitiesResponse.class,
    GetChangesetRcsProjectResponse.class,
    SearchFilesResponse.class,
    SearchBranchesResponse.class
})
public class PagedResponse {

    @XmlElement(name = "PageCount")
    protected Integer pageCount;
    @XmlElement(name = "PageIndex")
    protected Integer pageIndex;

    /**
     * Gets the value of the pageCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * Sets the value of the pageCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPageCount(Integer value) {
        this.pageCount = value;
    }

    /**
     * Gets the value of the pageIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * Sets the value of the pageIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPageIndex(Integer value) {
        this.pageIndex = value;
    }

}
