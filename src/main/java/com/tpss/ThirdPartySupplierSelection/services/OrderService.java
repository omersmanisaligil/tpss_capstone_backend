package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.OrderDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    public Page<Order> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Order> orders= orderDAO.findAll(pageRequest);

        return orders;
    }

    public Optional<Order> getOneByID(Long id){
        Optional<Order> order = orderDAO.findById(id);

        return order;
    }

    public Order updateOrder(Order order, Long id){
        Order orderToUpdate = null;

        orderToUpdate=order;
        order.setOrderId(id);

        orderDAO.save(orderToUpdate);

        return orderToUpdate;
    }

    public void deleteOrder(Long id){
        orderDAO.deleteById(id);
    }

    public void addOrder(Order order){
        orderDAO.save(order);
    }
}
