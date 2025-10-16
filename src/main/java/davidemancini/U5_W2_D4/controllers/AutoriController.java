package davidemancini.U5_W2_D4.controllers;


import davidemancini.U5_W2_D4.entities.Autori;
import davidemancini.U5_W2_D4.exceptions.ValidationException;
import davidemancini.U5_W2_D4.payloads.AutoriDTO;
import davidemancini.U5_W2_D4.payloads.AutoriPayload;
import davidemancini.U5_W2_D4.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AutoriController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public Page<Autori> getAutori(@RequestParam (defaultValue = "0") int page,
                                  @RequestParam (defaultValue = "10") int size,
                                  @RequestParam (defaultValue = "nome") String sortBy){
        return autoreService.findAll(page,size,sortBy);
    }
    @GetMapping("/{authorId}")
    public Autori authorById(@PathVariable UUID authorId){
        return autoreService.findAutoreById(authorId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autori createAuthor (@RequestBody @Validated AutoriDTO body, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
    return autoreService.saveAutore(body);
    }
    @PutMapping("/{authorId}")
    public Autori findAuthorByIdAndUpdate(@PathVariable UUID authorId, @RequestBody @Validated AutoriDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return autoreService.findByIdAndUpdate(authorId,body);
    }
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorById (@PathVariable UUID authorId){
        autoreService.deleteAutoreById(authorId);
    }
    @PatchMapping("/{authorId}/avatar")
    public Autori uploadImage(@PathVariable UUID authorId,@RequestParam("avatar")MultipartFile file)throws IOException{

        return autoreService.uploadAvatar(authorId,file);
    }
}
