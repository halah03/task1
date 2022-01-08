package com.example.car;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cars")
public class CarController {

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Color> colors = new ArrayList<>();
    ArrayList<YearsOfMake> yearsOfMake = new ArrayList<>();
    ArrayList<Price> prices = new ArrayList<>();
    int carPrice=10000;


    @GetMapping
    public ArrayList<Car> getCars() {
        return cars;
    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody Car car) {

        if (!checkInput(car)) {
            return ResponseEntity.status(400).body("Please send all the fields");
        }
        cars.add(car);
        return ResponseEntity.status(200).body("Car created");
    }

    @PostMapping("/color")
    public ResponseEntity colorFunds(@RequestBody Color color){

        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getColor().equals(color.getColor())) {
                if(!(cars.get(i).getColor().equals(color.getColor()))){
                    return ResponseEntity.status(400).body("color doesn't match the user id");
                }
                return ResponseEntity.status(200).body("Color found");
            }
        }
        return ResponseEntity.status(400).body("wrong id");
    }

    @PostMapping("/years")
    public ResponseEntity yearsFunds(@RequestBody YearsOfMake yearsOfMake){

        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getYearOfMake() == (yearsOfMake.getYearOfMake())) {
                if(!(cars.get(i).getYearOfMake() == (yearsOfMake.getYearOfMake()))){
                    return ResponseEntity.status(400).body("years doesn't match the user id");
                }
                return ResponseEntity.status(200).body("years found");
            }
        }
        return ResponseEntity.status(400).body("wrong id");
    }

    @PostMapping("/years")
    public ResponseEntity priceFunds(@RequestBody Price price){

        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getPrice() == (price.getPrice())) {
                if(!(cars.get(i).getPrice() == (price.getPrice()))){
                    return ResponseEntity.status(400).body("price doesn't match the user id");
                }
                return ResponseEntity.status(200).body("price found");
            }
        }
        return ResponseEntity.status(400).body("wrong id");
    }

    @DeleteMapping("/{price}")
    public  ResponseEntity<String> deleteCar(@PathVariable("price") String id) {

        boolean isFound = false;
        for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getPrice() > 15000) {
                    return ResponseEntity.status(400).body("cant sell the car");
                }
                cars.remove(i);
                break;
            }
            if (!isFound) {
                return ResponseEntity.status(400).body("Please send valid id");
            }

            return ResponseEntity.status(200).body("sell car");
        }


        @DeleteMapping("/{yearsOfMake}")
        public ResponseEntity<String> deleteYear (@PathVariable("yearsOfMake") String yearsOfMake){
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getYearOfMake() <= 2020) {
                    return ResponseEntity.status(400).body("cant find the car");

                }
                cars.remove(i);
            }
            return ResponseEntity.status(200).body("years found");

        }

        public boolean checkInput (Car car){
            if (car.getId() == 0 ||
                    car.getColor() == null
                    || car.getModel() == null
                    || car.getYearOfMake() == 0
                    || car.getPrice() == 0) {
                return false;
            }
            return true;
        }
    }
