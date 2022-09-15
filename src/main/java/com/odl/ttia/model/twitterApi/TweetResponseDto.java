package com.odl.ttia.model.twitterApi;

import java.util.List;

import com.odl.ttia.model.twitterApi.tweetV1Response.SearchV1Data;

public record TweetResponseDto(List<SearchV1Data> data) {

}
