import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.client.preference.PreferenceTrackRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrearPpreferenciaPago {       //CrearPpreferenciaPago
    public static void main(String[] args) {

        MercadoPagoConfig.setAccessToken("TEST-5473799711250780-091621-9ea8893d8d31636192b1b3e34168d367-439173641");

        PreferenceClient client = new PreferenceClient();

        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .title("Dummy Title")
                        .description("Dummy description")
                        .pictureUrl("http://www.myapp.com/myimage.jpg")
                        .quantity(1)
                        .currencyId("ARG")
                        .unitPrice(new BigDecimal("10"))
                        .build();
        items.add(item);

        List<PreferenceTrackRequest> tracks = new ArrayList<>();
       // PreferenceTrackRequest googleTrack = PreferenceTrackRequest.builder().type("google_ad").build();

       // tracks.add(googleTrack);

        PreferenceRequest request = PreferenceRequest.builder().items(items).tracks(tracks).build();

        try {
            final Preference preference = client.create(request);
            final String initPoint = preference.getInitPoint();
            System.out.println(  "initPoint"                +initPoint.toString()      );
        } catch (MPException e) {
            e.printStackTrace();
        } catch (MPApiException e) {
            e.printStackTrace();
        }
    }
}
