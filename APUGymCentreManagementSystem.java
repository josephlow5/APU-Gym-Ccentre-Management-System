package apu.gym.centre.management.system;

import apu.gym.centre.management.system.Objects.User;
import apu.gym.centre.management.system.Pages.Page1;
import apu.gym.centre.management.system.Pages.Page2;
import apu.gym.centre.management.system.Pages.Page3;
import apu.gym.centre.management.system.Pages.Page4;
import apu.gym.centre.management.system.Pages.Page5;
import apu.gym.centre.management.system.Pages.Page6;
import apu.gym.centre.management.system.Pages.Page7;
import apu.gym.centre.management.system.Pages.Page8;
import apu.gym.centre.management.system.Pages.Page9;
import apu.gym.centre.management.system.Pages.Page10;

/**
 *
 * @author Low Jia Quan TP063436
 */
public class APUGymCentreManagementSystem {
    public static Page1 page1 = new Page1();
    public static Page2 page2 = new Page2();
    public static Page3 page3 = new Page3();
    public static Page4 page4 = new Page4();
    public static Page5 page5 = new Page5();
    public static Page6 page6 = new Page6();
    public static Page7 page7 = new Page7();
    public static Page8 page8 = new Page8();
    public static Page9 page9 = new Page9();
    public static Page10 page10 = new Page10();
    
    public static User login = null;
    public static void main(String[] args) {
        DataIO.read();
    }
}
