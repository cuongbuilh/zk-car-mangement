package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.dto.client.CarSearchDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
@Slf4j
@RequiredArgsConstructor
public class CarController {
    @WireVariable
    private CarService carService;

    Page<CarDTO> page;

    @Getter
    @Setter
    CarSearchDTO carSearchDTO;

    @Getter
    @Setter
    private int pageSize;

    @Getter
    @Setter
    CarDTO car;

    @Getter
    @Setter
    boolean checkAll = false;

    @Getter
    @Setter
    List<Integer> sizeList = new ArrayList<>();

    @Command
    public void create() {
        System.out.println("createCar");
    }

    @Command
    public void edit(@BindingParam("car") CarDTO car) {
        Map<String, Object> args = new HashMap<>();
        args.put("car", car);
        args.put("title", "update");

        log.info("args in parent: {}", args);
        Window window = (Window) Executions.createComponents(
                "/car-form.zul", null, args);
        window.doModal();
    }

    @Command
    public void deleteAll() {
        List<Integer> deletedIds = getCars().stream()
                .filter(CarDTO::isChecked)
                .map(CarDTO::getId)
                .collect(Collectors.toList());

        Messagebox.show("Do you want to delete ids: " + deletedIds, "", Messagebox.OK | Messagebox.NO, Messagebox.EXCLAMATION, new EventListener<Event>() {
            @Override
            public void onEvent(final Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    if (carService.deleteCars(deletedIds)) {
                        page = carService.search(carSearchDTO);
                        BindUtils.postNotifyChange(null, null, this, "cars");
                    } else {
                        log.error("delete faild");
                    }
                }
            }
        });


    }

    @Command
    public void delete(@BindingParam("id") int id) {
        Messagebox.show("Do you want to delete id: " + id, "", Messagebox.OK | Messagebox.NO, Messagebox.EXCLAMATION, new EventListener<Event>() {
            @Override
            public void onEvent(final Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    if (carService.deleteCars(Arrays.asList(id))) {
                        page = carService.search(carSearchDTO);
                        BindUtils.postNotifyChange(null, null, this, "cars");
                    } else {
                        System.out.println("deleteCar: error");
                    }
                }
            }
        });
    }

    @Command
    public void search() {
        System.out.println("search");
    }


    @Command
    public void onCheckAll(@BindingParam("item") CarDTO item) {
        if (Objects.isNull(item)) {
            checkAll = !checkAll;


            getCars().forEach(car -> {
                car.setChecked(checkAll);
                BindUtils.postNotifyChange(null, null, car, "checked");
            });
        } else {
            item.setChecked(!item.isChecked());
            BindUtils.postNotifyChange(item, "checked");
            log.info("check all: {}", getCars().stream().allMatch(CarDTO::isChecked));
            checkAll = getCars().stream().allMatch(CarDTO::isChecked);
            log.info("check all-2: {}", checkAll);
        }
        BindUtils.postNotifyChange(null, null, this, "checkAll");
    }


    @Init
    public void init() {
        carSearchDTO = new CarSearchDTO();
        page = carService.search(carSearchDTO);
        pageSize = 1;

        sizeList.add(1);
        sizeList.add(2);
        sizeList.add(4);
        sizeList.add(5);
        sizeList.add(10);
    }

    public List<CarDTO> getCars() {
        return page.getContent();
    }

    @Command
    public void add() {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window) Executions.createComponents(
                "/car-form.zul", null, null);
        window.doModal();


    }


}
