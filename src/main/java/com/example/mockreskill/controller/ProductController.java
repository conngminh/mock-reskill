package com.example.mockreskill.controller;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.model.entity.Product;
import com.example.mockreskill.model.request.CreateProductRequest;
import com.example.mockreskill.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    ResponseEntity<List<Product>> getAllProducts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorHeader) {
        log.info(Constants.BEGIN_API + "getAllProducts");
        try {
            return ResponseEntity.ok().body(productService.getAllProducts());
        } finally {
            log.info(Constants.END_API + "getAllProducts");
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorHeader,
                                       @PathVariable String id) {
        log.info(Constants.BEGIN_API + "getProduct");
        try {
            Product product = productService.getProduct(id);
            return ResponseEntity.ok().body(product);
        } finally {
            log.info(Constants.BEGIN_API + "getProduct");
        }
    }

    @PostMapping("/create")
    ResponseEntity<Void> CreateProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorHeader,
                                       @RequestBody CreateProductRequest createProductRequest) {
        log.info(Constants.BEGIN_API + "CreateProduct");
        try {
            productService.createProduct(createProductRequest);
            return ResponseEntity.ok().body(null);
        } finally {
            log.info(Constants.BEGIN_API + "CreateProduct");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> DeleteProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorHeader,
                                       @PathVariable String id) {
        log.info(Constants.BEGIN_API + "DeleteProduct");
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().body(null);
        } finally {
            log.info(Constants.BEGIN_API + "DeleteProduct");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> UpdateProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorHeader,
                                       @RequestBody CreateProductRequest createProductRequest, @PathVariable String id) {
        log.info(Constants.BEGIN_API + "UpdateProduct");
        try {
            productService.updateProduct(createProductRequest,id);
            return ResponseEntity.ok().body(null);
        } finally {
            log.info(Constants.BEGIN_API + "UpdateProduct");
        }
    }
}
