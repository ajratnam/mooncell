package com.cats.mooncell.views.makeorders;

import com.cats.mooncell.data.*;
import com.cats.mooncell.security.AuthenticatedUser;
import com.cats.mooncell.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("Make Orders")
@Route(value = "make-order", layout = MainLayout.class)
@RolesAllowed("USER")
@Uses(Icon.class)
public class MakeOrdersView extends Composite<VerticalLayout> {

    Binder<Order> binder = new Binder<>(Order.class);
    Binder<CurrentOrder> currentBinder = new Binder<>(CurrentOrder.class);
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CurrentOrderRepository currentOrderRepository;
    private final AuthenticatedUser authenticatedUser;

    public MakeOrdersView(ItemRepository itemRepository, OrderRepository orderRepository, CurrentOrderRepository currentOrderRepository, AuthenticatedUser authenticatedUser) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.currentOrderRepository = currentOrderRepository;
        this.authenticatedUser = authenticatedUser;
        setComboBoxSampleData();
    }

    private void setComboBoxSampleData() {
        H1 h1 = new H1();
        HorizontalLayout layoutRow = new HorizontalLayout();
        ComboBox<String> comboBox = new ComboBox();
        NumberField numberField = new NumberField();
        DatePicker datePicker = new DatePicker();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        ProgressBar progressBar = new ProgressBar();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        Grid basicGrid = new Grid(CurrentOrder.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Place your Order :)");
        h1.setWidth("max-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("250px");
        layoutRow.setAlignItems(Alignment.START);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        comboBox.setLabel("Select Items");
        layoutRow.setAlignSelf(Alignment.CENTER, comboBox);
        comboBox.setWidth("400px");
//        setComboBoxSampleData(comboBox);
        numberField.setLabel("Quantity");
        layoutRow.setAlignSelf(Alignment.CENTER, numberField);
        numberField.setWidth("min-content");
        datePicker.setLabel("And you need it by?");
        layoutRow.setAlignSelf(Alignment.CENTER, datePicker);
        datePicker.setWidth("min-content");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        progressBar.setValue(0.33);
        buttonPrimary.setText("Place Order");
        buttonPrimary.addClickListener(event -> {
            Order order = new Order();
            CurrentOrder currentOrder = new CurrentOrder();
            try {
                binder.writeBean(order);
                order.setCost(order.getUnits() * itemRepository.findByName(order.getItemName()).getSellPrice());
                order.setCustomerName(authenticatedUser.getUser().getName());
                order.setWarehouseCode(itemRepository.findByName(order.getItemName()).getWarehouseCode());
//                orderRepository.save(order);

                currentBinder.writeBean(currentOrder);
                currentOrder.setCost(currentOrder.getUnits() * itemRepository.findByName(currentOrder.getItemName()).getSellPrice());
                currentOrder.setCustomerName(authenticatedUser.getUser().getName());
                currentOrder.setWarehouseCode(itemRepository.findByName(currentOrder.getItemName()).getWarehouseCode());
                currentOrderRepository.save(currentOrder);

                List<CurrentOrder> orders = currentOrderRepository.findByUserName(authenticatedUser.getUser().getName());
                basicGrid.setItems(orders);
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });
        layoutColumn2.setAlignSelf(Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Onto Payment");
        buttonSecondary.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("confirm-order"));
        });
        layoutColumn2.setAlignSelf(Alignment.CENTER, buttonSecondary);
        buttonSecondary.setWidth("min-content");
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
//        setGridSampleData(basicGrid);
        getContent().add(h1);
        getContent().add(layoutRow);
        layoutRow.add(comboBox);
        layoutRow.add(numberField);
        layoutRow.add(datePicker);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(progressBar);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(buttonSecondary);
        getContent().add(basicGrid);

        List<String> items = itemRepository.findAllByNameIsNotNull();
        comboBox.setItems(items);
        comboBox.setItemLabelGenerator(Object::toString);

        binder.forField(comboBox).bind(Order::getItemName, Order::setItemName);
        binder.forField( numberField).bind(Order::getUnits ,Order::setUnits);
        binder.forField(datePicker).bind(Order::getDate, Order::setDate);

        currentBinder.forField(comboBox).bind(CurrentOrder::getItemName, CurrentOrder::setItemName);
        currentBinder.forField( numberField).bind(CurrentOrder::getUnits ,CurrentOrder::setUnits);
        currentBinder.forField(datePicker).bind(CurrentOrder::getDate, CurrentOrder::setDate);

        List<CurrentOrder> orders = currentOrderRepository.findByUserName(authenticatedUser.getUser().getName());
        basicGrid.setItems(orders);
    }


    record SampleItem(String value, String label, Boolean disabled) {
    }


//    @Autowired()
//    private ItemService samplePersonService;
}