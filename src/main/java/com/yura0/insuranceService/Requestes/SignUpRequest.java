package com.yura0.insuranceService.Requestes;

import com.yura0.insuranceService.Entities.Policy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    String login;

    String password;

    String number;

    String mail;
}
