package org.awack.config;

import org.apache.tomcat.util.threads.VirtualThreadExecutor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer, DisposableBean {
    private ExecutorService executorService;
    private VirtualThreadExecutor virtualThreadExecutor;

    @Override
    public void destroy() throws Exception {
        if (executorService != null) {
            executorService.shutdown();
        }
        if (virtualThreadExecutor != null) {
            virtualThreadExecutor.shutdown();
        }
    }

    @Override
    public Executor getAsyncExecutor() {
        executorService = Executors.newVirtualThreadPerTaskExecutor();
        virtualThreadExecutor = new VirtualThreadExecutor(":awack-VT:");

        return task -> executorService.execute(
                () -> virtualThreadExecutor.execute(task));
    }

}
