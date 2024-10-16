package park20.Customer_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.domain.Customer.CustomerUsername;
import park20.Customer_Microservice.domain.ParkyWallet.ParkyWallet;

import javax.transaction.Transactional;
import java.math.BigDecimal;

public interface CustomerRepository  extends JpaRepository<Customer, String> {

    Customer getCustomerById(CustomerInternalId id);
    Customer getCustomerByUsername(CustomerUsername username);

    @Transactional
    @Modifying
    @Query(value="UPDATE Customer c SET c.wallet.amount = c.wallet.amount + :incrementValue")
    void updateAmountForAllCustomers(@Param("incrementValue") Integer incrementValue);
}
