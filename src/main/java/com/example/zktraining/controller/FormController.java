package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.ui.util.Toast;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class FormController {

    @WireVariable
    CarService carService;

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

    @Init
    public void init(){

        companys.add("Honda");
        companys.add("Hyundai");
        companys.add("Toyota");

        categorys.add("Suv");
        categorys.add("Sedan");
        categorys.add("Coupe");

        whereProductions.add("Hà Nội");
        whereProductions.add("Đà Nẵng");
        whereProductions.add("Hồ Chí Minh");

        carDTO = new CarDTO();
    }


    @Command
    public void saveCar() {
        CarDTO result = carService.addCar(carDTO);
        if(result != null){
            Toast.show("Thêm mới thành công");
        }
    }



}
