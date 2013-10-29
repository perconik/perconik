
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WindowStateTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WindowStateTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Normal"/>
 *     &lt;enumeration value="Minimized"/>
 *     &lt;enumeration value="Maximized"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WindowStateTypeEnum")
@XmlEnum
public enum WindowStateTypeEnum {

    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("Minimized")
    MINIMIZED("Minimized"),
    @XmlEnumValue("Maximized")
    MAXIMIZED("Maximized");
    private final String value;

    WindowStateTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WindowStateTypeEnum fromValue(String v) {
        for (WindowStateTypeEnum c: WindowStateTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
