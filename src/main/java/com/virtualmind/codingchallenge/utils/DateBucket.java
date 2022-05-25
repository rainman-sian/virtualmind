package com.virtualmind.codingchallenge.utils;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@lombok.Data
public class DateBucket {

    final Instant from;
    final Instant to;


    /**
     * This function creates a list of DateBuckets
     * A DateBuck is a kind of "Duration" (is a representation of an interval of time between instants `from` and `to`)
     * The returned listen then, will be "Durations" of size @bucketSize of units @bucketSizeUnit
     *
     * For example, given:
     *      fromDate = 01/01/2022 00:00:00
     *      toDate =   01/01/2022 23:00:00      (1 Day)
     *      bucketSize = 1
     *      bucketSizeUnit = HOUR
     *
     * The list will contain 24 items of "Duration" (buckets) 1 hour each
     */
    public static List<DateBucket> bucketsize(ZonedDateTime fromDate,
                                              ZonedDateTime toDate,
                                              int bucketSize,
                                              ChronoUnit bucketSizeUnit) {

        List<DateBucket> buckets = new ArrayList<>();
        boolean reachedDate = false;

        for (int i=0; !reachedDate; i++) {
            ZonedDateTime minDate = fromDate.plus(i * bucketSize, bucketSizeUnit);
            ZonedDateTime maxDate = fromDate.plus((i + 1) * bucketSize, bucketSizeUnit);
            reachedDate = toDate.isBefore(maxDate);
            buckets.add(new DateBucket(minDate.toInstant(), maxDate.toInstant()));
        }

        return buckets;
    }

    public static List<DateBucket> bucketSizeStreamsVersion(ZonedDateTime fromDate,
                                                            ZonedDateTime toDate,
                                                            int bucketSize,
                                                            ChronoUnit bucketSizeUnit) {

        // I iterate from `fromDate` for intervals of N=`bucketSize` units=`bucketSizeUnit`
        return Stream.iterate(fromDate, currentDate -> currentDate.plus(bucketSize, bucketSizeUnit))
                // I put a limit of N units + 1 (need the + 1 for `toDate` to be inclusive
                .limit(bucketSizeUnit.between(fromDate, toDate) + 1)
                // I map the zonedDateTime result to a DateBucked
                .map(zonedDateTime -> new DateBucket(zonedDateTime.toInstant(), zonedDateTime.plus(bucketSize, bucketSizeUnit).toInstant()))
                .collect(Collectors.toList());
    }



}
