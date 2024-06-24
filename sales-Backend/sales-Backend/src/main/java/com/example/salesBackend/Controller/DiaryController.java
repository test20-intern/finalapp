package com.example.salesBackend.Controller;


import com.example.salesBackend.Entity.DailySchedule;
import com.example.salesBackend.Entity.EventsTitle;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.Service.*;
//import com.example.salesBackend.Service.DailyScheduleService;
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
    private ClientDetailsForDiary clientDetailService;

    @Autowired
    private ProspectDetailService prospectDetailService;

    @Autowired
    private DailyScheduleService dailyScheduleService;

    //----------------starts getting details to sidebar from database.-----------------------
    // Endpoint to get all events
    @GetMapping("/eventNames")
    public List<EventsTitle> getAllEvents() {

        return eventsTitleService.getAllEvents();
    }

    //get client cities for agent number
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

    @GetMapping("/clientCitiesAndNamesForDiary")
    public ResponseEntity<?> getClientCitiesAndNamesForDiary(

            @RequestParam(required = true) String agntnum,
            @RequestParam(required = true) String city) {

        try {
            List<Object[]> clientCitiesAndNamesForDiary = clientDetailService.getClientCitiesAndNamesForDiary(agntnum, city);
            if (clientCitiesAndNamesForDiary.isEmpty()) {
                throw new ValueNotExistException("No clients found for the provided agent number: " + agntnum);
            }

            return new ResponseEntity<>(clientCitiesAndNamesForDiary, HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ClientsNotFound",
                    "No client  found for the provided agent number: " + agntnum), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetClientNamesOperationFailed",
                    "Error getting clients: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suspectDetails")
    public ResponseEntity<?> getSuspectsByAgentNumberForDiary(

            @RequestParam(required = true) String agntnum,
            @RequestParam (required = false)String city
    )
    {

        try {
            List<Object[]> SuspectNamesForDiary = prospectDetailService.getSuspectsByAgentNumberForDiary(agntnum,city);
            if (SuspectNamesForDiary.isEmpty()) {
                throw new ValueNotExistException("No clients found for the provided agent number: " + agntnum);
            }

            return new ResponseEntity<>(SuspectNamesForDiary, HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ClientsNotFound",
                    "No suspect found for the provided agent number: " + agntnum), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetSuspectsOperationFailed",
                    "Error getting clients: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/prospectDetails")
    public ResponseEntity<?> getProspectsByAgentNumberForDiary(

            @RequestParam(required = true) String agntnum,
            @RequestParam(required = false) String city
            ) {

        try {
            List<Object[]> ProspectsNamesForDiary = prospectDetailService.getProspectsByAgentNumberForDiary(agntnum,city);
            if (ProspectsNamesForDiary.isEmpty()) {
                throw new ValueNotExistException("No clients found for the provided agent number: " + agntnum);
            }

            return new ResponseEntity<>(ProspectsNamesForDiary, HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "ClientsNotFound",
                    "No prospect found for the provided agent number: " + agntnum), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetProspectsOperationFailed",
                    "Error getting clients: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







 // ---------------starts CRUD operations for diary events ( grid )---------------------------
    @PostMapping("/saveDailyDiary")
    public ResponseEntity<DailySchedule> saveDailyDiary(@RequestBody DailySchedule dailySchedule) {
        try {
            DailySchedule savedDailySchedule = dailyScheduleService.saveDailySchedule(dailySchedule);
            return new ResponseEntity<>(savedDailySchedule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/events/{agntnum}")
    public ResponseEntity<List<DailySchedule>> getEventsByAgntnum(@PathVariable String agntnum) {
        try {
            List<DailySchedule> events = dailyScheduleService.getDailySchedulesByAgntnum(agntnum);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<DailySchedule> updateDailySchedule(@PathVariable Long id, @RequestBody DailySchedule dailySchedule) {
        try {
            DailySchedule updatedDailySchedule = dailyScheduleService.updateDailySchedule(id, dailySchedule);
            if (updatedDailySchedule != null) {
                return new ResponseEntity<>(updatedDailySchedule, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDailySchedule(@PathVariable Long id) {
        try {
            dailyScheduleService.deleteDailySchedule(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
