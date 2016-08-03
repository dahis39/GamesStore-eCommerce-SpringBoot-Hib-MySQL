package com.tom.test.converters;

import com.tom.test.commands.ProductForm;
import com.tom.test.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by tom on 8/2/2016.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm ,Product>{
    @Override
    public Product convert(ProductForm productForm) {
        Product product = new Product();

        product.setId(productForm.getProductId());
        product.setVersion(productForm.getProductVersion());
        product.setName(productForm.getProductName());
        product.setDescription(productForm.getProductDescription());
        product.setPrice(productForm.getProductPrice());
        product.setImageUrl(productForm.getProductImageUrl());
        product.setYoutubeUrl(productForm.getProductYoutubeUrl().replace("watch?v=", "embed/"));
        product.setDeveloper(productForm.getProductDeveloper());
        product.setPublisher(productForm.getProductPublisher());

        return product;
    }
}
