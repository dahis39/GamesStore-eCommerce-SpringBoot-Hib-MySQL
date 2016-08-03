package com.tom.test.services;

import com.tom.test.commands.ProductForm;
import com.tom.test.domain.Product;

/**
 * Created by tom on 6/9/2016.
 */
public interface ProductService extends CRUDservice<Product>{
    Product saveOrUpdateProductForm(ProductForm productForm);
    ProductForm findProductFormById(Integer id);
}
