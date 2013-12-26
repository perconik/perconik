
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchChangesetsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchChangesetsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}PagedResponse">
 *       &lt;sequence>
 *         &lt;element name="Changesets" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ArrayOfChangesetDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchChangesetsResponse", propOrder = {
    "changesets"
})
public class SearchChangesetsResponse
    extends PagedResponse
{

    @XmlElementRef(name = "Changesets", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfChangesetDto> changesets;

    /**
     * Gets the value of the changesets property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfChangesetDto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfChangesetDto> getChangesets() {
        return changesets;
    }

    /**
     * Sets the value of the changesets property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfChangesetDto }{@code >}
     *     
     */
    public void setChangesets(JAXBElement<ArrayOfChangesetDto> value) {
        this.changesets = value;
    }

}
