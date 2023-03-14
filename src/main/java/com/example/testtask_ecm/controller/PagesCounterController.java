package com.example.testtask_ecm.controller;

import com.example.testtask_ecm.models.request.RequestDoc;
import com.example.testtask_ecm.models.response.ResponseDoc;
import com.example.testtask_ecm.service.PageCounterService;
import com.example.testtask_ecm.util.exception.EmptyUrlFieldException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api("Operations with UI - Swagger")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Client error"),
        @ApiResponse(responseCode = "500", description = "Server error")
})
public class PagesCounterController {

    private final PageCounterService pageCounterService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @Operation(summary = "Getting the number of pages by format or in general",
            description = "Pages are counted taking into account the format of documents." +
                    "If format is not placed, pages are counted in general.")
    @PostMapping("/docnumber")
    public ResponseEntity<ResponseDoc> getNumberOfPages(@RequestBody RequestDoc doc) {
        if (doc.getUrl() == null) throw new EmptyUrlFieldException("URL is not specified");
        return new ResponseEntity<>(pageCounterService.countPages(doc), HttpStatus.OK);
    }
}
