package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import com.example.zktraining.service.CarService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.*;

@VariableResolver(DelegatingVariableResolver.class)
public class HomeController {

    @WireVariable
    CarService carService;
    @Getter
    @Setter
    private List<CarDTO> listCarView;

    @Getter
    @Setter
    private Integer activeSize;
    @Getter
    @Setter
    private Integer activePage;
    @Getter
    @Setter
    private Integer totalSize;
    @Getter
    @Setter
    private boolean checkAll;

    private boolean checkAllHis = false;
    private Set<Integer> listNeChecked;
    private Set<Integer> listNeNoChecked;

    @Init
    public void init() {
        listCarView = carService.getListCar();
    }


    @Command
    public  void addCar(@BindingParam("item") CarDTO carDTO){
        Map<String, CarDTO> dataMap = new HashMap<>();
        dataMap.put("carDTO", carDTO);
        Window window = (Window) Executions.createComponents("form.zul", null, dataMap);
        window.doModal();
    }

//    @Command
//    public void updateCar(@BindingParam("item") CarDTO carDTO){
//        Map<String, CarDTO > dataMap = new HashMap<>();
//        dataMap.put("carDTO", carDTO);
//         Executions.createComponents("form.zul", null, dataMap);
//    }
//
//    @Command
//    public void deleteCar(@BindingParam("id") Integer id){
//
//                    carService.deleteCar(id);
//                    listCarView = carService.getListCar();
//                BindUtils.postNotifyChange(null, null, this, "listCarView");
//            }
//
//
//    private void initDataView() {
//        listNeView = new ArrayList<>(listNe.subList(activePage, activeSize));
//    }
//
//
//    @Command
//    public void onCheckTable(@BindingParam("item") NeDTO item) {
//        if (Objects.isNull(item)) {
//            checkAll = !checkAll;
//            BindUtils.postNotifyChange(null, null, this, "checkAll");
//            checkAllHis = isCheckAll();
//            listNeNoChecked = new HashSet<>();
//            listNeChecked = new HashSet<>();
//            listNeView.forEach(ne -> {
//                ne.setChecked(checkAll);
//                BindUtils.postNotifyChange(null, null, ne, "checked");
//            });
//        } else {
//            item.setChecked(!item.isChecked());
//            if (checkAllHis) {
//                if (item.isChecked()) {
//                    listNeNoChecked.remove(item.getId());
//                } else {
//                    listNeNoChecked.add(item.getId());
//                }
//                checkAll = listNeChecked.size() == totalSize;
//                BindUtils.postNotifyChange(null, null, this, "checkAll");
//
//            } else {
//                if (item.isChecked()) {
//                    listNeChecked.add(item.getId());
//                } else {
//                    listNeChecked.remove(item.getId());
//                }
//                checkAll = listNeChecked.size() == totalSize;
//                BindUtils.postNotifyChange(null, null, this, "checkAll");
//            }
//        }
//
//
//    }
}


