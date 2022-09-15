package com.odl.ttia.interfaces;

import java.util.List;

import com.odl.ttia.model.ttia.TtiaResponseDto;
import com.odl.ttia.model.twitterApi.tweetV1Response.RecentTweetSearchV1Response;

public interface TweetSearchServiceInterface {


    List<String> getUsers();

    List<String> getUrls();

    List<String> getMaliciousIps();

    RecentTweetSearchV1Response getTweetData();

    List<TtiaResponseDto> getAllData();

}
