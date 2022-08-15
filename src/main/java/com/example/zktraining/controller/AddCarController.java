package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.ui.util.Toast;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@VariableResolver(DelegatingVariableResolver.class)
@Slf4j
public class AddCarController {
    @Wire
    Window modalDialog;

    @Listen("onClick = #closeBtn")
    public void showModal(Event e) {
        modalDialog.detach();
    }

    @Getter
    @Setter
    private CarDTO car;

    @Getter
    @Setter
    List<String> manufacturerList = new ArrayList<>();

    @Getter
    @Setter
    List<String> assemblyList = new ArrayList<>();

    @WireVariable
    private CarService carService;

    @Getter
    @Setter
    private String title;



    @Init
    public void init() {
        Map<?, ?> arg = Executions.getCurrent().getArg();
        log.info("args {}", arg);

        if (Objects.nonNull(arg) && arg.containsKey("car")){
            car = (CarDTO) arg.get("car");
        }else {
            car = new CarDTO();
        }

        if (Objects.nonNull(arg) && arg.containsKey("title")){
            title = (String) arg.get("title");
        }else {
            title = "create";
        }

        manufacturerList.add("BMW");
        manufacturerList.add("Audi");
        manufacturerList.add("Mercedes");
        manufacturerList.add("Toyota");
        manufacturerList.add("Honda");

        assemblyList.add("Germany");
        assemblyList.add("USA");
        assemblyList.add("Japan");
        assemblyList.add("China");
        assemblyList.add("Korea");

    }

    @Command
    public void back() {
        Executions.sendRedirect("/");
    }

    @Command
    public void createCar() {
        log.info("createCar {}", car);
        if (carService.addCar(car)) {
            Toast.show("car was created", "info", null, 0, true);
            Executions.sendRedirect("/");
        } else {
            log.error("createCar error");
            Toast.show("error, try again!", "error", null, 0, true);
        }
    }
}
