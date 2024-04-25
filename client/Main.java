import Classes.Context;
import Classes.FlatUpdateRecord;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Context context;
        try {
            context = new Context();
        } catch (RuntimeException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return;
        }
        while (true){
            context.getInteractor().masterProcessInput(context.getInteractor().getScanner().nextLine());
        }
    }
}