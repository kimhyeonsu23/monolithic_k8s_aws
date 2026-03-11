package com.example.monolithic.user.ctrl;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monolithic.user.domain.dto.UserRequestDTO;
import com.example.monolithic.user.domain.dto.UserResponseDTO;
import com.example.monolithic.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService ;
    
    @PostMapping("/signIn")
    /*
    - 인증(Authentication) : 누구인지를 확인하는 절차 , 
        - Bearer token : JWT 기반 인증(access, refresh), OAuth2
        - 인증시점에 응답시(body X , header O(Authorization: Bearer access_token )) 
    - 인가(Authorization) : 권한부여(endpoint 접근권한) 
        - 사용자 요청시(header token) 유무를 체크하고 접근권한을 확인 
    */
    public ResponseEntity<?> signIn (@RequestBody UserRequestDTO request ){
        
        
        System.out.println(">>>> user ctrl path : /signIn"); 
        System.out.println(">>>> params : "+ request); 
        
        Map<String, Object> map = userService.signIn(request);
        
        System.out.println(">>>> response body : "+(UserResponseDTO)(map.get("response")));
        System.out.println(">>>> response at   : "+(String)(map.get("access")));
        System.out.println(">>>> response rt   : "+(String)(map.get("refresh")));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+(String)(map.get("access")) ) ;
        headers.add("Refresh-Token", (String)(map.get("refresh")) ) ;
        headers.add("Access-Control-Expose-Headers","Authorization, Refresh-Token");
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body( (String)(map.get("access")) ) ;
    }

}
