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

    /* ADDRESS */

    @RequestMapping(value = "generateAddress", method = RequestMethod.GET)
    public @ResponseBody BaseResponse generateAddress() {
        try {
            mainFacade.generateAddress();
            return new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "getAddress", method = RequestMethod.GET)
    public @ResponseBody BaseResponse getAddress() {
        try {
            return new OkResponse(mainFacade.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }



    /* ORDER */

    @RequestMapping(value = "generateOrder", method = RequestMethod.GET)
    public @ResponseBody BaseResponse generateOrder() {
        try {
            mainFacade.generateOrder();
            return new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "getOrder", method = RequestMethod.GET)
    public @ResponseBody BaseResponse getOrder() {
        try {
            return new OkResponse(mainFacade.getOrder());
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }




    /* test mongo */
    @RequestMapping(value = "student", method = RequestMethod.GET)
    public @ResponseBody BaseResponse getStudent(String id) {
        try {
            return new OkResponse(mainFacade.getStudent(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "student/find", method = RequestMethod.GET)
    public @ResponseBody BaseResponse find() {
        try {
            return new OkResponse(mainFacade.findStudents());
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "studentUpdate", method = RequestMethod.GET)
    public @ResponseBody BaseResponse studentUpdate(String id) {
        try {
            return new OkResponse(mainFacade.studentUpdate(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public @ResponseBody BaseResponse saveStudent() {
        try {
            mainFacade.saveStudent();
            return new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }

    @RequestMapping(value = "studentDelete", method = RequestMethod.POST)
    public @ResponseBody BaseResponse studentDelete() {
        try {
            mainFacade.studentDelete();
            return new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorResponseCode.GENERIC_ERROR);
        }

    }


}
