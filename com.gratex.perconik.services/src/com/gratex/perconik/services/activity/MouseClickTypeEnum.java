
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MouseClickTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MouseClickTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Left"/>
 *     &lt;enumeration value="Middle"/>
 *     &lt;enumeration value="Right"/>
 *     &lt;enumeration value="DbLeft"/>
 *     &lt;enumeration value="DbMiddle"/>
 *     &lt;enumeration value="DbRight"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MouseClickTypeEnum")
@XmlEnum
public enum MouseClickTypeEnum {

    @XmlEnumValue("Left")
    LEFT("Left"),
    @XmlEnumValue("Middle")
    MIDDLE("Middle"),
    @XmlEnumValue("Right")
    RIGHT("Right"),
    @XmlEnumValue("DbLeft")
    DB_LEFT("DbLeft"),
    @XmlEnumValue("DbMiddle")
    DB_MIDDLE("DbMiddle"),
    @XmlEnumValue("DbRight")
    DB_RIGHT("DbRight");
    private final String value;

    MouseClickTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MouseClickTypeEnum fromValue(String v) {
        for (MouseClickTypeEnum c: MouseClickTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
