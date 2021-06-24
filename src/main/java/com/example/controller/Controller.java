package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ProductModel;
import com.example.repo.ProductRepository;

@RestController
public class Controller {
	
	
	@Autowired
	ProductRepository ProductRepo;
	
	//Welcome Message
	@GetMapping("/")
	public String returnWelcome() {
		return "REST Server Started :)";
	}
	
	//save a new product
	@PostMapping("/saveProduct")
	public void saveProduct(@RequestBody ProductModel product) {
		ProductRepo.save(product);
	}
	
	//get all the products
	@GetMapping("/getAllProduct")
	public List<ProductModel> getAllProducts(){
		return (List<ProductModel>) ProductRepo.findAll();
	}
	
	//get a single products
	@GetMapping("/getProduct/{id}")
	public Optional<ProductModel> getProduct(@PathVariable(value = "id") String id) {
		return ProductRepo.findById(id);
	}
	
	//Delete a single products
	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProduct(@PathVariable(value = "id") String id) {
		ProductRepo.deleteById(id);
	}
	
	//get by Type
	@PostMapping("/getByType")
	public List<ProductModel> getByType(@RequestBody LinkedHashMap<String,Object> data){
		return ProductRepo.findAllByType(data.get("type").toString());
	}
}
