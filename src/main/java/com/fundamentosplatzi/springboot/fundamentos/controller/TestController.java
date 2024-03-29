package com.fundamentosplatzi.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping //Aceptar todas las solicitudes a nivel http
    @ResponseBody //responder un cuerpo a nivel de servicio
    //http://localhost:8081/app/
    public ResponseEntity<String> function(){

        return new ResponseEntity<>("Hello from Controller", HttpStatus.OK);
    }

}
