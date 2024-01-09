package com.cats.mooncell.views.confirmorder;

import com.cats.mooncell.data.SamplePerson;
import com.cats.mooncell.services.SamplePersonService;
import com.cats.mooncell.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Confirm Order")
@Route(value = "confirm-order", layout = MainLayout.class)
@RolesAllowed("USER")
@Uses(Icon.class)

public class ConfirmOrderView extends Composite<VerticalLayout> {

    public ConfirmOrderView() {
        Grid minimalistGrid = new Grid(SamplePerson.class);
        HorizontalLayout layoutRow = new HorizontalLayout();
        ProgressBar progressBar = new ProgressBar();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        Checkbox checkbox = new Checkbox();
        DatePicker datePicker = new DatePicker();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        getContent().setAlignSelf(Alignment.CENTER, minimalistGrid);
        minimalistGrid.setWidth("100%");
        minimalistGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(minimalistGrid);
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        progressBar.setValue(0.66);
        progressBar.setWidth("195px");
        buttonPrimary.setText("Confirm");
        buttonPrimary.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("checkout-form"));
        });
        layoutRow.setAlignSelf(Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Back");
        buttonPrimary2.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("make-order"));
        });
        layoutRow.setAlignSelf(Alignment.CENTER, buttonPrimary2);
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        checkbox.setLabel("Order Every month");
        checkbox.setWidth("200px");
        datePicker.setLabel("Date picker");
        datePicker.setWidth("min-content");
        getContent().add(minimalistGrid);
        getContent().add(layoutRow);
        layoutRow.add(progressBar);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonPrimary2);
        layoutRow.add(checkbox);
        layoutRow.add(datePicker);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
