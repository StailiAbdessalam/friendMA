package com.Abdessalam.friendMA.security.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SecurityProperties {

    List<String> validApplicationRoles;

}
