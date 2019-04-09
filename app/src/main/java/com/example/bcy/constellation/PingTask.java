package com.example.bcy.constellation;

import java.io.IOException;

public class PingTask extends Thread {

    private boolean success;
    private String host;

    public PingTask(String host) {
        this.host = host;
    }

    @Override
    public void run() {
        super.run();
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("ping -n 1 -W 100" + host);
            proc.waitFor();
            int exitCode = proc.exitValue();
            if (exitCode == 0) {
                success = true;
            } else {
                success = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }
}