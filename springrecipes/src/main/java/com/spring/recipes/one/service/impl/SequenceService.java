package com.spring.recipes.one.service.impl;

import com.spring.recipes.one.dao.SequenceDao;
import com.spring.recipes.one.domain.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {

    @Autowired
    private SequenceDao sequenceDao ;

    public void setSequenceDao(SequenceDao sequenceDao){
        this.sequenceDao = sequenceDao ;
    }

    public String generate(String sequenceId){
        Sequence sequence = sequenceDao.getSequence(sequenceId);
        int value = sequenceDao.getNextValue(sequenceId);
        return sequence.getPrefix() + value + sequence.getSuffix() ;
    }
}
