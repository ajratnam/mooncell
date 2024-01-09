package com.cats.mooncell.views;

import com.cats.mooncell.data.User;
import com.cats.mooncell.security.AuthenticatedUser;
import com.cats.mooncell.views.about.AboutView;
import com.cats.mooncell.views.chat.ChatView;
import com.cats.mooncell.views.checkoutform.CheckoutFormView;
import com.cats.mooncell.views.confirmorder.ConfirmOrderView;
import com.cats.mooncell.views.creditcardform.CreditCardFormView;
import com.cats.mooncell.views.dashboard.DashboardView;
import com.cats.mooncell.views.gridwithfilters.GridwithFiltersView;
import com.cats.mooncell.views.imagegallery.ImageGalleryView;
import com.cats.mooncell.views.makeorders.MakeOrdersView;
import com.cats.mooncell.views.map.MapView;
import com.cats.mooncell.views.masterdetail.MasterDetailView;
import com.cats.mooncell.views.myview.MyViewView;
import com.cats.mooncell.views.personform.PersonFormView;
import com.cats.mooncell.views.vieworders.ViewOrdersView;
import com.cats.mooncell.views.warehousemaster.WarehouseMaster;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    private final AuthenticatedUser authenticatedUser;
    private final AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Mooncell");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if (accessChecker.hasAccess(AboutView.class)) {
            nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

        }
        if (accessChecker.hasAccess(GridwithFiltersView.class)) {
            nav.addItem(new SideNavItem("Grid with Filters", GridwithFiltersView.class,
                    LineAwesomeIcon.FILTER_SOLID.create()));

        }
        if (accessChecker.hasAccess(WarehouseMaster.class)) {
            nav.addItem(new SideNavItem("Warehouse Master", WarehouseMaster.class,
                    LineAwesomeIcon.FILTER_SOLID.create()));

        }
        if (accessChecker.hasAccess(CheckoutFormView.class)) {
            nav.addItem(new SideNavItem("Checkout Form", CheckoutFormView.class, LineAwesomeIcon.CREDIT_CARD.create()));

        }
        if (accessChecker.hasAccess(DashboardView.class)) {
            nav.addItem(new SideNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.CHART_AREA_SOLID.create()));
        }
        if (accessChecker.hasAccess(ImageGalleryView.class)) {
            nav.addItem(
                    new SideNavItem("Image Gallery", ImageGalleryView.class, LineAwesomeIcon.TH_LIST_SOLID.create()));

        }
        if (accessChecker.hasAccess(ChatView.class)) {
            nav.addItem(new SideNavItem("Chat", ChatView.class, LineAwesomeIcon.COMMENTS.create()));

        }
        if (accessChecker.hasAccess(CreditCardFormView.class)) {
            nav.addItem(new SideNavItem("Credit Card Form", CreditCardFormView.class,
                    LineAwesomeIcon.CREDIT_CARD.create()));
        }
        if (accessChecker.hasAccess(MakeOrdersView.class)) {
            nav.addItem(
                    new SideNavItem("Make Orders", MakeOrdersView.class, LineAwesomeIcon.SHOPPING_CART_SOLID.create()));

        }
        if (accessChecker.hasAccess(ViewOrdersView.class)) {
            nav.addItem(
                    new SideNavItem("View Orders", ViewOrdersView.class, LineAwesomeIcon.PIZZA_SLICE_SOLID.create()));

        }
        if (accessChecker.hasAccess(MapView.class)) {
            nav.addItem(new SideNavItem("Map", MapView.class, LineAwesomeIcon.MAP.create()));
        }
        if (accessChecker.hasAccess(ConfirmOrderView.class)) {
            nav.addItem(new SideNavItem("Confirm Order", ConfirmOrderView.class,
                    LineAwesomeIcon.MONEY_BILL_WAVE_SOLID.create()));

        }
        if (accessChecker.hasAccess(MasterDetailView.class)) {
            nav.addItem(
                    new SideNavItem("Master-Detail", MasterDetailView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));

        }
        if (accessChecker.hasAccess(MyViewView.class)) {
            nav.addItem(new SideNavItem("My View", MyViewView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));

        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });
            userName.getSubMenu().addItem("Edit Profile", e -> {
                getUI().ifPresent(ui -> ui.navigate("profile"));
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
