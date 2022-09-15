package com.odl.ttia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odl.ttia.interfaces.api.ApiGet;
import com.odl.ttia.model.ttia.TtiaResponseDto;
import com.odl.ttia.model.ttia.TtiaSimpleResponseDto;
import com.odl.ttia.model.twitterApi.TweetResponseDto;
import com.odl.ttia.service.TweetSearchService;


@RestController
@RequestMapping("/api/v1/tweets")
public class TweetSearchController {

    @Autowired
    TweetSearchService tweetSearchService;

    @ApiGet("/users")
    public TtiaSimpleResponseDto getUsers() {
        return new TtiaSimpleResponseDto(tweetSearchService.getUsers());
    }

    @ApiGet("/urls")
    public TtiaSimpleResponseDto getUrls() {
        return new TtiaSimpleResponseDto(tweetSearchService.getUrls());
    }

    @ApiGet("/malicious-ips")
    public TtiaSimpleResponseDto getMaliciousIps() {
        return new TtiaSimpleResponseDto(tweetSearchService.getMaliciousIps());
    }

    @ApiGet("/tweet-data")
    public TweetResponseDto getTweetData() {
        return new TweetResponseDto(tweetSearchService.getTweetData().getStatuses());
    }

    @ApiGet("/all-data")
    public List<TtiaResponseDto> getAllData() {
        return tweetSearchService.getAllData();
    }

}
