package com.unitrs.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Term {
    private int id;
    private int termNumber;
    private String termName;

    public Term(int termNumber, String termName) {
        this.termNumber = termNumber;
        this.termName = termName;
    }
}
