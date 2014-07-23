
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IsChangesetQueuedOrImportedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IsChangesetQueuedOrImportedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="State" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}ChangesetQueuedOrImportedState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IsChangesetQueuedOrImportedResponse", propOrder = {
    "state"
})
public class IsChangesetQueuedOrImportedResponse {

    @XmlElement(name = "State")
    protected ChangesetQueuedOrImportedState state;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link ChangesetQueuedOrImportedState }
     *     
     */
    public ChangesetQueuedOrImportedState getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangesetQueuedOrImportedState }
     *     
     */
    public void setState(ChangesetQueuedOrImportedState value) {
        this.state = value;
    }

}
