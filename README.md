# Twitter Threat Intelligence API (TTIA)

Using the Twitter API and the #opendir hashtag we can use OSINT to help find malicious links out in the wild on specific websites. This API allows you to retrieve the most recent feed of tweets and will pull out the URL's, IP addresses, and usernames of those who found these phishing links.



## Project Setup

You will need the following installed to use this project

- Java 17
- Maven
- VSCode
- The extensions listed in the `extensions.json` file.
- Twitter API account

In the `resources/application.yml` add your bearer token to make the requests to the Twitter API
```
token:
  bearer: BEARERTOKENGOESHERE
```

After you have that setup you will need to import the postman collection into postman in order to start making requests:

`postman-collection/Twitter Threat Intelligence API (TTIA).postman_collection.json`

Is located in the project for your convenience


# Swagger
Once you have the application running you can view the response objects, errors, and templates on swagger's UI for API documentation.

**http://localhost:8080/swagger-ui.html#/**

# Get Users
**http://localhost:8080/api/v1/tweets/users**

This api call will return the username's of all the users that posted in the last 100 entries of the feed.

The response will be:
```json
{
    "response":[
        "username1",
        "username2"
    ]
}
```

# Get urls

**http://localhost:8080/api/v1/tweets/urls**

This api call will return the formatted url's of the last 100 entries of the feed. The url's are usually inserted into Twitter with `hxxp://` and `hxxps://`. The API will convert them to `http://` and `https://` and remove any other link breaking format. 

**DO NOT CLICK THE LINKS IN THE RESPONSE THEY ARE MALICIOUS**

The response will be:
```json
{
    "response":[
        "https://some.malicious.zip",
        "http://some.malicous.zip"
    ]
}
```

# Get malicious-ips

**http://localhost:8080/api/v1/tweets/malicious-ips**

This api call will return the host and ip of the url's that were parsed from the Twitter feed. If it cannot find an ip or host it will not return a match and an error will be thrown server side.

The response will be:
```json
{
    "response":[
        "host/ip",
        "host/ip"
    ]
}
```

# Get all-data
**http://localhost:8080/api/v1/tweets/all-data**

This api call will return a combination of data from the twitter feed. You will know the username of who posted the tweet, when it was created, the id of the author, the malicious IP(s) with Host(s), the formatted URL(s), and then text of the tweet for the last 100 tweets.
```json
[
  {
        "username": "Chris Bertsch",
        "authorId": 69097408,
        "maliciousIp": [
            "bttechnology.art/198.54.115.184"
        ],
        "urls": [
            "https://bttechnology.art/EMS-CI.zip"
        ],
        "tweetText": "RT @ecarlesi: Possible threat on hxxps://bttechnology[.]art/EMS-CI[.]zip #phishing #opendir  #nc",
        "createdAt": "Tue Jun 07 23:46:38 +0000 2022",
        "tweetUrl": "https://twitter.com/Malwar3Ninja/status/1534568419302854657"
    },
]
```

# Get tweet-data
**http://localhost:8080/api/v1/tweets/tweet-data**

This api call will return the Twitter API Search V1 response object. If you wanted to dig into the Twitter API and get all the information of the tweets.

