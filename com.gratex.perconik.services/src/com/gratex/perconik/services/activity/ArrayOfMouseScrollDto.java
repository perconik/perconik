
package com.gratex.perconik.services.activity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMouseScrollDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMouseScrollDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MouseScrollDto" type="{http://www.gratex.com/PerConIk/IActivitySvc}MouseScrollDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMouseScrollDto", propOrder = {
    "mouseScrollDto"
})
public class ArrayOfMouseScrollDto {

    @XmlElement(name = "MouseScrollDto", nillable = true)
    protected List<MouseScrollDto> mouseScrollDto;

    /**
     * Gets the value of the mouseScrollDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mouseScrollDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMouseScrollDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MouseScrollDto }
     * 
     * 
     */
    public List<MouseScrollDto> getMouseScrollDto() {
        if (mouseScrollDto == null) {
            mouseScrollDto = new ArrayList<MouseScrollDto>();
        }
        return this.mouseScrollDto;
    }

}
