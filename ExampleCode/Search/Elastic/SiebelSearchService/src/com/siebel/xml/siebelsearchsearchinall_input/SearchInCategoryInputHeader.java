
package com.siebel.xml.siebelsearchsearchinall_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInCategory_InputHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInCategory_InputHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="From" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="To" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SortField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SortOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchInAll_SecureSearchDef" type="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Input}SearchInAll_SecureSearchDef" maxOccurs="unbounded"/>
 *         &lt;element name="SearchInAll_CustomProps" type="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Input}SearchInAll_CustomProps" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInCategory_InputHeader", propOrder = {
         "searchText", "from", "to", "sortField", "sortOrder", "searchInAllSecureSearchDef", "searchInAllCustomProps"
    })
public class SearchInCategoryInputHeader {

    @XmlElement(name = "SearchText")
    protected String searchText;
    @XmlElement(name = "From")
    protected String from;
    @XmlElement(name = "To")
    protected String to;
    @XmlElement(name = "SortField")
    protected String sortField;
    @XmlElement(name = "SortOrder")
    protected String sortOrder;
    @XmlElement(name = "SearchInAll_SecureSearchDef", required = true)
    protected List<SearchInAllSecureSearchDef> searchInAllSecureSearchDef;
    @XmlElement(name = "SearchInAll_CustomProps", required = true)
    protected List<SearchInAllCustomProps> searchInAllCustomProps;

    /**
     * Gets the value of the searchText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Sets the value of the searchText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSearchText(String value) {
        this.searchText = value;
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

    /**
     * Gets the value of the sortField property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * Sets the value of the sortField property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSortField(String value) {
        this.sortField = value;
    }

    /**
     * Gets the value of the sortOrder property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSortOrder(String value) {
        this.sortOrder = value;
    }

    /**
     * Gets the value of the searchInAllSecureSearchDef property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchInAllSecureSearchDef property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchInAllSecureSearchDef().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchInAllSecureSearchDef }
     *
     *
     */
    public List<SearchInAllSecureSearchDef> getSearchInAllSecureSearchDef() {
        if (searchInAllSecureSearchDef == null) {
            searchInAllSecureSearchDef = new ArrayList<SearchInAllSecureSearchDef>();
        }
        return this.searchInAllSecureSearchDef;
    }

    /**
     * Gets the value of the searchInAllCustomProps property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchInAllCustomProps property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchInAllCustomProps().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchInAllCustomProps }
     *
     *
     */
    public List<SearchInAllCustomProps> getSearchInAllCustomProps() {
        if (searchInAllCustomProps == null) {
            searchInAllCustomProps = new ArrayList<SearchInAllCustomProps>();
        }
        return this.searchInAllCustomProps;
    }

}
