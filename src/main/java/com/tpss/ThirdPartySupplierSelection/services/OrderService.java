package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.OrderDAO;
import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.dto.OrderDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.util.PageImplCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    public Page<OrderDTO> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        List<Order> orders= orderDAO.findAll();

        List<OrderDTO> orderDTOs = DTOMapper.toOrderDTOList(orders);
        Page<OrderDTO> orderDTOPage = PageImplCustom.createPage(
                                                    orderDTOs,
                                                    pageRequest);

        return orderDTOPage;
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
