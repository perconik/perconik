
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseDragDropDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MouseDragDropDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}MouseMoveDto">
 *       &lt;sequence>
 *         &lt;element name="MouseButton" type="{http://www.gratex.com/PerConIk/IActivitySvc}MouseButtonTypeEnum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MouseDragDropDto", propOrder = {
    "mouseButton"
})
public class MouseDragDropDto
    extends MouseMoveDto
{

    @XmlElement(name = "MouseButton", required = true)
    protected MouseButtonTypeEnum mouseButton;

    /**
     * Gets the value of the mouseButton property.
     * 
     * @return
     *     possible object is
     *     {@link MouseButtonTypeEnum }
     *     
     */
    public MouseButtonTypeEnum getMouseButton() {
        return mouseButton;
    }

    /**
     * Sets the value of the mouseButton property.
     * 
     * @param value
     *     allowed object is
     *     {@link MouseButtonTypeEnum }
     *     
     */
    public void setMouseButton(MouseButtonTypeEnum value) {
        this.mouseButton = value;
    }

}
