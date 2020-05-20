package com.spring.recipes.one.dao;

import com.spring.recipes.one.domain.Sequence;

public interface SequenceDao {
    public Sequence getSequence(String sequenceId);

    public int getNextValue(String sequenceId);
}
