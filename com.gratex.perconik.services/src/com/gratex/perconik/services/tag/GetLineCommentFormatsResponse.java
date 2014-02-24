
package com.gratex.perconik.services.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetLineCommentFormatsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetLineCommentFormatsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Formats" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}ArrayOfLineCommentFormatDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetLineCommentFormatsResponse", propOrder = {
    "formats"
})
public class GetLineCommentFormatsResponse {

    @XmlElement(name = "Formats")
    protected ArrayOfLineCommentFormatDto formats;

    /**
     * Gets the value of the formats property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLineCommentFormatDto }
     *     
     */
    public ArrayOfLineCommentFormatDto getFormats() {
        return formats;
    }

    /**
     * Sets the value of the formats property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLineCommentFormatDto }
     *     
     */
    public void setFormats(ArrayOfLineCommentFormatDto value) {
        this.formats = value;
    }

}
