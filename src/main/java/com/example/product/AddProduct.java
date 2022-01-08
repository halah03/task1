package com.example.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/products")
public class AddProduct {

    ArrayList<Product> products = new ArrayList<>();

    @GetMapping
    public ArrayList<Product> getProduct() {
        return products;
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {

        if (!checkProduct(product)) {
            return ResponseEntity.status(400).body( "send all fields");
        }

        products.add(product);
        return ResponseEntity.status(200).body("Product added");
    }
    @PostMapping("/quantity/add")
    public ResponseEntity<String> addQuantity(int id, int amount){
        if(id <= 0 || amount <= 0)
        {
            return ResponseEntity.status(400).body("send all fields");
        }
        if(ProductExist(id))
        {
            for(int i=0; i<products.size();i++)
            {
                if(products.get(i).getId() == id)
                    products.get(i).addAmount(amount);
            }
            return ResponseEntity.status(200).body("quantity added");
        }
        else
            return ResponseEntity.status(400).body("no product for this id");
    }
    @DeleteMapping("/deleteproduct")
    public ResponseEntity<String> removeProduct(Product product){
        if(!checkProduct(product)){
            return ResponseEntity.status(400).body("send all fields");
        }
        if(!ProductExist(product.getId()))
            return ResponseEntity.status(400).body("Product not exist");
        for(int i=0; i<products.size();i++)
        {
            if(products.get(i).getId() == product.getId())
            {
                if(products.get(i).getAmount() == 0)
                {
                    products.remove(product);
                }
                else
                {
                   return ResponseEntity.status(400).body(" Amount More Than Zero You Can Not Delete it");
                }
            }
        }
        return ResponseEntity.status(400).body("delete");
    }
    @DeleteMapping("/deletequantity")
    public ResponseEntity<String> deleteQuantity(@PathVariable int id, int amount){
        if(id <= 0 || amount <= 0)
        {
            return ResponseEntity.status(400).body("Please send all the fields");
        }
        if(!ProductExist(id))
            return ResponseEntity.status(400).body("No Product for this id");

        for(int i=0; i<products.size();i++)
        {
            if(products.get(i).getId() == id)
            {
                if(products.get(i).getAmount() >= amount)
                {
                    products.get(i).removeAmount(amount);
                    return ResponseEntity.status(200).body("Quantity Removed from The Product");
                }
                else
                {
                    return ResponseEntity.status(400).body("Quantity is Less than quantity to Removed ");
                }
            }
        }
        return ResponseEntity.status(200).body("delete");
    }

    public boolean checkProduct(Product product) {
        if (product.getId() == 0 ||
                product.getName() == null
                || product.getYearOfMake() == null
                || product.getAmount() == 0) {
            return false;
        }
        return true;
    }
    public boolean ProductExist(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (id == products.get(i).getId())
                return true;
        }
        return false;
    }
}
