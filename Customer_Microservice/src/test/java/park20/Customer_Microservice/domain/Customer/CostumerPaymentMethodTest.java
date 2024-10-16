package park20.Customer_Microservice.domain.Customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPaymentMethodTest {

    @Test
    void testCreatePaymentMethod(){
        PaymentMethodInternalId id = PaymentMethodInternalId.genNewId();
        String phone = "912345678";


        CustomerPaymentMethod p = new CustomerPaymentMethod(phone,id);

        assertNotNull(p);
    }

    @Test
    void testCreateInvalidPaymentMethod(){
        PaymentMethodInternalId id = PaymentMethodInternalId.genNewId();

        CustomerPaymentMethod p = new CustomerPaymentMethod(null,id);

        assertNotNull(p);
    }

}