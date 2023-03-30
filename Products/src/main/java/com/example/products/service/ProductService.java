package com.example.products.service;

import com.example.products.models.Products;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    //Данный метод позволяет получить список продуктов из products
    public List<Products> getAllProduct(){
        return productRepository.findAll();
    }
    //Данный метод позволяет получить продукт по id
    public Products getProductsId(int id){
        Optional<Products> products = productRepository.findById(id);
        return products.orElse(null);
    }
    //Данный метод позволяет сохранять продукт в БД
    @Transactional
    public void newProduct(Products products){
        productRepository.save(products);
    }
    //Данный метод позволяет обновить данные о продукте
    @Transactional
    public void editProducts(int id,Products products){
        products.setId(id);
        productRepository.save(products);
    }
    //Данный метод позволяет удалить удалить продукт из БД по id
    @Transactional
    public void delete(int id){
        productRepository.deleteById(id);
    }

}
