
package com.gratex.perconik.services.itm.serialization.arrays;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZ_P7 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZ_P7">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ArrayOfKeyValueOfstringRDFDataWYX0VZ_P7" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfstringRDFDataWYX0VZ_P7" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZ_P7", propOrder = {
    "arrayOfKeyValueOfstringRDFDataWYX0VZP7"
})
public class ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7 {

    @XmlElement(name = "ArrayOfKeyValueOfstringRDFDataWYX0VZ_P7", nillable = true)
    protected List<ArrayOfKeyValueOfstringRDFDataWYX0VZP7> arrayOfKeyValueOfstringRDFDataWYX0VZP7;

    /**
     * Gets the value of the arrayOfKeyValueOfstringRDFDataWYX0VZP7 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrayOfKeyValueOfstringRDFDataWYX0VZP7 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrayOfKeyValueOfstringRDFDataWYX0VZP7().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfKeyValueOfstringRDFDataWYX0VZP7 }
     * 
     * 
     */
    public List<ArrayOfKeyValueOfstringRDFDataWYX0VZP7> getArrayOfKeyValueOfstringRDFDataWYX0VZP7() {
        if (arrayOfKeyValueOfstringRDFDataWYX0VZP7 == null) {
            arrayOfKeyValueOfstringRDFDataWYX0VZP7 = new ArrayList<ArrayOfKeyValueOfstringRDFDataWYX0VZP7>();
        }
        return this.arrayOfKeyValueOfstringRDFDataWYX0VZP7;
    }

}
