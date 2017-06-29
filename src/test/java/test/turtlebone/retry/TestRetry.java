package test.turtlebone.retry;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

import com.turtlebone.core.config.RetryConfig;

public class TestRetry {
	@Autowired
	private RetryTemplate retryTemplate;
	
	@Before
	public void before() {
		RetryConfig config = new RetryConfig();
		retryTemplate = config.getRetryTemplate();
	}
	@Test
	public void test() throws Throwable {
		retryTemplate.execute(new RetryCallback<Void, Throwable>() {

			@Override
			public Void doWithRetry(RetryContext arg0) throws Throwable {
				int x = (int)Math.round(Math.random()*100) % 3;
				System.out.println("x = " + x);
				if (x == 0) {
					throw new IllegalArgumentException("HEHE, unlucky");
				}
				return null;
			}
			
		}, 
				new RecoveryCallback<Void>(){

					@Override
					public Void recover(RetryContext arg0) throws Exception {
						System.out.println("Retry!");
						return null;
					}
			
		});
	}
}
