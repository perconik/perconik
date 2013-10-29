
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseStateBlobDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MouseStateBlobDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MouseMoves" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfMouseMoveDto" minOccurs="0"/>
 *         &lt;element name="MouseDragDrops" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfMouseDragDropDto" minOccurs="0"/>
 *         &lt;element name="MouseScrolls" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfMouseScrollDto" minOccurs="0"/>
 *         &lt;element name="MouseClicks" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfMouseClickDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MouseStateBlobDto", propOrder = {
    "mouseMoves",
    "mouseDragDrops",
    "mouseScrolls",
    "mouseClicks"
})
public class MouseStateBlobDto {

    @XmlElement(name = "MouseMoves")
    protected ArrayOfMouseMoveDto mouseMoves;
    @XmlElement(name = "MouseDragDrops")
    protected ArrayOfMouseDragDropDto mouseDragDrops;
    @XmlElement(name = "MouseScrolls")
    protected ArrayOfMouseScrollDto mouseScrolls;
    @XmlElement(name = "MouseClicks")
    protected ArrayOfMouseClickDto mouseClicks;

    /**
     * Gets the value of the mouseMoves property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMouseMoveDto }
     *     
     */
    public ArrayOfMouseMoveDto getMouseMoves() {
        return mouseMoves;
    }

    /**
     * Sets the value of the mouseMoves property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMouseMoveDto }
     *     
     */
    public void setMouseMoves(ArrayOfMouseMoveDto value) {
        this.mouseMoves = value;
    }

    /**
     * Gets the value of the mouseDragDrops property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMouseDragDropDto }
     *     
     */
    public ArrayOfMouseDragDropDto getMouseDragDrops() {
        return mouseDragDrops;
    }

    /**
     * Sets the value of the mouseDragDrops property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMouseDragDropDto }
     *     
     */
    public void setMouseDragDrops(ArrayOfMouseDragDropDto value) {
        this.mouseDragDrops = value;
    }

    /**
     * Gets the value of the mouseScrolls property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMouseScrollDto }
     *     
     */
    public ArrayOfMouseScrollDto getMouseScrolls() {
        return mouseScrolls;
    }

    /**
     * Sets the value of the mouseScrolls property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMouseScrollDto }
     *     
     */
    public void setMouseScrolls(ArrayOfMouseScrollDto value) {
        this.mouseScrolls = value;
    }

    /**
     * Gets the value of the mouseClicks property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMouseClickDto }
     *     
     */
    public ArrayOfMouseClickDto getMouseClicks() {
        return mouseClicks;
    }

    /**
     * Sets the value of the mouseClicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMouseClickDto }
     *     
     */
    public void setMouseClicks(ArrayOfMouseClickDto value) {
        this.mouseClicks = value;
    }

}
