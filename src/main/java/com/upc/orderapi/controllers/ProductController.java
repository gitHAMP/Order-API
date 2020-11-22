package com.upc.orderapi.controllers;

import java.util.List;

import com.upc.orderapi.converters.ProductConverter;
import com.upc.orderapi.dtos.ProductDTO;
import com.upc.orderapi.entity.Product;
import com.upc.orderapi.services.ProductService;
import com.upc.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter converter;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("productId") Long productId) {
        Product product = productService.findById(productId);
        ProductDTO productDTO = converter.fromEntity(product);
        return new WrapperResponse(true, "success", productDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return new WrapperResponse(true, "success", null)
                .createResponse(HttpStatus.OK);
    }

    // /products?pageNumber=1&pageSize=10
    /*@GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(
            @RequestParam(value="pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value="pageSize", required = false, defaultValue = "10") int pageSize
    ){

        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productService.findAll(page);
        List<ProductDTO> dtoProducts = converter.fromEntity(products);

        return new WrapperResponse(true, "success", dtoProducts)
                .createResponse(HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> products = productService.findAll();
        List<ProductDTO> dtoProducts = converter.fromEntity(products);

        return new WrapperResponse(true, "success", dtoProducts)
                .createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
        Product newProduct = productService.save(converter.fromDTO(product));
        ProductDTO productDTO = converter.fromEntity(newProduct);

        return new WrapperResponse(true, "success", productDTO)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
        Product updateProduct = productService.save(converter.fromDTO(product));
        ProductDTO productDTO = converter.fromEntity(updateProduct);

        return new WrapperResponse(true, "success", productDTO)
                .createResponse(HttpStatus.OK);
    }
}