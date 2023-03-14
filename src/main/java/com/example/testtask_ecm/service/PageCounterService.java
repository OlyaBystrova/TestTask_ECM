package com.example.testtask_ecm.service;

import com.example.testtask_ecm.models.request.RequestDoc;
import com.example.testtask_ecm.models.response.ResponseDoc;

public interface PageCounterService {
    ResponseDoc countPages(RequestDoc doc);
}
