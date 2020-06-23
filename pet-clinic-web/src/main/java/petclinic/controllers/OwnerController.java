package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import petclinic.model.Owner;
import petclinic.services.OwnerService;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.validation.Valid;
import java.util.List;
@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEW_OWNER_CREATE_OR_UPDATE = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }



    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");

    }


//    @RequestMapping({"/owners","/owners/index.html","/owners/index"})
//    public String getOwner(Model model){
//
//        model.addAttribute("owners",ownerService.findAll());
//
//        return "owners/index";
//    }
    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";

    }
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if (owner.getLastName()==null){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if (results.isEmpty()){
            result.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        } else if (results.size()==1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else {
            model.addAttribute("selections",results);
            return "owners/ownersList";

        }

    }


    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("/owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return VIEW_OWNER_CREATE_OR_UPDATE;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner,BindingResult result){
        if (result.hasErrors()){
            return VIEW_OWNER_CREATE_OR_UPDATE;
        } else{
            Owner savedOwner = ownerService.save(owner);
            return  "redirect:/owners/" + savedOwner.getId();
        }
    }
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEW_OWNER_CREATE_OR_UPDATE;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
                                         @PathVariable("ownerId") Long ownerId){
        if (result.hasErrors()){
            return VIEW_OWNER_CREATE_OR_UPDATE;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
