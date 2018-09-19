package com.husan.execute.buffer;

import text.UUID;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @Date 2018/9/3
 */
public class DelayExecuteBufferTest {

    public static void main(String[] args) throws InterruptedException {
        DelayExecuteBuffer<String> delayExecuteBuffer = 
                new DelayExecuteBuffer<>("test", 1000, 10000, 500, 1, new IBatchExecutor<String>() {
                    @Override
                    public void execute(List<String> records) {
                        System.out.println(records);
                    }
                });
        delayExecuteBuffer.start();
        while(true){
            delayExecuteBuffer.add(UUID.newUUID());
            TimeUnit.MILLISECONDS.sleep(10);
        }

    }
}
