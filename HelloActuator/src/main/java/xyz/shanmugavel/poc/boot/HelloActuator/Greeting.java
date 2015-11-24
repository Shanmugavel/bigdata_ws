/**
 * 
 */
package xyz.shanmugavel.poc.boot.HelloActuator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@AllArgsConstructor
@ToString
public class Greeting {

	@Getter
	private Long id;
	@Getter
	private String content;
}
