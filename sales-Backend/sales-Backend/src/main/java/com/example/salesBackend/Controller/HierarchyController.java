package com.example.salesBackend.Controller;

import com.example.salesBackend.Dto.Request.HierarchyRequest;
import com.example.salesBackend.Exceptions.BadRequestRuntimeException;
import com.example.salesBackend.Exceptions.ValueNotExistException;
import com.example.salesBackend.util.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.salesBackend.Service.HierarchyService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Hierarchy")
public class HierarchyController {

    @Autowired
    private HierarchyService hierarchyService;

    @GetMapping("/getSOcode")
    public ResponseEntity<AppResponse<List<String>>> getSOCode(
            @RequestParam(name = "groupCode") String groupCode,
            @RequestParam(name = "branchCode") String branchCode,
            @RequestParam(name = "unitCode") String unitCode,
            @RequestParam(name = "userType") String userType) {
        try {
            HierarchyRequest request = new HierarchyRequest();
            request.setGroupCode(groupCode);
            request.setBranchCode(branchCode);
            request.setUnitCode(unitCode);
            request.setUserType(userType);

            List<String> soCodes = hierarchyService.getSOCode(request);

            return new ResponseEntity<>(AppResponse.ok(soCodes), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "SOCodeNotFound",
                    "SO code not found for the provided hierarchy"), HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetSOCodeOperationFailed",
                    "Error getting SO code: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUnitcode")
    public ResponseEntity<AppResponse<List<String>>> getUnitCode(
            @RequestParam(name = "groupCode") String groupCode,
            @RequestParam(name = "branchCode") String branchCode,
           // @RequestParam(name = "unitCode") String unitCode,
            @RequestParam(name = "userType") String userType) {
        try {
            HierarchyRequest request = new HierarchyRequest();
            request.setGroupCode(groupCode);
            request.setBranchCode(branchCode);
            //request.setUnitCode(unitCode);
            request.setUserType(userType);

            List<String> unitCodes = hierarchyService.getUnitCode(request);

            return new ResponseEntity<>(AppResponse.ok(unitCodes), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "UnitCodeNotFound",
                    "Unit code not found for the provided hierarchy"), HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetSOCodeOperationFailed",
                    "Error getting Unit code: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBranchcode")
    public ResponseEntity<AppResponse<List<String>>> getBranchCode(
            @RequestParam(name = "groupCode") String groupCode,
            //RequestParam(name = "branchCode") String branchCode,
            //@RequestParam(name = "unitCode") String unitCode,
            @RequestParam(name = "userType") String userType) {
        try {
            HierarchyRequest request = new HierarchyRequest();
            request.setGroupCode(groupCode);
            //request.setBranchCode(branchCode);
            //request.setUnitCode(unitCode);
            request.setUserType(userType);

            List<String> branchCodes = hierarchyService.getBranchCode(request);

            return new ResponseEntity<>(AppResponse.ok(branchCodes), HttpStatus.OK);
        } catch (ValueNotExistException e) {
            return new ResponseEntity<>(AppResponse.error(null, "404", "Not Found", "BranchCodeNotFound",
                    "Branch Code not found for the provided hierarchy"), HttpStatus.NOT_FOUND);
        } catch (BadRequestRuntimeException e) {
            return new ResponseEntity<>(AppResponse.error(null, "400", "Bad Request", "BadRequest",
                    "Bad request received: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(AppResponse.error(null, "500", "Internal Server Error", "GetSOCodeOperationFailed",
                    "Error getting Branch code: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
