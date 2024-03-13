package com.fusi24.views.aiquery;

import com.fusi24.data.SamplePerson;
import com.fusi24.services.SamplePersonService;
import com.fusi24.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Ai Query")
@Route(value = "ai-query", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
@Uses(Icon.class)
public class AiQueryView extends Composite<VerticalLayout> {

    public AiQueryView() {
        Paragraph textSmall = new Paragraph();
        HorizontalLayout layoutRow = new HorizontalLayout();
        TextArea textArea = new TextArea();
        Button buttonPrimary = new Button();
        TextArea textArea2 = new TextArea();
        Paragraph textSmall2 = new Paragraph();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Grid basicGrid = new Grid(SamplePerson.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.START);
        textSmall.setText(
                "Basically, you can questioning anything using using this page. AI Query is using Gerative AI, with GPT-3.5-1106 in behind.");
        textSmall.setWidth("100%");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        textArea.setLabel("What do you want to ask?");
        layoutRow.setAlignSelf(FlexComponent.Alignment.END, textArea);
        textArea.setWidth("100%");
        buttonPrimary.setText("Submit");
        layoutRow.setAlignSelf(FlexComponent.Alignment.END, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.setHeight("80px");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        textArea2.setLabel("Assistant");
        textArea2.setWidth("100%");
        textSmall2.setText("Generated table from SQL and Seaborn Image (if any)");
        textSmall2.setWidth("100%");
        textSmall2.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        basicGrid.setWidth("100%");
        basicGrid.setHeight("100%");
        setGridSampleData(basicGrid);
        getContent().add(textSmall);
        getContent().add(layoutRow);
        layoutRow.add(textArea);
        layoutRow.add(buttonPrimary);
        getContent().add(textArea2);
        getContent().add(textSmall2);
        getContent().add(layoutRow2);
        layoutRow2.add(basicGrid);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
