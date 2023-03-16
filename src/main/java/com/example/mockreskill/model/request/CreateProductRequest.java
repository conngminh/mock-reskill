package com.example.mockreskill.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductRequest {
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String thumbnail;
    @NotBlank
    private BigDecimal price;

}
