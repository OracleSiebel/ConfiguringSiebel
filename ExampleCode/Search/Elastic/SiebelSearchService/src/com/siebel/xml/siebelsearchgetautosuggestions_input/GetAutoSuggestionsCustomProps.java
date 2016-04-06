
package com.siebel.xml.siebelsearchgetautosuggestions_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAutoSuggestions_CustomProps complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetAutoSuggestions_CustomProps">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PropName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PropValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAutoSuggestions_CustomProps", propOrder = { "propName", "propValue" })
public class GetAutoSuggestionsCustomProps {

    @XmlElement(name = "PropName", required = true)
    protected String propName;
    @XmlElement(name = "PropValue", required = true)
    protected String propValue;

    /**
     * Gets the value of the propName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPropName() {
        return propName;
    }

    /**
     * Sets the value of the propName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPropName(String value) {
        this.propName = value;
    }

    /**
     * Gets the value of the propValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPropValue() {
        return propValue;
    }

    /**
     * Sets the value of the propValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPropValue(String value) {
        this.propValue = value;
    }

}
