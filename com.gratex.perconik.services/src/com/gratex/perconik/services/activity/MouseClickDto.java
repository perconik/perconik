
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseClickDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MouseClickDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pos" type="{http://www.gratex.com/PerConIk/IActivitySvc}MousePosDto"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MouseClickType" type="{http://www.gratex.com/PerConIk/IActivitySvc}MouseClickTypeEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MouseClickDto", propOrder = {
    "pos",
    "duration",
    "mouseClickType"
})
public class MouseClickDto {

    @XmlElement(name = "Pos", required = true)
    protected MousePosDto pos;
    @XmlElement(name = "Duration")
    protected int duration;
    @XmlElement(name = "MouseClickType", required = true)
    protected MouseClickTypeEnum mouseClickType;

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link MousePosDto }
     *     
     */
    public MousePosDto getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link MousePosDto }
     *     
     */
    public void setPos(MousePosDto value) {
        this.pos = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the mouseClickType property.
     * 
     * @return
     *     possible object is
     *     {@link MouseClickTypeEnum }
     *     
     */
    public MouseClickTypeEnum getMouseClickType() {
        return mouseClickType;
    }

    /**
     * Sets the value of the mouseClickType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MouseClickTypeEnum }
     *     
     */
    public void setMouseClickType(MouseClickTypeEnum value) {
        this.mouseClickType = value;
    }

}
