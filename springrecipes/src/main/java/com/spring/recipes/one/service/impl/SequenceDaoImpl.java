package com.spring.recipes.one.service.impl;

import com.spring.recipes.one.dao.SequenceDao;
import com.spring.recipes.one.domain.Sequence;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SequenceDaoImpl implements SequenceDao {

    private Map<String, Sequence> sequenceMap ;

    private Map<String, Integer> values;

    public SequenceDaoImpl() {
        sequenceMap = new HashMap<>();
        sequenceMap.put("IT", new Sequence("IT", "30", "A"));
        values = new HashMap<>();
        values.put("IT", 100000);
    }

    @Override
    public Sequence getSequence(String sequenceId) {
        return sequenceMap.get(sequenceId);
    }

    @Override
    public int getNextValue(String sequenceId) {
        int value  = values.get(sequenceId);
        values.put(sequenceId, value + 1 );
        return value;
    }
}
