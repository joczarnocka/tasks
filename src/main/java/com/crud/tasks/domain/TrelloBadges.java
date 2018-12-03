package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrelloBadges {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentsByType attachmentsByType;

}
