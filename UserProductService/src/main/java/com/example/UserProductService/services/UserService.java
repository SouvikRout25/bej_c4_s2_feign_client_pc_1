package com.example.UserProductService.services;


import com.example.UserProductService.exception.ProductNotFoundException;
import com.example.UserProductService.exception.UserAlreadyExistsException;
import com.example.UserProductService.exception.UserNotFoundException;
import com.example.UserProductService.model.Product;
import com.example.UserProductService.model.User;

import java.util.List;


public interface UserService {

    public abstract User addUser(User user) throws UserAlreadyExistsException;

    public abstract User addProductForUser(int userId, Product product) throws UserNotFoundException;

    public abstract User deleteProductForUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException;

    public abstract List<Product> getAllProductForUser(int userId) throws UserNotFoundException;
}