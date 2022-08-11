package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class CarController {
    @Getter
    @Setter
    List<Integer> selectedIds = new ArrayList<>();

    @Getter
    @Setter
    CarDTO car;

    @Command
    public void create() {
        System.out.println("createCar");
    }

    @Command
    public void update() {
        System.out.println("updateCar");
    }

    @Command
    public void delete() {
        System.out.println("deleteCars");
    }

    @Command
    public void search() {
        System.out.println("search");
    }

    @Command
    public void handleSelect(int id) {
        System.out.println("select id: " + id);
    }
}
