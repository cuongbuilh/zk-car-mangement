package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.*;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.ui.util.Toast;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class FormController {

    @WireVariable
    CarService carService;

    private Window window;
    @Getter
    @Setter
    private  List<String> companys = new ArrayList<String>();
    @Getter
    @Setter
    private  List<String> categorys = new ArrayList<String>();
    @Getter
    @Setter
    private List<String> whereProductions = new ArrayList<String>();

    @Getter
    @Setter
    private CarDTO carDTO;

    private Boolean addCar;

    @Getter
    @Setter
    private String title;

    @Init
    public void init(){

        Execution execution = Executions.getCurrent();
        if(execution.getArg().get("carDTO") != null){
            carDTO = (CarDTO) execution.getArg().get("carDTO");
            title = "Edit Car";
            addCar = false;
        }
        else{
            title = "Add Car";
            addCar = true;
            carDTO = new CarDTO();
        }

        companys.add("Honda");
        companys.add("Hyundai");
        companys.add("Toyota");

        categorys.add("Suv");
        categorys.add("Sedan");
        categorys.add("Coupe");

        whereProductions.add("Hà Nội");
        whereProductions.add("Đà Nẵng");
        whereProductions.add("Hồ Chí Minh");


    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
        window = (Window) view;
    }

    @Command
    public void saveCar() {
        try{
            CarDTO result = carService.addCar(carDTO);
            if(result != null){
                    if(addCar){
                        Map<String, Object> arg = new HashMap<>();
                        arg.put("item", carDTO);
                        BindUtils.postGlobalCommand(null, null, "onLoadDataListCar",null);
                        window.detach();
                        Toast.show("Thêm mới thành công");

                    }else{
                        Toast.show("Cập nhật thành công");
                    }
            }
        }catch (Exception e){
            throw e;
        }

    }

    @Command
    public void closeForm() {
        this.window.detach();
    }


}
