package definitions;

import cucumber.api.java.en.And;

public class homeworkDefs {
    @And("I print if {string} is positive")
    public void iPrintIfIsPositive(String arg0) {
        int i=Integer.parseInt(arg0);

        if (i<0){
            System.out.println("negative");
        }
        else if(i>0){
            System.out.println("positive");
        }
        else {
            System.out.println("zerro");
        }

    }

    @And("I print {string} th day of week")
    public void iPrintThDayOfWeek(String arg0) {
        int i=Integer.parseInt(arg0)-1;
        String[]days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println(days[i]);
    }
}
