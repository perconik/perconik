
package com.gratex.perconik.services.uaca.vs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeCodeElementEventTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdeCodeElementEventTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VisibleStart"/>
 *     &lt;enumeration value="VisibleEnd"/>
 *     &lt;enumeration value="EditStart"/>
 *     &lt;enumeration value="EditEnd"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdeCodeElementEventTypeEnum")
@XmlEnum
public enum IdeCodeElementEventTypeEnum {

    @XmlEnumValue("VisibleStart")
    VISIBLE_START("VisibleStart"),
    @XmlEnumValue("VisibleEnd")
    VISIBLE_END("VisibleEnd"),
    @XmlEnumValue("EditStart")
    EDIT_START("EditStart"),
    @XmlEnumValue("EditEnd")
    EDIT_END("EditEnd");
    private final String value;

    IdeCodeElementEventTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdeCodeElementEventTypeEnum fromValue(String v) {
        for (IdeCodeElementEventTypeEnum c: IdeCodeElementEventTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
