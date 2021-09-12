package com.volleyservice.exception;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ControllerHelper {

    public ResponseEntity<?> createOrReturnBadRequest(EntityModel<?> objectResource) {
        try {
            return ResponseEntity
                    .created(new URI(objectResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(objectResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create");
        }

    }
}
