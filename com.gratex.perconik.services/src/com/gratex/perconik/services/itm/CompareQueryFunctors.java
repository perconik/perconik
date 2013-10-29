
package com.gratex.perconik.services.itm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompareQuery.Functors.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CompareQuery.Functors">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EQ"/>
 *     &lt;enumeration value="GT"/>
 *     &lt;enumeration value="GTE"/>
 *     &lt;enumeration value="LT"/>
 *     &lt;enumeration value="LTE"/>
 *     &lt;enumeration value="NE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CompareQuery.Functors", namespace = "http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries")
@XmlEnum
public enum CompareQueryFunctors {

    EQ,
    GT,
    GTE,
    LT,
    LTE,
    NE;

    public String value() {
        return name();
    }

    public static CompareQueryFunctors fromValue(String v) {
        return valueOf(v);
    }

}
