
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventListenerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventListenerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActionType" type="{http://perconik.fiit.stuba.sk/ITM}ActionType" minOccurs="0"/>
 *         &lt;element name="EventListenerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InformationTag" type="{http://perconik.fiit.stuba.sk/ITM}InformationTag" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventListenerResponse", propOrder = {
    "actionType",
    "eventListenerId",
    "informationTag"
})
public class EventListenerResponse {

    @XmlElement(name = "ActionType")
    protected ActionType actionType;
    @XmlElementRef(name = "EventListenerId", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<String> eventListenerId;
    @XmlElementRef(name = "InformationTag", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<InformationTag> informationTag;

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
     * Gets the value of the eventListenerId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEventListenerId() {
        return eventListenerId;
    }

    /**
     * Sets the value of the eventListenerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEventListenerId(JAXBElement<String> value) {
        this.eventListenerId = value;
    }

    /**
     * Gets the value of the informationTag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InformationTag }{@code >}
     *     
     */
    public JAXBElement<InformationTag> getInformationTag() {
        return informationTag;
    }

    /**
     * Sets the value of the informationTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InformationTag }{@code >}
     *     
     */
    public void setInformationTag(JAXBElement<InformationTag> value) {
        this.informationTag = value;
    }

}
