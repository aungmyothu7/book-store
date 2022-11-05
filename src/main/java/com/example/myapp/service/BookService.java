package com.example.myapp.service;

import com.example.myapp.dao.AuthorDao;
import com.example.myapp.dao.BookDao;
import com.example.myapp.dao.CategoryDao;
import com.example.myapp.ds.Author;
import com.example.myapp.ds.Book;
import com.example.myapp.ds.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookService {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CategoryDao categoryDao;

    //book
    @Transactional
    public void saveBook(Book book,int categoryId,int authorId){

        Category category=categoryDao.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        Author author=authorDao.findById(authorId).orElseThrow(EntityNotFoundException::new);
        category.addBook(book);
        author.addBook(book);

        bookDao.save(book);
    }

    public List<Book>findAllBooks(){
        return bookDao.findAll();
    }
//author
    public void saveAuthor(Author author){
        authorDao.save(author);
    }

    public List<Author>findAllAuthors(){
        return authorDao.findAll();
    }
//category
    public void saveCategory(Category category){
        categoryDao.save(category);
    }

    public List<Category>findAllCategory(){
        return categoryDao.findAll();
    }

    public Category findCategoryById(int id){
        return categoryDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Book findBookById(int id){
        return bookDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
