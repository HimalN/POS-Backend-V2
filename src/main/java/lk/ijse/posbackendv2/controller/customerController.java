package lk.ijse.posbackendv2.controller;


import lk.ijse.posbackendv2.dto.impl.CustomerDTO;
import lk.ijse.posbackendv2.exception.DataPersistException;
import lk.ijse.posbackendv2.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http:127.0.0.1:5500")
@RestController
@RequestMapping("api/v1/customers")
public class customerController {

    @Autowired
    CustomerService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(
            @RequestPart ("customerId" )String customerId,
            @RequestPart ("name" )String name,
            @RequestPart ("address" )String address,
            @RequestPart ("phone" )String phone
    )
    {
        try {
            var buildCustomerDTO = new CustomerDTO();
            buildCustomerDTO.setId(customerId);
            buildCustomerDTO.setName(name);
            buildCustomerDTO.setAddress(address);
            buildCustomerDTO.setPhone(phone);
            userService.saveCustomer(buildCustomerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
