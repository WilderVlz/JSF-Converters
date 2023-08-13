package beans.backing;

import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.*;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.*;

@Named
@RequestScoped
public class VacanteForm {
    
    /*aca utilizamos el concepto de CDI para inyectar 
      la dependencia de este bean*/
    @Inject
    private Candidato candidato;
    private Logger log = LogManager.getRootLogger();
    
    public VacanteForm(){
        this.log.info("creando el objeto vacanterForm");
    }
    
    public void setCandidato(Candidato candidato){
        this.candidato = candidato;
    }
    
    public String enviar(){
        
        if(this.candidato.getNombre().equalsIgnoreCase("Wilder")){
            
            /*esta validacion es para comprobar que un usuario ya sea empleado
            de nuestra empresa, si tanto el nombre como el apellido coinciden,
            se le indicará el error y será redirigido a la pagina de inicio
            */
            if(this.candidato.getApellido().equalsIgnoreCase("Ruiz")){
                String msg = "gracias pero wilder ruiz ya trabaja con nosotros.";
                /*con esto enviamos el mensaje a nuestra pagina jsf*/
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,msg,msg);
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String componentId = null; //este es un mensaje global
                facesContext.addMessage(componentId, facesMessage);
                
                return "index";
            }
            
            log.info("entrando al caso de exito");
            
            /*no es necesario agregar la extencion ya que buscara algun archivo que
            coincida con este nombre
            */
            return "exito.xhtml";
            
        } else {
             log.info("entrando al caso de fallo");
            return "fallo";
            
        }
    }
    
}
