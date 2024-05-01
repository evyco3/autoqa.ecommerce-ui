package com.evy.framework.pages.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddReviewToProductModel {
    private String stars;
    private String nickName;
    private String review;
    private String summary;
}
