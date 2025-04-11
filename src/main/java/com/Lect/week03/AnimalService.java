package com.Lect.week03;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AnimalService {
    private List<Animal> animals;
    private Map<String, Animal> animalMap;
}
