package park20.Customer_Microservice.domain.ParkyWallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.dto.CustomerDTO;
import park20.Customer_Microservice.dto.ParkyWalletDTO;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;

public class ParkyWallet implements ValueObject<ParkyWallet> {

    @JsonProperty
    @Column(nullable = false)
    private Integer amount;

    protected ParkyWallet() {
    }

    public ParkyWallet(Integer amount) {
        Validate.notNull(amount);
       // Validate.notEmpty(ammount);
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void addCoins(Integer amount) {
        this.amount = amount;
    }

    public Integer removeCoins(Integer value){
        if(amount-value < 0){
            return amount;}
        return -1;
    }

    @Override
    public boolean sameValueAs(ParkyWallet other) {
        return false;
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public ParkyWalletDTO toDto() {
        ParkyWalletDTO dto = new ParkyWalletDTO();
        dto.amount = this.amount.toString();
        return dto;
    }
}
