package com.onlineBookStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBookStore.exception.ResourceNotFoundException;
import com.onlineBookStore.model.Book;
import com.onlineBookStore.repository.BookRepository;
@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
public Book addBook(Book book) {
	return bookRepository.save(book);
}
public List<Book> getAllBooks() {
	return bookRepository.findAll();
}
public Book updateBook(Long id, Book updatedBookDetails) {
    // Fetch the book from the repository
    Book existingBook = bookRepository.findById(id)
    		 .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    
   

    // Update the book's details only if they are provided
    if (updatedBookDetails.getTitle() != null) {
        existingBook.setTitle(updatedBookDetails.getTitle());
    }
    if (updatedBookDetails.getAuthor() != null) {
        existingBook.setAuthor(updatedBookDetails.getAuthor());
    }
    if (updatedBookDetails.getDescription() != null) {
        existingBook.setDescription(updatedBookDetails.getDescription());
    }
    if (updatedBookDetails.getPrice() != null) {
        existingBook.setPrice(updatedBookDetails.getPrice());
    }
    if (updatedBookDetails.getQuantity() != null) {
        existingBook.setQuantity(updatedBookDetails.getQuantity());
    }

    // Save the updated book back to the repository
    return bookRepository.save(existingBook);
}
public String deleteBook(Long id) {
	// TODO Auto-generated method stub
	Optional<Book> existingBook = bookRepository.findById(id);
	if(existingBook.isPresent()) {
		bookRepository.deleteById(id);
		return "Book Deleted";
	}
	return "No Book Found";
	
}
public List<Book> searchBooks(String query) {
    return bookRepository.findByTitleContainingOrAuthorContainingOrDescriptionContaining(query, query, query);
}

}
