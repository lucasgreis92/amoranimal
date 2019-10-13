package br.com.lgrapplications.amoranimal.amoranimal.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public abstract class DefaultController<T>  {

    public abstract MongoRepository<T, String> getRepository();


    /*    @ApiOperation(value = "Lista todos os dados do documento", notes = "Lista todos os dados do documento", response = Rating.class, responseContainer = "List" )
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Avaliações Listadas com sucesso"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })*/
    @GetMapping
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @ApiPageable
    @GetMapping(path = "/pagination")
    public Page<T> findAllPagination(
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable){
        return getRepository().findAll(pageable);
    }

    @GetMapping("{id}")
    public T findById(@PathVariable("id") String id) {
        Optional<T> retorno =  getRepository().findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }else{
            return null;
        }

    }

    @GetMapping("count")
    public long count() {
        return getRepository().count();
    }

    @PostMapping
    public T save(@RequestBody @Valid T t) {
        return (T)getRepository().save(t);
    }

    @DeleteMapping
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") String id) {
        getRepository().deleteById(id);
    }



}
