
package com.ejemplo.SpringBoot.Controller;

import com.ejemplo.SpringBoot.model.Persona;
import com.ejemplo.SpringBoot.service.IPersonaService;
//import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontendprueba0912.web.app")
public class Controller {
  
    @Autowired
    private IPersonaService persoServ;
  
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new/persona")
    public void agregarPersona(@RequestBody Persona pers){
      persoServ.crearPersona(pers);
        // listaPersonas.add(pers);        
    }
    
    @GetMapping("/ver/personas")
    @ResponseBody
    public List<Persona>verPersonas(){
      return persoServ.verPersonas();
        // return listaPersonas;
    }
      @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void borrarPersona(@PathVariable Long id){
        persoServ.borrarPersona(id);
    }
      @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido){
        
        Persona persona = persoServ.buscarPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        
        persoServ.crearPersona(persona);
        return persona;
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return persoServ.buscarPersona((long)4);
    }
}
