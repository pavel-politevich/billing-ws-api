
package by.com.lifetech.billingapi.wsdl.message;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the by.com.lifetech.billingapi.wsdl.message package. 
 * <p>An ObjectFactory allows you to programmatically 
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

    private static final QName _GetAllMessages_QNAME = new QName("http://api.ws.rtn.idr.astelit.ukr/", "getAllMessages");
    private static final QName _GetAllMessagesResponse_QNAME = new QName("http://api.ws.rtn.idr.astelit.ukr/", "getAllMessagesResponse");
    private static final QName _TransmitBulkMessage_QNAME = new QName("http://api.ws.rtn.idr.astelit.ukr/", "transmitBulkMessage");
    private static final QName _TransmitBulkMessageResponse_QNAME = new QName("http://api.ws.rtn.idr.astelit.ukr/", "transmitBulkMessageResponse");
    private static final QName _TransmitMessage_QNAME = new QName("http://api.ws.rtn.idr.astelit.ukr/", "transmitMessage");
    private static final QName _TransmitMessageResponse_QNAME = new QName("http://api.ws.rtn.idr.astelit.ukr/", "transmitMessageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: by.com.lifetech.billingapi.wsdl.message
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MessageDescriptor }
     * 
     * @return
     *     the new instance of {@link MessageDescriptor }
     */
    public MessageDescriptor createMessageDescriptor() {
        return new MessageDescriptor();
    }

    /**
     * Create an instance of {@link MessageDescriptor.Templates }
     * 
     * @return
     *     the new instance of {@link MessageDescriptor.Templates }
     */
    public MessageDescriptor.Templates createMessageDescriptorTemplates() {
        return new MessageDescriptor.Templates();
    }

    /**
     * Create an instance of {@link Message }
     * 
     * @return
     *     the new instance of {@link Message }
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link Param }
     * 
     * @return
     *     the new instance of {@link Param }
     */
    public Param createParam() {
        return new Param();
    }

    /**
     * Create an instance of {@link SmppOptionalParameter }
     * 
     * @return
     *     the new instance of {@link SmppOptionalParameter }
     */
    public SmppOptionalParameter createSmppOptionalParameter() {
        return new SmppOptionalParameter();
    }

    /**
     * Create an instance of {@link Mm7Parameter }
     * 
     * @return
     *     the new instance of {@link Mm7Parameter }
     */
    public Mm7Parameter createMm7Parameter() {
        return new Mm7Parameter();
    }

    /**
     * Create an instance of {@link SmppMandatoryParameter }
     * 
     * @return
     *     the new instance of {@link SmppMandatoryParameter }
     */
    public SmppMandatoryParameter createSmppMandatoryParameter() {
        return new SmppMandatoryParameter();
    }

    /**
     * Create an instance of {@link TimeControl }
     * 
     * @return
     *     the new instance of {@link TimeControl }
     */
    public TimeControl createTimeControl() {
        return new TimeControl();
    }

    /**
     * Create an instance of {@link Identification }
     * 
     * @return
     *     the new instance of {@link Identification }
     */
    public Identification createIdentification() {
        return new Identification();
    }

    /**
     * Create an instance of {@link Language }
     * 
     * @return
     *     the new instance of {@link Language }
     */
    public Language createLanguage() {
        return new Language();
    }

    /**
     * Create an instance of {@link CommonLang }
     * 
     * @return
     *     the new instance of {@link CommonLang }
     */
    public CommonLang createCommonLang() {
        return new CommonLang();
    }

    /**
     * Create an instance of {@link WapPushExtension }
     * 
     * @return
     *     the new instance of {@link WapPushExtension }
     */
    public WapPushExtension createWapPushExtension() {
        return new WapPushExtension();
    }

    /**
     * Create an instance of {@link FlashSmsExtension }
     * 
     * @return
     *     the new instance of {@link FlashSmsExtension }
     */
    public FlashSmsExtension createFlashSmsExtension() {
        return new FlashSmsExtension();
    }

    /**
     * Create an instance of {@link MediaMessageExtension }
     * 
     * @return
     *     the new instance of {@link MediaMessageExtension }
     */
    public MediaMessageExtension createMediaMessageExtension() {
        return new MediaMessageExtension();
    }

    /**
     * Create an instance of {@link Attachment }
     * 
     * @return
     *     the new instance of {@link Attachment }
     */
    public Attachment createAttachment() {
        return new Attachment();
    }

    /**
     * Create an instance of {@link CustomBulkMessage }
     * 
     * @return
     *     the new instance of {@link CustomBulkMessage }
     */
    public CustomBulkMessage createCustomBulkMessage() {
        return new CustomBulkMessage();
    }

    /**
     * Create an instance of {@link CommonBulkMessage }
     * 
     * @return
     *     the new instance of {@link CommonBulkMessage }
     */
    public CommonBulkMessage createCommonBulkMessage() {
        return new CommonBulkMessage();
    }

    /**
     * Create an instance of {@link MessageDest }
     * 
     * @return
     *     the new instance of {@link MessageDest }
     */
    public MessageDest createMessageDest() {
        return new MessageDest();
    }

    /**
     * Create an instance of {@link GetAllMessages }
     * 
     * @return
     *     the new instance of {@link GetAllMessages }
     */
    public GetAllMessages createGetAllMessages() {
        return new GetAllMessages();
    }

    /**
     * Create an instance of {@link GetAllMessagesResponse }
     * 
     * @return
     *     the new instance of {@link GetAllMessagesResponse }
     */
    public GetAllMessagesResponse createGetAllMessagesResponse() {
        return new GetAllMessagesResponse();
    }

    /**
     * Create an instance of {@link TransmitBulkMessage }
     * 
     * @return
     *     the new instance of {@link TransmitBulkMessage }
     */
    public TransmitBulkMessage createTransmitBulkMessage() {
        return new TransmitBulkMessage();
    }

    /**
     * Create an instance of {@link TransmitBulkMessageResponse }
     * 
     * @return
     *     the new instance of {@link TransmitBulkMessageResponse }
     */
    public TransmitBulkMessageResponse createTransmitBulkMessageResponse() {
        return new TransmitBulkMessageResponse();
    }

    /**
     * Create an instance of {@link TransmitMessage }
     * 
     * @return
     *     the new instance of {@link TransmitMessage }
     */
    public TransmitMessage createTransmitMessage() {
        return new TransmitMessage();
    }

    /**
     * Create an instance of {@link TransmitMessageResponse }
     * 
     * @return
     *     the new instance of {@link TransmitMessageResponse }
     */
    public TransmitMessageResponse createTransmitMessageResponse() {
        return new TransmitMessageResponse();
    }

    /**
     * Create an instance of {@link GetAllMessagesResult }
     * 
     * @return
     *     the new instance of {@link GetAllMessagesResult }
     */
    public GetAllMessagesResult createGetAllMessagesResult() {
        return new GetAllMessagesResult();
    }

    /**
     * Create an instance of {@link GeneralResult }
     * 
     * @return
     *     the new instance of {@link GeneralResult }
     */
    public GeneralResult createGeneralResult() {
        return new GeneralResult();
    }

    /**
     * Create an instance of {@link MessageDescriptor.Templates.Entry }
     * 
     * @return
     *     the new instance of {@link MessageDescriptor.Templates.Entry }
     */
    public MessageDescriptor.Templates.Entry createMessageDescriptorTemplatesEntry() {
        return new MessageDescriptor.Templates.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMessages }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllMessages }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.ws.rtn.idr.astelit.ukr/", name = "getAllMessages")
    public JAXBElement<GetAllMessages> createGetAllMessages(GetAllMessages value) {
        return new JAXBElement<>(_GetAllMessages_QNAME, GetAllMessages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMessagesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllMessagesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.ws.rtn.idr.astelit.ukr/", name = "getAllMessagesResponse")
    public JAXBElement<GetAllMessagesResponse> createGetAllMessagesResponse(GetAllMessagesResponse value) {
        return new JAXBElement<>(_GetAllMessagesResponse_QNAME, GetAllMessagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmitBulkMessage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransmitBulkMessage }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.ws.rtn.idr.astelit.ukr/", name = "transmitBulkMessage")
    public JAXBElement<TransmitBulkMessage> createTransmitBulkMessage(TransmitBulkMessage value) {
        return new JAXBElement<>(_TransmitBulkMessage_QNAME, TransmitBulkMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmitBulkMessageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransmitBulkMessageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.ws.rtn.idr.astelit.ukr/", name = "transmitBulkMessageResponse")
    public JAXBElement<TransmitBulkMessageResponse> createTransmitBulkMessageResponse(TransmitBulkMessageResponse value) {
        return new JAXBElement<>(_TransmitBulkMessageResponse_QNAME, TransmitBulkMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmitMessage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransmitMessage }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.ws.rtn.idr.astelit.ukr/", name = "transmitMessage")
    public JAXBElement<TransmitMessage> createTransmitMessage(TransmitMessage value) {
        return new JAXBElement<>(_TransmitMessage_QNAME, TransmitMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmitMessageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransmitMessageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.ws.rtn.idr.astelit.ukr/", name = "transmitMessageResponse")
    public JAXBElement<TransmitMessageResponse> createTransmitMessageResponse(TransmitMessageResponse value) {
        return new JAXBElement<>(_TransmitMessageResponse_QNAME, TransmitMessageResponse.class, null, value);
    }

}
