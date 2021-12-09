package fr.projetFormation.Hashi.restcontroller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.Plat;
import fr.projetFormation.Hashi.services.PlatService;

@RestController
@RequestMapping("/api/plat")
@CrossOrigin(origins="*")
public class PlatRestController {
    
    @Autowired
    private PlatService platService;
    
    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Plat byId(@PathVariable("id") Long id){
        return platService.byId(id);
    }
    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Plat> all(){
        return platService.all();
    }
    @PostMapping("")
    @JsonView(JsonViews.Common.class)
    public Plat create(@Valid @RequestBody Plat plat){
    	
		return platService.save(plat);
    }
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
    	platService.delete(platService.byId(id));
    }
}