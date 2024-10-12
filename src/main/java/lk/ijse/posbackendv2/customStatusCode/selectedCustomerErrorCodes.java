package lk.ijse.posbackendv2.customStatusCode;

import lk.ijse.posbackendv2.dto.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class selectedCustomerErrorCodes implements CustomerStatus {
    private int statusCodes;
    private String statusMessage;

}
