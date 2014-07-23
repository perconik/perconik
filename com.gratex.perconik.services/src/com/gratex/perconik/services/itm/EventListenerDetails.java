
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for EventListenerDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventListenerDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActionType" type="{http://perconik.fiit.stuba.sk/ITM}ActionType" minOccurs="0"/>
 *         &lt;element name="Connection" type="{http://perconik.fiit.stuba.sk/ITM}ConnectionDetails" minOccurs="0"/>
 *         &lt;element name="InformationTagConstraint" type="{http://perconik.fiit.stuba.sk/ITM}InformationTagConstraint" minOccurs="0"/>
 *         &lt;element name="Targets" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventListenerDetails", propOrder = {
    "actionType",
    "connection",
    "informationTagConstraint",
    "targets"
})
public class EventListenerDetails {

    @XmlElement(name = "ActionType")
    protected ActionType actionType;
    @XmlElementRef(name = "Connection", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ConnectionDetails> connection;
    @XmlElementRef(name = "InformationTagConstraint", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<InformationTagConstraint> informationTagConstraint;
    @XmlElementRef(name = "Targets", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> targets;

    /**
     * Gets the value of the actionType property.
     * 
     * @return
     *     possible object is
     *     {@link ActionType }
     *     
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the actionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionType }
     *     
     */
    public void setActionType(ActionType value) {
        this.actionType = value;
    }

    /**
     * Gets the value of the connection property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConnectionDetails }{@code >}
     *     
     */
    public JAXBElement<ConnectionDetails> getConnection() {
        return connection;
    }

    /**
     * Sets the value of the connection property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConnectionDetails }{@code >}
     *     
     */
    public void setConnection(JAXBElement<ConnectionDetails> value) {
        this.connection = value;
    }

    /**
     * Gets the value of the informationTagConstraint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}
     *     
     */
    public JAXBElement<InformationTagConstraint> getInformationTagConstraint() {
        return informationTagConstraint;
    }

    /**
     * Sets the value of the informationTagConstraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}
     *     
     */
    public void setInformationTagConstraint(JAXBElement<InformationTagConstraint> value) {
        this.informationTagConstraint = value;
    }

    /**
     * Gets the value of the targets property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getTargets() {
        return targets;
    }

    /**
     * Sets the value of the targets property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setTargets(JAXBElement<ArrayOfstring> value) {
        this.targets = value;
    }

}
