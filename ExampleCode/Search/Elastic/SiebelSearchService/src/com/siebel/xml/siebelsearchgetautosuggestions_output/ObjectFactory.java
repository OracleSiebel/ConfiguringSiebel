
package com.siebel.xml.siebelsearchgetautosuggestions_output;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchgetautosuggestions_output package.
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

    private final static QName _GetAutoSuggestionsOutputHeader_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Output", "GetAutoSuggestions_OutputHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchgetautosuggestions_output
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAutoSuggestionsOutputHeader }
     *
     */
    public GetAutoSuggestionsOutputHeader createGetAutoSuggestionsOutputHeader() {
        return new GetAutoSuggestionsOutputHeader();
    }

    /**
     * Create an instance of {@link GetAutoSuggestionsSuggestions }
     *
     */
    public GetAutoSuggestionsSuggestions createGetAutoSuggestionsSuggestions() {
        return new GetAutoSuggestionsSuggestions();
    }

    /**
     * Create an instance of {@link GetAutoSuggestionsOutputHeaderTopElmt }
     *
     */
    public GetAutoSuggestionsOutputHeaderTopElmt createGetAutoSuggestionsOutputHeaderTopElmt() {
        return new GetAutoSuggestionsOutputHeaderTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAutoSuggestionsOutputHeader }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchGetAutoSuggestions_Output",
                    name = "GetAutoSuggestions_OutputHeader")
    public JAXBElement<GetAutoSuggestionsOutputHeader> createGetAutoSuggestionsOutputHeader(GetAutoSuggestionsOutputHeader value) {
        return new JAXBElement<GetAutoSuggestionsOutputHeader>(_GetAutoSuggestionsOutputHeader_QNAME,
                                                               GetAutoSuggestionsOutputHeader.class, null, value);
    }

}
