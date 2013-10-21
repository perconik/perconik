
package com.gratex.perconik.services.tag;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTagProfileSearchResItemDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTagProfileSearchResItemDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TagProfileSearchResItem" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}TagProfileSearchResItemDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTagProfileSearchResItemDto", propOrder = {
    "tagProfileSearchResItem"
})
public class ArrayOfTagProfileSearchResItemDto {

    @XmlElement(name = "TagProfileSearchResItem", nillable = true)
    protected List<TagProfileSearchResItemDto> tagProfileSearchResItem;

    /**
     * Gets the value of the tagProfileSearchResItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tagProfileSearchResItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTagProfileSearchResItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TagProfileSearchResItemDto }
     * 
     * 
     */
    public List<TagProfileSearchResItemDto> getTagProfileSearchResItem() {
        if (tagProfileSearchResItem == null) {
            tagProfileSearchResItem = new ArrayList<TagProfileSearchResItemDto>();
        }
        return this.tagProfileSearchResItem;
    }

}
