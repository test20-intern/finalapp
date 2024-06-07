package com.example.salesBackend.Controller;


import com.example.salesBackend.Entity.DailySchedule;
import com.example.salesBackend.Entity.EventsTitle;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.ClientCityService;
//import com.example.salesBackend.Service.DailyScheduleService;
import com.example.salesBackend.Service.DailyScheduleService;
import com.example.salesBackend.Service.EventsTitleService;
import com.example.salesBackend.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/Diary")
public class DiaryController {

    @Autowired
    private EventsTitleService eventsTitleService;

    @Autowired
    private ClientCityService clientCityService;

    @Autowired
    private DailyScheduleService dailyScheduleService;

    // Endpoint to get all events
    @GetMapping("/eventNames")
    public List<EventsTitle> getAllEvents() {

        return eventsTitleService.getAllEvents();
    }

    @GetMapping("/clientCities")
    public ResponseEntity<?> getClientCities(
            @RequestParam(required = false) String groupCode,
            @RequestParam(required = false) String branchCode,
            @RequestParam(required = false) String unitCode,
            @RequestParam(required = true) String agntnum,
            @RequestParam(required = false, defaultValue = "NULL") String city,
            @RequestParam(required = false) String userType) {

        try {
            List<Object[]> clientCities = clientCityService.getClientCities(groupCode, branchCode, unitCode, agntnum, city, userType);
            if (clientCities.isEmpty()) {
                throw new ValueNotExistException("No cities found for the provided agent number: " + agntnum);
            }

            // Flatten the list of lists into a single list of strings
            List<String> flattenedCities = clientCities.stream()
                    .flatMap(Arrays::stream)
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(flattenedCities, HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ClientCitiesNotFound",
                    "No client cities found for the provided agent number: " + agntnum), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetClientCitiesOperationFailed",
                    "Error getting client cities: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/saveDialyDiary")
    public ResponseEntity<DailySchedule> saveDailyDiary(@RequestBody DailySchedule dailySchedule) {
        try {
            DailySchedule savedDailySchedule = dailyScheduleService.saveDailySchedule(dailySchedule);
            return new ResponseEntity<>(savedDailySchedule, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<DailySchedule>((MultiValueMap<String, String>) AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<DailySchedule>((MultiValueMap<String, String>) AppResponse.error(null, "500", "Internal Server Error", "Saving is not complete",
                    "Error getting daily schedule: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getDailyDiary")
    public ResponseEntity<List<DailySchedule>> getDailyDiary(@RequestParam String agntnum) {
        List<DailySchedule> dailySchedules = dailyScheduleService.getDailySchedulesByagntnum(agntnum);
        return new ResponseEntity<>(dailySchedules, HttpStatus.OK);
    }

    @PutMapping("/updateDailyDiary")
    public ResponseEntity<String> updateDailyDiary(
            @RequestParam("agntnum") String agntnum,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam("newTitle") String newTitle) {

        dailyScheduleService.updateDailyDiary(agntnum, startDate, endDate, newTitle);
        return new ResponseEntity<>("Daily diary updated successfully", HttpStatus.OK);
    }









}