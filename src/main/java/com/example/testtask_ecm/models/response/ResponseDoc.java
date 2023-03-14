package com.example.testtask_ecm.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDoc {

    private String url;

    private Long docNumber;

    private Long pagesNumber;

}
