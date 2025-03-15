package com.scan_app.output_service.client;

import com.scan_app.output_service.dto.userServiceCommunication.CheckScanRequest;
import com.scan_app.output_service.dto.userServiceCommunication.UserScanSaveRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8083")
public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/user/scan/saveScan")
    public String saveScan(@RequestBody UserScanSaveRequest userScanSaveRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/api/user/scan/getScansId/{userId}")
    public List<String> getScansByUserId(@PathVariable String userId);

    @RequestMapping(method = RequestMethod.POST, value = "/api/user/scan/checkScans")
    public Boolean scansBelongToUser(@RequestBody CheckScanRequest checkScanRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/api/user/scan/deleteScans")
    public Boolean deleteScans(@RequestBody CheckScanRequest checkScanRequest);
}