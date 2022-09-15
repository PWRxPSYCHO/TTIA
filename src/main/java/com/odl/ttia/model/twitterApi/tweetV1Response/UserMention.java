package com.odl.ttia.model.twitterApi.tweetV1Response;

import lombok.Data;

@Data
public class UserMention {
    private String screen_name;
    private String name;
    private long id;
    private String id_str;
    private long[] indices;
}
