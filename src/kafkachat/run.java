/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafkachat;

import java.util.Scanner;

/**
 *
 * @author pranjal
 */
public class run {
    
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        System.out.println("please select one");
        System.out.println("1.user1\n2.user2");
        int choice = in.nextInt();
        switch(choice){
            case 1:
                MyProducer.main(args);
                break;
            case 2:
                MyConsumer.main(args);
                break;
            default:
                System.out.println("please specify one");
        }
    }
    
}
