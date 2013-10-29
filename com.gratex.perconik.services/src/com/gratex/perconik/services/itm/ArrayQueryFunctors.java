
package com.gratex.perconik.services.itm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayQuery.Functors.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ArrayQuery.Functors">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="In"/>
 *     &lt;enumeration value="NotIn"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ArrayQuery.Functors", namespace = "http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries")
@XmlEnum
public enum ArrayQueryFunctors {

    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("In")
    IN("In"),
    @XmlEnumValue("NotIn")
    NOT_IN("NotIn");
    private final String value;

    ArrayQueryFunctors(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ArrayQueryFunctors fromValue(String v) {
        for (ArrayQueryFunctors c: ArrayQueryFunctors.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
