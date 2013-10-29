
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseMoveDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MouseMoveDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartPos" type="{http://www.gratex.com/PerConIk/IActivitySvc}MousePosDto"/>
 *         &lt;element name="EndPos" type="{http://www.gratex.com/PerConIk/IActivitySvc}MousePosDto"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Distance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MouseMoveDto", propOrder = {
    "startPos",
    "endPos",
    "duration",
    "distance"
})
@XmlSeeAlso({
    MouseDragDropDto.class
})
public class MouseMoveDto {

    @XmlElement(name = "StartPos", required = true)
    protected MousePosDto startPos;
    @XmlElement(name = "EndPos", required = true)
    protected MousePosDto endPos;
    @XmlElement(name = "Duration")
    protected int duration;
    @XmlElement(name = "Distance")
    protected double distance;

    /**
     * Gets the value of the startPos property.
     * 
     * @return
     *     possible object is
     *     {@link MousePosDto }
     *     
     */
    public MousePosDto getStartPos() {
        return startPos;
    }

    /**
     * Sets the value of the startPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link MousePosDto }
     *     
     */
    public void setStartPos(MousePosDto value) {
        this.startPos = value;
    }

    /**
     * Gets the value of the endPos property.
     * 
     * @return
     *     possible object is
     *     {@link MousePosDto }
     *     
     */
    public MousePosDto getEndPos() {
        return endPos;
    }

    /**
     * Sets the value of the endPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link MousePosDto }
     *     
     */
    public void setEndPos(MousePosDto value) {
        this.endPos = value;
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
     * Gets the value of the distance property.
     * 
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     * 
     */
    public void setDistance(double value) {
        this.distance = value;
    }

}
