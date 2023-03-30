package com.example.products.Controller;


import com.example.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class Products {


    private final ProductService productService;

    @Autowired
    public Products(ProductService productService) {
        this.productService = productService;
    }

    //Данный метод позволяет получить get параметры и добавить товар в ArrayList из DAO класса
    //?name=Хлеб&price=50&weight=300&provider=LadyLux
    //?name=Молоко&price=100&weight=1000&provider=Obi
//    @GetMapping("/products/add")
//    public void addProducts(@RequestParam(value = "name", required=false)String name, @RequestParam(value = "price", required=false) float price, @RequestParam(value = "weight", required=false)String weight, @RequestParam(value = "provider", required=false)Provider provider){
//        daoProduct.addProduct(name,price,weight,provider);
//    }
    //Данный метод позволяет получить список всех продуктов и вернуть html страницу
    @GetMapping("/products")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    // Данный метод позволяет получить объект из листа по id
    @GetMapping("products/{id}")
    public String infoProducts(@PathVariable("id") int id, Model model) {
        model.addAttribute("products", productService.getProductsId(id));
        return "product_info";
    }

    // Данный метод позволяет отобразить представление с формой по добав товара
    @GetMapping("/products/add")
    public String newProduct(Model model) {
        model.addAttribute("products", new com.example.products.models.Products());
        return "add_products";

    }

    //Данный метод позволяет  принять данный с формы и сохранить товар в лист
    @PostMapping("/products/add")
    public String newProduct(@ModelAttribute("products") @Valid com.example.products.models.Products products, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_products";
        }
        productService.newProduct(products);
        return "redirect:/products";

    }

    //Данный метод позволяет получить редактируемый продукт по id и вернуть форму редактирования продукта
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit_product", productService.getProductsId(id));
        return "edit_product";

    }

    //Данный метод позволяет принять редактируемый объект с формы и обновить информацию о редактируемом товаре
    @PostMapping("/products/edit/{id}")
    public String edit_Product(@ModelAttribute("edit_product") @Valid com.example.products.models.Products products, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "edit_product";
        }
        productService.editProducts(id, products);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProducts(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/products";
    }

}
