
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetRcsServerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRcsServerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RcsServer" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}RcsServerDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRcsServerResponse", propOrder = {
    "rcsServer"
})
public class GetRcsServerResponse {

    @XmlElementRef(name = "RcsServer", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<RcsServerDto> rcsServer;

    /**
     * Gets the value of the rcsServer property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RcsServerDto }{@code >}
     *     
     */
    public JAXBElement<RcsServerDto> getRcsServer() {
        return rcsServer;
    }

    /**
     * Sets the value of the rcsServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RcsServerDto }{@code >}
     *     
     */
    public void setRcsServer(JAXBElement<RcsServerDto> value) {
        this.rcsServer = value;
    }

}
