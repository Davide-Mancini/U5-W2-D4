package davidemancini.U5_W2_D4.services;


import davidemancini.U5_W2_D4.entities.Autori;
import davidemancini.U5_W2_D4.entities.Blog;
import davidemancini.U5_W2_D4.exceptions.NotFoundExceptions;
import davidemancini.U5_W2_D4.payloads.BlogsDTO;
import davidemancini.U5_W2_D4.payloads.BlogsPayload;
import davidemancini.U5_W2_D4.repositories.AutoriRepository;
import davidemancini.U5_W2_D4.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogsService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AutoriRepository autoriRepository;
    public Page<Blog> findAll (int pageNumbe, int pageSize, String pageSortBy){
        if (pageSize>50) pageSize=50;
        Pageable pageable = PageRequest.of(pageNumbe,pageSize, Sort.by(pageSortBy));
        return blogRepository.findAll(pageable);
    }
    public Blog saveBlog (BlogsDTO body){
        Autori autoreTrovato = autoriRepository.findById(body.author_id()).orElseThrow(()-> new NotFoundExceptions(body.author_id()));
        Blog newBlog = new Blog(body.categoria(), body.titolo(), body.contenuto(),body.tempoDiLettura(),autoreTrovato);
        blogRepository.save(newBlog);
        return newBlog;
    }
    public Blog findById (UUID blogId) {
       return blogRepository.findById(blogId).orElseThrow(()-> new NotFoundExceptions(blogId));
    }

    //RICERCA TRAMITE ID E UPDATE
    public Blog findByIdAndUpdate(UUID blogId, BlogsPayload body){
        Autori autoreTrovato = autoriRepository.findById(body.getAuthor_id()).orElseThrow(()-> new NotFoundExceptions(body.getAuthor_id()));
        Blog found = findById(blogId);
                found.setCategoria(body.getCategoria());
                found.setTitolo(body.getTitolo());
                found.setContenuto(body.getContenuto());
                found.setTempoDiLettura(body.getTempoDiLettura());
                found.setAuthor_id(autoreTrovato);
            Blog blogModificato= found;
            blogRepository.save(blogModificato);
            return blogModificato;
        }


    //ELIMINA BLOG SPECIFICO
    public void deleteBlogById(UUID blogId){
        Blog found = findById(blogId) ;
        blogRepository.delete(found);

    }
}
