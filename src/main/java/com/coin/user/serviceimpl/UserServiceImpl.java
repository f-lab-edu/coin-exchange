package com.coin.user.serviceimpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import com.coin.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	int maxRetry = 5;
	
	@Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
	public int addUser(String userId, String password, String address, String name, String handPhone) {
        int rtn = 0;
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
        	System.out.println("thread1 시작");
        	
        	int userNumber = 0;
			try {
				userNumber = userDao.addUser(userId, password, address, name, handPhone);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("thread1 종료");
            return userNumber;
        });
        
        rtn= future.join();

        future.thenAccept(result -> {
        	try {
        		System.out.println("thread2 시작");
	        	System.out.println("thread1 결과 회원번호는?" + result);
	        	if(result != 0) {
					System.out.println("회원가입 성공");
					boolean retry = userDao.addAccount(result);
					if(!retry) {
						throw new Exception();
					}
	        	}
	        	System.out.println("thread2 종료");
        	} catch (Exception e) {
        		throw new CompletionException(e);
			}
        }).exceptionally(ex -> {
        	for (int i = 0; i < maxRetry; i++) {
                try {
                	boolean retry = userDao.addAccount(future.join());
					if(!retry) {
						throw new Exception();
					}
                } catch (Exception e) {
                    try {
                        Thread.sleep(1000); // 1초 후 재시도
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            return null;
        });
        
		return rtn;
	}
	
	@Scheduled(cron="0 04 17 * * ?")
	public void executeBatch() {
        System.out.println("Batch job is running1...");
//        AccountBatch ab = new AccountBatch();
        
    }
}

