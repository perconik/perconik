
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdePathTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdePathTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Server"/>
 *     &lt;enumeration value="RelativeLocal"/>
 *     &lt;enumeration value="ShortName"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdePathTypeEnum")
@XmlEnum
public enum IdePathTypeEnum {

    @XmlEnumValue("Server")
    SERVER("Server"),
    @XmlEnumValue("RelativeLocal")
    RELATIVE_LOCAL("RelativeLocal"),
    @XmlEnumValue("ShortName")
    SHORT_NAME("ShortName");
    private final String value;

    IdePathTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdePathTypeEnum fromValue(String v) {
        for (IdePathTypeEnum c: IdePathTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
