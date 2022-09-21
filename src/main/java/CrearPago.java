import com.mercadopago.MercadoPago;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.customer.CustomerCardClient;
import com.mercadopago.client.customer.CustomerClient;
import com.mercadopago.client.payment.*;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.common.Identification;
import com.mercadopago.resources.customer.Customer;
import com.mercadopago.resources.customer.CustomerCard;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CrearPago {   //CrearPago

    public static void main(String[] args) throws MPException, MPApiException {

         try {

     MercadoPagoConfig.setAccessToken("cuentatestin...xxxxxxxxxxxxxxxxxxxx");
        PaymentClient client = new PaymentClient();

        List<PaymentItemRequest> items = new ArrayList<>();

        PaymentItemRequest item =
                PaymentItemRequest.builder()
                        .id("PR0001")
                        .title("Point Mini")
                        .description("Producto Point para cobros con tarjetas mediante bluetooth")
                        .pictureUrl(
                                "https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium@2x.png")
                        .categoryId("electronics")
                        .quantity(1)
                        .unitPrice(new BigDecimal("58.8"))
                        .build();

        items.add(item);

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .additionalInfo(
                                PaymentAdditionalInfoRequest.builder()
                                        .items(items)
                                        .payer(
                                                PaymentAdditionalInfoPayerRequest.builder()
                                                        .firstName("Test")
                                                        .lastName("Test")
                                                        .phone(
                                                                PhoneRequest.builder().areaCode("11").number("987654321").build())
                                                        .build())
                                        .shipments(
                                                PaymentShipmentsRequest.builder()
                                                        .receiverAddress(
                                                                PaymentReceiverAddressRequest.builder()
                                                                        .zipCode("12312-123")
                                                                        .stateName("Rio de Janeiro")
                                                                        .cityName("Buzios")
                                                                        .streetName("Av das Nacoes Unidas")
                                                                        .streetNumber("3003")
                                                                        .build())
                                                        .build())
                                        .build())
                        .description("Payment for product")
                        .externalReference("MP0001")
                        .installments(1)
                        .order(PaymentOrderRequest.builder().type("mercadolibre").build())
                        .payer(PaymentPayerRequest.builder().entityType("individual").type("customer").build())
                        .paymentMethodId("visa")
                        .transactionAmount(new BigDecimal("58.8"))
                        .build();

//        final Payment payment = client.create(createRequest);
//        final String currencyId = payment.getCurrencyId();

             System.out.println("getMerchantAccountId "+createRequest.getMerchantAccountId());
             System.out.println("getToken "+createRequest. getToken()         );
             System.out.println("getPaymentMethodId "+createRequest.  getPaymentMethodId()       );

             final String callbackUrl = createRequest.getCallbackUrl();
             System.out.println(callbackUrl);
             final String externalReference = createRequest.getExternalReference();
             System.out.println("   externalReference  "     +  externalReference.toString()   );
             final String issuerId = createRequest.getIssuerId();

             System.out.println("  issuerId   "     +   issuerId  );

             final PaymentOrderRequest order = createRequest.getOrder();
             final String type = order.getType();
             System.out.println(" type    "     + type    );


             final PaymentPointOfInteractionRequest pointOfInteraction = createRequest.getPointOfInteraction();
             final String linkedTo = pointOfInteraction.getLinkedTo();
             System.out.println("  linkedTo   "     +  linkedTo   );


             final PaymentMerchantServicesRequest merchantServices = createRequest.getMerchantServices();
             final PaymentTransactionDetailsRequest transactionDetails = createRequest.getTransactionDetails();
             final String financialInstitution = transactionDetails.getFinancialInstitution();
             System.out.println(" financialInstitution "+financialInstitution.toString());

         }catch (Exception e){
             System.out.println(e.getMessage());
         }

    }}
