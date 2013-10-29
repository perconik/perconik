
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseStateDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MouseStateDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}StateDto">
 *       &lt;sequence>
 *         &lt;element name="Data" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="DeserializedBlob" type="{http://www.gratex.com/PerConIk/IActivitySvc}MouseStateBlobDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MouseStateDto", propOrder = {
    "data",
    "deserializedBlob"
})
public class MouseStateDto
    extends StateDto
{

    @XmlElement(name = "Data")
    protected byte[] data;
    @XmlElement(name = "DeserializedBlob")
    protected MouseStateBlobDto deserializedBlob;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setData(byte[] value) {
        this.data = value;
    }

    /**
     * Gets the value of the deserializedBlob property.
     * 
     * @return
     *     possible object is
     *     {@link MouseStateBlobDto }
     *     
     */
    public MouseStateBlobDto getDeserializedBlob() {
        return deserializedBlob;
    }

    /**
     * Sets the value of the deserializedBlob property.
     * 
     * @param value
     *     allowed object is
     *     {@link MouseStateBlobDto }
     *     
     */
    public void setDeserializedBlob(MouseStateBlobDto value) {
        this.deserializedBlob = value;
    }

}
