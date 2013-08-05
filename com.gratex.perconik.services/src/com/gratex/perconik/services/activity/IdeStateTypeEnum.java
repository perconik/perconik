
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeStateTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdeStateTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Debug"/>
 *     &lt;enumeration value="Build"/>
 *     &lt;enumeration value="Design"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdeStateTypeEnum")
@XmlEnum
public enum IdeStateTypeEnum {

    @XmlEnumValue("Debug")
    DEBUG("Debug"),
    @XmlEnumValue("Build")
    BUILD("Build"),
    @XmlEnumValue("Design")
    DESIGN("Design");
    private final String value;

    IdeStateTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdeStateTypeEnum fromValue(String v) {
        for (IdeStateTypeEnum c: IdeStateTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
