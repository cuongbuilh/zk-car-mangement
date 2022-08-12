package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class CarController extends SelectorComposer<Component> {
    @WireVariable
    CarService carService;
    @Getter
    @Setter
    private List<CarDTO> listCar;

    @Getter
    @Setter
    private String str;
    @Getter
    @Setter
    private boolean checkAll = false;

    @Init
    public void init(){
        listCar = carService.getListCar();
    }
    @Command("onCheckAll")
    public void checkAll(){
        setCheckAll(!checkAll);
        BindUtils.postNotifyChange(null, null, this, "checkAll");
        listCar.stream().forEach(carDTO -> {
            carDTO.setChecked(checkAll);
            BindUtils.postNotifyChange(null,null, carDTO, "checked");
        });
    }
    @Command("redirect")
    public void redirectAdd(){
        Window window = (Window)Executions.createComponents(
                "addCar.zul", null, null);
        window.doModal();    }
}
