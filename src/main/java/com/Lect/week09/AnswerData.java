package com.Lect.week09;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerData {
    private List<String> responses;
    private Respondent res;
}
