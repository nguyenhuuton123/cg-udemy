import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    // Endpoint để tạo Payment Intent
    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> data) {

        // Lấy số tiền từ request body
        int amount = (int) data.get("amount");

        // Thiết lập các tham số cho PaymentIntent
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount((long) amount)
            .setCurrency("usd")
            .build();

        PaymentIntent intent;
        try {
            // Gửi yêu cầu tới Stripe để tạo PaymentIntent
            intent = PaymentIntent.create(params);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra khi tạo PaymentIntent
            throw new RuntimeException(e);
        }

        // Trả về client secret cho frontend
        Map<String, String> responseData = new HashMap<>();
        responseData.put("clientSecret", intent.getClientSecret());
        return responseData;
    }
}