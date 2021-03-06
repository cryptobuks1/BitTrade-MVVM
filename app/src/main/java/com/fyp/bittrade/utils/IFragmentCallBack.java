package com.fyp.bittrade.utils;

import com.fyp.bittrade.models.Product;

public interface IFragmentCallBack {

    void onProductClick(Product product);
    void onBackPressed();
    void logout();
    void loadAddProductImagesFragment(Product product);
    void loadExploreFragment();
    void loadCheckoutFragment();

    void loadPaymentFragment(String url);

    void loadSearchFragment(String searchId);

    void loadMyOrdersFragment();

    void loadCartFragment();

    void loadProfileFragment();

    void loadMyProductsFragment();
}
