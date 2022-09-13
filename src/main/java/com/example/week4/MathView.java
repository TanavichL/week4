package com.example.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index1")
public class MathView extends FormLayout {
    private Button plus,minus,multi,divis,btnmod,btnmax;
    private TextField n1, n2,ans;
    public MathView(){
        n1 = new TextField();
        n2 = new TextField();
        n1.setLabel("Number 1");
        n1.setPlaceholder("Please Input");
        n2.setLabel("Number 2");
        n2.setPlaceholder("Please Input");
        ans = new TextField();
        ans.setLabel("Answer");
        plus = new Button("+");
        minus = new Button("-");
        multi = new Button("*");
        divis = new Button("/");
        btnmod = new Button("Mod");
        btnmax = new Button("Max");
        VerticalLayout v1 = new VerticalLayout();
        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(plus,minus,multi,divis,btnmod,btnmax);
        v1.add(n1,n2,h1,ans);
        this.add(v1);
        plus.addClickListener(event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        minus.addClickListener(event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        multi.addClickListener(event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        divis.addClickListener(event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        btnmod.addClickListener(event ->{
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
        btnmax.addClickListener(event ->{
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", n1.getValue());
            formData.add("n2", n2.getValue());
            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/max")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ans.setValue(out);
        });
    }
}

