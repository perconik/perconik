
package com.gratex.perconik.services.activity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfKeyboardGraphDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfKeyboardGraphDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyboardGraphDto" type="{http://www.gratex.com/PerConIk/IActivitySvc}KeyboardGraphDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfKeyboardGraphDto", propOrder = {
    "keyboardGraphDto"
})
public class ArrayOfKeyboardGraphDto {

    @XmlElement(name = "KeyboardGraphDto", nillable = true)
    protected List<KeyboardGraphDto> keyboardGraphDto;

    /**
     * Gets the value of the keyboardGraphDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyboardGraphDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyboardGraphDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyboardGraphDto }
     * 
     * 
     */
    public List<KeyboardGraphDto> getKeyboardGraphDto() {
        if (keyboardGraphDto == null) {
            keyboardGraphDto = new ArrayList<KeyboardGraphDto>();
        }
        return this.keyboardGraphDto;
    }

}
