
package com.gratex.perconik.services.tag;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEnumValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEnumValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnumValue" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}EnumValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEnumValue", propOrder = {
    "enumValue"
})
public class ArrayOfEnumValue {

    @XmlElement(name = "EnumValue", nillable = true)
    protected List<EnumValue> enumValue;

    /**
     * Gets the value of the enumValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enumValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnumValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnumValue }
     * 
     * 
     */
    public List<EnumValue> getEnumValue() {
        if (enumValue == null) {
            enumValue = new ArrayList<EnumValue>();
        }
        return this.enumValue;
    }

}
