package com.odl.ttia.service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import com.odl.ttia.config.Token;
import com.odl.ttia.interfaces.TweetSearchServiceInterface;
import com.odl.ttia.model.ttia.TtiaResponseDto;
import com.odl.ttia.model.twitterApi.tweetV1Response.RecentTweetSearchV1Response;
import com.odl.ttia.model.twitterApi.tweetV1Response.SearchV1Data;
import com.odl.ttia.rest.twitter.TwitterRestTemplateProvider;

@Service
public class TweetSearchService implements TweetSearchServiceInterface {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TweetSearchService.class);

    private TwitterRestTemplateProvider twitterRestTemplate;

    private final Token token;

    public TweetSearchService(Token token) {
        this.token = token;
        twitterRestTemplate = new TwitterRestTemplateProvider(this.token);

    }

    @Override
    public List<String> getUsers() {
        List<String> ids = new ArrayList<>();
        for (SearchV1Data tweet : twitterRestTemplate.getTweetData().getStatuses()) {
            ids.add(String.valueOf(tweet.getUser().getId()));
        }

        return twitterRestTemplate.getUserData(ids);
    }

    @Override
    public List<String> getUrls() {
        var tweetData = twitterRestTemplate.getTweetData();

        List<String> urls = new ArrayList<>();
        for (SearchV1Data data : tweetData.getStatuses()) {
            String formattedUrl = formatUrl(data);
            if (!formattedUrl.isEmpty()) {
                urls.add(formattedUrl);
            }
        }
        return urls;
    }

    /**
     * Receives SearchV1Data and converts any hxxp://, hxxps://, or ip to http://,
     * https:// or valid ip address
     * 
     * 
     * @param data
     * @return formatted URL
     */
    private String formatUrl(SearchV1Data data) {
        String url = "";
        Pattern p = Pattern.compile(
                "hxx.*? | (([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])");
        Matcher m = p.matcher(data.getText().replaceAll("\\[|\\]|", ""));
        if (m.find()) {
            url = m.group().replace("hxxp", "http").replace(" ", "");
        }
            return url;
    }

    @Override
    public List<String> getMaliciousIps() {

        List<String> urls = new ArrayList<>();
        for (SearchV1Data data : twitterRestTemplate.getTweetData().getStatuses()) {
            urls.add(formatUrl(data));
        }
        return parseMaliciousIps(urls);

    }

    /**
     * Accepts list of URL's in http:// or https:// format to lookup host and IP
     * address
     * 
     * @param urls list of or ip addresses in http:// or https:// format
     * @return list of host/ip returned from urls provided
     */
    private List<String> parseMaliciousIps(List<String> urls) {
        List<String> ips = new ArrayList<>();
        for (String url : urls) {
            try {
                if (!url.isEmpty()) {
                    if (isIP(url)) {
                        ips.add(Inet4Address.getByName(url).getHostName());
                    } else {
                    ips.add(InetAddress.getByName(new URL(url).getHost()).toString());
                }
            }
            } catch (MalformedURLException | UnknownHostException e) {
                ips.add(url + " : unable to find host");
                LOGGER.error("Unable to parse IP: " + url, e);
            }
        }
        return ips;
    }

    private boolean isIP(String address) {
        Pattern p = Pattern.compile(
                "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])");
        Matcher m = p.matcher(address);
        return m.find();
    }

    @Override
    public RecentTweetSearchV1Response getTweetData() {
        return twitterRestTemplate.getTweetData();
    }

    @Override
    public List<TtiaResponseDto> getAllData() {
        List<TtiaResponseDto> respList = new ArrayList<>();
        for (SearchV1Data v1 : twitterRestTemplate.getTweetData().getStatuses()) {
            List<String> urls = new ArrayList<>();
            String formattedUrl = formatUrl(v1);
            if (!formattedUrl.isEmpty()) {
                urls.add(formattedUrl);
            }
            respList.add(
                    new TtiaResponseDto(v1.getUser().getName(), v1.getUser().getId(), parseMaliciousIps(urls), urls,
                            v1.getText(), v1.getCreated_at(),
                            getTweetURL(v1)));
        }
        return respList;
    }

    private String getTweetURL(SearchV1Data data) {
        return String.format("https://twitter.com/%s/status/%s", data.getUser().getScreen_name(), data.getId());
    }

}
