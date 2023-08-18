package com.project.bookservice.dto;

import com.project.bookservice.model.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResultDTO extends ResultDTO {
    private Category category;

    public CategoryResultDTO(Category category, String message) {
        this.category = category;
        this.setMessage(message);
    }
}
