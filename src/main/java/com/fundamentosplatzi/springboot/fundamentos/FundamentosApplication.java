package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserService userService;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUserInDataBase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();
    }

    private void saveWithErrorTransactional() {
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("Test2Transactional2", "Test2Transactional2@domain.com", LocalDate.now());
        User test3 = new User("Test3Transactional3", "TestTransactional1@domain.com", LocalDate.now());
        User test4 = new User("Test4Transactional4", "Test4Transactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);
        try {
            userService.saveTransactional(users);
        } catch (Exception e) {
            LOGGER.error("Esto es una Excepcion dentro del metodo transaccional " + e);
        }

        userService.getAllUsers().stream().
                forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transacional " + user));

    }

    private void getInformationJpqlFromUser() {
  /*
        LOGGER.info("Usuario con el metodo findByUserEmail " +
                userRepository.findByUserEmail("Julie@domain.com").orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
        // Ordenar y bucar a partir de una propiedad
        //ascending -> tambien muestra los usuarios de manera ascendente
        userRepository.findAndSort("user", Sort.by("id").descending()).stream().forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

        userRepository.findByName("Jhon").stream().forEach(user -> LOGGER.info("Usuario con query method " + user));
        LOGGER.info("Usuario con query method findByEmailAndName " + userRepository.findByEmailAndName("Jhon@domain.com", "Jhon").orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        userRepository.findByNameLike("%u%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameLike " + user));
        ;

        userRepository.findByNameOrEmail(null,"user10@domain.com")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));
        ;
        userRepository.findByNameOrEmail("user10",null)
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));
        ;

*/
        userRepository.
                findByBirthDayBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 4, 2))
                .stream()
                .forEach(user -> LOGGER.info("Usuario con intervalo de fechas " + user));

        userRepository.findByNameLikeOrderByIdDesc("%user%")
                .stream()
                .forEach(user -> LOGGER.info("Usuarios encontrado con like y ordenado " + user));

        userRepository.findByNameLikeOrderByIdAsc("%user%")
                .stream()
                .forEach(user -> LOGGER.info("Usuarios encontrado con like y ordenado ASc " + user));

        userRepository.findByNameContainingOrderByIdDesc("user")
                .stream()
                .forEach(user -> LOGGER.info("Usuarios encontrado findByNameContainingOrderByIdDesc " + user));

        LOGGER.info("El usuario a partir del named parameter es: " +
                userRepository.getAllByBirthDayAndEmail(LocalDate.of(2021, 03, 20), "Jhon@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter"))
        );

    }


    private void saveUserInDataBase() {
        User user1 = new User("Jhon", "Jhon@domain.com", LocalDate.of(2021, 03, 20));
        User user2 = new User("Julie", "Julie@domain.com", LocalDate.of(2021, 05, 21));
        User user3 = new User("Daniela", "Daniela@domain.com", LocalDate.of(2021, 8, 11));
        User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021, 7, 12));
        User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 11, 15));
        User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 2, 9));
        User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 3, 13));
        User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 4, 16));
        User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 5, 25));
        User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 8, 7));
        User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021, 12, 1));
        User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021, 1, 15));
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
        list.stream().forEach(userRepository::save);
    }


    private void ejemplosAnteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
        System.out.println(userPojo.toString());
        try {
            //error
            int value = 10 / 0;
            LOGGER.info("Mi valor: " + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error al dividir por cero" + e.getMessage());
        }
    }


}
