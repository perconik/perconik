
package com.gratex.perconik.services.activity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMouseDragDropDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMouseDragDropDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MouseDragDropDto" type="{http://www.gratex.com/PerConIk/IActivitySvc}MouseDragDropDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMouseDragDropDto", propOrder = {
    "mouseDragDropDto"
})
public class ArrayOfMouseDragDropDto {

    @XmlElement(name = "MouseDragDropDto", nillable = true)
    protected List<MouseDragDropDto> mouseDragDropDto;

    /**
     * Gets the value of the mouseDragDropDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mouseDragDropDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMouseDragDropDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MouseDragDropDto }
     * 
     * 
     */
    public List<MouseDragDropDto> getMouseDragDropDto() {
        if (mouseDragDropDto == null) {
            mouseDragDropDto = new ArrayList<MouseDragDropDto>();
        }
        return this.mouseDragDropDto;
    }

}
