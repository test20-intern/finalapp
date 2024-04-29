package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.BENEFICIARYSERVICE;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/birthdays")
public class BirthdaysController {

    @Autowired
    private BENEFICIARYSERVICE beneficiaryService;
    @Autowired
    private CLIENTINFOSERVICE clientInfoService;


    //API to get beneficiaries birthdays for a given date range.
    @GetMapping("/getBeneficiaryBirthdays")
    public ResponseEntity<AppResponse<List<BirthdaysResponse>>> getBeneficiaryBirthdays(
            @RequestParam String agntnum,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam String userType
    ) {
        // Set default values if startDate or endDate is not provided. Default dates are start date = today
        // end date = today + 7 days.
        if (startDate == null) {
            startDate = new Date(); // Today's date
        }
        if (endDate == null) {
            // Calculate end date as 7 days after today
            endDate = calculateEndDate(startDate, 7);
        }
        // exception handling part
        try {
            List<BirthdaysResponse> birthdaysResponseList = beneficiaryService.getBeneficiaryBirthdays(agntnum, startDate, endDate,userType);
            return new ResponseEntity<>(AppResponse.ok(birthdaysResponseList), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "BeneficiaryBirthdaysNotFound",
                    e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetBeneficiaryBirthdaysOperationFailed",
                    "Error getting beneficiary birthdays: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // API to get clients birthdays for a given data range.
    @GetMapping("/getClientBirthdays")
    public ResponseEntity<List<Map<String, Object>>> getClientInfo(
            @RequestParam String agentNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam String userType,
            @RequestParam(required = false) String groupCode,
            @RequestParam(required = false) String branchCode,
            @RequestParam(required = false) String unitCode) {
        try {
            List<Object[]> clientInfoList = clientInfoService.getClientInfoByAgentAndDateRange(agentNumber, startDate, endDate, userType, groupCode, branchCode, unitCode);

            // Transform Object arrays to Map<String, Object>
            List<Map<String, Object>> resultList = new ArrayList<>();
            for (Object[] objArray : clientInfoList) {
                Map<String, Object> formattedItem = new HashMap<>();
                formattedItem.put("clientNo", objArray[0]);
                formattedItem.put("name", objArray[1]);
                formattedItem.put("dob", objArray[2]);
                formattedItem.put("add_City", objArray[3]);
                formattedItem.put("tel_1", objArray[4]);
                formattedItem.put("tel_2", objArray[5]);
                resultList.add(formattedItem);
            }

            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // Helper method to calculate end date based on start date and daysToAdd
    private Date calculateEndDate(Date startDate, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return calendar.getTime();
    }


}
