package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Monitor extends Thread {
    private Thread thread;
    private Timer timer;

    public Monitor(Thread thread) {
        this.thread = thread;
        this.timer = new Timer();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Thread - " + thread.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel info = new JLabel();
        frame.getContentPane().add(info);

        frame.setPreferredSize(new Dimension(300,300));
        frame.pack();
        frame.setVisible(true);

        timer.schedule(new UpdateFrame(frame,thread,info),0,1);
    }

    class UpdateFrame extends TimerTask {
        private JFrame frame;
        private Thread thread;
        private JLabel label;

        public UpdateFrame(JFrame frame, Thread thread, JLabel label) {
            this.frame = frame;
            this.thread = thread;
            this.label = label;
        }

        @Override
        public void run() {
            StringBuilder builder = new StringBuilder();
            builder.append("<html>Thread state: " + thread.getState() + "<br/>");
            builder.append("\nThread priority: " + thread.getPriority() + "<br/>");
            builder.append("\nIs alive: " + thread.isAlive() + "<html>");

            this.label.setText(builder.toString());
        }
    }

}
