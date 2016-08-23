package com.tom.test.converters;

import com.tom.test.domain.Bundle;
import com.tom.test.domain.CartDetail;
import com.tom.test.domain.OrderHistory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 8/23/2016.
 */
@Component
public class BundleToOderHistory implements Converter<Bundle, OrderHistory>{
    @Override
    public OrderHistory convert(Bundle bundle) {
        List<CartDetail> cartDetailList = new ArrayList<>();
        bundle.getProducts().forEach(product -> {
            cartDetailList.add(new CartDetail(product,1));
        });

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setTotalPrice(bundle.getPrice());
        orderHistory.setCartDetails(cartDetailList);

        return orderHistory;
    }
}
