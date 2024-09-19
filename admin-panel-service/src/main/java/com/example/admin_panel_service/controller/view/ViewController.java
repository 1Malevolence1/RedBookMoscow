package com.example.admin_panel_service.controller.view;


import com.example.admin_panel_service.exeption.Validation;
import com.example.admin_panel_service.dto.view.RequestDtoView;
import com.example.admin_panel_service.service.view.ViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/admin/view")
@RequiredArgsConstructor
@Slf4j
public class ViewController {

    public final ViewService viewService;

    // @TODO доабвить нравзание странички

    @GetMapping()
    public String getPage(Model model){
        log.info("{}", viewService.findAll());
        model.addAttribute("views" ,viewService.findAll());
        return "admin_add_view";
    }

    @PostMapping("/create")
    public String saveNewView(@RequestParam(name = "title") String title) {
        log.info("->>>>>>>>>{}", title);
        RequestDtoView dto = new RequestDtoView(title); // Создаем DTO с новым названием
        viewService.save(dto); // Сохраняем новый вид
        return "redirect:/api/admin/view"; // Перенаправление на страницу со списком видов
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "viewId") Long viewId) {
        log.info("..............{}", viewId);
        viewService.delete(viewId); // Удаляем вид по ID
        return "redirect:/api/admin/view"; // Перенаправление на страницу со списком видов
    }


    @ExceptionHandler(Validation.class)
    public String handlerValidation(Validation validation){
        return "...";
    }
}
