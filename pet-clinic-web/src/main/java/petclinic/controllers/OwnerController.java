package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.services.OwnerService;

@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"/owners","/owners/index.html","/owners/index"})
    public String getOwner(Model model){

        model.addAttribute("owners",ownerService.findAll());

        return "owners/index";
    }
    @RequestMapping("/owners/find")
    public String findOwners(){
        return "notImplemented";
    }
}
