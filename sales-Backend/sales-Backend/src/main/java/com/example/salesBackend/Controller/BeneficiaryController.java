package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.BENEFICIARYREQUEST;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.BENEFICIARYSERVICE;
import com.example.salesBackend.util.AppResponse;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/beneficiary")

public class BeneficiaryController {

    @Autowired
    private BENEFICIARYSERVICE beneficiaryService;

    @GetMapping("/beneficiary-details")
    public AppResponse<List<Map<String, Object>>> getBeneficiaryDetailsByPolicyNo(
            @RequestParam(required = true) String policyNo,
            @RequestParam(required = true) String userType

    ) {
        try {
            List<BENEFICIARYREQUEST> beneficiaryDetails = beneficiaryService.getBeneficiaryDetailsByPolicyNo(policyNo,userType);

            // Convert the result to pass with field names (Uppercase Letters) and an incrementing "id".
            List<Map<String, Object>> response = beneficiaryDetails.stream()
                    .map(item -> {
                        Map<String, Object> formattedItem = new HashMap<>();
                        formattedItem.put("id", generateIncrementingId()); // Incrementing "id"
                        formattedItem.put("RELATIONSHIP", item.getRELATIONSHIP());
                        formattedItem.put("NAME", item.getNAME());
                        formattedItem.put("DOB", item.getDOB());
                        formattedItem.put("PERCENTAGE", item.getPERCENTAGE());
                        return formattedItem;
                    })
                    .collect(Collectors.toList());
            return AppResponse.ok(response);

        } catch (ValueNotExistException e) {
            return AppResponse.error(null, "404", "Not Found", "BeneficiaryDetailsNotFound",
                    "Beneficiary details not found for policy number: " + policyNo);
        } catch (BadRequestRuntimeException e) {
            return AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage());
        } catch (Exception e) {
            return AppResponse.error(null, "500", "Internal Server Error", "GetBeneficiaryDetailsOperationFailed",
                    "Error getting beneficiary details: " + e.getMessage());
        }
    }


    // auto-incrementing id function
    private static long idCounter = 1;
    private synchronized long generateIncrementingId() {
        return idCounter++;
    }
}
