
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.gratex.perconik.services.ast.rcs.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for EnsureBranchesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnsureBranchesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Names" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
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
@XmlType(name = "EnsureBranchesRequest", propOrder = {
    "names",
    "rcsProjectId"
})
public class EnsureBranchesRequest {

    @XmlElementRef(name = "Names", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> names;
    @XmlElement(name = "RcsProjectId")
    protected Integer rcsProjectId;

    /**
     * Gets the value of the names property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getNames() {
        return names;
    }

    /**
     * Sets the value of the names property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setNames(JAXBElement<ArrayOfstring> value) {
        this.names = value;
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
