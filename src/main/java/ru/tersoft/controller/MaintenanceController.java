package ru.tersoft.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.*;
import ru.tersoft.entity.Maintenance;
import ru.tersoft.service.MaintenanceService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("attractions/main/")
@Api(description = "Maintenance settings for attractions", tags = {"Maintenance"})
public class MaintenanceController {
    @Resource(name = "MaintenanceService")
    private MaintenanceService maintenanceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get list of maintenance dates by attraction id or date")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "Access token", required = true, dataType = "string", paramType = "query"),
    })
    public List<Maintenance> getMaintenances
            (@RequestParam(value = "id", required = false) UUID attractionid,
             @RequestParam(value = "date", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date today) {
        if(today != null) {
            return (List<Maintenance>) maintenanceService.getAll(today);
        } else if(attractionid != null) {
            return (List<Maintenance>) maintenanceService.getAll(attractionid);
        }
        else throw new RequestRejectedException("You must pass attraction id or date");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Add new maintenance period")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "Access token", required = true, dataType = "string", paramType = "query"),
    })
    public ResponseEntity<?> add(@RequestBody Maintenance maintenance) {
        if(maintenance != null) {
            maintenanceService.add(maintenance);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
