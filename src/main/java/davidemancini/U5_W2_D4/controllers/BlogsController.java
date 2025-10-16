package davidemancini.U5_W2_D4.controllers;




import davidemancini.U5_W2_D4.entities.Blog;
import davidemancini.U5_W2_D4.exceptions.ValidationException;
import davidemancini.U5_W2_D4.payloads.BlogsDTO;
import davidemancini.U5_W2_D4.payloads.BlogsPayload;
import davidemancini.U5_W2_D4.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
@Autowired
    private BlogsService blogsService;


//RITORNA LA LISTA DI TUTTI I BLOG
    @GetMapping
    public Page<Blog> getBlogs (@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "titolo") String sortBy){
        return blogsService.findAll(page,size,sortBy);
    }

//SALVO UN NUOVO BLOG
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Blog createBlog (@RequestBody @Validated BlogsDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return blogsService.saveBlog(body);
}
//RITORNA UN SINGOLO BLOG
    @GetMapping("/{blogId}")
public Blog blogById(@PathVariable UUID blogId){
        return blogsService.findById(blogId);
    }
//MODIFICA UNO SPECIFICO BLOG
    @PutMapping("/{blogId}")
    public Blog getBlogByIdAndUpdate(@PathVariable UUID blogId, @RequestBody BlogsPayload body){
    return blogsService.findByIdAndUpdate(blogId,body);
    }
//ELIMINA SPECIFICO BLOG
@DeleteMapping("/{blogId}")
@ResponseStatus(HttpStatus.NO_CONTENT)public void deleteBlogById(@PathVariable UUID blogId){
        blogsService.deleteBlogById(blogId);
}
}
