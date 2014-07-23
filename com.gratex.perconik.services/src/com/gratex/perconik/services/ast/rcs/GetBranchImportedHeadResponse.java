
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetBranchImportedHeadResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetBranchImportedHeadResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Changeset" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ChangesetDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetBranchImportedHeadResponse", propOrder = {
    "changeset"
})
public class GetBranchImportedHeadResponse {

    @XmlElementRef(name = "Changeset", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ChangesetDto> changeset;

    /**
     * Gets the value of the changeset property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ChangesetDto }{@code >}
     *     
     */
    public JAXBElement<ChangesetDto> getChangeset() {
        return changeset;
    }

    /**
     * Sets the value of the changeset property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ChangesetDto }{@code >}
     *     
     */
    public void setChangeset(JAXBElement<ChangesetDto> value) {
        this.changeset = value;
    }

}
