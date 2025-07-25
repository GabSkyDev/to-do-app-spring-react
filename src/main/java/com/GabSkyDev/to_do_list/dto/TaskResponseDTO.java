package com.GabSkyDev.to_do_list.dto;

import com.GabSkyDev.to_do_list.model.Priority;

public record TaskResponseDTO(
        String name,
        String description,
        Boolean state,
        Priority priority
    ){}
