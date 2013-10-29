
package com.gratex.perconik.services.itm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfKeyValueOfstringRDFDataWYX0VZ_P7 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfKeyValueOfstringRDFDataWYX0VZ_P7">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValueOfstringRDFDataWYX0VZ_P7" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Value" type="{http://perconik.fiit.stuba.sk/ITM}RDFData"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfKeyValueOfstringRDFDataWYX0VZ_P7", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", propOrder = {
    "keyValueOfstringRDFDataWYX0VZP7"
})
public class ArrayOfKeyValueOfstringRDFDataWYX0VZP7 {

    @XmlElement(name = "KeyValueOfstringRDFDataWYX0VZ_P7")
    protected List<ArrayOfKeyValueOfstringRDFDataWYX0VZP7 .KeyValueOfstringRDFDataWYX0VZP7> keyValueOfstringRDFDataWYX0VZP7;

    /**
     * Gets the value of the keyValueOfstringRDFDataWYX0VZP7 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValueOfstringRDFDataWYX0VZP7 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValueOfstringRDFDataWYX0VZP7().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfKeyValueOfstringRDFDataWYX0VZP7 .KeyValueOfstringRDFDataWYX0VZP7 }
     * 
     * 
     */
    public List<ArrayOfKeyValueOfstringRDFDataWYX0VZP7 .KeyValueOfstringRDFDataWYX0VZP7> getKeyValueOfstringRDFDataWYX0VZP7() {
        if (keyValueOfstringRDFDataWYX0VZP7 == null) {
            keyValueOfstringRDFDataWYX0VZP7 = new ArrayList<ArrayOfKeyValueOfstringRDFDataWYX0VZP7 .KeyValueOfstringRDFDataWYX0VZP7>();
        }
        return this.keyValueOfstringRDFDataWYX0VZP7;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Value" type="{http://perconik.fiit.stuba.sk/ITM}RDFData"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "key",
        "value"
    })
    public static class KeyValueOfstringRDFDataWYX0VZP7 {

        @XmlElement(name = "Key", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", required = true, nillable = true)
        protected String key;
        @XmlElement(name = "Value", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", required = true, nillable = true)
        protected RDFData value;

        /**
         * Gets the value of the key property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets the value of the key property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKey(String value) {
            this.key = value;
        }

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link RDFData }
         *     
         */
        public RDFData getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link RDFData }
         *     
         */
        public void setValue(RDFData value) {
            this.value = value;
        }

    }

}
