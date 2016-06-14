package com.grishberg.viper_rest_android.domain.models;

/**
 * Created by grishberg on 14.06.16.
 */
public class RegistrationContainer {
    private static final String TAG = RegistrationContainer.class.getSimpleName();
    private String login;
    private String password;
    private String name;
    private int sex;
    private int age;

    public RegistrationContainer(String login, String password, String name, int sex, int age) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "RegistrationContainer{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
