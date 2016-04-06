
package com.siebel.xml.siebelsearchcreateschema_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateSchema_InputHeaderTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CreateSchema_InputHeaderTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateSchema_InputHeader" type="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Input}CreateSchema_InputHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateSchema_InputHeaderTopElmt", propOrder = { "createSchemaInputHeader" })
public class CreateSchemaInputHeaderTopElmt {

    @XmlElement(name = "CreateSchema_InputHeader", required = true)
    protected CreateSchemaInputHeader createSchemaInputHeader;

    /**
     * Gets the value of the createSchemaInputHeader property.
     *
     * @return
     *     possible object is
     *     {@link CreateSchemaInputHeader }
     *
     */
    public CreateSchemaInputHeader getCreateSchemaInputHeader() {
        return createSchemaInputHeader;
    }

    /**
     * Sets the value of the createSchemaInputHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link CreateSchemaInputHeader }
     *
     */
    public void setCreateSchemaInputHeader(CreateSchemaInputHeader value) {
        this.createSchemaInputHeader = value;
    }

}
