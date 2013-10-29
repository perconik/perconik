
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseButtonTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MouseButtonTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Left"/>
 *     &lt;enumeration value="Middle"/>
 *     &lt;enumeration value="Right"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MouseButtonTypeEnum")
@XmlEnum
public enum MouseButtonTypeEnum {

    @XmlEnumValue("Left")
    LEFT("Left"),
    @XmlEnumValue("Middle")
    MIDDLE("Middle"),
    @XmlEnumValue("Right")
    RIGHT("Right");
    private final String value;

    MouseButtonTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MouseButtonTypeEnum fromValue(String v) {
        for (MouseButtonTypeEnum c: MouseButtonTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
