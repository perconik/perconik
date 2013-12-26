
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetChildCodeEntitiesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetChildCodeEntitiesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}PagedResponse">
 *       &lt;sequence>
 *         &lt;element name="Versions" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ArrayOfCodeEntityVersionDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetChildCodeEntitiesResponse", propOrder = {
    "versions"
})
public class GetChildCodeEntitiesResponse
    extends PagedResponse
{

    @XmlElementRef(name = "Versions", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfCodeEntityVersionDto> versions;

    /**
     * Gets the value of the versions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionDto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCodeEntityVersionDto> getVersions() {
        return versions;
    }

    /**
     * Sets the value of the versions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionDto }{@code >}
     *     
     */
    public void setVersions(JAXBElement<ArrayOfCodeEntityVersionDto> value) {
        this.versions = value;
    }

}
