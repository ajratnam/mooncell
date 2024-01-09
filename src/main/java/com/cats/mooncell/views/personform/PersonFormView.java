package com.cats.mooncell.views.personform;

import com.cats.mooncell.data.User;
import com.cats.mooncell.data.UserRepository;
import com.cats.mooncell.data.UserRole;
import com.cats.mooncell.security.AuthenticatedUser;
import com.cats.mooncell.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import com.cats.mooncell.data.UserRoleRepository;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


@PageTitle("Registration Form")
@Route(value = "profile", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class PersonFormView extends Composite<VerticalLayout> {
    private static final Set<String> states = new LinkedHashSet<>();
    private static final Set<String> countries = new LinkedHashSet<>();

    static {
        states.addAll(Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"));

        countries.addAll(Arrays.asList("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Federated States of Micronesia", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "United States Virgin Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City State", "Venezuela", "Vietnam", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zaire", "Zambia", "Zimbabwe"));
    }

    Binder<User> binder = new Binder<>(User.class);


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    private final AuthenticatedUser authenticatedUser;
    public PersonFormView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();

        HorizontalLayout layoutRow = new HorizontalLayout();
        Button save = new Button();
        Button reset = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.BETWEEN);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("1000px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.getStyle().set("padding-right", "50px");
//        h3.setText("Personal Information");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        save.setText("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reset.setText("Reset");
        reset.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        layoutRow.add(save, reset);

        var personalDetails = createPersonalDetailsSection();
        var personAddressSection = createPersonAddressSection();

        // Adjusted layout for two columns
        formLayout2Col.add(personalDetails);
        formLayout2Col.add(personAddressSection);
        layoutColumn2.add(formLayout2Col, layoutRow);

        getContent().add(layoutColumn2);


//      after submitting the user table should get updated with the data of the new user
        save.addClickListener(event -> {
            try {
                User user = authenticatedUser.getUser();
                binder.writeBean(user);
                var role = user.getRole();
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);


                userRepository.save(user);
                userRoleRepository.save(userRole);
                Notification.show("Saved successfully");
            } catch (ValidationException e) {
                Notification.show("Please fill in all fields");
                throw new RuntimeException(e);
            }
        });

        binder.readBean(authenticatedUser.getUser());
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        personalDetails.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.Margin.Bottom.XLARGE, LumoUtility.Margin.Top.MEDIUM);

        H3 header = new H3("Details");
        header.addClassNames(LumoUtility.Margin.Bottom.MEDIUM, LumoUtility.Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE, LumoUtility.AlignContent.START);

        TextField name = new TextField("Name");
        name.setRequiredIndicatorVisible(true);
        name.setPattern("[\\p{L} \\-]+");
        name.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        name.setWidth("100%"); // Set width to 100%

        EmailField email = new EmailField("Email address");
        email.setRequiredIndicatorVisible(true);
        email.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        email.setWidth("100%"); // Set width to 100%

        TextField phone = new TextField("Phone number");
        phone.setRequiredIndicatorVisible(true);
        phone.setPattern("[\\d \\-\\+]+");
        phone.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        phone.setMaxWidth("50%");
        phone.getElement().getThemeList().add("align-center");

        /*Checkbox rememberDetails = new Checkbox("Remember personal details for next time");
        rememberDetails.addClassNames(LumoUtility.Margin.Top.SMALL);*/

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Birthday");
        datePicker.setPlaceholder("Pick a date");
        datePicker.setRequiredIndicatorVisible(true);
        datePicker.setWidth("50%"); // Set width to 100%
        datePicker.getElement().getThemeList().add("align-center");

        // Personal details section organized into two columns
        Div personalDetailsDiv = new Div();
        personalDetailsDiv.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, Gap.MEDIUM);
        personalDetailsDiv.add(name, email, phone);
        personalDetails.add(header, personalDetailsDiv, datePicker);


        binder.forField(name).bind(User::getName, User::setName);
        binder.forField(email).bind(User::getEmail, User::setEmail);
        binder.forField(phone).bind(User::getPhone, User::setPhone);
        binder.forField(datePicker).bind(User::getBirthday, User::setBirthday);

        return personalDetails;
    }

    private Section createPersonAddressSection() {
        Section shippingDetails = new Section();
        shippingDetails.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.Margin.Bottom.XLARGE, LumoUtility.Margin.Top.XLARGE); // Increased top margin

        H3 header = new H3("Address");
        header.addClassNames(LumoUtility.Margin.Bottom.MEDIUM, LumoUtility.Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE, LumoUtility.AlignContent.START);

        // Shipping details section organized into two columns
        Div shippingDetailsDiv = new Div();
        shippingDetailsDiv.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, Gap.MEDIUM);

        ComboBox<String> countrySelect = new ComboBox<>("Country");
        countrySelect.setRequiredIndicatorVisible(true);
        countrySelect.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        countrySelect.setWidth("100%"); // Set width to 100%

        TextArea address = new TextArea("Street address");
        address.setMaxLength(200);
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        address.setWidth("100%"); // Set width to 100%

        Div subSection = new Div();
        subSection.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexWrap.WRAP, Gap.MEDIUM);

        TextField postalCode = new TextField("Postal Code");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("[\\d \\p{L}]*");
        postalCode.addClassNames(LumoUtility.Margin.Bottom.SMALL);
        postalCode.setWidth("100%"); // Set width to 100%

        TextField city = new TextField("City");
        city.setRequiredIndicatorVisible(true);
        city.addClassNames(LumoUtility.Flex.GROW, LumoUtility.Margin.Bottom.SMALL);
        city.setWidth("100%"); // Set width to 100%

        subSection.add(postalCode, city);

        ComboBox<String> stateSelect = new ComboBox<>("State");
        stateSelect.setRequiredIndicatorVisible(true);
        stateSelect.setItems(states);
        stateSelect.setVisible(false);
        countrySelect.setItems(countries);
//        countrySelect.addValueChangeListener(e -> stateSelect.setVisible(countrySelect.getValue().equals("United States")));


        // Shipping details section organized into two columns
        shippingDetailsDiv.add(countrySelect, address, subSection, stateSelect);
        shippingDetails.add(header, shippingDetailsDiv);

        binder.forField(countrySelect).bind(User::getCountry, User::setCountry);
        binder.forField(address).bind(User::getAddress, User::setAddress);
        binder.forField(postalCode).bind(User::getPostalCode, User::setPostalCode);
        binder.forField(city).bind(User::getCity, User::setCity);
        binder.forField(stateSelect).bind(User::getState, User::setState);

        return shippingDetails;
    }



}




