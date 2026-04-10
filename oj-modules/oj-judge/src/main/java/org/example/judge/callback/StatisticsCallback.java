package org.example.judge.callback;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Statistics;
import lombok.Getter;
import lombok.Setter;

import java.io.Closeable;
import java.io.IOException;

@Getter
@Setter
public class StatisticsCallback implements ResultCallback<Statistics> {

    private Long maxMemory = 0L;

    @Override
    public void onStart(Closeable closeable) {

    }

    @Override
    public void onNext(Statistics statistics) {
        Long usage = statistics.getMemoryStats().getMaxUsage();//程序运行到某个时间点上的内存使用的最大值
        if (usage != null) {
            maxMemory = Math.max(usage, maxMemory);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void close() throws IOException {

    }
}
