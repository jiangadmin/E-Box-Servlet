package com.jiang.e_box_servlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by JiangAdmin
 * on 17/5/6.
 * Email: www.fangmu@qq.com
 * Phone：186 6120 1018
 * Purpose: 萌豆网广告机服务端
 */
public class Main {
    static boolean img1 = false;
    static boolean img2 = false;

    static JButton message;
    static JButton chooseimge1;
    static JButton chooseimge2;
    static JButton submit;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setTitle("萌豆网广告机 服务端");
        jFrame.setSize(320, 480);
        jFrame.setVisible(true);
        jFrame.setResizable(false);//禁止拖动
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);//显示屏幕中央

        message = new JButton();
        message.setBounds(0, 0, 320, 40);
        jFrame.add(message);

        chooseimge1 = new JButton();
        chooseimge1.setBounds(0, 40, 320, 40);
        jFrame.add(chooseimge1, BorderLayout.LINE_END);

        chooseimge2 = new JButton();
        chooseimge2.setBounds(0, 80, 320, 40);
        jFrame.add(chooseimge2, BorderLayout.LINE_END);

        submit = new JButton();
        submit.setBounds(0, 120, 320, 40);
        jFrame.add(submit, BorderLayout.LINE_END);

        JButton ip = new JButton("本机 IP：" + getLocalIPForJava());
        ip.setEnabled(false);
        ip.setBounds(0, 380, 320, 40);
        jFrame.add(ip, BorderLayout.LINE_END);

        JButton right = new JButton("制作人：JiangAdmin");
        right.setEnabled(false);
        right.setBounds(0, 420, 320, 40);
        jFrame.add(right, BorderLayout.LINE_END);

        message.setAction(new AbstractAction("需要本机具有IIS,图片保存在images文件夹下") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        chooseimge1.setAction(new AbstractAction("点击选择 图片 用于:前台-横屏") {
            @Override
            public void actionPerformed(ActionEvent e) {
                img1 = GetFileName(chooseimge1);

            }
        });

        chooseimge2.setAction(new AbstractAction("点击选择 图片 用于:走廊-纵屏") {
            @Override
            public void actionPerformed(ActionEvent e) {
                img2 = GetFileName(chooseimge2);
            }

        });

        submit.setAction(new AbstractAction("确定") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    /**
     * 选择文件
     *
     * @param jButton
     */
    public static boolean GetFileName(JButton jButton) {
        JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(null);
        if (i == jFileChooser.APPROVE_OPTION) { //打开文件
            String path = jFileChooser.getSelectedFile().getAbsolutePath();
            String name = jFileChooser.getSelectedFile().getName();
            jButton.setText("已选择:" + name);
            System.out.println("当前文件路径：" + path + ";\n当前文件名：" + name);
            return true;
        } else {
            if (jButton ==chooseimge1){
                jButton.setText("点击选择 图片 用于:前台-横屏");
            }else {
                jButton.setText("点击选择 图片 用于:走廊-纵屏");
            }
            return false;
        }

    }

    /**
     * 获取局域网IP
     *
     * @return
     */
    public static String getLocalIPForJava() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        sb.append(inetAddress.getHostAddress().toString() + "\n");
                    }
                }
            }
        } catch (SocketException e) {
        }
        return sb.toString();
    }


}
