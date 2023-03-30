package com.example.products.models;

import com.example.products.enumm.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 30, message = "Название должно быть в диапозоне от 2 до 30 символов")
    @Column(nullable = false, columnDefinition = "text")
    private String name;

    @NotNull(message = "Цена не может быть пустой")
    @Min(value = 1, message = "Цена не может быть меньше 1")
    @Column(nullable = false)
    private float price;

    @NotNull(message = "Вес не может быть пустым")
    @Min(value = 1, message = "Вес не может быть меньше одного")
    @Column(nullable = false, columnDefinition = "float")
    private float weight;

    private Provider provider;

    public Products() {
    }

    public Products(int id, String name, float price, float weight, Provider provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }


}
