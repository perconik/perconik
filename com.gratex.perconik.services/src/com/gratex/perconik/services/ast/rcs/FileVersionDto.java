
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileVersionDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileVersionDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}EntityVersionDto">
 *       &lt;sequence>
 *         &lt;element name="ContentNotIncludedReason" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ContentNotIncludedReason" minOccurs="0"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileVersionDto", propOrder = {
    "contentNotIncludedReason",
    "url"
})
public class FileVersionDto
    extends EntityVersionDto
{

    @XmlElement(name = "ContentNotIncludedReason")
    protected ContentNotIncludedReason contentNotIncludedReason;
    @XmlElementRef(name = "Url", namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", type = JAXBElement.class, required = false)
    protected JAXBElement<String> url;

    /**
     * Gets the value of the contentNotIncludedReason property.
     * 
     * @return
     *     possible object is
     *     {@link ContentNotIncludedReason }
     *     
     */
    public ContentNotIncludedReason getContentNotIncludedReason() {
        return contentNotIncludedReason;
    }

    /**
     * Sets the value of the contentNotIncludedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentNotIncludedReason }
     *     
     */
    public void setContentNotIncludedReason(ContentNotIncludedReason value) {
        this.contentNotIncludedReason = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUrl(JAXBElement<String> value) {
        this.url = value;
    }

}
