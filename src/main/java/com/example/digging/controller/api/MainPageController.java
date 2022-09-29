/*
package com.example.digging.controller.api;

import com.example.digging.domain.network.Header;
import com.example.digging.domain.network.response.RecentDiggingResponse;
import com.example.digging.service.MainPageLogicService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api")
@Api
public class MainPageController {
    @Autowired
    MainPageLogicService mainPageLogicService;

    @GetMapping("/recent")
    public ArrayList<RecentDiggingResponse> recentPostsRead(){
        return mainPageLogicService.recentPostsRead();
    }
}
*/
