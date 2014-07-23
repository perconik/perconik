
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchTypeCodeEntitiesLightRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchTypeCodeEntitiesLightRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}PagedRequest">
 *       &lt;sequence>
 *         &lt;element name="ChangesetId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchTypeCodeEntitiesLightRequest", propOrder = {
    "changesetId"
})
public class SearchTypeCodeEntitiesLightRequest
    extends PagedRequest
{

    @XmlElement(name = "ChangesetId")
    protected Integer changesetId;

    /**
     * Gets the value of the changesetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChangesetId() {
        return changesetId;
    }

    /**
     * Sets the value of the changesetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChangesetId(Integer value) {
        this.changesetId = value;
    }

}
