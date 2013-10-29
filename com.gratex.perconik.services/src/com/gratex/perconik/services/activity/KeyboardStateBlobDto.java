
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyboardStateBlobDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyboardStateBlobDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Graphs" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfKeyboardGraphDto" minOccurs="0"/>
 *         &lt;element name="DiGraphs" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfKeyboardGraphDto" minOccurs="0"/>
 *         &lt;element name="TriGraphs" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfKeyboardGraphDto" minOccurs="0"/>
 *         &lt;element name="Shortcuts" type="{http://www.gratex.com/PerConIk/IActivitySvc}ArrayOfKeyboardGraphDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyboardStateBlobDto", propOrder = {
    "graphs",
    "diGraphs",
    "triGraphs",
    "shortcuts"
})
public class KeyboardStateBlobDto {

    @XmlElement(name = "Graphs")
    protected ArrayOfKeyboardGraphDto graphs;
    @XmlElement(name = "DiGraphs")
    protected ArrayOfKeyboardGraphDto diGraphs;
    @XmlElement(name = "TriGraphs")
    protected ArrayOfKeyboardGraphDto triGraphs;
    @XmlElement(name = "Shortcuts")
    protected ArrayOfKeyboardGraphDto shortcuts;

    /**
     * Gets the value of the graphs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public ArrayOfKeyboardGraphDto getGraphs() {
        return graphs;
    }

    /**
     * Sets the value of the graphs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public void setGraphs(ArrayOfKeyboardGraphDto value) {
        this.graphs = value;
    }

    /**
     * Gets the value of the diGraphs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public ArrayOfKeyboardGraphDto getDiGraphs() {
        return diGraphs;
    }

    /**
     * Sets the value of the diGraphs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public void setDiGraphs(ArrayOfKeyboardGraphDto value) {
        this.diGraphs = value;
    }

    /**
     * Gets the value of the triGraphs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public ArrayOfKeyboardGraphDto getTriGraphs() {
        return triGraphs;
    }

    /**
     * Sets the value of the triGraphs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public void setTriGraphs(ArrayOfKeyboardGraphDto value) {
        this.triGraphs = value;
    }

    /**
     * Gets the value of the shortcuts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public ArrayOfKeyboardGraphDto getShortcuts() {
        return shortcuts;
    }

    /**
     * Sets the value of the shortcuts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyboardGraphDto }
     *     
     */
    public void setShortcuts(ArrayOfKeyboardGraphDto value) {
        this.shortcuts = value;
    }

}
