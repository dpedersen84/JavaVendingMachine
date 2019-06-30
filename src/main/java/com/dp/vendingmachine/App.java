package com.dp.vendingmachine;

import com.dp.vendingmachine.controller.VendingMachineController;
import com.dp.vendingmachine.dao.ItemDao;
import com.dp.vendingmachine.dao.ItemDaoFileImpl;
import com.dp.vendingmachine.service.VendingMachineServiceLayer;
import com.dp.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.dp.vendingmachine.ui.UserIO;
import com.dp.vendingmachine.ui.UserIOConsoleImpl;
import com.dp.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static final String ITEM_FILE = "items.txt";

    public static void main(String[] args) {

        // instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
        // instantiate the View and wire the UserIO implementation to it
//        VendingMachineView myView = new VendingMachineView(myIo);
        // instantiate the DAO
//        ItemDao myDao = new ItemDaoFileImpl(ITEM_FILE);
        // instantiate the service layer and wire the DAO
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
        // instantiate the controller and wire the service layer into it
//        VendingMachineController controller = 
//            new VendingMachineController(myView, myService);
        // kick of the controller
//        controller.run();
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
                ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
