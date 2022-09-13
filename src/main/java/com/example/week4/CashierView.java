package com.example.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index2")
public class CashierView extends VerticalLayout {
    private Button calcu;
    private TextField thon, bt1000, bt500, bt100, bt20, bt10, b5, b1;
    public CashierView(){
        TextField thon = new TextField();
        thon.setLabel("เงินทอน");
        thon.setPlaceholder("$");
        Button calcu = new Button("คำนวณเงินทอน");
        TextField bt1000 = new TextField();
        bt1000.setPrefixComponent(new Span("$1000 : "));
        TextField bt500 = new TextField();
        bt500.setPrefixComponent(new Span("$500 : "));
        TextField bt100 = new TextField();
        bt100.setPrefixComponent(new Span("$100 : "));
        TextField bt20 = new TextField();
        bt20.setPrefixComponent(new Span("$20 : "));
        TextField bt10 = new TextField();
        bt10.setPrefixComponent(new Span("$10 : "));
        TextField bt5 = new TextField();
        bt5.setPrefixComponent(new Span("$5 : "));
        TextField bt1 = new TextField();
        bt1.setPrefixComponent(new Span("$1 : "));
        this.add(thon,calcu,bt1000,bt500,bt100,bt20,bt10,bt5,bt1);
        calcu.addClickListener(event -> {
            double n1 = Double.parseDouble(thon.getValue());
            Change out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/"+n1)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            bt1000.setValue(out.getB1000()+"");
            bt500.setValue(out.getB500()+"");
            bt100.setValue(out.getB100()+"");
            bt20.setValue(out.getB20()+"");
            bt10.setValue(out.getB10()+"");
            bt5.setValue(out.getB5()+"");
            bt1.setValue(out.getB1()+"");
        });
    }
}