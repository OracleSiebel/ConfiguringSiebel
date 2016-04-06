
package com.siebel.xml.siebelsearchcreateschema_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateSchema_FieldDef complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CreateSchema_FieldDef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AnalyzedFields" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Weightage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VisibilityField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KeyField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreateSchema_CustomProp" type="{http://www.siebel.com/xml/SiebelSearchCreateSchema_Input}CreateSchema_CustomProp" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateSchema_FieldDef", propOrder = {
         "fieldName", "fieldType", "analyzedFields", "weightage", "visibilityField", "keyField",
         "createSchemaCustomProp"
    })
public class CreateSchemaFieldDef {

    @XmlElement(name = "FieldName")
    protected String fieldName;
    @XmlElement(name = "FieldType")
    protected String fieldType;
    @XmlElement(name = "AnalyzedFields")
    protected String analyzedFields;
    @XmlElement(name = "Weightage")
    protected String weightage;
    @XmlElement(name = "VisibilityField")
    protected String visibilityField;
    @XmlElement(name = "KeyField")
    protected String keyField;
    @XmlElement(name = "CreateSchema_CustomProp")
    protected List<CreateSchemaCustomProp> createSchemaCustomProp;

    /**
     * Gets the value of the fieldName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the fieldType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Sets the value of the fieldType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFieldType(String value) {
        this.fieldType = value;
    }

    /**
     * Gets the value of the analyzedFields property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAnalyzedFields() {
        return analyzedFields;
    }

    /**
     * Sets the value of the analyzedFields property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAnalyzedFields(String value) {
        this.analyzedFields = value;
    }

    /**
     * Gets the value of the weightage property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getWeightage() {
        return weightage;
    }

    /**
     * Sets the value of the weightage property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setWeightage(String value) {
        this.weightage = value;
    }

    /**
     * Gets the value of the visibilityField property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVisibilityField() {
        return visibilityField;
    }

    /**
     * Sets the value of the visibilityField property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVisibilityField(String value) {
        this.visibilityField = value;
    }

    /**
     * Gets the value of the keyField property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getKeyField() {
        return keyField;
    }

    /**
     * Sets the value of the keyField property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setKeyField(String value) {
        this.keyField = value;
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

}
