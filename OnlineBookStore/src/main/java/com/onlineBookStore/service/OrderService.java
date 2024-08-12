package com.onlineBookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBookStore.exception.ResourceNotFoundException;
import com.onlineBookStore.model.Book;
import com.onlineBookStore.model.Order;
import com.onlineBookStore.model.OrderBook;
import com.onlineBookStore.model.User;
import com.onlineBookStore.repository.BookRepository;
import com.onlineBookStore.repository.OrderRepository;
import com.onlineBookStore.repository.UserRepository;

@Service
public class OrderService {

	  @Autowired
	    private OrderRepository orderRepository;
	  
	  @Autowired
	  private BookRepository bookRepository;
	  
	  @Autowired
	  private UserRepository userRepository;

	  public Order placeOrder(Order order) {
	        double totalPrice = 0.0;
	        Long userId=order.getUser();
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

	        // Set the user for the order
	        order.setUser(userId);
	        int count=0;
	        for (OrderBook orderBook : order.getOrderBooks()) {
	            Long bookId = orderBook.getBookId();
	            int orderQuantity = orderBook.getQuantity() != null ? orderBook.getQuantity() : 1;
	            

	            // Fetch the book from the repository
	            Book book = bookRepository.findById(bookId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

	            // Check if sufficient quantity is available
	            if (book.getQuantity() < orderQuantity) {
	                throw new IllegalArgumentException("Not enough stock for book with id: " + bookId);
	            }

	            // Decrease the book's stock
	            book.setQuantity(book.getQuantity() - orderQuantity);
	            bookRepository.save(book);

	            // Update total price
	            totalPrice += book.getPrice() * orderQuantity;

	            // Update the order book with the calculated quantity
	            orderBook.setQuantity(orderQuantity);
	            System.out.println(count++);
	        }

	        // Set the total price for the order
	        order.setTotalPrice(totalPrice);

	        // Save and return the order
	        return orderRepository.save(order);
	    }

	public List<Order> getUserOrders() {
		return orderRepository.findAll();
	}

}
