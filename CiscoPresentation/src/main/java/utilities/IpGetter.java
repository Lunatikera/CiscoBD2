/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author carli
 */
public class IpGetter {
   public static String getLocalIPAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress(); // Returns the IP address as a string
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unable to determine IP address.";
        }
    } 
}
