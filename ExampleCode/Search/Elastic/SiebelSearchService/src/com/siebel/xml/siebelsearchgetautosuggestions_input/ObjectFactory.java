
package com.siebel.xml.siebelsearchgetautosuggestions_input;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchgetautosuggestions_input package.
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

    private final static QName _GetAutoSuggestionsInputHeader_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Input", "GetAutoSuggestions_InputHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchgetautosuggestions_input
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAutoSuggestionsInputHeader }
     *
     */
    public GetAutoSuggestionsInputHeader createGetAutoSuggestionsInputHeader() {
        return new GetAutoSuggestionsInputHeader();
    }

    /**
     * Create an instance of {@link GetAutoSuggestionsCustomProps }
     *
     */
    public GetAutoSuggestionsCustomProps createGetAutoSuggestionsCustomProps() {
        return new GetAutoSuggestionsCustomProps();
    }

    /**
     * Create an instance of {@link GetAutoSuggestionsInputHeaderTopElmt }
     *
     */
    public GetAutoSuggestionsInputHeaderTopElmt createGetAutoSuggestionsInputHeaderTopElmt() {
        return new GetAutoSuggestionsInputHeaderTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAutoSuggestionsInputHeader }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Input",
                    name = "GetAutoSuggestions_InputHeader")
    public JAXBElement<GetAutoSuggestionsInputHeader> createGetAutoSuggestionsInputHeader(GetAutoSuggestionsInputHeader value) {
        return new JAXBElement<GetAutoSuggestionsInputHeader>(_GetAutoSuggestionsInputHeader_QNAME,
                                                              GetAutoSuggestionsInputHeader.class, null, value);
    }

}
