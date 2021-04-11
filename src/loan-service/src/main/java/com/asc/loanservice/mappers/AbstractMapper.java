package com.asc.loanservice.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public abstract class AbstractMapper {

    protected static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }
}
