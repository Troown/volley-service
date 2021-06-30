package com.volleyservice.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class NotFoundException extends ChangeSetPersister.NotFoundException{
    public NotFoundException(String message) {
    }
}
