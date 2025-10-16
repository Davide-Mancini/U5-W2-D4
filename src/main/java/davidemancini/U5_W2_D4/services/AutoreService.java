package davidemancini.U5_W2_D4.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import davidemancini.U5_W2_D4.entities.Autori;
import davidemancini.U5_W2_D4.exceptions.NotFoundExceptions;
import davidemancini.U5_W2_D4.payloads.AutoriDTO;
import davidemancini.U5_W2_D4.repositories.AutoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class AutoreService {
    @Autowired
    AutoriRepository autoriRepository;
    @Autowired
    private Cloudinary imageUploader;


    public Page<Autori> findAll (int pageNumber, int pageSize, String pageSortBy){
        if (pageSize>50) pageSize=50;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(pageSortBy));
        return autoriRepository.findAll(pageable);
    }

    public Autori saveAutore(AutoriDTO body){
        Autori newAutore = new Autori(body.nome(), body.cognome(), body.email(), body.dataDiNascita());
        autoriRepository.save(newAutore);
        return newAutore;
    }

    public Autori findAutoreById (UUID autoreId){
        return autoriRepository.findById(autoreId).orElseThrow(()->new NotFoundExceptions(autoreId));
    }

    public Autori findByIdAndUpdate (UUID autoreId, AutoriDTO body){
        Autori trovato = findAutoreById(autoreId);
        trovato.setNome(body.nome());
        trovato.setCognome(body.cognome());
        trovato.setDataDiNascita(body.dataDiNascita());
        trovato.setEmail(body.email());
        Autori autoreModificato = autoriRepository.save(trovato);
        return autoreModificato;
    }

    public void deleteAutoreById(UUID autoreId){
        Autori trovato= findAutoreById(autoreId);
        autoriRepository.delete(trovato);

    }
    public Autori uploadAvatar(UUID autoreId, MultipartFile file){
       Autori trovato = findAutoreById(autoreId);
       try {
        Map result = imageUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
       String imgUrl = (String) result.get("url");
        trovato.setAvatar(imgUrl);
       }catch (IOException ex){
        throw new RuntimeException(ex);
       }
        return trovato;
    }
}
