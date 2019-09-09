package com.example.a327lab1;

import android.content.Context;

import com.example.a327lab1.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserJSONProcessor {
    private static final String USER_FILE_NAME = "users.json";

    private Context context;
    private ArrayList<User> listOfUsers;

    public UserJSONProcessor(Context context) {
        this.context = context;
        this.listOfUsers = deserializeUsersFromJSON();
    }

    private ArrayList<User> deserializeUsersFromJSON() {
        if (fileExists(USER_FILE_NAME)) {

            //Read from user JSON
            StringBuilder userSB = new StringBuilder();
            try {
                File file = new File(context.getFilesDir(), USER_FILE_NAME);
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line;
                while ((line = br.readLine()) != null) {
                    userSB.append(line);
                    userSB.append("\n");
                }
                br.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            //Convert to StringBuilder to ArrayList<User>
            Gson gson = new Gson();
            Type userType = new TypeToken<ArrayList<User>>(){}.getType();
            listOfUsers = gson.fromJson(userSB.toString(), userType);
        } else {
            listOfUsers = new ArrayList<User>();
        }
        return listOfUsers;
    }

    private void writeUsersToJSON() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(listOfUsers);

        try {

            FileOutputStream fileOut = context.openFileOutput(USER_FILE_NAME, context.MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            outputWriter.write(json);
            outputWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean fileExists(String filename) {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public boolean hasUserName(String name) {
        for (User myUser : listOfUsers) {
            if (myUser.getNameOnly().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for listOfUsers
     * @return  Array of users
     */
    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    /**
     * Setter method for listOfUsers
     * @param listOfUsers   Array of users
     */
    public void setListOfUsers(ArrayList<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    /**
     * Adds user to listOfUsers and writes to userJSON file
     * @param user  User being added to our system
     */
    public void addUser(User user) {
        this.listOfUsers.add(user);
        writeUsersToJSON();
    }

    /**
     * Gets user object based on the username
     * @param name  Username
     */
    public User getUser(String name) {
        for (User myUser : listOfUsers) {
            if (myUser.getNameOnly().equals(name)) {
                return myUser;
            }
        }
        return new User(context);
    }
}
