package com.example.mockreskill.service.impl;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.common.contants.MessageCode;
import com.example.mockreskill.common.exeption.BadRequestException;
import com.example.mockreskill.common.exeption.NotFoundException;
import com.example.mockreskill.model.entity.Product;
import com.example.mockreskill.model.request.CreateProductRequest;
import com.example.mockreskill.repository.ProductRepository;
import com.example.mockreskill.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        log.info(Constants.BEGIN_SERVICE + "getAllProducts");
        try {
            return productRepository.findAll();
        }catch (BadRequestException e) {
            throw new BadRequestException(MessageCode.USER_NOT_FOUND);
        }
        finally {
            log.info(Constants.END_API + "getAllProducts");
        }
    }

    @Override
    public Product getProduct(String id) {
        log.info(Constants.BEGIN_SERVICE + "getProduct");
        try {
            Optional<Product> productOpt = productRepository.findById(UUID.fromString(id).toString());
            productOpt.orElseThrow(()-> new NotFoundException(MessageCode.PRODUCT_NOT_FOUND));
            return productOpt.get();
        }finally {
            log.info(Constants.END_SERVICE + "getProduct");
        }
    }

    @Override
    public void createProduct(CreateProductRequest createProductRequest) {
        log.info(Constants.BEGIN_SERVICE + "CreateProduct");
        try  {
            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setCode(createProductRequest.getCode());
            product.setName(createProductRequest.getName());
            product.setDescription(createProductRequest.getDescription());
            product.setPrice(createProductRequest.getPrice());
            productRepository.save(product);
        } finally {
            log.info(Constants.END_SERVICE + "CreateProduct");
        }
    }

    @Override
    public void deleteProduct(String id) {
        log.info(Constants.BEGIN_SERVICE + "DeleteProduct");
        try {
            Optional<Product> productOpt = productRepository.findById(UUID.fromString(id).toString());
            productOpt.orElseThrow(()-> new BadRequestException(MessageCode.PRODUCT_NOT_FOUND));
            productRepository.deleteById(UUID.fromString(id).toString());
        } finally {
            log.info(Constants.END_SERVICE + "DeleteProduct");
        }
    }

    @Override
    public void updateProduct(CreateProductRequest createProductRequest, String id) {
        log.info(Constants.BEGIN_SERVICE + "UpdateProduct");
        try {
            Optional<Product> productOpt = productRepository.findById(UUID.fromString(id).toString());
            productOpt.orElseThrow(()-> new BadRequestException(MessageCode.PRODUCT_NOT_FOUND));
            Product product = productOpt.get();
            product.setName(createProductRequest.getName());
            product.setCode(createProductRequest.getCode());
            product.setDescription(createProductRequest.getDescription());
            product.setPrice(createProductRequest.getPrice());
            productRepository.save(product);
        } finally {
            log.info(Constants.END_SERVICE + "UpdateProduct");
        }
    }
}
