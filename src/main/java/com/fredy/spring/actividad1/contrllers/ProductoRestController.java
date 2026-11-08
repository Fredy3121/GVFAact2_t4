package com.fredy.spring.actividad1.contrllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fredy.spring.actividad1.dto.ProductoDTO;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @PostMapping("/crear")
    public ResponseEntity crearProducto(@RequestBody ProductoDTO nuevoProducto) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto recibido y procesado vía REST POST");
        respuesta.put("producto", nuevoProducto);
        respuesta.put("status", HttpStatus.CREATED.value());
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}