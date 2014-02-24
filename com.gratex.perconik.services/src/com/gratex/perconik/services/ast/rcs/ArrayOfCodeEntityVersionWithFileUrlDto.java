
package com.gratex.perconik.services.ast.rcs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCodeEntityVersionWithFileUrlDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCodeEntityVersionWithFileUrlDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodeEntityVersionWithFileUrlDto" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}CodeEntityVersionWithFileUrlDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCodeEntityVersionWithFileUrlDto", propOrder = {
    "codeEntityVersionWithFileUrlDto"
})
public class ArrayOfCodeEntityVersionWithFileUrlDto {

    @XmlElement(name = "CodeEntityVersionWithFileUrlDto", nillable = true)
    protected List<CodeEntityVersionWithFileUrlDto> codeEntityVersionWithFileUrlDto;

    /**
     * Gets the value of the codeEntityVersionWithFileUrlDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codeEntityVersionWithFileUrlDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodeEntityVersionWithFileUrlDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeEntityVersionWithFileUrlDto }
     * 
     * 
     */
    public List<CodeEntityVersionWithFileUrlDto> getCodeEntityVersionWithFileUrlDto() {
        if (codeEntityVersionWithFileUrlDto == null) {
            codeEntityVersionWithFileUrlDto = new ArrayList<CodeEntityVersionWithFileUrlDto>();
        }
        return this.codeEntityVersionWithFileUrlDto;
    }

}
