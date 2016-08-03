package com.tom.test.converters;

import com.tom.test.commands.ProductForm;
import com.tom.test.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by tom on 8/2/2016.
 */
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();

        productForm.setProductId(product.getId());
        productForm.setProductVersion(product.getVersion());
        productForm.setProductName(product.getName());
        productForm.setProductDescription(product.getDescription());
        productForm.setProductPrice(product.getPrice());
        productForm.setProductImageUrl(product.getImageUrl());
        productForm.setProductYoutubeUrl(product.getYoutubeUrl());
        productForm.setProductDeveloper(product.getDeveloper());
        productForm.setProductPublisher(product.getPublisher());

        return productForm;
    }
}
