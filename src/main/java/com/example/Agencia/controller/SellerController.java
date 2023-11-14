package com.example.Agencia.controller;

import com.example.Agencia.Seller.SellerEmployeeRequestDTO;
import com.example.Agencia.Seller.Seller;
import com.example.Agencia.Seller.SellerRepository;
import com.example.Agencia.Seller.SellerRequestDTO;
import com.example.Agencia.Seller.SellerResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerRepository repository;

    @GetMapping
    public List<SellerResponseDTO> getAll(){
        List<SellerResponseDTO> sellerList = repository.findAll().stream().map(SellerResponseDTO::new).toList();
        return sellerList;
    }

    @PostMapping
    public void saveSeller(@RequestBody SellerEmployeeRequestDTO requestData) {
        //Employee employee = new Employee(requestData.getEmployeeData());
        Seller sellerData = new Seller(requestData.getSellerData(), requestData.getEmployeeData());
        repository.save(sellerData);
    }


    @PutMapping
    @Transactional
    public ResponseEntity updateSeller(@RequestBody SellerRequestDTO seller_Data){
        Optional<Seller> optionalSeller = repository.findById(seller_Data.id());
        if(optionalSeller.isPresent()){
            Seller seller = optionalSeller.get();
            seller.setWorkload(seller_Data.workload());
            return ResponseEntity.ok(seller);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}