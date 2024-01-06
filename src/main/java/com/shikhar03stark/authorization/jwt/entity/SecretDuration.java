package com.shikhar03stark.authorization.jwt.entity;

import java.time.Duration;
import java.time.Instant;

public enum SecretDuration {
    ONE_HOUR(Duration.ofHours(1)),
    EIGHT_HOUR(Duration.ofHours(8)),
    ONE_DAY(Duration.ofDays(1)),
    ONE_WEEK(Duration.ofDays(7));

    private final Duration duration;
    SecretDuration(Duration duration){
        this.duration = duration;
    }

    public Instant toInstant(Instant fromInstant){
        return fromInstant.plus(this.duration);
    }
}
