package com.example.admin_panel_service.controller.view;


import com.example.admin_panel_service.Validation;
import com.example.admin_panel_service.dto.view.RequestDtoView;
import com.example.admin_panel_service.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/admin/view")
@RequiredArgsConstructor
public class ViewController {

    public final ViewService viewService;

    // @TODO доабвить нравзание странички

    @GetMapping()
    public String getPage(Model model){
        model.addAttribute("views" ,viewService.findAll());
        return "....";
    }


    @PostMapping("create")
    public String saveNewView(RequestDtoView dto, Model model){

        viewService.save(dto);
        return "...";
    }

    @PostMapping("/delete/{viewId:\\d+}")
    public String delete(@PathVariable(name = "viewId") Long id){
        viewService.delete(id);
        return "redirect://api/admin/view";
    }

    @ExceptionHandler(Validation.class)
    public String handlerValidation(Validation validation){
        return "...";
    }
}
