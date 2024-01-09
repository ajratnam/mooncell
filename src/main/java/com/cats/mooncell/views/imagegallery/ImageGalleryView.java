package com.cats.mooncell.views.imagegallery;

import com.cats.mooncell.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@PageTitle("Deals of the day")
@Route(value = "image-gallery", layout = MainLayout.class)
@AnonymousAllowed
public class    ImageGalleryView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public ImageGalleryView() {
        constructUI();

        imageContainer.add(new ImageGalleryViewCard("Power Drill","A versatile power drill with multiple speed settings and various drill bits",
                "https://th.bing.com/th/id/OIP.i_VIiP82mbkS6hTmpTX1ggHaHa?rs=1&pid=ImgDetMain","Tools"));
        imageContainer.add(new ImageGalleryViewCard("Circular Saw","Heavy-duty circular saw for precision cutting in woodworking projects",
                "https://th.bing.com/th/id/OIP.pjsJRfgRbbqEv0HWlfSHHQHaE8?rs=1&pid=ImgDetMain","Tools"));
        imageContainer.add(new ImageGalleryViewCard("Toolbox Set","Complete set of essential hand tools in a durable and portable toolbox.","https://th.bing.com/th/id/OIP.cpVVY8Izqwq9NpOqYcUedQHaEj?rs=1&pid=ImgDetMain","Tools"));
        imageContainer.add(new ImageGalleryViewCard("Electric Screwdriver Kit","Compact electric screwdriver with interchangeable bits for quick and easy screwing.",
                "https://www.pproreviews.com/wp-content/uploads/2021/01/Cordless-Screwdriver.jpg","Tools"));
        imageContainer.add(new ImageGalleryViewCard("Hammer","High-quality steel hammer with a comfortable grip for various construction tasks",
                "https://3.imimg.com/data3/YX/VI/MY-3332131/rip-claw-with-smooth-face-hammer-with-large-strikeface-500x500.jpg","Tools"));
        imageContainer.add(new ImageGalleryViewCard("Notebook coolers"," Cooling Pad comes with a Large Sturdy and Robust design that is suitable for Laptops upto 15.6 inches. The Metal Mesh Design provides added Strength and cooling ability to the laptop and silicon pads ensure a stable grip on the surface","https://m.media-amazon.com/images/I/716LIV7bJfL._SX522_.jpg","Laptop Accessories"));
        imageContainer.add(new ImageGalleryViewCard("Laptop Backpack","Anti Theft 23 L Backpack with USB Charging Port 15 Inch Laptop Backpack ","https://m.media-amazon.com/images/I/61y5mUKvMdL._SX679_.jpg","Laptop Accessories"));
        imageContainer.add(new ImageGalleryViewCard("Marshal Speakers","Stanmore II is the most versatile speaker in the Marshall line-up and is perfect for any room, big or small","https://m.media-amazon.com/images/I/71pTGJ3LnDL._SY879_.jpg","Speakers"));
    }

    private void constructUI() {
        addClassNames("image-gallery-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Hot deals of the day 🔥🔥🔥");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Order now!!!");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);



        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);

    }
}
