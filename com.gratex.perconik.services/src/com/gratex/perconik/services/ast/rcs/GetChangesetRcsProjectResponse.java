
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetChangesetRcsProjectResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetChangesetRcsProjectResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}PagedResponse">
 *       &lt;sequence>
 *         &lt;element name="RcsProject" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}RcsProjectDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetChangesetRcsProjectResponse", propOrder = {
    "rcsProject"
})
public class GetChangesetRcsProjectResponse
    extends PagedResponse
{

    @XmlElementRef(name = "RcsProject", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<RcsProjectDto> rcsProject;

    /**
     * Gets the value of the rcsProject property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RcsProjectDto }{@code >}
     *     
     */
    public JAXBElement<RcsProjectDto> getRcsProject() {
        return rcsProject;
    }

    /**
     * Sets the value of the rcsProject property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RcsProjectDto }{@code >}
     *     
     */
    public void setRcsProject(JAXBElement<RcsProjectDto> value) {
        this.rcsProject = value;
    }

}
