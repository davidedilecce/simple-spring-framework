package fe.facade;

import fe.exceptions.NameNotSettedException;
import fe.exceptions.SurnameNotSettedException;
import fe.viewmodel.UISavePerson;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MainFacade {


    public void savePerson(UISavePerson person) throws NameNotSettedException, SurnameNotSettedException {

        if (StringUtils.isEmpty(person.getName())) {
            throw new NameNotSettedException();
        }
        if (StringUtils.isEmpty(person.getSurname())) {
            throw new SurnameNotSettedException();
        }



    }

}
