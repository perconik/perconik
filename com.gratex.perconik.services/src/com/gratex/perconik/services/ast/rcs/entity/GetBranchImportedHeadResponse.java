
package com.gratex.perconik.services.ast.rcs.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="GetBranchImportedHeadResult" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}GetBranchImportedHeadResponse" minOccurs="0"/>
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
    "getBranchImportedHeadResult"
})
@XmlRootElement(name = "GetBranchImportedHeadResponse")
public class GetBranchImportedHeadResponse {

    @XmlElementRef(name = "GetBranchImportedHeadResult", namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", type = JAXBElement.class, required = false)
    protected JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse> getBranchImportedHeadResult;

    /**
     * Gets the value of the getBranchImportedHeadResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse }{@code >}
     *     
     */
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse> getGetBranchImportedHeadResult() {
        return getBranchImportedHeadResult;
    }

    /**
     * Sets the value of the getBranchImportedHeadResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse }{@code >}
     *     
     */
    public void setGetBranchImportedHeadResult(JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse> value) {
        this.getBranchImportedHeadResult = value;
    }

}
