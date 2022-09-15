package com.odl.ttia.model.twitterApi.tweetV1Response;

import java.util.List;

import lombok.Data;

@Data
public class RecentTweetSearchV1Response {
    List<SearchV1Data> statuses;
}