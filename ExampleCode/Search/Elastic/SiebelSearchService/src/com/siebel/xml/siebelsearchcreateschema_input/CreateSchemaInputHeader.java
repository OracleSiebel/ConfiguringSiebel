
package com.siebel.xml.siebelsearchcreateschema_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateSchema_InputHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CreateSchema_InputHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BCName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreateSchema_CustomProp" type="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Input}CreateSchema_CustomProp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CreateSchema_FieldDef" type="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Input}CreateSchema_FieldDef" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateSchema_InputHeader", propOrder = {
         "bcName", "categoryName", "createSchemaCustomProp", "createSchemaFieldDef" })
public class CreateSchemaInputHeader {

    @XmlElement(name = "BCName")
    protected String bcName;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "CreateSchema_CustomProp")
    protected List<CreateSchemaCustomProp> createSchemaCustomProp;
    @XmlElement(name = "CreateSchema_FieldDef", required = true)
    protected List<CreateSchemaFieldDef> createSchemaFieldDef;

    /**
     * Gets the value of the bcName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBCName() {
        return bcName;
    }

    /**
     * Sets the value of the bcName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBCName(String value) {
        this.bcName = value;
    }

    /**
     * Gets the value of the categoryName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the createSchemaCustomProp property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the createSchemaCustomProp property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreateSchemaCustomProp().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreateSchemaCustomProp }
     *
     *
     */
    public List<CreateSchemaCustomProp> getCreateSchemaCustomProp() {
        if (createSchemaCustomProp == null) {
            createSchemaCustomProp = new ArrayList<CreateSchemaCustomProp>();
        }
        return this.createSchemaCustomProp;
    }

    /**
     * Gets the value of the createSchemaFieldDef property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the createSchemaFieldDef property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreateSchemaFieldDef().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreateSchemaFieldDef }
     *
     *
     */
    public List<CreateSchemaFieldDef> getCreateSchemaFieldDef() {
        if (createSchemaFieldDef == null) {
            createSchemaFieldDef = new ArrayList<CreateSchemaFieldDef>();
        }
        return this.createSchemaFieldDef;
    }

}
