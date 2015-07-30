package test.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> newUser() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/newUser.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> filesPath() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/filesPath.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> existingUser() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/existingUser.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> existingUserNegative() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/existingUserNegative.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return userData.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> usersSimple() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{"demo30", "1234"});
        data.add(new Object[]{"demo29", "1234"});
        return data.iterator();
    }


    @DataProvider
    public static Iterator<Object[]> folder() {
        List<Object[]> data = new ArrayList<Object[]>();
        for (int i = 0; i < 2; i++) {
            data.add(new Object[]{
                    generateRandomName()
            });
        }
        return data.iterator();
    }

    private Object generateRandomPassword() {
        return "pass" + new Random().nextInt();
    }
    //---------------End--------------------//

    private static Object generateRandomName() {
        return "demo" + new Random().nextInt();
    }
}