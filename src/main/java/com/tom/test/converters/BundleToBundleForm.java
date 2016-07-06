package com.tom.test.converters;

import com.tom.test.commands.BundleForm;
import com.tom.test.domain.Bundle;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 7/5/2016.
 */
@Component
public class BundleToBundleForm implements Converter<Bundle,BundleForm> {
    @Override
    public BundleForm convert(Bundle bundle) {
        BundleForm bundleForm = new BundleForm();

        bundleForm.setBundleId(bundle.getId());
        bundleForm.setBundleVersion(bundle.getVersion());
        bundleForm.setBundleName(bundle.getName());
        bundleForm.setBundleDescription(bundle.getDescription());
        bundleForm.setBundleImageUrl(bundle.getImageUrl());
        bundleForm.setBundlePrice(bundle.getPrice());

        List<Integer> productsId = new ArrayList<>();
        if (!bundle.getProducts().isEmpty()) {
            bundle.getProducts().forEach(product -> productsId.add(product.getId()));
        }
        bundleForm.setBundlePruductIds(productsId);
        return bundleForm;
    }
}
