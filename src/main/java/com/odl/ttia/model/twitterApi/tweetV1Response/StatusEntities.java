package com.odl.ttia.model.twitterApi.tweetV1Response;

import java.util.List;

import lombok.Data;

@Data
public class StatusEntities {
    private Hashtag[] hashtags;
    private Object[] symbols;
    private UserMention[] user_mentions;
    private List<TwitterURLObject> urls;

}
