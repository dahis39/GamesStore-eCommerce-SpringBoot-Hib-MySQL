package com.tom.test.converters;

import com.tom.test.commands.BundleForm;
import com.tom.test.domain.Bundle;
import com.tom.test.domain.Product;
import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by tom on 7/5/2016.
 */
@Component
public class BundleFormToBundle implements Converter<BundleForm,Bundle> {

    @Autowired
    ProductService productService;

    @Override
    public Bundle convert(BundleForm bundleForm) {
        Bundle bundle = new Bundle();

        bundle.setId(bundleForm.getBundleId());
        bundle.setVersion(bundleForm.getBundleVersion());
        bundle.setName(bundleForm.getBundleName());
        bundle.setDescription(bundleForm.getBundleDescription());
        bundle.setImageUrl(bundleForm.getBundleImageUrl());
        bundle.setPrice(bundleForm.getBundlePrice());

        if(bundleForm.getBundlePruductIds() != null){
            bundleForm.getBundlePruductIds().forEach(id -> {
                Product product = productService.getById(id);
                bundle.getProducts().add(product);
            product.addBundle(bundle);
        });
        }
        return bundle;
    }
}
