package com.onlineBookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBookStore.model.Book;
import com.onlineBookStore.model.Order;
import com.onlineBookStore.model.User;
import com.onlineBookStore.model.UserDto;
import com.onlineBookStore.service.BookService;
import com.onlineBookStore.service.OrderService;
import com.onlineBookStore.service.UserService;


@RestController
@RequestMapping("/onlinestore")
public class MainController {
	
	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthCheck(){
		return new ResponseEntity<>("Backend is working",HttpStatus.OK);
	}
	
	//user Methods
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody User user){ 
	    UserDto u1 = userService.createNewUser(user);
	    return new ResponseEntity<>(u1, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users=userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book){
		Book createdBook = bookService.addBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
	}
	
	@GetMapping("/getBooks")
	public ResponseEntity<List<Book>> getBooks(){
		List<Book> books=bookService.getAllBooks();
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@PutMapping("/updateBook/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

	@DeleteMapping("/deleteBook/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        String response=bookService.deleteBook(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
	
	
	 @GetMapping("/searchBooks")
	    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
	        List<Book> books = bookService.searchBooks(query);
	        return new ResponseEntity<>(books, HttpStatus.OK);
	    }

	    // Order Management
	 
	 
	 @Autowired
	 private OrderService orderService;

	    @PostMapping("/placeOrder")
//	    @PreAuthorize("hasRole('USER')")
	    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
//	    	System.out.println(order.getUser());
	        Order createdOrder = orderService.placeOrder(order);
	        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	    }

	    @GetMapping("/getOrders")
//	    @PreAuthorize("hasRole('USER')")
	    public ResponseEntity<List<Order>> getUserOrders() {
	        List<Order> orders = orderService.getUserOrders();
	        return new ResponseEntity<>(orders, HttpStatus.OK);
	    }

//	    @GetMapping("/getAllOrders")
////	    @PreAuthorize("hasRole('ADMIN')")
//	    public ResponseEntity<List<Order>> getAllOrders() {
//	        List<Order> orders = orderService.getAllOrders();
//	        return new ResponseEntity<>(orders, HttpStatus.OK);
//	    }
//
//	    // User Profile Management
//
//	    @GetMapping("/profile")
////	    @PreAuthorize("hasRole('USER')")
//	    public ResponseEntity<UserDto> getUserProfile() {
//	        UserDto user = userService.getUserProfile();
//	        return new ResponseEntity<>(user, HttpStatus.OK);
//	    }
//
//	    @PutMapping("/updateProfile")
////	    @PreAuthorize("hasRole('USER')")
//	    public ResponseEntity<UserDto> updateUserProfile(@RequestBody UserDto userDto) {
//	        UserDto updatedUser = userService.updateUserProfile(userDto);
//	        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//	    }
//
	    @DeleteMapping("/disableAccount/{userId}")
//	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> disableUserAccount(@PathVariable Long userId) {
	        boolean usr=userService.disableUserAccount(userId);
	        if(usr) {
	        	return new ResponseEntity<>("User Deleted",HttpStatus.OK);
	        }
	        return new ResponseEntity<>("User Not Found",HttpStatus.BAD_REQUEST);

	    }
}
