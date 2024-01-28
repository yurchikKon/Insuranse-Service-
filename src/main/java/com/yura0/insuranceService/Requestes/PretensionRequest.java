package com.yura0.insuranceService.Requestes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PretensionRequest {

    String title;
    String text;
    String type;

}
