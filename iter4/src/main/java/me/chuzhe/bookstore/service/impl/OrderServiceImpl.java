package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.domain.entity.*;
import me.chuzhe.bookstore.domain.dao.*;
import me.chuzhe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tang on 2017/6/23.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    private final BookRepository bookRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(UserRepository userRepository, CartItemRepository cartItemRepository, BookRepository bookRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getOrderByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        return orderRepository.findByUserByUserId(user, new Sort("orderId"));
    }

    @Transactional
    public boolean execute(String deliverTo, String username) {
        // check if that cart item already exists
        User user = userRepository.findByUsername(username);
        int userId = user.getUserId();
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);
        if (cartItems.isEmpty()) {
            return false;
        }

        // create an empty order for now
        Order order = new Order();
        order.setDeliverTo("");
        order.setPlaceTime(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        order.setTotalPrice(0);
        order.setUserByUserId(user);
        orderRepository.save(order);

        // add every order item
        int totalPrice = 0;
        for (CartItem cartItem :
                cartItems) {
            // get target book
            Book book = cartItem.getBookByBookId();

            // get desired quantity
            int quantity = cartItem.getQuantity();

            // check if stock is enough
            if (quantity > book.getStock()) {
                throw new RuntimeException("Not enough book for this order");
            }

            // create new order item
            int unitPrice = book.getPrice();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderByOrderId(order);
            orderItem.setBookByBookId(book);
            orderItem.setOriginalUnitPrice(unitPrice);
            orderItem.setQuantity(quantity);
            orderItemRepository.save(orderItem);

            // update store stock
            book.setStock(book.getStock() - quantity);
            bookRepository.save(book);

            // accumulate total price
            totalPrice += quantity * unitPrice;
        }

        // check if user is rich enough to pay
        if (totalPrice > user.getCredit()) {
            throw new RuntimeException("Not enough credit for this order");
        }

        // update order detail
        order.setTotalPrice(totalPrice);
        order.setDeliverTo(deliverTo);
        orderRepository.save(order);

        // update user credit
        user.setCredit(user.getCredit() - totalPrice);
        userRepository.save(user);

        // clear user's cart
        cartItemRepository.deleteByUserId(user.getUserId());

        return true;
    }
}
