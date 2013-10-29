
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ActivityDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Events" type="{http://www.gratex.com/PerConIk/IActivitySvc}XmlSerializableInterfaceCollection-Gratex.PerConIK.UserActivity.Svc.Interfaces.EventDto" minOccurs="0"/>
 *         &lt;element name="States" type="{http://www.gratex.com/PerConIk/IActivitySvc}XmlSerializableInterfaceCollection-Gratex.PerConIK.UserActivity.Svc.Interfaces.StateDto" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StartTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="EndTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Workstation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="User" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ActivityId" use="required" type="{http://microsoft.com/wsdl/types/}guid" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityDto", propOrder = {
    "events",
    "states"
})
public class ActivityDto {

    @XmlElement(name = "Events")
    protected XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto events;
    @XmlElement(name = "States")
    protected XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto states;
    @XmlAttribute(name = "StartTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlAttribute(name = "EndTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    @XmlAttribute(name = "Workstation")
    protected String workstation;
    @XmlAttribute(name = "User")
    protected String user;
    @XmlAttribute(name = "ActivityId", required = true)
    protected String activityId;

    /**
     * Gets the value of the events property.
     * 
     * @return
     *     possible object is
     *     {@link XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto }
     *     
     */
    public XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto getEvents() {
        return events;
    }

    /**
     * Sets the value of the events property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto }
     *     
     */
    public void setEvents(XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto value) {
        this.events = value;
    }

    /**
     * Gets the value of the states property.
     * 
     * @return
     *     possible object is
     *     {@link XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto }
     *     
     */
    public XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto getStates() {
        return states;
    }

    /**
     * Sets the value of the states property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto }
     *     
     */
    public void setStates(XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto value) {
        this.states = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the workstation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkstation() {
        return workstation;
    }

    /**
     * Sets the value of the workstation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkstation(String value) {
        this.workstation = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the activityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Sets the value of the activityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityId(String value) {
        this.activityId = value;
    }

}
