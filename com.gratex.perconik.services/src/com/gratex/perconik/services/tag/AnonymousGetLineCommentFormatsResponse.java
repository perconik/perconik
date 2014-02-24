
package com.gratex.perconik.services.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="GetLineCommentFormatsResult" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}GetLineCommentFormatsResponse" minOccurs="0"/>
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
    "getLineCommentFormatsResult"
})
@XmlRootElement(name = "GetLineCommentFormatsResponse")
public class AnonymousGetLineCommentFormatsResponse {

    @XmlElement(name = "GetLineCommentFormatsResult")
    protected GetLineCommentFormatsResponse getLineCommentFormatsResult;

    /**
     * Gets the value of the getLineCommentFormatsResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetLineCommentFormatsResponse }
     *     
     */
    public GetLineCommentFormatsResponse getGetLineCommentFormatsResult() {
        return getLineCommentFormatsResult;
    }

    /**
     * Sets the value of the getLineCommentFormatsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetLineCommentFormatsResponse }
     *     
     */
    public void setGetLineCommentFormatsResult(GetLineCommentFormatsResponse value) {
        this.getLineCommentFormatsResult = value;
    }

}
