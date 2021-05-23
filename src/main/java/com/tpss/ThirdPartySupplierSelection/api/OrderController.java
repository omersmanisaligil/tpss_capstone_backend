package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.dto.OrderDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    public OrderController(OrderService orderService){
	this.orderService = orderService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<OrderDTO>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
	Page<OrderDTO> allOrders = orderService.getAll(page,size);
	return ResponseEntity.status(HttpStatus.OK).body(allOrders);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<OrderDTO> getOneByID(@PathVariable("id") Long id){
	OrderDTO order = orderService.getOneByID(id);
	return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addOrder(@Validated @NonNull @RequestBody Order order){
	orderService.addOrder(order);
	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Order> updateOrder(@Validated @NonNull @RequestBody Order order,
					   @PathVariable("id") Long id){

	Order updatedOrder = orderService.updateOrder(order,id);
	return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
	orderService.deleteOrder(id);
	return ResponseEntity.status(HttpStatus.OK).build();
    }
}
