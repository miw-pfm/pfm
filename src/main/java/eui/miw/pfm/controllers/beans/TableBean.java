/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.models.LazyCarDataModel;
import eui.miw.pfm.models.entities.Car;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;


import javax.servlet.ServletContext;

import org.primefaces.model.LazyDataModel;

@ManagedBean
public class TableBean {

    private LazyDataModel<Car> lazyModel;

    private Car selectedCar;

    private List<Car> cars=new ArrayList<Car>();
    private static String[] colors;
    private static String[] manufacturers;
    private static String[] models;

    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";

        manufacturers = new String[10];
        manufacturers[0] = "Mercedes";
        manufacturers[1] = "BMW";
        manufacturers[2] = "Volvo";
        manufacturers[3] = "Audi";
        manufacturers[4] = "Renault";
        manufacturers[5] = "Opel";
        manufacturers[6] = "Volkswagen";
        manufacturers[7] = "Chrysler";
        manufacturers[8] = "Ferrari";
        manufacturers[9] = "Ford";

        models = new String[10];
        models[0] = "Sail";
        models[1] = "Cruze";
        models[2] = "Patriot";
        models[3] = "Gol";
        models[4] = "Vivant";
        models[5] = "Bora";
        models[6] = "Yaris";
        models[7] = "Cherokee";
        models[8] = "McLauren";
        models[9] = "A3";
    }

    public TableBean() {
        populateRandomCars(cars, 50);
        lazyModel = new LazyCarDataModel(cars);
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public LazyDataModel<Car> getLazyModel() {
        return lazyModel;
    }

    private void populateRandomCars(List<Car> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));
            //list.add(new Car("Modelo"+i, 2000+i, "Manufactura"+i*2, "Color"+i));
        }
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    private String getRandomManufacturer() {
        return manufacturers[(int) (Math.random() * 10)];
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomModel() {
        return models[(int) (Math.random() * 10)];
    }
}
