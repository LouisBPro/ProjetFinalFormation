package fr.projetFormation.Hashi.restcontroller;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Plat update(@Valid @RequestBody Plat plat, BindingResult br, @PathVariable("id") Long id) {
		Plat platEnBase = platService.byId(id);
		platEnBase.setNom(plat.getNom());
		platEnBase.setDescription(plat.getDescription());
		platEnBase.setPhoto(plat.getPhoto());
		platEnBase.setPrix(plat.getPrix());
		return platService.update(platEnBase);
	}
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
    	platService.delete(platService.byId(id));
    }
    @PostMapping(value="/update/{id}",consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    @JsonView(JsonViews.Common.class)
	public String updatePhoto(@RequestParam("file") MultipartFile file,@PathVariable("id") Long id) throws IOException{
    	try {
    		Plat platEnBase = platService.byId(id);
        	byte[] image = file.getBytes();
        	platEnBase.setPhoto(image);
        	platService.update(platEnBase);
        	return "success";
		} catch (Exception e) {
			 return "error";
		}
    	
    }
    
}