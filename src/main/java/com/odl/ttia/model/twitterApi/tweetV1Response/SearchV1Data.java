package com.odl.ttia.model.twitterApi.tweetV1Response;

import lombok.Data;

@Data
public class SearchV1Data {
    private String created_at;
    private long id;
    private String id_str;
    private String text;
    private boolean truncated;
    private StatusEntities entities;
    private Metadata metadata;
    private String source;
    private Object in_reply_to_status_id;
    private Object in_reply_to_status_id_str;
    private Object in_reply_to_user_id;
    private Object in_reply_to_user_id_str;
    private Object in_reply_to_screen_name;
    private StatusUser user;
    private Object geo;
    private Object coordinates;
    private Object place;
    private Object contributors;
    private RetweetedStatus retweeted_status;
    private boolean is_quote_status;
    private long retweet_count;
    private long favorite_count;
    private boolean favorited;
    private boolean retweeted;
    private String lang;
}
