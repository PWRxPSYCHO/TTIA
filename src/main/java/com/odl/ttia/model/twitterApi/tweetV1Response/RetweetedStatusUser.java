package com.odl.ttia.model.twitterApi.tweetV1Response;

import lombok.Data;

@Data
public class RetweetedStatusUser {
    private long id;
    private String id_str;
    private String name;
    private String screen_name;
    private String location;
    private String description;
    private String url;
    private PurpleEntities entities;
    private boolean userProtected;
    private long followers_count;
    private long friends_count;
    private long listed_count;
    private String created_at;
    private long favourites_count;
    private Object utc_offset;
    private Object time_zone;
    private boolean geo_enabled;
    private boolean verified;
    private long statuses_count;
    private Object lang;
    private boolean contributors_enabled;
    private boolean is_translator;
    private boolean is_translation_enabled;
    private String profile_background_color;
    private String profile_background_image_url;
    private String profile_background_image_url_https;
    private boolean profile_background_tile;
    private String profile_image_url;
    private String profile_image_url_https;
    private String profile_banner_url;
    private String profile_link_color;
    private String profile_sidebar_border_color;
    private String profile_sidebar_fill_color;
    private String profile_text_color;
    private boolean profile_use_background_image;
    private boolean has_extended_profile;
    private boolean default_profile;
    private boolean default_profile_image;
    private Object following;
    private Object follow_request_sent;
    private Object notifications;
    private String translator_type;
    private Object[] withheld_in_countries;
}