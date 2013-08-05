
package com.gratex.perconik.iactivitysvc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeCodeOperationTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdeCodeOperationTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PasteFromWeb"/>
 *     &lt;enumeration value="SelectionChanged"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdeCodeOperationTypeEnum")
@XmlEnum
public enum IdeCodeOperationTypeEnum {

    @XmlEnumValue("PasteFromWeb")
    PASTE_FROM_WEB("PasteFromWeb"),
    @XmlEnumValue("SelectionChanged")
    SELECTION_CHANGED("SelectionChanged");
    private final String value;

    IdeCodeOperationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdeCodeOperationTypeEnum fromValue(String v) {
        for (IdeCodeOperationTypeEnum c: IdeCodeOperationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
