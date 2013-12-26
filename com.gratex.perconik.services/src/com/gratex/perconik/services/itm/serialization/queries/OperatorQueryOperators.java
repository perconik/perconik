
package com.gratex.perconik.services.itm.serialization.queries;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatorQuery.Operators.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OperatorQuery.Operators">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="And"/>
 *     &lt;enumeration value="Or"/>
 *     &lt;enumeration value="Not"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperatorQuery.Operators", namespace = "http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries")
@XmlEnum
public enum OperatorQueryOperators {

    @XmlEnumValue("And")
    AND("And"),
    @XmlEnumValue("Or")
    OR("Or"),
    @XmlEnumValue("Not")
    NOT("Not");
    private final String value;

    OperatorQueryOperators(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperatorQueryOperators fromValue(String v) {
        for (OperatorQueryOperators c: OperatorQueryOperators.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
