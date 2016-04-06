
package com.siebel.xml.siebelsearchcreateschema_output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateSchema_StatusTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CreateSchema_StatusTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateSchema_Status" type="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Output}CreateSchema_Status"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateSchema_StatusTopElmt", propOrder = { "createSchemaStatus" })
public class CreateSchemaStatusTopElmt {

    @XmlElement(name = "CreateSchema_Status", required = true)
    protected CreateSchemaStatus createSchemaStatus;

    /**
     * Gets the value of the createSchemaStatus property.
     *
     * @return
     *     possible object is
     *     {@link CreateSchemaStatus }
     *
     */
    public CreateSchemaStatus getCreateSchemaStatus() {
        return createSchemaStatus;
    }

    /**
     * Sets the value of the createSchemaStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link CreateSchemaStatus }
     *
     */
    public void setCreateSchemaStatus(CreateSchemaStatus value) {
        this.createSchemaStatus = value;
    }

}
