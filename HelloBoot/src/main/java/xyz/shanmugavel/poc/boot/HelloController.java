/**
 * 
 */
package xyz.shanmugavel.poc.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String index() {
		return "Greetings from Shan!";
	}
}
