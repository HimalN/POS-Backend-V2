package lk.ijse.posbackendv2.customStatusCode;

import lk.ijse.posbackendv2.dto.productStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class selectedProductErrorCodes implements productStatus {
    private int statusCode;
    private String statusMessage;
}