The response will be:
```json
{
         "data": [
        {
            "created_at": "Wed Jun 08 12:56:42 +0000 2022",
            "id": 1534519789791502337,
            "id_str": "1534519789791502337",
            "text": "RT @Malwar3Ninja: [https://t.co/otlIKKaN3I]🌀⚡Our proactive hunter detected #Opendir hosting multiple malicious files at 3.144[.]124[.]4\n\n#t…",
            "truncated": false,
            "entities": {
                "hashtags": [
                    {
                        "text": "Opendir",
                        "indices": [
                            75,
                            83
                        ]
                    }
                ],
                "symbols": [],
                "user_mentions": [
                    {
                        "screen_name": "Malwar3Ninja",
                        "name": "Malwar3Ninja | Threatview.io 💻",
                        "id": 953204257171238917,
                        "id_str": "953204257171238917",
                        "indices": [
                            3,
                            16
                        ]
                    }
                ],
                "urls": [
                    {
                        "url": "https://t.co/otlIKKaN3I",
                        "expanded_url": "http://Threatview.io",
                        "display_url": "Threatview.io",
                        "indices": [
                            19,
                            42
                        ]
                    }
                ]
            },
            "metadata": {
                "iso_language_code": "en",
                "result_type": "recent"
            },
            "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
            "in_reply_to_status_id": null,
            "in_reply_to_status_id_str": null,
            "in_reply_to_user_id": null,
            "in_reply_to_user_id_str": null,
            "in_reply_to_screen_name": null,
            "user": {
                "id": 1439416397038194696,
                "id_str": "1439416397038194696",
                "name": "104shit",
                "screen_name": "1o4shit",
                "location": "",
                "description": "",
                "url": null,
                "entities": {
                    "description": {
                        "urls": []
                    }
                },
                "userProtected": false,
                "followers_count": 12,
                "friends_count": 227,
                "listed_count": 0,
                "created_at": "Sun Sep 19 02:29:56 +0000 2021",
                "favourites_count": 47,
                "utc_offset": null,
                "time_zone": null,
                "geo_enabled": false,
                "verified": false,
                "statuses_count": 1085,
                "lang": null,
                "contributors_enabled": false,
                "profile_background_color": "F5F8FA",
                "profile_background_image_url": null,
                "profile_background_image_url_https": null,
                "profile_background_tile": false,
                "profile_image_url": "http://pbs.twimg.com/profile_images/1506195727881752580/mSurW9JP_normal.jpg",
                "profile_image_url_https": "https://pbs.twimg.com/profile_images/1506195727881752580/mSurW9JP_normal.jpg",
                "profile_banner_url": null,
                "profile_link_color": "1DA1F2",
                "profile_sidebar_border_color": "C0DEED",
                "profile_sidebar_fill_color": "DDEEF6",
                "profile_text_color": "333333",
                "profile_use_background_image": true,
                "has_extended_profile": true,
                "default_profile": true,
                "default_profile_image": false,
                "following": null,
                "follow_request_sent": null,
                "notifications": null,
                "translator_type": "none",
                "withheld_in_countries": [],
                "_translator": false,
                "_translation_enabled": false
            },
            "geo": null,
            "coordinates": null,
            "place": null,
            "contributors": null,
            "retweeted_status": {
                "created_at": "Tue Jun 07 17:27:11 +0000 2022",
                "id": 1534225470647963648,
                "id_str": "1534225470647963648",
                "text": "[https://t.co/otlIKKaN3I]🌀⚡Our proactive hunter detected #Opendir hosting multiple malicious files at 3.144[.]124[.… https://t.co/E8MpL5Cu1b",
                "truncated": true,
                "entities": {
                    "hashtags": [
                        {
                            "text": "Opendir",
                            "indices": [
                                57,
                                65
                            ]
                        }
                    ],
                    "symbols": [],
                    "user_mentions": [],
                    "urls": [
                        {
                            "url": "https://t.co/otlIKKaN3I",
                            "expanded_url": "http://Threatview.io",
                            "display_url": "Threatview.io",
                            "indices": [
                                1,
                                24
                            ]
                        },
                        {
                            "url": "https://t.co/E8MpL5Cu1b",
                            "expanded_url": "https://twitter.com/i/web/status/1534225470647963648",
                            "display_url": "twitter.com/i/web/status/1…",
                            "indices": [
                                117,
                                140
                            ]
                        }
                    ]
                },
                "metadata": {
                    "iso_language_code": "en",
                    "result_type": "recent"
                },
                "source": "<a href=\"http://twitter.com/download/android\" rel=\"nofollow\">Twitter for Android</a>",
                "in_reply_to_status_id": null,
                "in_reply_to_status_id_str": null,
                "in_reply_to_user_id": null,
                "in_reply_to_user_id_str": null,
                "in_reply_to_screen_name": null,
                "user": {
                    "id": 953204257171238917,
                    "id_str": "953204257171238917",
                    "name": "Malwar3Ninja | Threatview.io 💻",
                    "screen_name": "Malwar3Ninja",
                    "location": "Threatview.io",
                    "description": "Malware Hunter | ⚡🆓Threat Intelligence: https://t.co/aidnL0GBmB | Member @CuratedIntel | Cyber Defense | DFIR | Views are personal | Retweet≠endorsement | 🍺🥃& Cigar",
                    "url": "https://t.co/KhCapBTyYQ",
                    "entities": {
                        "url": {
                            "urls": [
                                {
                                    "url": "https://t.co/KhCapBTyYQ",
                                    "expanded_url": "https://Threatview.io",
                                    "display_url": "Threatview.io",
                                    "indices": [
                                        0,
                                        23
                                    ]
                                }
                            ]
                        },
                        "description": {
                            "urls": [
                                {
                                    "url": "https://t.co/aidnL0GBmB",
                                    "expanded_url": "http://Threatview.io",
                                    "display_url": "Threatview.io",
                                    "indices": [
                                        40,
                                        63
                                    ]
                                }
                            ]
                        }
                    },
                    "userProtected": false,
                    "followers_count": 1651,
                    "friends_count": 2487,
                    "listed_count": 28,
                    "created_at": "Tue Jan 16 09:56:19 +0000 2018",
                    "favourites_count": 2209,
                    "utc_offset": null,
                    "time_zone": null,
                    "geo_enabled": false,
                    "verified": false,
                    "statuses_count": 2006,
                    "lang": null,
                    "contributors_enabled": false,
                    "profile_background_color": "F5F8FA",
                    "profile_background_image_url": null,
                    "profile_background_image_url_https": null,
                    "profile_background_tile": false,
                    "profile_image_url": "http://pbs.twimg.com/profile_images/953223267715813381/roa9gsRe_normal.jpg",
                    "profile_image_url_https": "https://pbs.twimg.com/profile_images/953223267715813381/roa9gsRe_normal.jpg",
                    "profile_banner_url": "https://pbs.twimg.com/profile_banners/953204257171238917/1625323414",
                    "profile_link_color": "1DA1F2",
                    "profile_sidebar_border_color": "C0DEED",
                    "profile_sidebar_fill_color": "DDEEF6",
                    "profile_text_color": "333333",
                    "profile_use_background_image": true,
                    "has_extended_profile": false,
                    "default_profile": true,
                    "default_profile_image": false,
                    "following": null,
                    "follow_request_sent": null,
                    "notifications": null,
                    "translator_type": "none",
                    "withheld_in_countries": [],
                    "_translator": false,
                    "_translation_enabled": false
                },
                "geo": null,
                "coordinates": null,
                "place": null,
                "contributors": null,
                "retweet_count": 17,
                "favorite_count": 33,
                "favorited": false,
                "retweeted": false,
                "lang": "en",
                "_quote_status": false
            },
            "retweet_count": 17,
            "favorite_count": 0,
            "favorited": false,
            "retweeted": false,
            "lang": "en",
            "_quote_status": false
        }
    ]
}

```