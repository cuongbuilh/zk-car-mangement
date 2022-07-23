package com.example.zktraining.controller;

import com.example.zktraining.dto.DataResponseDTO;
import com.example.zktraining.dto.NeDTO;
import com.example.zktraining.entity.Ne;
import com.example.zktraining.service.NeService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.event.PagingEvent;

import java.util.*;

@VariableResolver(DelegatingVariableResolver.class)
public class HomeController {

    @WireVariable
    NeService neService;
    private static List<NeDTO> listNe;
    @Getter
    @Setter
    private List<NeDTO> listNeView;

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
        initObject();
        DataResponseDTO dataResponseDTO = neService.getListNe(activePage, activeSize);
        listNeView = dataResponseDTO.getListNe();
        totalSize = dataResponseDTO.getTotalNe();
        //initObject();
        //initDataView();
    }

    private void initObject() {
        activeSize = 5;
        activePage = 0;
        listNeChecked = new HashSet<>();
        listNeNoChecked = new HashSet<>();
    }

    private void initDataView() {
        listNeView = new ArrayList<>(listNe.subList(activePage, activeSize));
    }

    @Command
    @NotifyChange({"listNeView", "activeSize", "activePage"})
    public void unitPaging(@BindingParam("event") PagingEvent event) {
        this.activeSize = event.getPageable().getPageSize();
        this.activePage = event.getActivePage();
//        int min = activePage * activeSize;
//        int max = min + activeSize;
//        if (max > totalSize) {
//            max = totalSize;
//        }
//        listNeView = new ArrayList<>(listNe.subList(min, max));
        DataResponseDTO dataResponseDTO = neService.getListNe(activePage, activeSize);
        listNeView =  dataResponseDTO.getListNe();
        totalSize = dataResponseDTO.getTotalNe();
        listNeView.forEach(item -> {
            if (checkAllHis){
                if (listNeNoChecked.contains(item.getId())){
                    item.setChecked(false);
                }else {
                    item.setChecked(checkAllHis);
                }
            }else if (listNeChecked.contains(item.getId())){
                item.setChecked(true);
            }

        });
    }

    @Command
    public void onCheckTable(@BindingParam("item") NeDTO item) {
        if (Objects.isNull(item)) {
            checkAll = !checkAll;
            BindUtils.postNotifyChange(null, null, this, "checkAll");
            checkAllHis = isCheckAll();
            listNeNoChecked = new HashSet<>();
            listNeChecked = new HashSet<>();
            listNeView.forEach(ne -> {
                ne.setChecked(checkAll);
                BindUtils.postNotifyChange(null, null, ne, "checked");
            });
        } else {
            item.setChecked(!item.isChecked());
            if (checkAllHis) {
                if (item.isChecked()) {
                    listNeNoChecked.remove(item.getId());
                } else {
                    listNeNoChecked.add(item.getId());
                }
                checkAll = listNeChecked.size() == totalSize;
                BindUtils.postNotifyChange(null, null, this, "checkAll");

            } else {
                if (item.isChecked()) {
                    listNeChecked.add(item.getId());
                } else {
                    listNeChecked.remove(item.getId());
                }
                checkAll = listNeChecked.size() == totalSize;
                BindUtils.postNotifyChange(null, null, this, "checkAll");
            }
        }


    }
}


