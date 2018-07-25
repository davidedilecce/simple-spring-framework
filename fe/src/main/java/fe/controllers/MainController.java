package fe.controllers;

import fe.exceptions.NameNotSettedException;
import fe.exceptions.SurnameNotSettedException;
import fe.facade.MainFacade;
import fe.viewmodel.UISavePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.responses.BaseResponse;
import utils.responses.ErrorResponse;
import utils.responses.OkResponse;
import utils.responses.errorCodes.ErrorResponseCode;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private MainFacade mainFacade;


    @RequestMapping("")
    public String index(Model model) {
        //model.addAttribute("list", )
        return "index";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResponse save(UISavePerson person) {
        try {
            mainFacade.savePerson(person);
            return new OkResponse();
        } catch (NameNotSettedException e) {
            return new ErrorResponse(ErrorResponseCode.NAME_NOT_SETTED);
        } catch (SurnameNotSettedException e) {
            return new ErrorResponse(ErrorResponseCode.SURNAME_NOT_SETTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

}
