package com.fyp.bittrade.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fyp.bittrade.models.Product;
import com.fyp.bittrade.repositories.CartRepository;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {

    private static final String TAG = FavoritesViewModel.class.getName();

    private MutableLiveData<List<Product>> mutableLiveData;
    private List<Product> list = new ArrayList<>();
    private MutableLiveData<Double> priceLiveData;
    private CartRepository cartRepository;

    public CartViewModel() {
        cartRepository = CartRepository.getInstance();
    }

    public MutableLiveData<List<Product>> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            priceLiveData = new MutableLiveData<>();
            priceLiveData.setValue(0.0);
        }
        return mutableLiveData;
    }

    public MutableLiveData<Double> getPriceLiveData() {
        if (priceLiveData == null) {
            priceLiveData = new MutableLiveData<>();
            priceLiveData.setValue(0.0);
        }

        return priceLiveData;
    }

    public void setPriceLiveData(double price) {
        priceLiveData.setValue(price);
    }

    public List<Product> getProductsList() {
        List<Product> list = mutableLiveData.getValue();
        return list;
    }

    public List<Product> getList() {
        return list;
    }

    public double getPrice() {
        double price = 0.0;
        if (priceLiveData.getValue() != null)
            price = priceLiveData.getValue();

        return price;
    }

    public void setList(List<Product> list) {
        this.list = list;
        mutableLiveData.setValue(list);
    }

    public void add(Product p, String userId, CartRepository.IResponseAddCartCallBack responseCart) {
        p.incrementProductCount();
        list.add(p);
        double prePrice = priceLiveData.getValue();
        double newPrice = prePrice + p.getPrice();
        priceLiveData.setValue(newPrice);
        mutableLiveData.setValue(list);

        cartRepository.addToCart(p.getId(), userId, responseCart);
    }

    public void remove(Product p, String userId, CartRepository.IResponseAddCartCallBack responseCart) {
        list.remove(p);
        double prePrice = priceLiveData.getValue();
        double newPrice = prePrice - p.getPrice();
        priceLiveData.setValue(newPrice);
        mutableLiveData.setValue(list);

        cartRepository.removeFromCart(p.getId(), userId, responseCart);
    }

    public void incrementProductCount(String userId, String productId, int newQty, CartRepository.IResponseAddCartCallBack cartCallBack) {
        cartRepository.incrementProductCount(productId, userId, newQty, cartCallBack);
    }

    public void decrementProductCount(String userId, String productId, int newQty, CartRepository.IResponseAddCartCallBack cartCallBack) {
        cartRepository.decrementProductCount(productId, userId, newQty, cartCallBack);
    }

    public boolean hasProduct(Product p) {
        for (Product product:
             list) {
            if (product.getId().equals(p.getId())) {
                return true;
            }
        }
        return false;
    }

}
