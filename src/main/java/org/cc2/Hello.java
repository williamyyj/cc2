/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cc2;

/**
 *
 * @author William
 */
public class Hello {

    public static void main(String[] args) {
        //Runnable r1 = () -> System.out.println(getClass());
        Runnable r2 = () -> System.out.println("Hello world two!");

        Runnable r3 = new Runnable() {
            public void run() {
                System.out.println(getClass());
            }
        };
        r2.run();
        r3.run();
    }
}
