package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Response.BirthdaysResponse;
import com.example.salesBackend.Entity.PG_BENEFICIARY;
import com.example.salesBackend.Entity.PG_CLIENTINFO;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.BENEFICIARYSERVICE;
import com.example.salesBackend.Service.CLIENTINFOSERVICE;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/birthdays")
public class BirthdaysController {

    @Autowired
    private BENEFICIARYSERVICE beneficiaryService;
    @Autowired
    private CLIENTINFOSERVICE clientInfoService;



    @GetMapping("/getBeneficiaryBirthdays")
    public ResponseEntity<AppResponse<List<BirthdaysResponse>>> getBeneficiaryBirthdays(
            @RequestParam String agntnum,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        // Set default values if startDate or endDate is not provided
        if (startDate == null) {
            startDate = new Date(); // Today's date
        }
        if (endDate == null) {
            // Calculate end date as 7 days after today
            endDate = calculateEndDate(startDate, 7);
        }

        try {
            List<BirthdaysResponse> birthdaysResponseList = beneficiaryService.getBeneficiaryBirthdays(agntnum, startDate, endDate);
            return new ResponseEntity<>(AppResponse.ok(birthdaysResponseList), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "BeneficiaryBirthdaysNotFound",
                    e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetBeneficiaryBirthdaysOperationFailed",
                    "Error getting beneficiary birthdays: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getClientBirthdays")
    public ResponseEntity<AppResponse<List<PG_CLIENTINFO>>> getClientBirthdays(
            @RequestParam String agntnum,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        // Set default values if startDate or endDate is not provided
        if (startDate == null) {
            startDate = new Date(); // Today's date
        }
        if (endDate == null) {
            // Calculate end date as 7 days after today
            endDate = calculateEndDate(startDate, 7);
        }

        try {
            List<PG_CLIENTINFO> clientInfoList = clientInfoService.getClientBirthdays(agntnum, startDate, endDate);

            if (clientInfoList.isEmpty()) {
                throw new ValueNotExistException("No client birthdays found for the specified criteria.");
            }

            return new ResponseEntity<>(AppResponse.ok(clientInfoList), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ClientBirthdaysNotFound",
                    e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetClientBirthdaysOperationFailed",
                    "Error getting client birthdays: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
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
