
package com.siebel.xml.siebelsearchsearchinall_output;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInAll_OutputHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInAll_OutputHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalRecords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EOF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchInAll_ResultRecords" type="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Output}SearchInAll_ResultRecords" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="From" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="To" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInAll_OutputHeader", propOrder = {
         "totalRecords", "eof", "searchInAllResultRecords", "from", "to" })
public class SearchInAllOutputHeader {

    @XmlElement(name = "TotalRecords")
    protected String totalRecords;
    @XmlElement(name = "EOF")
    protected String eof;
    @XmlElement(name = "SearchInAll_ResultRecords")
    protected List<SearchInAllResultRecords> searchInAllResultRecords;
    @XmlElement(name = "From")
    protected String from;
    @XmlElement(name = "To")
    protected String to;

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
     * Gets the value of the eof property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEOF() {
        return eof;
    }

    /**
     * Sets the value of the eof property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEOF(String value) {
        this.eof = value;
    }

    /**
     * Gets the value of the searchInAllResultRecords property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchInAllResultRecords property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchInAllResultRecords().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchInAllResultRecords }
     *
     *
     */
    public List<SearchInAllResultRecords> getSearchInAllResultRecords() {
        if (searchInAllResultRecords == null) {
            searchInAllResultRecords = new ArrayList<SearchInAllResultRecords>();
        }
        return this.searchInAllResultRecords;
    }

    /**
     * Gets the value of the from property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTo(String value) {
        this.to = value;
    }

}
