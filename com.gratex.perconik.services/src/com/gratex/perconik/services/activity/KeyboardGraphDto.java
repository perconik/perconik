
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyboardGraphDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyboardGraphDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Character" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Latency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FlightTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyboardGraphDto", propOrder = {
    "character",
    "latency",
    "flightTime"
})
public class KeyboardGraphDto {

    @XmlElement(name = "Character")
    protected String character;
    @XmlElement(name = "Latency")
    protected int latency;
    @XmlElement(name = "FlightTime")
    protected int flightTime;

    /**
     * Gets the value of the character property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Sets the value of the character property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharacter(String value) {
        this.character = value;
    }

    /**
     * Gets the value of the latency property.
     * 
     */
    public int getLatency() {
        return latency;
    }

    /**
     * Sets the value of the latency property.
     * 
     */
    public void setLatency(int value) {
        this.latency = value;
    }

    /**
     * Gets the value of the flightTime property.
     * 
     */
    public int getFlightTime() {
        return flightTime;
    }

    /**
     * Sets the value of the flightTime property.
     * 
     */
    public void setFlightTime(int value) {
        this.flightTime = value;
    }

}
