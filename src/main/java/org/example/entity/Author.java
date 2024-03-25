package org.example.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Author {
    private int id;
    private String name;
}
