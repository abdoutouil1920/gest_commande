package com.gestcomm.gestcomm.Controller;

import com.gestcomm.gestcomm.Model.Rapport;
import com.gestcomm.gestcomm.Service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reporting")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping
    public Rapport getRapport() {
        return reportingService.generateRapport();
    }
}
