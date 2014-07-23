
package com.gratex.perconik.services.tag;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfLineCommentFormatDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLineCommentFormatDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LineCommentFormat" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}LineCommentFormatDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLineCommentFormatDto", propOrder = {
    "lineCommentFormat"
})
public class ArrayOfLineCommentFormatDto {

    @XmlElement(name = "LineCommentFormat", nillable = true)
    protected List<LineCommentFormatDto> lineCommentFormat;

    /**
     * Gets the value of the lineCommentFormat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineCommentFormat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineCommentFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineCommentFormatDto }
     * 
     * 
     */
    public List<LineCommentFormatDto> getLineCommentFormat() {
        if (lineCommentFormat == null) {
            lineCommentFormat = new ArrayList<LineCommentFormatDto>();
        }
        return this.lineCommentFormat;
    }

}
