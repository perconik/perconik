
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeCheckinDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeCheckinDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeEventDto">
 *       &lt;sequence>
 *         &lt;element name="RcsServer" type="{http://www.gratex.com/PerConIk/IActivitySvc}RcsServerDto" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="IdInRcs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeCheckinDto", propOrder = {
    "rcsServer"
})
public class IdeCheckinDto
    extends IdeEventDto
{

    @XmlElement(name = "RcsServer")
    protected RcsServerDto rcsServer;
    @XmlAttribute(name = "IdInRcs")
    protected String idInRcs;

    /**
     * Gets the value of the rcsServer property.
     * 
     * @return
     *     possible object is
     *     {@link RcsServerDto }
     *     
     */
    public RcsServerDto getRcsServer() {
        return rcsServer;
    }

    /**
     * Sets the value of the rcsServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link RcsServerDto }
     *     
     */
    public void setRcsServer(RcsServerDto value) {
        this.rcsServer = value;
    }

    /**
     * Gets the value of the idInRcs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdInRcs() {
        return idInRcs;
    }

    /**
     * Sets the value of the idInRcs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdInRcs(String value) {
        this.idInRcs = value;
    }

}
