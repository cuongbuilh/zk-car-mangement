package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.dto.client.CarSearchDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.*;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
public class CarController {
    @WireVariable
    private CarService carService;

    Set<Integer> selectedIds = new HashSet<>();
    Page<CarDTO> page;
    CarSearchDTO carSearchDTO;

    @Getter
    @Setter
    CarDTO car;

    @Getter
    @Setter
    boolean checkAll = false;

    @Command
    public void create() {
        System.out.println("createCar");
    }

    @Command
    public void update() {
        System.out.println("updateCar");
    }

    @Command
    public void deleteAll() {
        List<Integer> deletedIds = getCars().stream()
                .filter(CarDTO::isChecked)
                .map(CarDTO::getId)
                .collect(Collectors.toList());
        if (carService.deleteCars(deletedIds)) {
            page = carService.search(carSearchDTO);
            BindUtils.postNotifyChange(null, null, this, "cars");
        }else {
            System.out.println("delete failed");
        }
    }

    @Command
    public void delete(@BindingParam("id") int id) {
        System.out.println("deleteCar: " + id);
        if (carService.deleteCars(Arrays.asList(id))) {
            page = carService.search(carSearchDTO);
            BindUtils.postNotifyChange(null, null, this, "cars");
        } else {
            System.out.println("deleteCar: error");
        }
    }

    @Command
    public void search() {
        System.out.println("search");
    }


    @Command
    public void onCheckAll(@BindingParam("item") CarDTO item) {
        if (Objects.isNull(item)) {
            checkAll = !checkAll;
            BindUtils.postNotifyChange(null, null, this, "checkAll");

            getCars().forEach(car -> {
                car.setChecked(checkAll);
                BindUtils.postNotifyChange(null, null, car, "checked");
            });
        } else {
            item.setChecked(!item.isChecked());
            BindUtils.postNotifyChange(null, null, item, "checked");
        }

        System.out.println(getCars().stream().map(CarDTO::isChecked).collect(Collectors.toList()));
    }


    @Init
    public void init() {
        carSearchDTO = new CarSearchDTO();
        page = carService.search(carSearchDTO);

    }

    public List<CarDTO> getCars() {
        return page.getContent();
    }

}
