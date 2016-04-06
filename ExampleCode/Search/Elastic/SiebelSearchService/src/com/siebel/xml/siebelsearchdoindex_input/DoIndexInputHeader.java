
package com.siebel.xml.siebelsearchdoindex_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DoIndex_InputHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DoIndex_InputHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BCName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalRecords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OpType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoIndex_CustomHeaderProps" type="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}DoIndex_CustomHeaderProps" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ListOfDoIndex_DataRecords" type="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}ListOfDoIndex_DataRecords" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoIndex_InputHeader", propOrder = {
         "bcName", "categoryName", "totalRecords", "opType", "doIndexCustomHeaderProps", "listOfDoIndexDataRecords"
    })
public class DoIndexInputHeader {

    @XmlElement(name = "BCName")
    protected String bcName;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "TotalRecords")
    protected String totalRecords;
    @XmlElement(name = "OpType")
    protected String opType;
    @XmlElement(name = "DoIndex_CustomHeaderProps")
    protected List<DoIndexCustomHeaderProps> doIndexCustomHeaderProps;
    @XmlElement(name = "ListOfDoIndex_DataRecords")
    protected ListOfDoIndexDataRecords listOfDoIndexDataRecords;

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
     * Gets the value of the totalRecords property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTotalRecords() {
        return totalRecords;
    }

    /**
     * Sets the value of the totalRecords property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotalRecords(String value) {
        this.totalRecords = value;
    }

    /**
     * Gets the value of the opType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOpType() {
        return opType;
    }

    /**
     * Sets the value of the opType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOpType(String value) {
        this.opType = value;
    }

    /**
     * Gets the value of the doIndexCustomHeaderProps property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doIndexCustomHeaderProps property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoIndexCustomHeaderProps().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DoIndexCustomHeaderProps }
     *
     *
     */
    public List<DoIndexCustomHeaderProps> getDoIndexCustomHeaderProps() {
        if (doIndexCustomHeaderProps == null) {
            doIndexCustomHeaderProps = new ArrayList<DoIndexCustomHeaderProps>();
        }
        return this.doIndexCustomHeaderProps;
    }

    /**
     * Gets the value of the listOfDoIndexDataRecords property.
     *
     * @return
     *     possible object is
     *     {@link ListOfDoIndexDataRecords }
     *
     */
    public ListOfDoIndexDataRecords getListOfDoIndexDataRecords() {
        return listOfDoIndexDataRecords;
    }

    /**
     * Sets the value of the listOfDoIndexDataRecords property.
     *
     * @param value
     *     allowed object is
     *     {@link ListOfDoIndexDataRecords }
     *
     */
    public void setListOfDoIndexDataRecords(ListOfDoIndexDataRecords value) {
        this.listOfDoIndexDataRecords = value;
    }

}
