package com.egem.ram.service;

import com.egem.ram.dto.ThreadResponseDto;
import com.egem.ram.serviceapi.ThreadCreatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class ThreadCreaterServiceImpl implements ThreadCreatorService {

    @Override
    public List<ThreadResponseDto> getThreadResponse(List<String> charNames) throws InterruptedException {
        List<ThreadResponseDto> threadResponses = new ArrayList<>();
        if (charNames.size() > 0) {
            int processors = Runtime.getRuntime().availableProcessors();
            if (processors < 25) processors = 25;

            int size = charNames.size() / processors;
            int lastSize = size + (charNames.size() - processors * size);
            CountDownLatch latch = new CountDownLatch(processors);

            for (int i = 0; i < processors; i++) {
                int start;
                int end;
                if (i == processors - 1) {
                    start = i * size;
                    end = start + lastSize;
                } else {
                    start = i * size;
                    end = start + size;
                }
                List<String> subCharNames = charNames.subList(start, end);
                threadResponses.add(createThread(latch, subCharNames, "thread" + i));
            }
            latch.await();
        }
        return threadResponses;
    }

    private ThreadResponseDto createThread(CountDownLatch latch, List<String> charNames, String threadName) {
        ThreadResponseDto threadResponseDto = new ThreadResponseDto();
        ThreadLetterCounter threadSubsCustomerVehicles = new ThreadLetterCounter();
        threadSubsCustomerVehicles.setGeneralObjects(charNames, threadName, latch, threadResponseDto);
        Thread thread = new Thread(threadSubsCustomerVehicles);
        thread.start();
        return threadResponseDto;
    }
}
