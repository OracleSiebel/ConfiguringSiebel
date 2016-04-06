
package com.siebel.xml.siebelsearchsearchinall_input;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchsearchinall_input package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchInCategoryInputHeader_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchSearchInAll_Input", "SearchInCategory_InputHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchsearchinall_input
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchInCategoryInputHeader }
     *
     */
    public SearchInCategoryInputHeader createSearchInCategoryInputHeader() {
        return new SearchInCategoryInputHeader();
    }

    /**
     * Create an instance of {@link SearchInAllCustomProps }
     *
     */
    public SearchInAllCustomProps createSearchInAllCustomProps() {
        return new SearchInAllCustomProps();
    }

    /**
     * Create an instance of {@link SearchInCategoryInputHeaderTopElmt }
     *
     */
    public SearchInCategoryInputHeaderTopElmt createSearchInCategoryInputHeaderTopElmt() {
        return new SearchInCategoryInputHeaderTopElmt();
    }

    /**
     * Create an instance of {@link SearchInAllVisibilityProps }
     *
     */
    public SearchInAllVisibilityProps createSearchInAllVisibilityProps() {
        return new SearchInAllVisibilityProps();
    }

    /**
     * Create an instance of {@link SearchInAllSecureSearchDef }
     *
     */
    public SearchInAllSecureSearchDef createSearchInAllSecureSearchDef() {
        return new SearchInAllSecureSearchDef();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchInCategoryInputHeader }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchSearchInAll_Input",
                    name = "SearchInCategory_InputHeader")
    public JAXBElement<SearchInCategoryInputHeader> createSearchInCategoryInputHeader(SearchInCategoryInputHeader value) {
        return new JAXBElement<SearchInCategoryInputHeader>(_SearchInCategoryInputHeader_QNAME,
                                                            SearchInCategoryInputHeader.class, null, value);
    }

}
