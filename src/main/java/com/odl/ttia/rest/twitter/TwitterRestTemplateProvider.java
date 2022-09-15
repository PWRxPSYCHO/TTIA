package com.odl.ttia.rest.twitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.odl.ttia.config.Token;
import com.odl.ttia.model.twitterApi.searchUserV2Response.SearchUserResponse;
import com.odl.ttia.model.twitterApi.searchUserV2Response.User;
import com.odl.ttia.model.twitterApi.tweetV1Response.RecentTweetSearchV1Response;


public class TwitterRestTemplateProvider {

    private RestTemplate rest = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request;

    public TwitterRestTemplateProvider(Token token) {
        headers.add("Authorization",
                "Bearer " +  token.getBearer());
        request = new HttpEntity<>(headers);

    }

    /**
     * Fetches Tweet Search V1 data for 1 page of tweet data using teh #opendir hashtag
     * @return Tweet Search V1 response
     */
    public RecentTweetSearchV1Response getTweetData() {
        String url = "https://api.twitter.com/1.1/search/tweets.json?q={query}&count=100";

        //TODO: Configure count=100 depending on subscription level and look into API limiting
        return rest.exchange(url, HttpMethod.GET, request, RecentTweetSearchV1Response.class, "exclude:retweets(%23opendir)").getBody();
    }

    /**
     * Fetches user data from TwitterAPI
     * @param userIds list of user id's to pass along to twitter api
     * @return list of usernames from user id's
     */
    public List<String> getUserData(List<String> userIds) {
        String url = "https://api.twitter.com/2/users?ids={IDS}&tweet.fields=id";
        SearchUserResponse resp = rest
                .exchange(url, HttpMethod.GET, request, SearchUserResponse.class, String.join(",", userIds)).getBody();
        List<String> userNames = new ArrayList<>();
        for (User user : resp.getData()) {
            userNames.add(user.getUsername());
        }
        return userNames;

    }

}
