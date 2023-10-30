package org.musical.ticketing.domain;

import java.util.List;

public record Page<T>(List<T> data, long totalSize, int page, int pageSize) {}
