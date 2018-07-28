package fe.controllers;

import domain.Person;
import fe.facade.MainFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utils.responses.BaseResponse;
import utils.responses.ErrorResponse;
import utils.responses.OkResponse;
import utils.responses.errorCodes.ErrorResponseCode;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private MainFacade mainFacade;


    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("list", mainFacade.getAllPersons(null));
        return "index";
    }

    @RequestMapping("find")
    public @ResponseBody BaseResponse index(String name) {
        try {
            List<Person> persons = mainFacade.getAllPersons(name);
            return new OkResponse(persons);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody BaseResponse save(@RequestBody Person person) {

        try {
            Person p = mainFacade.savePerson(person);
            return new OkResponse(p);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody BaseResponse save(@PathVariable Integer id) {
        try {
            mainFacade.deletePerson(id);
            return new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

}
