package com.odl.ttia.model.twitterApi.tweetV1Response;

import lombok.Data;

@Data
public class TwitterURLObject {
    private String url;
    private String expanded_url;
    private String display_url;
    private long[] indices;
}
