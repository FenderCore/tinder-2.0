/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;

/**
 *
 * @author Shannon
 */
public class Profile  implements Serializable
{
    private String name;
    private int age;
    private String sex;
    private int id;

    public Profile(String name, int age, String sex, int id) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
