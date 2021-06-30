package com.volleyservice.controller;

import com.volleyservice.entity.Match;
import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.service.MatchService;
import com.volleyservice.to.MatchRequestTO;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @GetMapping("/matches")
    ResponseEntity<CollectionModel<EntityModel<MatchTO>>> findAll() {
        List<EntityModel<MatchTO>> matches = matchService.findAl().stream()
                .map(match -> EntityModel.of(matchMapper.mapsToTO(match),
                        linkTo(methodOn(MatchController.class).findOne(match.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(matches
                , linkTo(methodOn(MatchController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/matches")
    ResponseEntity<?> newMatch(@RequestBody @Validated MatchRequestTO matchRequestTO) {
        Match savedMatch = matchService.save(matchMapper.mapsToEntity(matchRequestTO));

        EntityModel<MatchTO> matchResource = EntityModel.of(matchMapper.mapsToTO(savedMatch)
                , linkTo(methodOn(MatchController.class).findOne(savedMatch.getId())).withSelfRel());
        try {
            return ResponseEntity
                    .created(new URI(matchResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(matchResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create" + matchRequestTO);
        }
    }

    @GetMapping("/matches/{id}")
    ResponseEntity<EntityModel<MatchTO>> findOne(@PathVariable long id) {

        return matchService.findById(id).map(match -> EntityModel.of(matchMapper.mapsToTO(match), //
                linkTo(methodOn(MatchController.class).findOne(match.getId())).withSelfRel(), //
                linkTo(methodOn(MatchController.class).findAll()).withRel("matches"))).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }


//    @PatchMapping(path = "/players/{id}", consumes = "application/json-patch+json")
//    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody JsonPatch patch) {
//        Player player = playerService.findById(id).orElseThrow(IllegalArgumentException::new);
//        try {
//            Player playerPatched = applyPatchToPlayer(patch, player);
//            playerService.save(playerPatched);
//            return ResponseEntity.ok(playerPatched);
//
//        } catch (JsonPatchException jsonPatchException) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        } catch (JsonProcessingException jsonProcessingException) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//    }
//
//
//    private Player applyPatchToPlayer(JsonPatch patch, Player targetPlayer)
//            throws JsonPatchException, JsonProcessingException {
//        JsonNode patched = patch.apply(objectMapper.convertValue(targetPlayer, JsonNode.class));
//        return objectMapper.treeToValue(patched, Player.class);
//    }

}
