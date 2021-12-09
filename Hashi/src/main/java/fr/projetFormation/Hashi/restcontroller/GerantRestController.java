package fr.projetFormation.Hashi.restcontroller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projetFormation.Hashi.entities.Gerant;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.services.GerantService;

@RestController
@RequestMapping("/api/gerant")
@CrossOrigin(origins="*")
public class GerantRestController {

    @Autowired
    private GerantService gerantService;

    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Gerant byId(@PathVariable("id") Long id) {
        return gerantService.byId(id);
    }

    @PostMapping("")
    @JsonView(JsonViews.Common.class)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Gerant create(@Valid @RequestBody Gerant gerant, BindingResult br) {
        return gerantService.create(gerant);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.class)
    public Gerant update(@Valid @RequestBody Gerant gerant, BindingResult br, @PathVariable("id") Long id) {
        Gerant gerantEnBase = gerantService.byId(id);
        gerantEnBase.setNom(gerant.getNom());
        gerantEnBase.setPrenom(gerant.getPrenom());
        gerantEnBase.setEmail(gerant.getEmail());
        return gerantService.update(gerantEnBase);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        gerantService.delete(id);
    }
}
