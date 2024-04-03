package com.learn.graphqlsecurity.model;

import java.time.OffsetDateTime;

public record Order(Integer orderId, Coffee coffee, User user, OffsetDateTime orderedOn)
{
}
