
package com.gratex.perconik.services.activity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMouseMoveDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMouseMoveDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MouseMoveDto" type="{http://www.gratex.com/PerConIk/IActivitySvc}MouseMoveDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMouseMoveDto", propOrder = {
    "mouseMoveDto"
})
public class ArrayOfMouseMoveDto {

    @XmlElement(name = "MouseMoveDto", nillable = true)
    protected List<MouseMoveDto> mouseMoveDto;

    /**
     * Gets the value of the mouseMoveDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mouseMoveDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMouseMoveDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MouseMoveDto }
     * 
     * 
     */
    public List<MouseMoveDto> getMouseMoveDto() {
        if (mouseMoveDto == null) {
            mouseMoveDto = new ArrayList<MouseMoveDto>();
        }
        return this.mouseMoveDto;
    }

}
