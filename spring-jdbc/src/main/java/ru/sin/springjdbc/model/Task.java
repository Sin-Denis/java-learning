package ru.sin.springjdbc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Task {
    int id;
    String name;
    String description;
    int difficult;
}
