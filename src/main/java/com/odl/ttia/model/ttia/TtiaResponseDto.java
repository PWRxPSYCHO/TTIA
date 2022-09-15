package com.odl.ttia.model.ttia;

import java.util.List;

public record TtiaResponseDto(
        String username,
        Long authorId,
        List<String> maliciousIp,
        List<String> urls,
        String tweetText,
        String createdAt,
        String tweetUrl
        ) {

}
