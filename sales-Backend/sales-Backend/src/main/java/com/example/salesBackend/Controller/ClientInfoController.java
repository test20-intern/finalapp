package com.example.salesBackend.Controller;

import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/clientinfo")
public class ClientInfoController {

    @Autowired
    private CLIENTINFOSERVICE clientInfoService;

    // auto incrementing id function
    private static long idCounter = 1;

    @GetMapping("/client-details")
    public ResponseEntity<AppResponse<List<Map<String, Object>>>> getClientDetailsByPolicyNo(
            @RequestParam(required = true) String POLICY_NO,
            @RequestParam(required = true) String userType

    ) {
        try {
            List<PG_CLIENTINFO> clientDetails = clientInfoService.getClientDetailsByPolicyNo(POLICY_NO,userType);

            // Convert the result to pass with field names and an incrementing "id".
            List<Map<String, Object>> formattedResult = clientDetails.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("CLIENT_NO", item.getCLIENT_NO());
                        formattedItem.put("NIC", item.getNIC());
                        formattedItem.put("NAME", item.getNAME());
                        formattedItem.put("ADD_1", item.getADD_1());
                        formattedItem.put("ADD_2", item.getADD_2());
                        formattedItem.put("ADD_CITY", item.getADD_CITY());
                        formattedItem.put("PCODE", item.getPCODE());
                        formattedItem.put("TEL_1", item.getTEL_1());
                        formattedItem.put("TEL_2", item.getTEL_2());
                        formattedItem.put("DOB", item.getDOB());

                        return formattedItem;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(AppResponse.ok(formattedResult), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ClientDetailsNotFound",
                    "Client details not found for policy number: " + POLICY_NO), HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetClientDetailsOperationFailed",
                    "Error getting client details: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Incrementing "id" method
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}
