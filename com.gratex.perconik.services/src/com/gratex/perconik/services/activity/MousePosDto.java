
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MousePosDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MousePosDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MousePosDto", propOrder = {
    "x",
    "y"
})
public class MousePosDto {

    @XmlElement(name = "X")
    protected short x;
    @XmlElement(name = "Y")
    protected short y;

    /**
     * Gets the value of the x property.
     * 
     */
    public short getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(short value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public short getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(short value) {
        this.y = value;
    }

}
