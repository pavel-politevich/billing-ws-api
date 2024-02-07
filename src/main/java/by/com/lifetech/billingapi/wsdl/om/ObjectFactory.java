
package by.com.lifetech.billingapi.wsdl.om;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ukr.astelit.om package. 
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

    private final static QName _Fulfill_QNAME = new QName("astelit.ukr:om", "fulfill");
    private final static QName _GetFlexInstallmentsResponse_QNAME = new QName("astelit.ukr:om", "getFlexInstallmentsResponse");
    private final static QName _ProductComponent_QNAME = new QName("astelit.ukr:om", "productComponent");
    private final static QName _GetProductsResponse_QNAME = new QName("astelit.ukr:om", "getProductsResponse");
    private final static QName _OperateProduct_QNAME = new QName("astelit.ukr:om", "operateProduct");
    private final static QName _GetProducts_QNAME = new QName("astelit.ukr:om", "getProducts");
    private final static QName _OperateProductResponse_QNAME = new QName("astelit.ukr:om", "operateProductResponse");
    private final static QName _ProductSpecCharacteristicValue_QNAME = new QName("astelit.ukr:om", "productSpecCharacteristicValue");
    private final static QName _GetProductOfferingsResponse_QNAME = new QName("astelit.ukr:om", "getProductOfferingsResponse");
    private final static QName _GetFlexInstallments_QNAME = new QName("astelit.ukr:om", "getFlexInstallments");
    private final static QName _MarketSegmentCharacteristicValue_QNAME = new QName("astelit.ukr:om", "marketSegmentCharacteristicValue");
    private final static QName _ProdSpecCharValueUse_QNAME = new QName("astelit.ukr:om", "prodSpecCharValueUse");
    private final static QName _ProductSpecCharacteristic_QNAME = new QName("astelit.ukr:om", "productSpecCharacteristic");
    private final static QName _SimpleProductOffering_QNAME = new QName("astelit.ukr:om", "simpleProductOffering");
    private final static QName _AtomicMarketSegmentCharacteristic_QNAME = new QName("astelit.ukr:om", "atomicMarketSegmentCharacteristic");
    private final static QName _ProductCharacteristicValue_QNAME = new QName("astelit.ukr:om", "productCharacteristicValue");
    private final static QName _GetProductOfferings_QNAME = new QName("astelit.ukr:om", "getProductOfferings");
    private final static QName _FulfillResponse_QNAME = new QName("astelit.ukr:om", "fulfillResponse");
    private final static QName _ProductSpecCharUse_QNAME = new QName("astelit.ukr:om", "productSpecCharUse");
    private final static QName _GetAvailableProductOfferings_QNAME = new QName("astelit.ukr:om", "getAvailableProductOfferings");
    private final static QName _GetAvailableProductOfferingsResponse_QNAME = new QName("astelit.ukr:om", "getAvailableProductOfferingsResponse");
    private final static QName _AtomicProductSpecification_QNAME = new QName("astelit.ukr:om", "atomicProductSpecification");
    private final static QName _TimePeriod_QNAME = new QName("astelit.ukr:om", "timePeriod");
    private final static QName _AtomicMarketSegment_QNAME = new QName("astelit.ukr:om", "atomicMarketSegment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ukr.astelit.om
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchKey }
     * 
     */
    public SearchKey createSearchKey() {
        return new SearchKey();
    }

    /**
     * Create an instance of {@link SearchKey.Values }
     * 
     */
    public SearchKey.Values createSearchKeyValues() {
        return new SearchKey.Values();
    }

    /**
     * Create an instance of {@link MarketSegmentCharacteristicValue }
     * 
     */
    public MarketSegmentCharacteristicValue createMarketSegmentCharacteristicValue() {
        return new MarketSegmentCharacteristicValue();
    }

    /**
     * Create an instance of {@link ProdSpecCharValueUse }
     * 
     */
    public ProdSpecCharValueUse createProdSpecCharValueUse() {
        return new ProdSpecCharValueUse();
    }

    /**
     * Create an instance of {@link ProductSpecCharacteristic }
     * 
     */
    public ProductSpecCharacteristic createProductSpecCharacteristic() {
        return new ProductSpecCharacteristic();
    }

    /**
     * Create an instance of {@link Fulfill }
     * 
     */
    public Fulfill createFulfill() {
        return new Fulfill();
    }

    /**
     * Create an instance of {@link GetFlexInstallments }
     * 
     */
    public GetFlexInstallments createGetFlexInstallments() {
        return new GetFlexInstallments();
    }

    /**
     * Create an instance of {@link GetFlexInstallmentsResponse }
     * 
     */
    public GetFlexInstallmentsResponse createGetFlexInstallmentsResponse() {
        return new GetFlexInstallmentsResponse();
    }

    /**
     * Create an instance of {@link ProductComponent }
     * 
     */
    public ProductComponent createProductComponent() {
        return new ProductComponent();
    }

    /**
     * Create an instance of {@link AtomicMarketSegmentCharacteristic }
     * 
     */
    public AtomicMarketSegmentCharacteristic createAtomicMarketSegmentCharacteristic() {
        return new AtomicMarketSegmentCharacteristic();
    }

    /**
     * Create an instance of {@link ProductCharacteristicValue }
     * 
     */
    public ProductCharacteristicValue createProductCharacteristicValue() {
        return new ProductCharacteristicValue();
    }

    /**
     * Create an instance of {@link GetProductsResponse }
     * 
     */
    public GetProductsResponse createGetProductsResponse() {
        return new GetProductsResponse();
    }

    /**
     * Create an instance of {@link SimpleProductOffering }
     * 
     */
    public SimpleProductOffering createSimpleProductOffering() {
        return new SimpleProductOffering();
    }

    /**
     * Create an instance of {@link FulfillResponse }
     * 
     */
    public FulfillResponse createFulfillResponse() {
        return new FulfillResponse();
    }

    /**
     * Create an instance of {@link ProductSpecCharUse }
     * 
     */
    public ProductSpecCharUse createProductSpecCharUse() {
        return new ProductSpecCharUse();
    }

    /**
     * Create an instance of {@link GetAvailableProductOfferings }
     * 
     */
    public GetAvailableProductOfferings createGetAvailableProductOfferings() {
        return new GetAvailableProductOfferings();
    }

    /**
     * Create an instance of {@link GetAvailableProductOfferingsResponse }
     * 
     */
    public GetAvailableProductOfferingsResponse createGetAvailableProductOfferingsResponse() {
        return new GetAvailableProductOfferingsResponse();
    }

    /**
     * Create an instance of {@link OperateProduct }
     * 
     */
    public OperateProduct createOperateProduct() {
        return new OperateProduct();
    }

    /**
     * Create an instance of {@link GetProductOfferings }
     * 
     */
    public GetProductOfferings createGetProductOfferings() {
        return new GetProductOfferings();
    }

    /**
     * Create an instance of {@link TimePeriod }
     * 
     */
    public TimePeriod createTimePeriod() {
        return new TimePeriod();
    }

    /**
     * Create an instance of {@link OperateProductResponse }
     * 
     */
    public OperateProductResponse createOperateProductResponse() {
        return new OperateProductResponse();
    }

    /**
     * Create an instance of {@link ProductSpecCharacteristicValue }
     * 
     */
    public ProductSpecCharacteristicValue createProductSpecCharacteristicValue() {
        return new ProductSpecCharacteristicValue();
    }

    /**
     * Create an instance of {@link GetProductOfferingsResponse }
     * 
     */
    public GetProductOfferingsResponse createGetProductOfferingsResponse() {
        return new GetProductOfferingsResponse();
    }

    /**
     * Create an instance of {@link AtomicMarketSegment }
     * 
     */
    public AtomicMarketSegment createAtomicMarketSegment() {
        return new AtomicMarketSegment();
    }

    /**
     * Create an instance of {@link GetProducts }
     * 
     */
    public GetProducts createGetProducts() {
        return new GetProducts();
    }

    /**
     * Create an instance of {@link AtomicProductSpecification }
     * 
     */
    public AtomicProductSpecification createAtomicProductSpecification() {
        return new AtomicProductSpecification();
    }

    /**
     * Create an instance of {@link DistributionChannelSpecification }
     * 
     */
    public DistributionChannelSpecification createDistributionChannelSpecification() {
        return new DistributionChannelSpecification();
    }

    /**
     * Create an instance of {@link DistributionChannelCharacteristicValue }
     * 
     */
    public DistributionChannelCharacteristicValue createDistributionChannelCharacteristicValue() {
        return new DistributionChannelCharacteristicValue();
    }

    /**
     * Create an instance of {@link BundledProductOffering }
     * 
     */
    public BundledProductOffering createBundledProductOffering() {
        return new BundledProductOffering();
    }

    /**
     * Create an instance of {@link DistributionChannel }
     * 
     */
    public DistributionChannel createDistributionChannel() {
        return new DistributionChannel();
    }

    /**
     * Create an instance of {@link DistributionChannelCharacteristic }
     * 
     */
    public DistributionChannelCharacteristic createDistributionChannelCharacteristic() {
        return new DistributionChannelCharacteristic();
    }

    /**
     * Create an instance of {@link FlexibleInstallment }
     * 
     */
    public FlexibleInstallment createFlexibleInstallment() {
        return new FlexibleInstallment();
    }

    /**
     * Create an instance of {@link ProductBundle }
     * 
     */
    public ProductBundle createProductBundle() {
        return new ProductBundle();
    }

    /**
     * Create an instance of {@link ChainVar }
     * 
     */
    public ChainVar createChainVar() {
        return new ChainVar();
    }

    /**
     * Create an instance of {@link SearchKey.Values.Entry }
     * 
     */
    public SearchKey.Values.Entry createSearchKeyValuesEntry() {
        return new SearchKey.Values.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fulfill }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "fulfill")
    public JAXBElement<Fulfill> createFulfill(Fulfill value) {
        return new JAXBElement<Fulfill>(_Fulfill_QNAME, Fulfill.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlexInstallmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getFlexInstallmentsResponse")
    public JAXBElement<GetFlexInstallmentsResponse> createGetFlexInstallmentsResponse(GetFlexInstallmentsResponse value) {
        return new JAXBElement<GetFlexInstallmentsResponse>(_GetFlexInstallmentsResponse_QNAME, GetFlexInstallmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductComponent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "productComponent")
    public JAXBElement<ProductComponent> createProductComponent(ProductComponent value) {
        return new JAXBElement<ProductComponent>(_ProductComponent_QNAME, ProductComponent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getProductsResponse")
    public JAXBElement<GetProductsResponse> createGetProductsResponse(GetProductsResponse value) {
        return new JAXBElement<GetProductsResponse>(_GetProductsResponse_QNAME, GetProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperateProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "operateProduct")
    public JAXBElement<OperateProduct> createOperateProduct(OperateProduct value) {
        return new JAXBElement<OperateProduct>(_OperateProduct_QNAME, OperateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getProducts")
    public JAXBElement<GetProducts> createGetProducts(GetProducts value) {
        return new JAXBElement<GetProducts>(_GetProducts_QNAME, GetProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperateProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "operateProductResponse")
    public JAXBElement<OperateProductResponse> createOperateProductResponse(OperateProductResponse value) {
        return new JAXBElement<OperateProductResponse>(_OperateProductResponse_QNAME, OperateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSpecCharacteristicValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "productSpecCharacteristicValue")
    public JAXBElement<ProductSpecCharacteristicValue> createProductSpecCharacteristicValue(ProductSpecCharacteristicValue value) {
        return new JAXBElement<ProductSpecCharacteristicValue>(_ProductSpecCharacteristicValue_QNAME, ProductSpecCharacteristicValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductOfferingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getProductOfferingsResponse")
    public JAXBElement<GetProductOfferingsResponse> createGetProductOfferingsResponse(GetProductOfferingsResponse value) {
        return new JAXBElement<GetProductOfferingsResponse>(_GetProductOfferingsResponse_QNAME, GetProductOfferingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlexInstallments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getFlexInstallments")
    public JAXBElement<GetFlexInstallments> createGetFlexInstallments(GetFlexInstallments value) {
        return new JAXBElement<GetFlexInstallments>(_GetFlexInstallments_QNAME, GetFlexInstallments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketSegmentCharacteristicValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "marketSegmentCharacteristicValue")
    public JAXBElement<MarketSegmentCharacteristicValue> createMarketSegmentCharacteristicValue(MarketSegmentCharacteristicValue value) {
        return new JAXBElement<MarketSegmentCharacteristicValue>(_MarketSegmentCharacteristicValue_QNAME, MarketSegmentCharacteristicValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProdSpecCharValueUse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "prodSpecCharValueUse")
    public JAXBElement<ProdSpecCharValueUse> createProdSpecCharValueUse(ProdSpecCharValueUse value) {
        return new JAXBElement<ProdSpecCharValueUse>(_ProdSpecCharValueUse_QNAME, ProdSpecCharValueUse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSpecCharacteristic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "productSpecCharacteristic")
    public JAXBElement<ProductSpecCharacteristic> createProductSpecCharacteristic(ProductSpecCharacteristic value) {
        return new JAXBElement<ProductSpecCharacteristic>(_ProductSpecCharacteristic_QNAME, ProductSpecCharacteristic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleProductOffering }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "simpleProductOffering")
    public JAXBElement<SimpleProductOffering> createSimpleProductOffering(SimpleProductOffering value) {
        return new JAXBElement<SimpleProductOffering>(_SimpleProductOffering_QNAME, SimpleProductOffering.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AtomicMarketSegmentCharacteristic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "atomicMarketSegmentCharacteristic")
    public JAXBElement<AtomicMarketSegmentCharacteristic> createAtomicMarketSegmentCharacteristic(AtomicMarketSegmentCharacteristic value) {
        return new JAXBElement<AtomicMarketSegmentCharacteristic>(_AtomicMarketSegmentCharacteristic_QNAME, AtomicMarketSegmentCharacteristic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductCharacteristicValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "productCharacteristicValue")
    public JAXBElement<ProductCharacteristicValue> createProductCharacteristicValue(ProductCharacteristicValue value) {
        return new JAXBElement<ProductCharacteristicValue>(_ProductCharacteristicValue_QNAME, ProductCharacteristicValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductOfferings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getProductOfferings")
    public JAXBElement<GetProductOfferings> createGetProductOfferings(GetProductOfferings value) {
        return new JAXBElement<GetProductOfferings>(_GetProductOfferings_QNAME, GetProductOfferings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FulfillResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "fulfillResponse")
    public JAXBElement<FulfillResponse> createFulfillResponse(FulfillResponse value) {
        return new JAXBElement<FulfillResponse>(_FulfillResponse_QNAME, FulfillResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSpecCharUse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "productSpecCharUse")
    public JAXBElement<ProductSpecCharUse> createProductSpecCharUse(ProductSpecCharUse value) {
        return new JAXBElement<ProductSpecCharUse>(_ProductSpecCharUse_QNAME, ProductSpecCharUse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableProductOfferings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getAvailableProductOfferings")
    public JAXBElement<GetAvailableProductOfferings> createGetAvailableProductOfferings(GetAvailableProductOfferings value) {
        return new JAXBElement<GetAvailableProductOfferings>(_GetAvailableProductOfferings_QNAME, GetAvailableProductOfferings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableProductOfferingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "getAvailableProductOfferingsResponse")
    public JAXBElement<GetAvailableProductOfferingsResponse> createGetAvailableProductOfferingsResponse(GetAvailableProductOfferingsResponse value) {
        return new JAXBElement<GetAvailableProductOfferingsResponse>(_GetAvailableProductOfferingsResponse_QNAME, GetAvailableProductOfferingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AtomicProductSpecification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "atomicProductSpecification")
    public JAXBElement<AtomicProductSpecification> createAtomicProductSpecification(AtomicProductSpecification value) {
        return new JAXBElement<AtomicProductSpecification>(_AtomicProductSpecification_QNAME, AtomicProductSpecification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimePeriod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "timePeriod")
    public JAXBElement<TimePeriod> createTimePeriod(TimePeriod value) {
        return new JAXBElement<TimePeriod>(_TimePeriod_QNAME, TimePeriod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AtomicMarketSegment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om", name = "atomicMarketSegment")
    public JAXBElement<AtomicMarketSegment> createAtomicMarketSegment(AtomicMarketSegment value) {
        return new JAXBElement<AtomicMarketSegment>(_AtomicMarketSegment_QNAME, AtomicMarketSegment.class, null, value);
    }

}
