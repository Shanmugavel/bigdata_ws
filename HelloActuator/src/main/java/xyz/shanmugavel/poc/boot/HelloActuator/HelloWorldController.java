/**
 * 
 */
package xyz.shanmugavel.poc.boot.HelloActuator;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@RestController
@Slf4j
public class HelloWorldController {
	
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public @ResponseBody Greeting sayHello(@RequestParam(name="user", defaultValue="Stranger!", required=false) String name) {
		Greeting greeting = new Greeting( counter.incrementAndGet(), name);
		log.info("Greetings {}", greeting);
		return greeting;
	}
}
