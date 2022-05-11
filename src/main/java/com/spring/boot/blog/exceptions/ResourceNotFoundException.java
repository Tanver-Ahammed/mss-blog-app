package com.spring.boot.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String resourceFieldName;
    private long resourceFieldValue;

    public ResourceNotFoundException(String resourceName, String resourceFieldName, long resourceFieldValue) {
        super(String.format("%s is not found %s: %d", resourceName, resourceFieldName, resourceFieldValue));
        this.resourceName = resourceName;
        this.resourceFieldName = resourceFieldName;
        this.resourceFieldValue = resourceFieldValue;
    }
}
