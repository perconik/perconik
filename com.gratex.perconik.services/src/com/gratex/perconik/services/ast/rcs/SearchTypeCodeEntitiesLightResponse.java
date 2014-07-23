
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchTypeCodeEntitiesLightResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchTypeCodeEntitiesLightResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}PagedResponse">
 *       &lt;sequence>
 *         &lt;element name="CodeEntityVersions" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ArrayOfCodeEntityVersionIdWithFileUrlDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchTypeCodeEntitiesLightResponse", propOrder = {
    "codeEntityVersions"
})
public class SearchTypeCodeEntitiesLightResponse
    extends PagedResponse
{

    @XmlElementRef(name = "CodeEntityVersions", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfCodeEntityVersionIdWithFileUrlDto> codeEntityVersions;

    /**
     * Gets the value of the codeEntityVersions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdWithFileUrlDto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCodeEntityVersionIdWithFileUrlDto> getCodeEntityVersions() {
        return codeEntityVersions;
    }

    /**
     * Sets the value of the codeEntityVersions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdWithFileUrlDto }{@code >}
     *     
     */
    public void setCodeEntityVersions(JAXBElement<ArrayOfCodeEntityVersionIdWithFileUrlDto> value) {
        this.codeEntityVersions = value;
    }

}
